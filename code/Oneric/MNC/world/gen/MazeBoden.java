package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MazeBoden
{
    public int normalX;
    public int normalY;
    public int normalZ;

    public MazeBoden(World world, Random rand, int x, int y, int z)
    {
        this.normalX = x;
        this.normalY = y;
        this.normalZ = z;

        for (int w = 0; w <= 4; ++w)
        {
            for (int l = 0; l <= 4; ++l)
            {
                int newX1 = x + w;
                int newZ1 = z + l;
                int newX2 = x - w;
                int newZ2 = z - l;

                world.setBlock(newX1, y - 1, newZ1, Blocks.netherrack, 0, 2);
                world.setBlock(newX1, y, newZ1, Blocks.lava, 0, 2);
                world.setBlock(newX1, y + 1, newZ1, Blocks.netherrack, 0, 2);
                world.setBlock(newX1, y + 2, newZ1, Blocks.netherrack, 0, 2);
                world.setBlock(newX1, y + 3, newZ1, Blocks.netherrack, 0, 2);

                world.setBlock(newX2, y - 1, newZ2, Blocks.netherrack, 0, 2);
                world.setBlock(newX2, y, newZ2, Blocks.lava, 0, 2);
                world.setBlock(newX2, y + 1, newZ2, Blocks.netherrack, 0, 2);
                world.setBlock(newX2, y + 2, newZ2, Blocks.netherrack, 0, 2);
                world.setBlock(newX2, y + 3, newZ2, Blocks.netherrack, 0, 2);

                world.setBlock(newX1, y - 1, newZ2, Blocks.netherrack, 0, 2);
                world.setBlock(newX1, y, newZ2, Blocks.lava, 0, 2);
                world.setBlock(newX1, y + 1, newZ2, Blocks.netherrack, 0, 2);
                world.setBlock(newX1, y + 2, newZ2, Blocks.netherrack, 0, 2);
                world.setBlock(newX1, y + 3, newZ2, Blocks.netherrack, 0, 2);

                world.setBlock(newX2, y - 1, newZ1, Blocks.netherrack, 0, 2);
                world.setBlock(newX2, y, newZ1, Blocks.lava, 0, 2);
                world.setBlock(newX2, y + 1, newZ1, Blocks.netherrack, 0, 2);
                world.setBlock(newX2, y + 2, newZ1, Blocks.netherrack, 0, 2);
                world.setBlock(newX2, y + 3, newZ1, Blocks.netherrack, 0, 2);
            }
        }
    }

    protected ItemStack pickCheckLootItem(Random rand)
    {
        int choice = rand.nextInt(11);

        if (choice == 0) return new ItemStack(Items.nether_wart);
        if (choice == 1) return new ItemStack(Items.iron_ingot, rand.nextInt(4) + 1);
        if (choice == 2) return new ItemStack(MNCItems.HellPilz_Samen);
        if (choice == 3) return new ItemStack(Items.gold_nugget, rand.nextInt(4) + 1);
        if (choice == 4) return new ItemStack(MNCItems.NBucketEmpty, 1);
        if (choice == 5) return new ItemStack(MNCItems.NBucketFull, 1);
        if (choice == 6) return new ItemStack(Items.blaze_rod);
        if (choice == 7 && rand.nextInt(100) == 0) return new ItemStack(MNCItems.epicingot);
        if (choice == 8 && rand.nextInt(2) == 0) return new ItemStack(MNCItems.GGold_i, rand.nextInt(4) + 1);
        if (choice == 9 && rand.nextInt(10) == 0) return new ItemStack(rand.nextBoolean() ? Items.record_13 : Items.record_cat);
        if (choice == 10) return new ItemStack(MNCItems.Netherstick, rand.nextInt(4) + 1);

        return null;
    }

    protected ItemStack pickCheckLootItemFood(Random rand)
    {
        int choice = rand.nextInt(11);

        if (choice == 0) return new ItemStack(Items.apple);
        if (choice == 1) return new ItemStack(Items.beef, rand.nextInt(4) + 1);
        if (choice == 2) return new ItemStack(MNCItems.HellPilz);
        if (choice == 3) return new ItemStack(Items.bread, rand.nextInt(4) + 1);
        if (choice == 4) return new ItemStack(Items.porkchop, rand.nextInt(4) + 1);
        if (choice == 5) return new ItemStack(Items.cooked_porkchop, rand.nextInt(4) + 1);
        if (choice == 6) return new ItemStack(Items.golden_apple);
        if (choice == 7 && rand.nextInt(100) == 0) return new ItemStack(Items.golden_apple, 1, 1);
        if (choice == 8 && rand.nextInt(2) == 0) return new ItemStack(Items.chicken, rand.nextInt(4) + 1);
        if (choice == 9 && rand.nextInt(10) == 0) return new ItemStack(Items.cooked_chicken, rand.nextInt(2) + 1);
        if (choice == 10) return new ItemStack(Items.rotten_flesh, rand.nextInt(4) + 1);

        return null;
    }

    protected String pickMobSpawner(Random rand)
    {
        int choice = rand.nextInt(4);
        if (choice == 0) return "LostSoul";
        if (choice == 1) return "Skeleton";
        if (choice == 2) return "PigZombie";
        return "Blaze";
    }

    protected void setWithRotationAndNotify(int rotation, int invert, World world, int plusX, int plusY, int plusZ, Block block)
    {
        if (rotation == 0)
        {
            world.setBlock(this.normalX + plusZ + (2 * plusZ * invert), this.normalY + plusY, this.normalZ + plusX + (2 * plusX * invert), block, 0, 2);
        }
        else
        {
            world.setBlock(this.normalX + plusX + (2 * plusX * invert), this.normalY + plusY, this.normalZ + plusZ + (2 * plusZ * invert), block, 0, 2);
        }
    }

    protected void setWithRotation(int rotation, int invert, World world, int plusX, int plusY, int plusZ, Block block)
    {
        setWithRotationAndNotify(rotation, invert, world, plusX, plusY, plusZ, block);
    }

    protected void setWithRotationAndMetadata(int rotation, int invert, World world, int plusX, int plusY, int plusZ, Block block, int metadata)
    {
        if (rotation == 0)
        {
            world.setBlock(this.normalX + plusZ + (2 * plusZ * invert), this.normalY + plusY, this.normalZ + plusX + (2 * plusX * invert), block, metadata, 2);
        }
        else
        {
            world.setBlock(this.normalX + plusX + (2 * plusX * invert), this.normalY + plusY, this.normalZ + plusZ + (2 * plusZ * invert), block, metadata, 2);
        }
    }
}
