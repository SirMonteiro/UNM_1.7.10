package Oneric.MNC.block;

import Oneric.MNC.entity.EntityLostSoul;
import Oneric.MNC.entity.EntitySlaughter;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlackStone extends MNCBlock {

	public BlackStone(Material material) {
		super(material);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlackStone(int par1, Material par2Material) {
		this(par2Material);
	}

	@Override
	public boolean isNormalCube() {
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox((float)par2 + f, par3, (float)par4 + f, (float)(par2 + 1) - f, (float)(par3 + 1) - f, (float)(par4 + 1) - f);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
		// Fire/lava natives are not burned by the blackstone itself. Magma cubes
		// need this exemption too, otherwise every spawn is killed by the block
		// damage before it can survive in the Blackstone biome.
		if (entity instanceof EntitySlaughter || entity instanceof EntityLostSoul
				|| entity instanceof EntityPigZombie || entity instanceof EntityMagmaCube) {

		} else {
			entity.attackEntityFrom(DamageSource.fall, 1);
		}
	}
}
