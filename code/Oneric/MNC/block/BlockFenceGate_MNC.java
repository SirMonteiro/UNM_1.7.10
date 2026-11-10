package Oneric.MNC.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFenceGate_MNC extends BlockFenceGate
{
	boolean diamondrequires;

    public BlockFenceGate_MNC(boolean j)
    {
        super();
        diamondrequires = j;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public BlockFenceGate_MNC(int par1, boolean j)
    {
        this(j);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        IIcon tmp = Blocks.glowstone.getIcon(par1, par2);

        if (this == MNCBlocks.OFenceGate)
        {
            tmp = Blocks.obsidian.getIcon(par1, par2);
        }

        if (this == MNCBlocks.NFenceGate)
        {
            tmp = Blocks.nether_brick.getIcon(par1, par2);
        }

        if (this == MNCBlocks.NWFenceGate && MNCBlocks.Netherplanks != null)
        {
            tmp = MNCBlocks.Netherplanks.getIcon(par1, par2);
        }

        return tmp;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        return this.getIcon(side, world.getBlockMetadata(x, y, z));
    }

    @Override
    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
    {
        if (diamondrequires)
        {
            if (entityplayer.getCurrentEquippedItem() != null &&
               (entityplayer.getCurrentEquippedItem().getItem() == Items.diamond_pickaxe ||
                entityplayer.getCurrentEquippedItem().getItem() == MNCItems.NetherPickaxe))
            {
                super.harvestBlock(world, entityplayer, i, j, k, l);
            }
        }
        else
        {
            super.harvestBlock(world, entityplayer, i, j, k, l);
        }
    }
}
