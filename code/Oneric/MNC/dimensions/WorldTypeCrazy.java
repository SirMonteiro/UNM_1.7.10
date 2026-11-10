package Oneric.MNC.dimensions;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeCrazy extends WorldType {
	
	public WorldTypeCrazy() {
		super("CRAZY");
	}
	
	@Override
	public WorldChunkManager getChunkManager(World world) {
        return new WorldChunkManagerCrazy(0.0D);
	}
	
	@Override
	public IChunkProvider getChunkGenerator(World world, String flatOptions) {
		 return new ChunkProviderCrazy(world, world.getSeed());
	}
	
	@Override
	public boolean hasVoidParticles(boolean flag) {
        return false;
	}
	
	@Override
	public int getMinimumSpawnHeight(World world) {
		return 64;
    }

	@Override
	public double voidFadeMagnitude() {
        return 0.0D;
	}
}
