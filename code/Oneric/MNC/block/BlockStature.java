package Oneric.MNC.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStature extends BlockContainer {

	private Class StatureEntityClass;

	public BlockStature(Class tClass) {
		super(Material.rock);
		StatureEntityClass = tClass;
		this.setBlockBounds(0, 0, 0, 1, 2, 1);
		this.setCreativeTab(null);
	}

	public BlockStature(int i, Class tClass) {
		this(tClass);
	}

	public TileEntity getBlockEntity() {
		try {
			return (TileEntity) StatureEntityClass.newInstance();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return getBlockEntity();
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, l, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = Blocks.stone.getIcon(0, 0);
	}
}
