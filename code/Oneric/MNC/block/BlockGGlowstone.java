package Oneric.MNC.block;

import java.util.Random;

import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import Oneric.MNC.common.mod_netherbr;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockGGlowstone extends MNCBlock {

	public BlockGGlowstone(Material par2Material) {
		super(Material.rock);
		float f = 0.375F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		setTickRandomly(true);
		this.setCreativeTab(null);
	}

	public BlockGGlowstone(int par1, Material par2Material) {
		this(par2Material);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random par5Random)
	{
		if (world.isAirBlock(x, y + 1, z) && !(world.getBlock(x, y - 1, z) == this && world.getBlock(x, y - 2, z) == this))
		{
			world.setBlock(x, y + 1, z, this, 0, 2);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister registry)
	{
		this.blockIcon = registry.registerIcon(mod_netherbr.getId() + ":" + (this.getUnlocalizedName().substring(5)));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k)
	{
		Block l = world.getBlock(i, j - 1, k);

		if (l == this)
		{
			return true;
		}

		if (l != MNCBlocks.Blackstone)
		{
			return false;
		}

		if (world.getBlock(i - 1, j - 1, k).getMaterial() == Material.lava) return true;
		if (world.getBlock(i + 1, j - 1, k).getMaterial() == Material.lava) return true;
		if (world.getBlock(i, j - 1, k - 1).getMaterial() == Material.lava) return true;
		return world.getBlock(i, j - 1, k + 1).getMaterial() == Material.lava;
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block l)
	{
		checkBlockCoordValid(world, i, j, k);
	}

	protected final void checkBlockCoordValid(World world, int i, int j, int k)
	{
		if (!canBlockStay(world, i, j, k))
		{
			dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
			world.setBlock(i, j, k, Blocks.air, 0, 2);
		}
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k)
	{
		return canPlaceBlockAt(world, i, j, k);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return Items.glowstone_dust;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return par1Random.nextInt(4) + 1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		return MNCItems.GGlowstone_i;
	}
}
