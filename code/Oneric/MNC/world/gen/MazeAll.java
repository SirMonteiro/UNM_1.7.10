package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MazeAll
{
    public MazeAll(World world, Random rand, int x, int y, int z)
    {
        switch (rand.nextInt(4))
        {
            case 0:
                new MazeKreuzung(world, rand, x, y, z);
                break;
            case 1:
                new MazeAbbiegung(world, rand, x, y, z);
                break;
            case 2:
                new Maze3Ausg1(world, rand, x, y, z);
                break;
            default:
                new MazeNormal(world, rand, x, y, z);
                break;
        }

        for (int w = 0; w <= 4; ++w)
        {
            for (int l = 0; l <= 4; ++l)
            {
                int newX1 = x + w;
                int newZ1 = z + l;
                int newX2 = x - w;
                int newZ2 = z - l;

                if (world.isAirBlock(newX1, y + 1, newZ1) && rand.nextInt(30) == 0
                        && world.getBlock(newX1 + 1, y + 1, newZ1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX1 - 1, y + 1, newZ1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX1, y + 1, newZ1 + 1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX1, y + 1, newZ1 - 1) != Blocks.stone_pressure_plate)
                {
                    new MazeTrap(world, rand, newX1, y, newZ1);
                }

                if (world.isAirBlock(newX2, y + 1, newZ2) && rand.nextInt(30) == 0
                        && world.getBlock(newX2 + 1, y + 1, newZ2) != Blocks.stone_pressure_plate
                        && world.getBlock(newX2 - 1, y + 1, newZ2) != Blocks.stone_pressure_plate
                        && world.getBlock(newX2, y + 1, newZ2 + 1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX2, y + 1, newZ2 - 1) != Blocks.stone_pressure_plate)
                {
                    new MazeTrap(world, rand, newX2, y, newZ2);
                }

                if (world.isAirBlock(newX1, y + 1, newZ2) && rand.nextInt(30) == 0
                        && world.getBlock(newX1 + 1, y + 1, newZ2) != Blocks.stone_pressure_plate
                        && world.getBlock(newX1 - 1, y + 1, newZ2) != Blocks.stone_pressure_plate
                        && world.getBlock(newX1, y + 1, newZ2 + 1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX1, y + 1, newZ2 - 1) != Blocks.stone_pressure_plate)
                {
                    new MazeTrap(world, rand, newX1, y, newZ2);
                }

                if (world.isAirBlock(newX2, y + 1, newZ1) && rand.nextInt(30) == 0
                        && world.getBlock(newX2 + 1, y + 1, newZ1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX2 - 1, y + 1, newZ1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX2, y + 1, newZ1 + 1) != Blocks.stone_pressure_plate
                        && world.getBlock(newX2, y + 1, newZ1 - 1) != Blocks.stone_pressure_plate)
                {
                    new MazeTrap(world, rand, newX2, y, newZ1);
                }

                if (world.isAirBlock(newX1, y + 2, newZ1))
                {
                    world.setBlock(newX1, y + 3, newZ1, Blocks.nether_brick, 0, 2);
                }
                if (world.isAirBlock(newX2, y + 2, newZ2))
                {
                    world.setBlock(newX2, y + 3, newZ2, Blocks.nether_brick, 0, 2);
                }
                if (world.isAirBlock(newX1, y + 2, newZ2))
                {
                    world.setBlock(newX1, y + 3, newZ2, Blocks.nether_brick, 0, 2);
                }
                if (world.isAirBlock(newX2, y + 2, newZ1))
                {
                    world.setBlock(newX2, y + 3, newZ1, Blocks.nether_brick, 0, 2);
                }
            }
        }
    }
}
