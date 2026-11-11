package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class WorldGenDungeon_Nether extends Buildings
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        Block[] validSurroundings = new Block[]{Blocks.netherrack, MNCBlocks.NetherGold, Blocks.quartz_ore};

        if (this.isBoxFilledWith(world, x, y - 1, z, 6, 4, 6, validSurroundings))
        {
            this.setBoxHollow(world, x, y - 1, z, 6, 4, 6, Blocks.nether_brick, 0);

            world.setBlock(x + 1, y, z + 2, Blocks.chest, 0, 2);
            TileEntityChest chest1 = (TileEntityChest) world.getTileEntity(x + 1, y, z + 2);
            if (chest1 != null)
            {
                for (int i = 0; i < 8; ++i)
                {
                    ItemStack item = this.pickCheckLootItem(rand);
                    if (item != null)
                    {
                        chest1.setInventorySlotContents(rand.nextInt(chest1.getSizeInventory()), item);
                    }
                }
            }

            world.setBlock(x + 2, y, z + 3, Blocks.mob_spawner, 0, 2);
            TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(x + 2, y, z + 3);
            if (spawner != null)
            {
                spawner.func_145881_a().setEntityName(this.pickMobSpawner(rand));
            }

            world.setBlock(x + 4, y, z + 3, Blocks.chest, 0, 2);
            TileEntityChest chest2 = (TileEntityChest) world.getTileEntity(x + 4, y, z + 3);
            if (chest2 != null)
            {
                for (int i = 0; i < 8; ++i)
                {
                    ItemStack item = this.pickCheckLootItem(rand);
                    if (item != null)
                    {
                        chest2.setInventorySlotContents(rand.nextInt(chest2.getSizeInventory()), item);
                    }
                }
            }

            return true;
        }

        return false;
    }

    public ItemStack pickCheckLootItem(Random rand)
    {
        int choice = rand.nextInt(11);

        if (choice == 0)
        {
            return new ItemStack(Items.nether_wart);
        }
        if (choice == 1)
        {
            return new ItemStack(Items.iron_ingot, rand.nextInt(4) + 1);
        }
        if (choice == 2)
        {
            return new ItemStack(MNCItems.HellPilz_Samen);
        }
        if (choice == 3)
        {
            return new ItemStack(Items.gold_nugget, rand.nextInt(4) + 1);
        }
        if (choice == 4)
        {
            return rand.nextBoolean() ? new ItemStack(MNCItems.NBucketEmpty, 1) : new ItemStack(Items.saddle);
        }
        if (choice == 5)
        {
            return rand.nextBoolean() ? new ItemStack(MNCItems.NBucketFull, 1) : new ItemStack(Items.ender_pearl);
        }
        if (choice == 6)
        {
            return new ItemStack(Items.blaze_rod);
        }
        if (choice == 7 && rand.nextInt(100) == 0)
        {
            return new ItemStack(MNCItems.epicingot);
        }
        if (choice == 8 && rand.nextInt(2) == 0)
        {
            return new ItemStack(MNCItems.GGold_i, rand.nextInt(4) + 1);
        }
        if (choice == 9 && rand.nextInt(10) == 0)
        {
            return new ItemStack(rand.nextBoolean() ? Items.record_13 : Items.record_cat);
        }
        if (choice == 10)
        {
            return new ItemStack(MNCItems.Netherstick, rand.nextInt(4) + 1);
        }

        return null;
    }

    public String pickMobSpawner(Random rand)
    {
        int choice = rand.nextInt(4);
        if (choice == 0) return "Blaze";
        if (choice == 1 || choice == 2) return "PigZombie";
        return "Blaze";
    }
}
