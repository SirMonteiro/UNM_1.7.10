package Oneric.MNC.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWall_MNC extends BlockWall {

	boolean diamondrequires;

	public BlockWall_MNC(Block block, boolean i) {
		super(block);
		this.diamondrequires = i;
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockWall_MNC(int par1, Block block, boolean i) {
		this(block, i);
	}

	@Override
	public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		Block var5 = par1IBlockAccess.getBlock(par2, par3, par4);

		if (var5 != this && var5 != Blocks.fence_gate)
		{
			return var5 != null && var5.getMaterial().isOpaque() && var5.renderAsNormalBlock() && var5.getMaterial() != Material.gourd;
		}
		else
		{
			return true;
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

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		IIcon tmp = Blocks.glowstone.getIcon(par1, par2);

		if (this == MNCBlocks.OWall)
		{
			tmp = Blocks.obsidian.getIcon(par1, par2);
		}

		if (this == MNCBlocks.NWall)
		{
			tmp = Blocks.nether_brick.getIcon(par1, par2);
		}

		if (this == MNCBlocks.NWWall && MNCBlocks.Netherplanks != null)
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
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}
}
