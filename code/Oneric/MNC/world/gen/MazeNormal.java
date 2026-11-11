package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MazeNormal extends MazeBoden
{
    public MazeNormal(World world, Random rand, int x, int y, int z)
    {
        super(world, rand, x, y, z);
        int i = rand.nextInt(2);
        int invert = rand.nextInt(2) * (-1);

        switch (i)
        {
            case 0:
                world.setBlock(x + 4 + (2 * 4 * invert), y + 1, z, Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 1, z, Blocks.nether_brick, 0, 2);
                world.setBlock(x, y + 1, z, Blocks.air, 0, 2);

                for (int l = 0; l <= 4; ++l)
                {
                    world.setBlock(x + l + (2 * l * invert), y + 1, z, Blocks.air, 0, 2);
                    world.setBlock((x - l) - (2 * (l * invert)), y + 1, z, Blocks.air, 0, 2);
                }

                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, z + 1 + (2 * 1 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.air, 0, 2);

                world.setBlock(x, y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x, y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);

                world.setBlock(x + 4 + (2 * 4 * invert), y + 2, z, Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 2, z, Blocks.nether_brick, 0, 2);
                world.setBlock(x, y + 2, z, Blocks.air, 0, 2);

                for (int l2 = 0; l2 <= 4; ++l2)
                {
                    world.setBlock(x + l2 + (2 * l2 * invert), y + 2, z, Blocks.air, 0, 2);
                    world.setBlock((x - l2) - (2 * (l2 * invert)), y + 2, z, Blocks.air, 0, 2);
                }

                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, z + 1 + (2 * 1 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.air, 0, 2);

                world.setBlock(x, y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x, y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);
                break;

            default:
                world.setBlock(x, y + 1, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x, y + 1, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x, y + 1, z, Blocks.air, 0, 2);

                for (int l3 = 0; l3 <= 4; ++l3)
                {
                    world.setBlock(x, y + 1, z + l3 + (2 * l3 * invert), Blocks.air, 0, 2);
                    world.setBlock(x, y + 1, (z - l3) - (2 * (l3 * invert)), Blocks.air, 0, 2);
                }

                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.air, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.air, 0, 2);

                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, z, Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z, Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 1, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 1, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 1, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 1, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 1, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 1, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);

                world.setBlock(x, y + 2, z, Blocks.air, 0, 2);

                for (int l4 = 0; l4 <= 4; ++l4)
                {
                    world.setBlock(x, y + 2, z + l4 + (2 * l4 * invert), Blocks.air, 0, 2);
                    world.setBlock(x, y + 2, (z - l4) - (2 * (l4 * invert)), Blocks.air, 0, 2);
                }

                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.air, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.air, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 2 + (2 * 2 * invert), Blocks.air, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.air, 0, 2);

                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, z, Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z, Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 4) - (2 * (4 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 2, (z - 3) - (2 * (3 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 4) - (2 * (4 * invert)), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 2) - (2 * (2 * invert)), y + 2, (z - 2) - (2 * (2 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 3) - (2 * (3 * invert)), y + 2, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock((x - 1) - (2 * (1 * invert)), y + 2, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 2, z + 2 + (2 * 2 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 4 + (2 * 4 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 3 + (2 * 3 * invert), y + 2, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1 + (2 * 1 * invert), y + 2, z + 4 + (2 * 4 * invert), Blocks.nether_brick, 0, 2);
                world.setBlock(x + 2 + (2 * 2 * invert), y + 2, z + 3 + (2 * 3 * invert), Blocks.nether_brick, 0, 2);
                break;
        }
    }
}
