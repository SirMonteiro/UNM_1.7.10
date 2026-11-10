package Oneric.MNC.biomes;

import net.minecraft.init.Blocks;

import Oneric.MNC.entity.EntityLostSoul;
import Oneric.MNC.entity.EntitySlaughter;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class biome_HellPilz extends BiomeGenBase
{

	public biome_HellPilz(int par1)
	{
		super(par1);

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityLostSoul.class, 40, 4, 4));
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySlaughter.class, 14, 1, 1));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGhast.class, 50, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 5, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGiantZombie.class, 30, 4, 4));

		this.topBlock = Blocks.netherrack;
		this.fillerBlock = Blocks.flowing_lava;

		this.setBiomeName("HellPilz");
		this.setColor(0x00000);
		this.waterColorMultiplier = 0x00000;

		this.temperature = 1.0F;
		this.rainfall = 0.0F;

		this.theBiomeDecorator.generateLakes = false;

		//this.theBiomeDecorator.dirtGen = new WorldGenMinable(MNCBlocks.NetherGold, 32);
		this.theBiomeDecorator.gravelGen = new WorldGenMinable(Blocks.gold_ore, 3);
	}

}