package Oneric.MNC.biomes;

import net.minecraft.init.Blocks;

import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.entity.EntityLostSoul;
import Oneric.MNC.entity.EntitySlaughter;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class biome_Blackstone extends BiomeGenBase
{

	public biome_Blackstone(int par1)
	{
		super(par1);

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		// LostSoul and Slaughter are hostile mobs. They used to be added to the
		// passive "creature" list (as in 1.6.4), which no longer works in 1.7.10:
		// passive entries only spawn during chunk generation and then never
		// respawn, so the blackstone biome ended up feeling empty. Put them in
		// the monster list where they belong.
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityLostSoul.class, 40, 3, 6));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlaughter.class, 20, 1, 1));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGhast.class, 50, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 5, 4, 4));

		this.topBlock = MNCBlocks.Blackstone;
		this.fillerBlock = Blocks.flowing_lava;

		this.theBiomeDecorator.generateLakes = false;

		this.temperature = 1.0F;
		this.rainfall = 0.0F;

		this.setBiomeName("Blackstone");
		this.setColor(0x00000);
		this.waterColorMultiplier = 0x00000;

		this.theBiomeDecorator.gravelGen = new WorldGenMinable(Blocks.gold_ore, 3);
	}

}