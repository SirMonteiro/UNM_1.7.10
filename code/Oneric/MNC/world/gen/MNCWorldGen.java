package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.biomes.biome_Blackstone;
import Oneric.MNC.biomes.biome_HellPilz;
import Oneric.MNC.biomes.biome_IceHell;
import Oneric.MNC.common.MNCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class MNCWorldGen implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int x = chunkX * 16;
        int z = chunkZ * 16;

        switch (world.provider.dimensionId)
        {
            case -1:
                this.generateNether(world, random, x, z);
                break;
            case 0:
                this.generateSurface(world, random, x, z);
                break;
            default:
                break;
        }
    }

    private void generateSurface(World world, Random random, int x, int z)
    {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        if ((biome instanceof biome_HellPilz) || (biome instanceof biome_Blackstone))
        {
            for (int i = 0; i < 50; ++i)
            {
                int bx = x + random.nextInt(16);
                int by = random.nextInt(world.getHeight());
                int bz = z + random.nextInt(16);
                new WorldGenPilz().generate(world, random, bx, by, bz);
            }
        }

        if ((biome instanceof BiomeGenHell) || (biome instanceof biome_IceHell))
        {
            for (int i = 0; i < 6; ++i)
            {
                int bx = x + random.nextInt(16);
                int by = random.nextInt(world.getHeight());
                int bz = z + random.nextInt(16);
                new WorldGenMudLake().generate(world, random, bx, by, bz);
            }
        }

        for (int i = 0; i < 6; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(64) + 5;
            int bz = z + random.nextInt(16);
            new WorldGenMinable(MNCBlocks.orePlumbum, 7, Blocks.stone).generate(world, random, bx, by, bz);
        }
    }

    private void generateNether(World world, Random random, int x, int z)
    {
        for (int i = 0; i < 20; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(this.getNetherHeight());
            int bz = z + random.nextInt(16);
            new WorldGenNether(MNCBlocks.NetherGold, 10).generate(world, random, bx, by, bz);
        }

        for (int i = 0; i < 5; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(108) + 10;
            int bz = z + random.nextInt(16);
            new WorldGenMinable(MNCBlocks.orePlumbum, 6, Blocks.netherrack).generate(world, random, bx, by, bz);
        }

        for (int i = 0; i < 2; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(this.getNetherHeight());
            int bz = z + random.nextInt(16);
            new WorldGenDungeon_Nether().generate(world, random, bx, by, bz);
        }

        for (int i = 0; i < 10; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(this.getNetherHeight());
            int bz = z + random.nextInt(16);
            new WorldGenPilz().generate(world, random, bx, by, bz);
        }

        for (int i = 0; i < 8; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(this.getNetherHeight());
            int bz = z + random.nextInt(16);
            new WorldGenLache().generate(world, random, bx, by, bz);
        }

        for (int i = 0; i < 1; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(this.getNetherHeight());
            int bz = z + random.nextInt(16);
            new WorldGenMaze().generate(world, random, bx, by, bz);
        }

        for (int i = 0; i < 8; ++i)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(this.getNetherHeight());
            int bz = z + random.nextInt(16);
            new WorldGenFireTree(true).generate(world, random, bx, by, bz);
        }

        if (random.nextInt(60) == 0)
        {
            int bx = x + random.nextInt(16);
            int by = random.nextInt(64) + 20;
            int bz = z + random.nextInt(16);
            new DungeonGenDaemon().generate(world, random, bx, by, bz);
        }
    }

    private int getNetherHeight()
    {
        return 128;
    }
}
