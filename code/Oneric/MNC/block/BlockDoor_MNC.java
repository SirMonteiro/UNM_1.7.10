package Oneric.MNC.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCItems;
import Oneric.MNC.common.mod_netherbr;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoor_MNC extends BlockDoor {

	private static final String[] field_94467_a = new String[] {"mnc_GDoorBottom", "mnc_GDoorTop", "mnc_NDoorBottom", "mnc_NDoorTop", "mnc_NWDoorBottom", "mnc_NWDoorTop"};
    private final int field_94465_b;
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

	public BlockDoor_MNC(Material material) {
		super(material);

		if (material == Material.rock)
		{
			field_94465_b = 2;
		}
		else if (material == Material.glass)
		{
			field_94465_b = 0;
		}
		else
		{
			field_94465_b = 4;
		}
	}

	public BlockDoor_MNC(int par1, Material material) {
		this(material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return this.iconArray[this.field_94465_b];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par5 != 1 && par5 != 0)
		{
			int i1 = this.func_150012_g(par1IBlockAccess, par2, par3, par4);
			int j1 = i1 & 3;
			boolean flag = (i1 & 4) != 0;
			boolean flag1 = false;
			boolean flag2 = (i1 & 8) != 0;

			if (flag)
			{
				if (j1 == 0 && par5 == 2) flag1 = !flag1;
				else if (j1 == 1 && par5 == 5) flag1 = !flag1;
				else if (j1 == 2 && par5 == 3) flag1 = !flag1;
				else if (j1 == 3 && par5 == 4) flag1 = !flag1;
			}
			else
			{
				if (j1 == 0 && par5 == 5) flag1 = !flag1;
				else if (j1 == 1 && par5 == 3) flag1 = !flag1;
				else if (j1 == 2 && par5 == 4) flag1 = !flag1;
				else if (j1 == 3 && par5 == 2) flag1 = !flag1;

				if ((i1 & 16) != 0) flag1 = !flag1;
			}

			return this.iconArray[this.field_94465_b + (flag1 ? field_94467_a.length : 0) + (flag2 ? 1 : 0)];
		}
		else
		{
			return this.iconArray[this.field_94465_b];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.iconArray = new IIcon[field_94467_a.length * 2];

		for (int i = 0; i < field_94467_a.length; ++i)
		{
			this.iconArray[i] = par1IconRegister.registerIcon(mod_netherbr.getId() + ":" + field_94467_a[i]);
			this.iconArray[i + field_94467_a.length] = new IconFlipped(this.iconArray[i], true, false);
		}
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
			return MNCItems.GDoorItem;
		}
		else if (this.getMaterial() == Material.rock)
		{
			return MNCItems.NDoorItem;
		}
		else
		{
			return MNCItems.NWDoorItem;
		}
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		if (this.getMaterial() == Material.glass)
		{
			return MNCItems.GDoorItem;
		}
		else if (this.getMaterial() == Material.rock)
		{
			return MNCItems.NDoorItem;
		}
		else
		{
			return MNCItems.NWDoorItem;
		}
	}
}
