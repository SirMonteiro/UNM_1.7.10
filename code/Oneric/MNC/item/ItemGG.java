package Oneric.MNC.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Generic "places a block" item (Growing Gold / Growing Glowstone / Statue).
 * It behaves like an ItemBlock but has a flat item icon of its own.
 */
public class ItemGG extends Item
{
    private final Block block;

    public ItemGG(Block block)
    {
        super();
        this.block = block;
    }

    public Block getBlock()
    {
        return this.block;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        Block target = world.getBlock(x, y, z);

        if (target == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
        {
            side = 1;
        }
        else if (target != Blocks.vine && target != Blocks.tallgrass && target != Blocks.deadbush && !target.isReplaceable(world, x, y, z))
        {
            if (side == 0) --y;
            if (side == 1) ++y;
            if (side == 2) --z;
            if (side == 3) ++z;
            if (side == 4) --x;
            if (side == 5) ++x;
        }

        if (stack.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x, y, z, side, stack))
        {
            return false;
        }
        else if (y == 255 && this.block.getMaterial().isSolid())
        {
            return false;
        }
        else if (world.canPlaceEntityOnSide(this.block, x, y, z, false, side, (Entity)null, stack))
        {
            int meta = this.getMetadata(stack.getItemDamage());

            if (world.setBlock(x, y, z, this.block, meta, 3))
            {
                if (world.getBlock(x, y, z) == this.block)
                {
                    this.block.onBlockPlacedBy(world, x, y, z, player, stack);
                }
                --stack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
