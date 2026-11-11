package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPilz extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        Block soil = world.getBlock(x, y, z);
        boolean validGround = (soil == Blocks.netherrack || soil == MNCBlocks.Blackstone);

        if (validGround && world.isAirBlock(x, y + 1, z) && world.isAirBlock(x, y + 2, z)
                && world.isAirBlock(x, y + 3, z) && world.isAirBlock(x, y + 4, z)
                && world.isAirBlock(x, y + 5, z) && world.isAirBlock(x, y + 6, z)
                && world.isAirBlock(x, y + 7, z))
        {
            Block stem = MNCBlocks.Pilz2;
            Block cap = MNCBlocks.Pilz;

            world.setBlock(x, y + 0, z, stem, 0, 2);
            world.setBlock(x, y + 1, z, stem, 0, 2);
            world.setBlock(x, y + 2, z, stem, 0, 2);
            world.setBlock(x, y + 3, z, stem, 0, 2);
            world.setBlock(x, y + 4, z, stem, 0, 2);
            world.setBlock(x, y + 5, z, stem, 0, 2);
            world.setBlock(x, y + 6, z, stem, 0, 2);

            world.setBlock(x, y + 1, z + 1, stem, 0, 2);
            world.setBlock(x, y + 1, z - 1, stem, 0, 2);
            world.setBlock(x - 1, y + 1, z, stem, 0, 2);
            world.setBlock(x + 1, y + 1, z, stem, 0, 2);

            world.setBlock(x, y, z + 1, stem, 0, 2);
            world.setBlock(x, y, z - 1, stem, 0, 2);
            world.setBlock(x - 1, y, z, stem, 0, 2);
            world.setBlock(x + 1, y, z, stem, 0, 2);
            world.setBlock(x + 1, y, z - 1, stem, 0, 2);
            world.setBlock(x + 1, y, z + 1, stem, 0, 2);
            world.setBlock(x - 1, y, z - 1, stem, 0, 2);
            world.setBlock(x - 1, y, z + 1, stem, 0, 2);
            world.setBlock(x - 2, y, z, stem, 0, 2);
            world.setBlock(x, y, z + 2, stem, 0, 2);
            world.setBlock(x + 2, y, z, stem, 0, 2);
            world.setBlock(x, y, z - 2, stem, 0, 2);

            world.setBlock(x, y + 5, z + 1, cap, 0, 2);
            world.setBlock(x, y + 5, z - 1, cap, 0, 2);
            world.setBlock(x - 1, y + 5, z, cap, 0, 2);
            world.setBlock(x + 1, y + 5, z, cap, 0, 2);
            world.setBlock(x - 1, y + 5, z + 1, cap, 0, 2);
            world.setBlock(x - 1, y + 5, z - 1, cap, 0, 2);
            world.setBlock(x + 1, y + 5, z - 1, cap, 0, 2);
            world.setBlock(x + 1, y + 5, z + 1, cap, 0, 2);
            world.setBlock(x, y + 5, z + 2, cap, 0, 2);
            world.setBlock(x, y + 5, z - 2, cap, 0, 2);
            world.setBlock(x + 2, y + 5, z, cap, 0, 2);
            world.setBlock(x - 2, y + 5, z, cap, 0, 2);
            world.setBlock(x + 2, y + 5, z + 2, cap, 0, 2);
            world.setBlock(x + 2, y + 5, z - 2, cap, 0, 2);
            world.setBlock(x + 2, y + 5, z + 1, cap, 0, 2);
            world.setBlock(x + 2, y + 5, z - 1, cap, 0, 2);
            world.setBlock(x - 2, y + 5, z + 2, cap, 0, 2);
            world.setBlock(x - 2, y + 5, z - 2, cap, 0, 2);
            world.setBlock(x - 2, y + 5, z + 1, cap, 0, 2);
            world.setBlock(x - 2, y + 5, z - 1, cap, 0, 2);
            world.setBlock(x - 1, y + 5, z + 2, cap, 0, 2);
            world.setBlock(x - 1, y + 5, z - 2, cap, 0, 2);
            world.setBlock(x + 1, y + 5, z + 2, cap, 0, 2);
            world.setBlock(x + 1, y + 5, z - 2, cap, 0, 2);

            world.setBlock(x, y + 6, z + 1, cap, 0, 2);
            world.setBlock(x, y + 6, z - 1, cap, 0, 2);
            world.setBlock(x - 1, y + 6, z, cap, 0, 2);
            world.setBlock(x + 1, y + 6, z, cap, 0, 2);
            world.setBlock(x - 1, y + 6, z + 1, cap, 0, 2);
            world.setBlock(x - 1, y + 6, z - 1, cap, 0, 2);
            world.setBlock(x + 1, y + 6, z - 1, cap, 0, 2);
            world.setBlock(x + 1, y + 6, z + 1, cap, 0, 2);

            world.setBlock(x, y + 7, z, cap, 0, 2);
            world.setBlock(x, y + 7, z + 1, cap, 0, 2);
            world.setBlock(x, y + 7, z - 1, cap, 0, 2);
            world.setBlock(x - 1, y + 7, z, cap, 0, 2);
            world.setBlock(x + 1, y + 7, z, cap, 0, 2);
            return true;
        }
        return false;
    }
}
