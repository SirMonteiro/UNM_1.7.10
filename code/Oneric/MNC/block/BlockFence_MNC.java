package Oneric.MNC.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFence_MNC extends BlockFence
{
	boolean diamondrequires;

    public BlockFence_MNC(boolean j)
    {
        super("", Material.rock);
        diamondrequires = j;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public BlockFence_MNC(Material par3Material, boolean j)
    {
        super("", par3Material);
        diamondrequires = j;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public BlockFence_MNC(int par1, boolean j)
    {
        this(j);
    }

    public BlockFence_MNC(int par1, Material par3Material, boolean j)
    {
        this(par3Material, j);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        IIcon tmp = Blocks.glowstone.getIcon(par1, par2);

        if (this == MNCBlocks.OFence)
        {
            tmp = Blocks.obsidian.getIcon(par1, par2);
        }

        if (this == MNCBlocks.NFence_NEW)
        {
            tmp = Blocks.nether_brick.getIcon(par1, par2);
        }

        if (this == MNCBlocks.NWFence && MNCBlocks.Netherplanks != null)
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
    public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        Block i = par1IBlockAccess.getBlock(par2, par3, par4);

        if (i == this || i == Blocks.fence_gate || i == Blocks.fence)
        {
            return true;
        }

        if (i == MNCBlocks.GFence || i == MNCBlocks.GFenceGate)
        {
            return true;
        }

        if (i == Blocks.nether_brick_fence || i == MNCBlocks.NFenceGate)
        {
            return true;
        }

        if (i == MNCBlocks.OFenceGate || i == MNCBlocks.OFence)
        {
            return true;
        }

        if (i == MNCBlocks.NWFence || i == MNCBlocks.NWFenceGate)
        {
            return true;
        }

        if (i != null && i.getMaterial().isOpaque() && i.renderAsNormalBlock())
        {
            return i.getMaterial() != Material.gourd;
        }
        else
        {
            return false;
        }
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
