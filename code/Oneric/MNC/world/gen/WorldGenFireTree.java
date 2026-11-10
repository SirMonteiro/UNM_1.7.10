package Oneric.MNC.world.gen;

import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/** The huge burning trees found in the Nether and in the Fallen Sky. */
public class WorldGenFireTree extends WorldGenerator
{
    private final boolean alwaysGrow;

    public WorldGenFireTree(boolean alwaysGrow)
    {
        this.alwaysGrow = alwaysGrow;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        Block soil = world.getBlock(x, y - 1, z);

        if (!this.alwaysGrow && soil != MNCBlocks.deadDirt && soil != MNCBlocks.deadGrass && soil != net.minecraft.init.Blocks.netherrack)
        {
            return false;
        }

        if (!world.isAirBlock(x, y, z) && world.getBlock(x, y, z) != MNCBlocks.FireSapling)
        {
            return false;
        }

        int height = 5 + random.nextInt(4);

        for (int i = 0; i < height; ++i)
        {
            world.setBlock(x, y + i, z, MNCBlocks.FireLog, 0, 2);
        }

        int top = y + height;

        for (int dy = -2; dy <= 1; ++dy)
        {
            int radius = dy <= -1 ? 2 : 1;

            for (int dx = -radius; dx <= radius; ++dx)
            {
                for (int dz = -radius; dz <= radius; ++dz)
                {
                    if (Math.abs(dx) == radius && Math.abs(dz) == radius && random.nextInt(3) != 0)
                    {
                        continue;
                    }

                    Block block = world.getBlock(x + dx, top + dy, z + dz);

                    if (block.isAir(world, x + dx, top + dy, z + dz) || block.isLeaves(world, x + dx, top + dy, z + dz))
                    {
                        world.setBlock(x + dx, top + dy, z + dz, MNCBlocks.FireLeaf, 0, 2);
                    }
                }
            }
        }

        return true;
    }
}