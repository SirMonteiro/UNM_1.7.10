package Oneric.MNC.block;

import Oneric.MNC.common.MNCItems;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockOrePlumbum extends MNCBlock {

	public BlockOrePlumbum(Material par2Material) {
		super(par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockOrePlumbum(int par1, Material par2Material) {
		this(par2Material);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int par2, int par3, int par4, int par5) {
		if (!world.isRemote) {
			EntityXPOrb var6 = new EntityXPOrb(world);
			var6.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(var6);
		}
		super.onBlockDestroyedByPlayer(world, par2, par3, par4, par5);
	}

	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
		par6EntityPlayer.addExperience(1);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
		if (entityplayer.getCurrentEquippedItem() != null) {
			Item eq = entityplayer.getCurrentEquippedItem().getItem();
			if (eq != Items.wooden_pickaxe && eq != Items.stone_pickaxe && eq != Items.golden_pickaxe && eq != MNCItems.GlowPickaxe) {
				super.harvestBlock(world, entityplayer, i, j, k, l);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int par2, int par3, int par4, Entity entity) {
		if (entity instanceof EntityLiving) {
			if (((EntityLiving) entity).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD) {
				if (world.rand.nextInt(50) == 0) {
					((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 100));
				}
			}
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox((float)par2 + f, par3, (float)par4 + f, (float)(par2 + 1) - f, (float)(par3 + 1) - f, (float)(par4 + 1) - f);
	}
}
