package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMudLake extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (rand.nextInt(5) == 0 && !world.isAirBlock(x, y, z) && !world.isAirBlock(x + 6, y, z)
                && !world.isAirBlock(x, y, z + 6) && !world.isAirBlock(x + 6, y, z + 6))
        {
            int start = rand.nextInt(3) + 1;
            for (int one = 0; one <= start; ++one)
            {
                world.setBlock(x + one, y, z, MNCBlocks.Mud, 0, 2);
            }

            int zweitens = start + rand.nextInt(2) + 2;
            for (int i = 0; i <= zweitens; ++i)
            {
                world.setBlock(x + i, y, z + 1, MNCBlocks.Mud, 0, 2);
            }

            int drittens = zweitens + 3 + rand.nextInt(2);
            for (int i = 0; i <= drittens; ++i)
            {
                world.setBlock(x + i, y, z + 2, MNCBlocks.Mud, 0, 2);
            }

            int z2 = z;
            if (rand.nextInt(3) == 0)
            {
                int viertens = drittens + 1 + rand.nextInt(3);
                for (int i = 0; i <= viertens; ++i)
                {
                    world.setBlock(x + i, y, z + 3, MNCBlocks.Mud, 0, 2);
                }

                int fuenftens = viertens + 1 + rand.nextInt(2);
                for (int i = 0; i <= fuenftens; ++i)
                {
                    world.setBlock(x + i, y, z + 4, MNCBlocks.Mud, 0, 2);
                }

                int sechstens = (fuenftens - 1) - rand.nextInt(3);
                for (int i = 0; i <= sechstens; ++i)
                {
                    world.setBlock(x + i, y, z + 5, MNCBlocks.Mud, 0, 2);
                }
                z2 = z + 6;
            }

            int letztens = (drittens - 2) - rand.nextInt(3);
            for (int i = 0; i <= letztens; ++i)
            {
                world.setBlock(x + i, y, z2, MNCBlocks.Mud, 0, 2);
            }
            return true;
        }
        return true;
    }
}
