package Oneric.MNC.world.gen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Smog "clouds" for the Fallen Sky dimension. Faithful 1.6.4 port: meandering
 * x/z with occasional y drift, filling small box blobs of the given block.
 * isFlat (the "huge" flag in the 1.6.4 callers) widens the blobs.
 */
public class WorldGenClouds extends WorldGenerator
{
    private final Block block;
    private final boolean isFlat;
    private final int numberOfBlocks;

    public WorldGenClouds(Block block, int number, boolean flag)
    {
        this.block = block;
        this.numberOfBlocks = number;
        this.isFlat = flag;
    }

    @Override
    public boolean generate(World world, Random rand, int i1, int j1, int k1)
    {
        int x = i1;
        int y = j1;
        int z = k1;
        int rand1 = rand.nextInt(3) - 1;
        int rand2 = rand.nextInt(3) - 1;

        for (int i = 0; i <= this.numberOfBlocks; ++i)
        {
            x += rand.nextInt(3) - 1 + rand1;

            if (rand.nextBoolean() && !this.isFlat || this.isFlat && rand.nextInt(15) == 0)
            {
                y += rand.nextInt(3) - 1 + rand2;
            }

            z += rand.nextInt(3) - 1 + rand2;

            int extentX = rand.nextInt(3) + 2 * (this.isFlat ? (rand.nextInt(5) == 0 ? 4 : 3) : 1);

            for (int posX = x; posX < x + extentX; ++posX)
            {
                int extentY = rand.nextInt(1) + 2;

                for (int posY = y; posY < y + extentY; ++posY)
                {
                    int extentZ = rand.nextInt(3) + 2 * (this.isFlat ? (rand.nextInt(5) == 0 ? 4 : 3) : 1);

                    for (int posZ = z; posZ < z + extentZ; ++posZ)
                    {
                        if (world.isAirBlock(posX, posY, posZ))
                        {
                            world.setBlock(posX, posY, posZ, this.block, 0, 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}
