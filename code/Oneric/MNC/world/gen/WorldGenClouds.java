package Oneric.MNC.world.gen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/** Smog "clouds" floating in the Fallen Sky dimension. */
public class WorldGenClouds extends WorldGenerator
{
    private final Block block;
    private final int count;
    private final boolean huge;

    public WorldGenClouds(Block block, int count, boolean huge)
    {
        this.block = block;
        this.count = count;
        this.huge = huge;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int radius = this.huge ? 4 + random.nextInt(4) : 2 + random.nextInt(2);
        int height = this.huge ? 2 + random.nextInt(2) : 1;

        for (int i = 0; i < this.count; ++i)
        {
            int bx = x + random.nextInt(radius * 2 + 1) - radius;
            int by = y + random.nextInt(height + 1);
            int bz = z + random.nextInt(radius * 2 + 1) - radius;

            if (world.isAirBlock(bx, by, bz))
            {
                world.setBlock(bx, by, bz, this.block, 0, 2);
            }
        }

        return true;
    }
}