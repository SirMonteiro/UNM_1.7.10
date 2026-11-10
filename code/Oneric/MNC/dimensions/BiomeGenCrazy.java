package Oneric.MNC.dimensions;

import java.util.Random;

import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.entity.EntityHarpye;
import Oneric.MNC.entity.EntityIceGhost;
import Oneric.MNC.entity.EntityLostSoul;
import Oneric.MNC.entity.EntitySlaughter;
import Oneric.MNC.world.gen.WorldGenDeadTree;
import Oneric.MNC.world.gen.WorldGenFireTree;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenCrazy extends BiomeGenBase {

	public static BiomeGenCrazy me;

	public BiomeGenCrazy() {
		super(230);
		rainfall = 0.0F;
	    this.spawnableMonsterList.clear();
	    this.spawnableCreatureList.clear();
	    this.spawnableWaterCreatureList.clear();
	    setBiomeName("Madness");
	    this.topBlock = MNCBlocks.deadGrass;
	    this.fillerBlock = MNCBlocks.deadDirt;
	    
	    this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHarpye.class, 16, 4, 8));
	    
	    this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityLostSoul.class, 11, 4, 6));
	    this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityIceGhost.class, 9, 1, 2));
	    this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlaughter.class, 1, 1, 1));
	    
	    me = this;
	}
	
	/**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if (random.nextInt(100) == 0)
        {
            return new WorldGenFireTree(true);
        }
        else
        {
        	return new WorldGenDeadTree(true);
        }
    }
    
    /**
    * takes temperature, returns color
    */
   public int getSkyColorByTemp(float f)
   {
       return 0xc0c0ff;
   }
}
