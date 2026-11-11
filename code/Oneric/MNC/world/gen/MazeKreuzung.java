package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class MazeKreuzung extends MazeBoden
{
    public MazeKreuzung(World world, Random rand, int x, int y, int z)
    {
        super(world, rand, x, y, z);

        for (int h = 1; h <= 2; ++h)
        {
            world.setBlock(x, y + h, z, Blocks.air, 0, 2);

            for (int l = 0; l <= 4; ++l)
            {
                world.setBlock(x + l, y + h, z, Blocks.air, 0, 2);
                world.setBlock(x - l, y + h, z, Blocks.air, 0, 2);
                world.setBlock(x, y + h, z + l, Blocks.air, 0, 2);
                world.setBlock(x, y + h, z - l, Blocks.air, 0, 2);
            }

            for (int l2 = 1; l2 <= 4; ++l2)
            {
                world.setBlock(x + 1, y + h, z + l2, Blocks.nether_brick, 0, 2);
                world.setBlock(x + 1, y + h, z - l2, Blocks.nether_brick, 0, 2);
                world.setBlock(x - 1, y + h, z + l2, Blocks.nether_brick, 0, 2);
                world.setBlock(x - 1, y + h, z - l2, Blocks.nether_brick, 0, 2);
                world.setBlock(x + l2, y + h, z + 1, Blocks.nether_brick, 0, 2);
                world.setBlock(x - l2, y + h, z + 1, Blocks.nether_brick, 0, 2);
                world.setBlock(x + l2, y + h, z - 1, Blocks.nether_brick, 0, 2);
                world.setBlock(x - l2, y + h, z - 1, Blocks.nether_brick, 0, 2);
            }
        }

        if (rand.nextInt(6) == 0)
        {
            world.setBlock(x + 1, y + 1, z + 1, Blocks.chest, 0, 2);
            TileEntityChest chest = (TileEntityChest)world.getTileEntity(x + 1, y + 1, z + 1);

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
            return;
        }

        if (rand.nextInt(5) == 0)
        {
            world.setBlock(x + 1, y + 1, z + 1, Blocks.mob_spawner, 0, 2);
            TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x + 1, y + 1, z + 1);

            if (spawner != null)
            {
                spawner.func_145881_a().setEntityName(this.pickMobSpawner(rand));
            }
        }
    }
}
