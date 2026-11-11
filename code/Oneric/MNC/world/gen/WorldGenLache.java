package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Nether slime ("Lache") puddle generator.
 *
 * Faithful to the 1.6.4 original: the random offsets are computed but the
 * block check / placement is done at the in-chunk seed coordinate (x,y,z).
 * The 1.7.10 port had changed these to rx/ry/rz, which read blocks in
 * neighbouring, not-yet-generated chunks. In 1.7.10 World.getBlock() forces
 * that chunk to populate, which re-enters MNCWorldGen and recurses until the
 * StackOverflowError seen when entering the Nether. Keeping the in-chunk
 * coordinate (plus the MNCWorldGen re-entrancy guard) removes that trigger.
 */
public class WorldGenLache extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        for (int i = 0; i < 10; ++i)
        {
            // 1.6.4 computed these but evaluated the world at (x,y,z).
            int rx = (x + rand.nextInt(8)) - rand.nextInt(8);
            int ry = (y + rand.nextInt(4)) - rand.nextInt(4);
            int rz = (z + rand.nextInt(8)) - rand.nextInt(8);

            if (world.getBlock(x, y, z) == Blocks.netherrack &&
                    (world.isAirBlock(x + 1, y, z) || world.isAirBlock(x - 1, y, z)
                    || world.isAirBlock(x, y, z + 1) || world.isAirBlock(x, y, z - 1)))
            {
                int count = 1 + rand.nextInt(rand.nextInt(3) + 1);
                for (int c = 0; c < count; ++c)
                {
                    world.setBlock(x, y, z, MNCBlocks.SchleimFliess, 0, 2);
                }
            }
        }
        return true;
    }
}
