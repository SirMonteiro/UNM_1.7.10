package Oneric.MNC.world.gen;

import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/** A dead, leafless tree for the Fallen Sky. */
public class WorldGenDeadTree extends WorldGenerator
{
    private final boolean alwaysGrow;

    public WorldGenDeadTree(boolean alwaysGrow)
    {
        this.alwaysGrow = alwaysGrow;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        Block soil = world.getBlock(x, y - 1, z);

        if (!this.alwaysGrow && soil != MNCBlocks.deadDirt && soil != MNCBlocks.deadGrass)
        {
            return false;
        }

        if (!world.isAirBlock(x, y, z) && world.getBlock(x, y, z) != MNCBlocks.FireSapling)
        {
            return false;
        }

        int height = 4 + random.nextInt(5);

        for (int i = 0; i < height; ++i)
        {
            world.setBlock(x, y + i, z, MNCBlocks.FireLog, 0, 2);

            if (i > 2 && random.nextInt(4) == 0)
            {
                int dir = random.nextInt(4);
                int len = 1 + random.nextInt(2);

                for (int j = 1; j <= len; ++j)
                {
                    int bx = x + (dir == 0 ? j : dir == 1 ? -j : 0);
                    int bz = z + (dir == 2 ? j : dir == 3 ? -j : 0);

                    if (world.isAirBlock(bx, y + i, bz))
                    {
                        world.setBlock(bx, y + i, bz, MNCBlocks.FireLog, 0, 2);
                    }
                }
            }
        }

        return true;
    }
}