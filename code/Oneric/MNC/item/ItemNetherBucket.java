package Oneric.MNC.item;

import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ItemNetherBucket extends MNCItem
{
    private final Block fluidBlock;

    public ItemNetherBucket(Block fluidBlock)
    {
        super();
        this.fluidBlock = fluidBlock;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    public ItemNetherBucket(int id, int isFull)
    {
        this(isFull == 0 ? null : MNCBlocks.SchleimFliess);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        boolean isEmpty = (this.fluidBlock == null || this.fluidBlock == Blocks.air);
        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, isEmpty);

        if (mop == null)
        {
            return stack;
        }

        FillBucketEvent event = new FillBucketEvent(player, stack, world, mop);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return stack;
        }

        if (event.getResult() == Event.Result.ALLOW)
        {
            if (player.capabilities.isCreativeMode)
            {
                return stack;
            }
            if (--stack.stackSize <= 0)
            {
                return event.result;
            }
            if (!player.inventory.addItemStackToInventory(event.result))
            {
                player.dropPlayerItemWithRandomChoice(event.result, false);
            }
            return stack;
        }

        if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            int x = mop.blockX;
            int y = mop.blockY;
            int z = mop.blockZ;

            if (!world.canMineBlock(player, x, y, z))
            {
                return stack;
            }

            if (isEmpty)
            {
                if (!player.canPlayerEdit(x, y, z, mop.sideHit, stack))
                {
                    return stack;
                }

                Material mat = world.getBlock(x, y, z).getMaterial();
                int meta = world.getBlockMetadata(x, y, z);
                Block block = world.getBlock(x, y, z);

                if ((mat == Material.water && meta == 0)
                        || ((block == MNCBlocks.SchleimStill || block == MNCBlocks.SchleimFliess) && meta == 0))
                {
                    world.setBlockToAir(x, y, z);

                    if (player.capabilities.isCreativeMode)
                    {
                        return stack;
                    }

                    ItemStack filled = new ItemStack(MNCItems.NBucketFull);
                    if (--stack.stackSize <= 0)
                    {
                        return filled;
                    }
                    if (!player.inventory.addItemStackToInventory(filled))
                    {
                        player.dropPlayerItemWithRandomChoice(filled, false);
                    }
                    return stack;
                }
            }
            else
            {
                if (mop.sideHit == 0) --y;
                if (mop.sideHit == 1) ++y;
                if (mop.sideHit == 2) --z;
                if (mop.sideHit == 3) ++z;
                if (mop.sideHit == 4) --x;
                if (mop.sideHit == 5) ++x;

                if (!player.canPlayerEdit(x, y, z, mop.sideHit, stack))
                {
                    return stack;
                }

                if (this.tryPlaceContainedLiquid(world, x, y, z) && !player.capabilities.isCreativeMode)
                {
                    return new ItemStack(MNCItems.NBucketEmpty);
                }
            }
        }

        return stack;
    }

    public boolean tryPlaceContainedLiquid(World world, int x, int y, int z)
    {
        if (this.fluidBlock == null || this.fluidBlock == Blocks.air)
        {
            return false;
        }

        Material mat = world.getBlock(x, y, z).getMaterial();
        boolean notSolid = !mat.isSolid();

        if (!world.isAirBlock(x, y, z) && !notSolid)
        {
            return false;
        }

        Block placeBlock = this.fluidBlock != null ? this.fluidBlock : MNCBlocks.SchleimFliess;
        world.setBlock(x, y, z, placeBlock, 0, 3);
        world.scheduleBlockUpdate(x, y, z, placeBlock, placeBlock.tickRate(world));
        return true;
    }
}
