package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class Maze3Ausg1 extends MazeBoden
{
    public Maze3Ausg1(World world, Random rand, int x, int y, int z)
    {
        super(world, rand, x, y, z);
        int rotation = rand.nextInt(2);
        int invert = rand.nextInt(2) * (-1);

        for (int h = 1; h <= 2; ++h)
        {
            this.setWithRotationAndNotify(rotation, invert, world, 0, h, 0, Blocks.air);
            for (int l = 1; l <= 4; ++l)
            {
                this.setWithRotationAndNotify(rotation, invert, world, 0, h, l, Blocks.air);
                this.setWithRotationAndNotify(rotation, invert, world, 0, h, -l, Blocks.air);
            }

            this.setWithRotationAndNotify(rotation, invert, world, 1, h, -3, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 2, h, -3, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 2, h, -2, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 3, h, -2, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 1, h, 3, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 2, h, 3, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 2, h, 2, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 2, h, 1, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 2, h, 0, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 3, h, 0, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, 4, h, 0, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -1, h, 2, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -2, h, 2, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -2, h, 3, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -3, h, 3, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -2, h, 1, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -3, h, 1, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -3, h, 0, Blocks.air);
            this.setWithRotationAndNotify(rotation, invert, world, -4, h, 0, Blocks.air);

            this.setWithRotation(rotation, invert, world, 1, h, 0, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 1, h, 1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 1, h, 2, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 1, h, -1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 1, h, -2, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 2, h, -1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 3, h, -1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 4, h, -1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 4, h, -2, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 3, h, -3, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 4, h, -4, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 2, h, -4, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 1, h, -4, Blocks.nether_brick);

            for (int l2 = 0; l2 <= 4; ++l2)
            {
                this.setWithRotation(rotation, invert, world, -1, h, -l2, Blocks.nether_brick);
            }

            this.setWithRotation(rotation, invert, world, -1, h, 1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -2, h, 0, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -3, h, -1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -4, h, -1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -4, h, 1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -3, h, 2, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -4, h, 3, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -3, h, 4, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -2, h, 4, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -1, h, 4, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, -1, h, 3, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 1, h, 4, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 2, h, 4, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 3, h, 3, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 3, h, 2, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 3, h, 1, Blocks.nether_brick);
            this.setWithRotation(rotation, invert, world, 4, h, 1, Blocks.nether_brick);
        }

        if (rand.nextInt(4) == 0)
        {
            this.setWithRotationAndNotify(rotation, invert, world, -1, 1, 0, Blocks.chest);
            TileEntityChest chest = (TileEntityChest)world.getTileEntity((x - 1) - (2 * (1 * invert)), y + 1, z);
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
    }
}
