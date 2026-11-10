package Oneric.MNC.biomes;

import net.minecraft.init.Blocks;

import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.world.biome.BiomeGenBase;

public class biome_IceHell extends BiomeGenBase
{

	public biome_IceHell(int par1)
	{
		super(par1);

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		//this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(net.minecraft.src.EntityLostSoul.class, 50, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySilverfish.class, 20, 4, 4));
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySnowman.class, 100, 4, 4));

		this.topBlock = Blocks.snow;
		this.fillerBlock = Blocks.ice;

		this.setBiomeName("IceHell");
		this.setColor(0xFFFFFF);

		this.theBiomeDecorator.generateLakes = true;
		this.setEnableSnow();
		this.rainfall = 0.5F;
		this.temperature = 0.0F;

		// In 1.7.10 minHeight/maxHeight became rootHeight/heightVariation.
		this.rootHeight = 0.3F;
		this.heightVariation = 1.5F;
	}

}