package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class MazeMiddle extends MazeBoden
{
    public MazeMiddle(World world, Random rand, int x, int y, int z)
    {
        super(world, rand, x, y, z);

        for (int i = 0; i <= 3; ++i)
        {
            world.setBlock(x - 3, y + 1, z + i, Blocks.air, 0, 2);
            world.setBlock(x - 3, y + 1, z - i, Blocks.air, 0, 2);
            world.setBlock(x - 2, y + 1, z + i, Blocks.air, 0, 2);
            world.setBlock(x - 2, y + 1, z - i, Blocks.air, 0, 2);
            world.setBlock(x - 1, y + 1, z + i, Blocks.air, 0, 2);
            world.setBlock(x - 1, y + 1, z - i, Blocks.air, 0, 2);
            world.setBlock(x, y + 1, z + i, Blocks.air, 0, 2);
            world.setBlock(x, y + 1, z - i, Blocks.air, 0, 2);
            world.setBlock(x + 1, y + 1, z + i, Blocks.air, 0, 2);
            world.setBlock(x + 1, y + 1, z - i, Blocks.air, 0, 2);
            world.setBlock(x + 2, y + 1, z + i, Blocks.air, 0, 2);
            world.setBlock(x + 2, y + 1, z - i, Blocks.air, 0, 2);
            world.setBlock(x + 3, y + 1, z + i, Blocks.air, 0, 2);
            world.setBlock(x + 3, y + 1, z - i, Blocks.air, 0, 2);

            world.setBlock(x - 3, y + 2, z + i, Blocks.air, 0, 2);
            world.setBlock(x - 3, y + 2, z - i, Blocks.air, 0, 2);
            world.setBlock(x - 2, y + 2, z + i, Blocks.air, 0, 2);
            world.setBlock(x - 2, y + 2, z - i, Blocks.air, 0, 2);
            world.setBlock(x - 1, y + 2, z + i, Blocks.air, 0, 2);
            world.setBlock(x - 1, y + 2, z - i, Blocks.air, 0, 2);
            world.setBlock(x, y + 2, z + i, Blocks.air, 0, 2);
            world.setBlock(x, y + 2, z - i, Blocks.air, 0, 2);
            world.setBlock(x + 1, y + 2, z + i, Blocks.air, 0, 2);
            world.setBlock(x + 1, y + 2, z - i, Blocks.air, 0, 2);
            world.setBlock(x + 2, y + 2, z + i, Blocks.air, 0, 2);
            world.setBlock(x + 2, y + 2, z - i, Blocks.air, 0, 2);
            world.setBlock(x + 3, y + 2, z + i, Blocks.air, 0, 2);
            world.setBlock(x + 3, y + 2, z - i, Blocks.air, 0, 2);
        }

        for (int i2 = 0; i2 <= 4; ++i2)
        {
            world.setBlock(x + 4, y + 1, z + i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x + 4, y + 1, z - i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x - 4, y + 1, z + i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x - 4, y + 1, z - i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x + i2, y + 1, z + 4, Blocks.nether_brick, 0, 2);
            world.setBlock(x - i2, y + 1, z + 4, Blocks.nether_brick, 0, 2);
            world.setBlock(x + i2, y + 1, z - 4, Blocks.nether_brick, 0, 2);
            world.setBlock(x - i2, y + 1, z - 4, Blocks.nether_brick, 0, 2);

            world.setBlock(x + 4, y + 2, z + i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x + 4, y + 2, z - i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x - 4, y + 2, z + i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x - 4, y + 2, z - i2, Blocks.nether_brick, 0, 2);
            world.setBlock(x + i2, y + 2, z + 4, Blocks.nether_brick, 0, 2);
            world.setBlock(x - i2, y + 2, z + 4, Blocks.nether_brick, 0, 2);
            world.setBlock(x + i2, y + 2, z - 4, Blocks.nether_brick, 0, 2);
            world.setBlock(x - i2, y + 2, z - 4, Blocks.nether_brick, 0, 2);
        }

        world.setBlock(x + 4, y + 1, z, Blocks.air, 0, 2);
        world.setBlock(x + 4, y + 2, z, Blocks.air, 0, 2);
        world.setBlock(x - 4, y + 1, z, Blocks.air, 0, 2);
        world.setBlock(x - 4, y + 2, z, Blocks.air, 0, 2);
        world.setBlock(x, y + 1, z + 4, Blocks.air, 0, 2);
        world.setBlock(x, y + 2, z + 4, Blocks.air, 0, 2);
        world.setBlock(x, y + 1, z - 4, Blocks.air, 0, 2);
        world.setBlock(x, y + 2, z - 4, Blocks.air, 0, 2);

        world.setBlock(x, y, z, Blocks.obsidian, 0, 2);
        world.setBlock(x + 1, y, z, Blocks.obsidian, 0, 2);
        world.setBlock(x - 1, y, z, Blocks.obsidian, 0, 2);
        world.setBlock(x, y, z + 1, Blocks.obsidian, 0, 2);
        world.setBlock(x, y, z - 1, Blocks.obsidian, 0, 2);
        world.setBlock(x + 1, y, z + 1, Blocks.obsidian, 0, 2);
        world.setBlock(x + 1, y, z - 1, Blocks.obsidian, 0, 2);
        world.setBlock(x - 1, y, z + 1, Blocks.obsidian, 0, 2);
        world.setBlock(x - 1, y, z - 1, Blocks.obsidian, 0, 2);

        world.setBlock(x, y + 1, z, MNCBlocks.Stature, 0, 2);

        world.setBlock(x + 3, y + 1, z + 3, Blocks.chest, 0, 2);
        TileEntityChest c1 = (TileEntityChest)world.getTileEntity(x + 3, y + 1, z + 3);
        if (c1 != null)
        {
            for (int i = 0; i < 8; ++i)
            {
                ItemStack item = this.pickCheckLootItem(rand);
                if (item != null)
                {
                    c1.setInventorySlotContents(rand.nextInt(c1.getSizeInventory()), item);
                }
            }
        }

        world.setBlock(x - 3, y + 1, z - 3, Blocks.chest, 0, 2);
        TileEntityChest c2 = (TileEntityChest)world.getTileEntity(x - 3, y + 1, z - 3);
        if (c2 != null)
        {
            for (int i = 0; i < 8; ++i)
            {
                ItemStack item = this.pickCheckLootItem(rand);
                if (item != null)
                {
                    c2.setInventorySlotContents(rand.nextInt(c2.getSizeInventory()), item);
                }
            }
        }

        world.setBlock(x + 3, y + 1, z - 3, Blocks.chest, 0, 2);
        TileEntityChest c3 = (TileEntityChest)world.getTileEntity(x + 3, y + 1, z - 3);
        if (c3 != null)
        {
            for (int i = 0; i < 8; ++i)
            {
                ItemStack item = this.pickCheckLootItem(rand);
                if (item != null)
                {
                    c3.setInventorySlotContents(rand.nextInt(c3.getSizeInventory()), item);
                }
            }
        }

        world.setBlock(x - 3, y + 1, z + 3, Blocks.chest, 0, 2);
        TileEntityChest c4 = (TileEntityChest)world.getTileEntity(x - 3, y + 1, z + 3);
        if (c4 != null)
        {
            for (int i = 0; i < 8; ++i)
            {
                ItemStack item = this.pickCheckLootItem(rand);
                if (item != null)
                {
                    c4.setInventorySlotContents(rand.nextInt(c4.getSizeInventory()), item);
                }
            }
        }

        for (int w = 0; w <= 4; ++w)
        {
            for (int l = 0; l <= 4; ++l)
            {
                world.setBlock(x + w, y + 3, z + l, Blocks.nether_brick, 0, 2);
                world.setBlock(x - w, y + 3, z - l, Blocks.nether_brick, 0, 2);
                world.setBlock(x + w, y + 3, z - l, Blocks.nether_brick, 0, 2);
                world.setBlock(x - w, y + 3, z + l, Blocks.nether_brick, 0, 2);
            }
        }
    }
}
