package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.mod_netherbr;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMaze extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int length = 1 + rand.nextInt(3);
        int width = 2 + rand.nextInt(3);

        if (rand.nextInt(100 + mod_netherbr.MazeRarity) == 0)
        {
            for (int w = 1; w <= width; ++w)
            {
                for (int l = 1; l <= length; ++l)
                {
                    int newX1 = x + (w * 9);
                    int newZ1 = z + (l * 9);
                    int newX2 = x - (w * 9);
                    int newZ2 = z - (l * 9);
                    new MazeAll(world, rand, newX1, y, newZ1);
                    new MazeAll(world, rand, newX2, y, newZ2);
                    new MazeAll(world, rand, newX2, y, newZ1);
                    new MazeAll(world, rand, newX1, y, newZ2);
                }
            }

            for (int l2 = 1; l2 <= length; ++l2)
            {
                new MazeAll(world, rand, x, y, z + (l2 * 9));
                new MazeAll(world, rand, x, y, z - (l2 * 9));
            }

            for (int w2 = 1; w2 <= width; ++w2)
            {
                new MazeAll(world, rand, x + (w2 * 9), y, z);
                new MazeAll(world, rand, x - (w2 * 9), y, z);
            }

            new MazeMiddle(world, rand, x, y, z);
            world.setBlock(x, y, z, Blocks.glowstone, 0, 2);
            return true;
        }

        return true;
    }
}
