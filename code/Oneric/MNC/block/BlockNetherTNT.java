package Oneric.MNC.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockNetherTNT extends Block {

	public BlockNetherTNT() {
		super(Material.ground);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public BlockNetherTNT(int par1) {
		this();
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block neighbourBlock) {
		if (neighbourBlock != null && neighbourBlock.canProvidePower() && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
		if (!par1World.isRemote) {
			if ((par5 & 1) == 1) {
				EntityTNTPrimed var6 = new EntityTNTPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), (EntityLivingBase)null);
				par1World.spawnEntityInWorld(var6);
				par1World.playSoundAtEntity(var6, "random.fuse", 1.0F, 1.0F);
			}
		}
	}

	@Override
	public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return Blocks.gravel.getIcon(par1IBlockAccess, par2, par3, par4, par5);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return Blocks.gravel.getIcon(par1, par2);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
	}
}
