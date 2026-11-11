package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class MazeAbbiegung extends MazeBoden
{
    public MazeAbbiegung(World world, Random rand, int x, int y, int z)
    {
        super(world, rand, x, y, z);
        int r = rand.nextInt(2);
        int invert = rand.nextInt(2) * (-1);

        switch (r)
        {
            case 0:
                for (int h = 1; h <= 2; ++h)
                {
                    for (int i = 0; i <= 4; ++i)
                    {
                        world.setBlock(x, y + h, z + i + (2 * i * invert), Blocks.air, 0, 2);
                        world.setBlock(x + i + (2 * i * invert), y + h, z, Blocks.air, 0, 2);
                        world.setBlock((x - 1) - (2 * (1 * invert)), y + h, z + i + (2 * i * invert), Blocks.nether_brick, 0, 2);
                        world.setBlock(x + i + (2 * i * invert), y + h, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                    }
                    for (int i2 = 1; i2 <= 4; ++i2)
                    {
                        world.setBlock(x + 1 + (2 * 1 * invert), y + h, z + i2 + (2 * i2 * invert), Blocks.nether_brick, 0, 2);
                        world.setBlock(x + i2 + (2 * i2 * invert), y + h, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                    }
                }

                if (rand.nextInt(17) == 0)
                {
                    world.setBlock(x + 1 + (2 * 1 * invert), y, z + 1 + (2 * 1 * invert), Blocks.chest, 0, 2);
                    TileEntityChest chest = (TileEntityChest)world.getTileEntity(x + 1 + (2 * 1 * invert), y, z + 1 + (2 * 1 * invert));
                    if (chest != null)
                    {
                        for (int i = 0; i < 8; ++i)
                        {
                            ItemStack item = this.pickCheckLootItemFood(rand);
                            if (item != null)
                            {
                                chest.setInventorySlotContents(rand.nextInt(chest.getSizeInventory()), item);
                            }
                        }
                    }
                }
                break;

            default:
                for (int h = 1; h <= 2; ++h)
                {
                    for (int i = 0; i <= 4; ++i)
                    {
                        world.setBlock(x, y + h, z + i + (2 * i * invert), Blocks.air, 0, 2);
                        world.setBlock((x - i) - (2 * (i * invert)), y + h, z, Blocks.air, 0, 2);
                        world.setBlock(x + 1 + (2 * 1 * invert), y + h, z + i + (2 * i * invert), Blocks.nether_brick, 0, 2);
                        world.setBlock((x - i) - (2 * (i * invert)), y + h, (z - 1) - (2 * (1 * invert)), Blocks.nether_brick, 0, 2);
                    }
                    for (int i2 = 1; i2 <= 4; ++i2)
                    {
                        world.setBlock((x - 1) - (2 * (1 * invert)), y + h, z + i2 + (2 * i2 * invert), Blocks.nether_brick, 0, 2);
                        world.setBlock((x - i2) - (2 * (i2 * invert)), y + h, z + 1 + (2 * 1 * invert), Blocks.nether_brick, 0, 2);
                    }
                }

                if (rand.nextInt(17) == 0)
                {
                    world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.chest, 0, 2);
                    TileEntityChest chest = (TileEntityChest)world.getTileEntity((x - 1) - (2 * (1 * invert)), y, (z - 1) - (2 * (1 * invert)));
                    if (chest != null)
                    {
                        for (int i = 0; i < 8; ++i)
                        {
                            ItemStack item = this.pickCheckLootItemFood(rand);
                            if (item != null)
                            {
                                chest.setInventorySlotContents(rand.nextInt(chest.getSizeInventory()), item);
                            }
                        }
                    }
                }
                else if (rand.nextInt(5) == 0)
                {
                    world.setBlock((x - 1) - (2 * (1 * invert)), y + 1, (z - 1) - (2 * (1 * invert)), Blocks.mob_spawner, 0, 2);
                    TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity((x - 1) - (2 * (1 * invert)), y + 1, (z - 1) - (2 * (1 * invert)));
                    if (spawner != null)
                    {
                        spawner.func_145881_a().setEntityName(this.pickMobSpawner(rand));
                    }
                }
                break;
        }
    }
}
