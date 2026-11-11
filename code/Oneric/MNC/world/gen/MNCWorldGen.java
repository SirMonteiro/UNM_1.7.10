package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.biomes.biome_Blackstone;
import Oneric.MNC.biomes.biome_HellPilz;
import Oneric.MNC.biomes.biome_IceHell;
import Oneric.MNC.common.MNCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.chunk.IChunkProvider;

public class MNCWorldGen implements IWorldGenerator
{
    /**
     * 1.6.4 -> 1.7.10 crash guard.
     *
     * In 1.7.10 World.getBlock(x,y,z) force-loads (and therefore POPULATES) the
     * containing chunk.  Our generators deliberately scatter probes with
     * +/-8 block offsets, so a probe that lands in a not-yet-generated
     * neighbouring chunk triggers that chunk's population, which calls
     * GameRegistry.generateWorld -> MNCWorldGen.generate -> another probe ->
     * another population ... until the stack overflows (the exact loop in the
     * crash report: WorldGenLache -> World.func_147439_a -> ChunkProviderServer
     * .func_73153_a -> GameRegistry.generateWorld -> MNCWorldGen.generate).
     *
     * In 1.6.4 world.a(x,y,z) (getBlockId) returned 0 for an unloaded chunk
     * without generating it, so the original code was safe by accident.
     * A per-thread re-entrancy flag reproduces that safety net: while we are
     * already generating, any nested generate() call is a no-op, so getBlock()
     * simply returns and the outer generation continues.
     */
    private static final ThreadLocal<Boolean> GENERATING = new ThreadLocal<Boolean>()
    {
        @Override
        protected Boolean initialValue()
        {
            return Boolean.FALSE;
        }
    };

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (GENERATING.get())
        {
            return;
        }

        GENERATING.set(Boolean.TRUE);
        try
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
        finally
        {
            GENERATING.set(Boolean.FALSE);
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

    }

    private int getNetherHeight()
    {
        return 128;
    }
}
