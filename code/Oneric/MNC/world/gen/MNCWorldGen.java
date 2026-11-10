package Oneric.MNC.world.gen;

import Oneric.MNC.common.MNCBlocks;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/** Adds the mod's ores and vegetation to the Nether and the overworld. */
public class MNCWorldGen implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int x = chunkX * 16;
        int z = chunkZ * 16;

        if (world.provider.dimensionId == -1)
        {
            this.generateNether(world, random, x, z);
        }
        else if (world.provider.dimensionId == 0)
        {
            this.generateOverworld(world, random, x, z);
        }
    }

    private void generateNether(World world, Random random, int x, int z)
    {
        for (int i = 0; i < 10; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(108) + 10;
            int bz = z + random.nextInt(16);
            (new WorldGenMinable(MNCBlocks.NetherGold, 8, Blocks.netherrack)).generate(world, random, bx, by, bz);
        }

        for (int i = 0; i < 5; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(108) + 10;
            int bz = z + random.nextInt(16);
            (new WorldGenMinable(MNCBlocks.orePlumbum, 6, Blocks.netherrack)).generate(world, random, bx, by, bz);
        }

        if (random.nextInt(24) == 0)
        {
            int bx = x + random.nextInt(16);
            int by = 32 + random.nextInt(64);
            int bz = z + random.nextInt(16);
            (new WorldGenFireTree(true)).generate(world, random, bx, by, bz);
        }
    }

    private void generateOverworld(World world, Random random, int x, int z)
    {
        for (int i = 0; i < 6; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(64) + 5;
            int bz = z + random.nextInt(16);
            (new WorldGenMinable(MNCBlocks.orePlumbum, 7, Blocks.stone)).generate(world, random, bx, by, bz);
        }
    }
}