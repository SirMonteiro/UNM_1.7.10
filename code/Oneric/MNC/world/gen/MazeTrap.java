package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MazeTrap
{
    public MazeTrap(World world, Random rand, int x, int y, int z)
    {
        world.setBlock(x, y + 1, z, Blocks.stone_pressure_plate, 0, 2);
        world.setBlock(x, y, z, MNCBlocks.nTNT, 0, 2);
        world.setBlock(x + 1, y - 1, z, Blocks.netherrack, 0, 2);
        world.setBlock(x - 1, y - 1, z, Blocks.netherrack, 0, 2);
        world.setBlock(x, y - 1, z + 1, Blocks.netherrack, 0, 2);
        world.setBlock(x, y - 1, z - 1, Blocks.netherrack, 0, 2);
        world.setBlock(x, y - 1, z, Blocks.lava, 0, 2);
        world.setBlock(x + 1, y - 2, z, Blocks.netherrack, 0, 2);
        world.setBlock(x - 1, y - 2, z, Blocks.netherrack, 0, 2);
        world.setBlock(x, y - 2, z + 1, Blocks.netherrack, 0, 2);
        world.setBlock(x, y - 2, z - 1, Blocks.netherrack, 0, 2);
        world.setBlock(x, y - 2, z, Blocks.lava, 0, 2);
        world.setBlock(x, y - 3, z, Blocks.netherrack, 0, 2);
    }
}
