package Oneric.MNC.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDoor_MNC extends Item
{
    private final Block door;

    public ItemDoor_MNC(Block door)
    {
        super();
        this.door = door;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (side != 1)
        {
            return false;
        }

        ++y;

        if (!player.canPlayerEdit(x, y, z, side, stack) || !player.canPlayerEdit(x, y + 1, z, side, stack))
        {
            return false;
        }
        else if (!this.door.canPlaceBlockAt(world, x, y, z))
        {
            return false;
        }
        else
        {
            int dir = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
            placeDoorBlock(world, x, y, z, dir, this.door);
            --stack.stackSize;
            return true;
        }
    }

    public static void placeDoorBlock(World world, int x, int y, int z, int meta, Block door)
    {
        byte b0 = 0;
        byte b1 = 0;

        if (meta == 0) b1 = 1;
        if (meta == 1) b0 = -1;
        if (meta == 2) b1 = -1;
        if (meta == 3) b0 = 1;

        int i1 = (world.getBlock(x - b0, y, z - b1).isNormalCube() ? 1 : 0) + (world.getBlock(x - b0, y + 1, z - b1).isNormalCube() ? 1 : 0);
        int j1 = (world.getBlock(x + b0, y, z + b1).isNormalCube() ? 1 : 0) + (world.getBlock(x + b0, y + 1, z + b1).isNormalCube() ? 1 : 0);
        boolean flag = world.getBlock(x - b0, y, z - b1) == door || world.getBlock(x - b0, y + 1, z - b1) == door;
        boolean flag1 = world.getBlock(x + b0, y, z + b1) == door || world.getBlock(x + b0, y + 1, z + b1) == door;
        boolean flag2 = false;

        if (flag && !flag1)
        {
            flag2 = true;
        }
        else if (j1 > i1)
        {
            flag2 = true;
        }

        world.setBlock(x, y, z, door, meta, 2);
        world.setBlock(x, y + 1, z, door, 8 | (flag2 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, door);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, door);
    }
}
