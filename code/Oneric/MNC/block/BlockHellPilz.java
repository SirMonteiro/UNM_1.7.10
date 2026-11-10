package Oneric.MNC.block;

import java.util.Random;

import Oneric.MNC.common.MNCItems;
import Oneric.MNC.common.mod_netherbr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHellPilz extends BlockBush {

	@SideOnly(Side.CLIENT)
	private IIcon[] stageTextures;

	public BlockHellPilz() {
		super(Material.plants);
		setTickRandomly(true);
		float f = 0.5F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
	}

	public BlockHellPilz(int par1) {
		this();
	}

	@Override
	protected boolean canPlaceBlockOn(Block ground) {
		return ground == Blocks.netherrack;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z) == Blocks.netherrack;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z) == Blocks.netherrack;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);

		if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 2) {
			int i = par1World.getBlockMetadata(par2, par3, par4);

			if (i < 7) {
				float f = getGrowthRate(par1World, par2, par3, par4);

				if (par5Random.nextInt((int)(25F / f) + 1) == 0) {
					i++;
					par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 2);
				}
			}
		}
	}

	public void fertilize(World par1World, int par2, int par3, int par4) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if (meta < 6) {
			if (meta < 4) {
				if (par1World.rand.nextInt(meta + 1) == 0) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, meta + 1, 2);
					par1World.spawnParticle("largeexplode", par2 + 0.5D, par3 + 0.2D, par4 + 0.5D, 0.0D, 0.0D, 0.0D);
				} else {
					par1World.spawnParticle("mobSpell", par2 + 0.5D, par3 + 0.2D, par4 + 0.5D, 0.0D, 0.0D, 0.0D);
				}
			} else {
				if (par1World.rand.nextInt(meta * 2) == 0) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, meta + 1, 2);
					par1World.spawnParticle("largeexplode", par2 + 0.5D, par3 + 0.2D, par4 + 0.5D, 0.0D, 0.0D, 0.0D);
				} else {
					par1World.spawnParticle("mobSpell", par2 + 0.5D, par3 + 0.2D, par4 + 0.5D, 0.0D, 0.0D, 0.0D);
				}
			}
		} else {
			if (par1World.rand.nextInt(100) == 0) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 7, 2);
				par1World.spawnParticle("largeexplode", par2 + 0.5D, par3 + 0.2D, par4 + 0.5D, 0.0D, 0.0D, 0.0D);
			} else {
				par1World.spawnParticle("mobSpell", par2 + 0.5D, par3 + 0.2D, par4 + 0.5D, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	private float getGrowthRate(World par1World, int par2, int par3, int par4) {
		float f = 1.0F;
		Block i = par1World.getBlock(par2, par3, par4 - 1);
		Block j = par1World.getBlock(par2, par3, par4 + 1);
		Block k = par1World.getBlock(par2 - 1, par3, par4);
		Block l = par1World.getBlock(par2 + 1, par3, par4);
		Block i1 = par1World.getBlock(par2 - 1, par3, par4 - 1);
		Block j1 = par1World.getBlock(par2 + 1, par3, par4 - 1);
		Block k1 = par1World.getBlock(par2 + 1, par3, par4 + 1);
		Block l1 = par1World.getBlock(par2 - 1, par3, par4 + 1);
		boolean flag = k == this || l == this;
		boolean flag1 = i == this || j == this;
		boolean flag2 = i1 == this || j1 == this || k1 == this || l1 == this;

		for (int i2 = par2 - 1; i2 <= par2 + 1; i2++) {
			for (int j2 = par4 - 1; j2 <= par4 + 1; j2++) {
				Block k2 = par1World.getBlock(i2, par3 - 1, j2);
				float f1 = 0.0F;

				if (k2 == Blocks.netherrack) {
					f1 = 1.0F;

					if (par1World.getBlockMetadata(i2, par3 - 1, j2) > 0) {
						f1 = 3F;
					}
				}

				if (i2 != par2 || j2 != par4) {
					f1 /= 4F;
				}

				f += f1;
			}
		}

		if (flag2 || (flag && flag1)) {
			f /= 2.0F;
		}

		return f;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBlockTextureFromSideAndMetadata(int i, int j) {
		if (j >= 0 && j <= 2) {
			return this.stageTextures[0];
		}
		if (j >= 3 && j <= 6) {
			return this.stageTextures[1];
		}
		if (j == 7) {
			return this.stageTextures[2];
		}
		return this.stageTextures[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.stageTextures = new IIcon[3];
		for (int i = 0; i < this.stageTextures.length; i++) {
			int j = i + 1;
			this.stageTextures[i] = par1IconRegister.registerIcon(mod_netherbr.getId() + ":mnc_HellPilz-" + j);
		}
	}

	public IIcon getTexture(int i) {
		return this.stageTextures[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int i, int par2) {
		return this.getBlockTextureFromSideAndMetadata(i, par2);
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);

		if (par1World.isRemote) {
			return;
		}

		int i = 3 + par7;
		for (int j = 0; j < i; j++) {
			if (par1World.rand.nextInt(15) <= par5) {
				float f = 0.7F;
				float f1 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5F;
				float f2 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5F;
				float f3 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5F;
				EntityItem entityitem = new EntityItem(par1World, (float)par2 + f1, (float)par3 + f2, (float)par4 + f3, new ItemStack(MNCItems.HellPilz_Samen));
				entityitem.delayBeforeCanPickup = 10;
				par1World.spawnEntityInWorld(entityitem);
			}
		}
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		if (par1 == 7) {
			return MNCItems.HellPilz;
		} else {
			return Items.gunpowder;
		}
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1 + par1Random.nextInt(2);
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4) {
		return MNCItems.HellPilz_Samen;
	}
}
