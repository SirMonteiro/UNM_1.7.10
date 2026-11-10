package Oneric.MNC.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSlab extends Block {

	private boolean diamondrequires;

	public BlockSlab(boolean j) {
		super(Material.rock);
		this.setBlockBounds(0, 0, 0, 1, 0.5F, 1);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.0F);
		this.diamondrequires = j;
	}

	public BlockSlab(int par1, boolean j) {
		this(j);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(this))
		{
			if (this == MNCBlocks.GSlab) { world.setBlock(x, y, z, Blocks.glowstone, 0, 2); }
			if (this == MNCBlocks.OSlab) { world.setBlock(x, y, z, Blocks.obsidian, 0, 2); }
			if (this == MNCBlocks.NWSlab && MNCBlocks.Netherplanks != null) { world.setBlock(x, y, z, MNCBlocks.Netherplanks, 0, 2); }

			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, this.stepSound.func_150496_b(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);

			if (!player.capabilities.isCreativeMode)
			{
				player.inventory.getCurrentItem().stackSize--;
			}
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		IIcon tmp = Blocks.glowstone.getIcon(par1, par2);

		if (this == MNCBlocks.OSlab)
		{
			tmp = Blocks.obsidian.getIcon(par1, par2);
		}

		if (this == MNCBlocks.NWSlab && MNCBlocks.Netherplanks != null)
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

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister)
	{
	}
}
