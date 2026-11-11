package Oneric.MNC.item;

import Oneric.MNC.common.MNCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class MNCItemSeeds extends MNCItem implements IPlantable
{
    private final Block cropBlock;
    private final Block soilBlock;

    public MNCItemSeeds(Block cropBlock, Block soilBlock)
    {
        super();
        this.cropBlock = cropBlock;
        this.soilBlock = soilBlock;
        this.maxStackSize = 64;
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public MNCItemSeeds(int id, int cropBlockId, int soilBlockId)
    {
        this(MNCBlocks.HellPilz_Block, Blocks.netherrack);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (side != 1)
        {
            return false;
        }

        if (!player.canPlayerEdit(x, y, z, side, stack) || !player.canPlayerEdit(x, y + 1, z, side, stack))
        {
            return false;
        }

        Block soil = world.getBlock(x, y, z);

        if ((soil == Blocks.netherrack || soil == MNCBlocks.Blackstone || (this.soilBlock != null && soil == this.soilBlock))
                && world.isAirBlock(x, y + 1, z))
        {
            Block place = this.cropBlock != null ? this.cropBlock : MNCBlocks.HellPilz_Block;
            world.setBlock(x, y + 1, z, place, 0, 3);
            --stack.stackSize;
            return true;
        }

        return false;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Nether;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return this.cropBlock != null ? this.cropBlock : MNCBlocks.HellPilz_Block;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }
}
