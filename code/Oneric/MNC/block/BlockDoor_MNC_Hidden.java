package Oneric.MNC.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoor_MNC_Hidden extends BlockDoor {

	public BlockDoor_MNC_Hidden(Material par2Material) {
		super(par2Material);
	}

	public BlockDoor_MNC_Hidden(int par1, Material par2Material) {
		this(par2Material);
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		if ((par1 & 8) != 0)
		{
			return null;
		}

		if (this.getMaterial() == Material.glass)
		{
			return MNCItems.GDoorHiddenItem;
		}
		else
		{
			return MNCItems.NDoorHiddenItem;
		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		IIcon tmp = Blocks.glowstone.getIcon(par1IBlockAccess, par2, par3, par4, par5);

		if (this == MNCBlocks.NDoorHidden)
		{
			tmp = Blocks.nether_brick.getIcon(par1IBlockAccess, par2, par3, par4, par5);
		}

		return tmp;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int meta)
	{
		IIcon tmp = Blocks.glowstone.getBlockTextureFromSide(par1);

		if (this == MNCBlocks.NDoorHidden)
		{
			tmp = Blocks.nether_brick.getBlockTextureFromSide(par1);
		}

		return tmp;
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		if (this.getMaterial() == Material.glass)
		{
			return MNCItems.GDoorHiddenItem;
		}
		else
		{
			return MNCItems.NDoorHiddenItem;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister)
	{
	}
}
