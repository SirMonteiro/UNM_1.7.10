package Oneric.MNC.block;

import java.util.Random;

import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.mod_netherbr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDeadGrass extends MNCBlock {

	@SideOnly(Side.CLIENT)
	private IIcon iconGrassTop;

	public BlockDeadGrass() {
		super(Material.grass);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockDeadGrass(int par1) {
		this();
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote) {
			if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2) {
				par1World.setBlock(par2, par3, par4, MNCBlocks.deadDirt, 0, 2);
			} else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
				for (int var6 = 0; var6 < 4; ++var6) {
					int var7 = par2 + par5Random.nextInt(3) - 1;
					int var8 = par3 + par5Random.nextInt(5) - 3;
					int var9 = par4 + par5Random.nextInt(3) - 1;

					if (par1World.getBlock(var7, var8, var9) == MNCBlocks.deadDirt && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && par1World.getBlockLightOpacity(var7, var8 + 1, var9) <= 2) {
						par1World.setBlock(var7, var8, var9, MNCBlocks.deadGrass, 0, 3);
					}
				}
			}
		}
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(MNCBlocks.deadDirt);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(mod_netherbr.getId() + ":mnc_deadGrassSide");
		this.iconGrassTop = par1IconRegister.registerIcon(mod_netherbr.getId() + ":mnc_deadGrassTop");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		if (par5 == 1) {
			return this.iconGrassTop;
		} else if (par5 == 0) {
			return MNCBlocks.deadDirt.getIcon(par5, 0);
		} else {
			return this.blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par1 == 1) {
			return this.iconGrassTop;
		} else if (par1 == 0) {
			return MNCBlocks.deadDirt.getIcon(par1, 0);
		} else {
			return this.blockIcon;
		}
	}
}
