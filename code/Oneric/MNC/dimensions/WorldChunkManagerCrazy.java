package Oneric.MNC.dimensions;

import java.util.*;

import Oneric.MNC.common.MNCBiomes;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerCrazy extends WorldChunkManager
{
    private BiomeGenBase biomeGenerator;
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private BiomeCache biomeCache;

    public WorldChunkManagerCrazy(double d)
    {
        biomeGenerator = MNCBiomes.crazy;
        
        WorldType type = new WorldTypeCrazy();
        long seed = new Random().nextLong();
        
        GenLayer[] var4 = GenLayer.initializeAllBiomeGenerators(seed, type);
        var4 = getModdedBiomeGenerators(type, seed, var4);
        this.genBiomes = var4[0];
        this.biomeIndexLayer = var4[1];
        this.biomeCache = new BiomeCache(this);
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int i, int j)
    {
        return biomeGenerator;
    }

    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        int[] var7 = this.genBiomes.getInts(par2, par3, par4, par5);

        try
        {
            for (int var8 = 0; var8 < par4 * par5; ++var8)
            {
                par1ArrayOfBiomeGenBase[var8] = MNCBiomes.CrazyBiomeList[var7[var8]];
            }

            return par1ArrayOfBiomeGenBase;
        }
        catch (Throwable var11)
        {
            Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, biomeGenerator);
            return par1ArrayOfBiomeGenBase;
        }
    }

    @Override
    public float[] getRainfall(float af[], int i, int j, int k, int l)
    {
        if (af == null || af.length < k * l)
        {
            af = new float[k * l];
        }

        Arrays.fill(af, 0, k * l, 0.0F);
        return af;
    }

    @Override
    public ChunkPosition findBiomePosition(int i, int j, int k, List list, Random random)
    {
        if (list.contains(biomeGenerator))
        {
            return new ChunkPosition((i - k) + random.nextInt(k * 2 + 1), 0, (j - k) + random.nextInt(k * 2 + 1));
        }
        else
        {
            return null;
        }
    }

    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
        {
            BiomeGenBase[] var9 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(var9, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        else
        {
            int[] var7 = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

            for (int var8 = 0; var8 < par4 * par5; ++var8)
            {
                par1ArrayOfBiomeGenBase[var8] = MNCBiomes.CrazyBiomeList[var7[var8]];
            }

            return par1ArrayOfBiomeGenBase;
        }
    }

    @Override
    public boolean areBiomesViable(int i, int j, int k, List list)
    {
        return list.contains(biomeGenerator);
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase abiomegenbase[], int i, int j, int k, int l)
    {
        if (abiomegenbase == null || abiomegenbase.length < k * l)
        {
            abiomegenbase = new BiomeGenBase[k * l];
        }

        Arrays.fill(abiomegenbase, 0, k * l, biomeGenerator);
        return abiomegenbase;
    }
}
