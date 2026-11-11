package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLache extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        for (int i = 0; i < 10; ++i)
        {
            int rx = (x + rand.nextInt(8)) - rand.nextInt(8);
            int ry = (y + rand.nextInt(4)) - rand.nextInt(4);
            int rz = (z + rand.nextInt(8)) - rand.nextInt(8);

            if (world.getBlock(rx, ry, rz) == Blocks.netherrack &&
                    (world.isAirBlock(rx + 1, ry, rz) || world.isAirBlock(rx - 1, ry, rz)
                    || world.isAirBlock(rx, ry, rz + 1) || world.isAirBlock(rx, ry, rz - 1)))
            {
                int count = 1 + rand.nextInt(rand.nextInt(3) + 1);
                for (int c = 0; c < count; ++c)
                {
                    world.setBlock(rx, ry, rz, MNCBlocks.SchleimFliess, 0, 2);
                }
            }
        }
        return true;
    }
}
