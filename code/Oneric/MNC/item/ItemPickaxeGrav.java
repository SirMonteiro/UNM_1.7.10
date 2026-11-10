package Oneric.MNC.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * Gravitation pickaxe (TODO in the original mod). Mines like a pickaxe and
 * magnetically pulls nearby drops towards the player.
 */
public class ItemPickaxeGrav extends ItemPickaxe
{
    public ItemPickaxeGrav(Item.ToolMaterial material)
    {
        super(material);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        double radius = 5.0D;
        AxisAlignedBB box = player.boundingBox.expand(radius, radius, radius);
        List<?> items = world.getEntitiesWithinAABB(EntityItem.class, box);

        for (Object o : items)
        {
            EntityItem item = (EntityItem)o;
            double dx = player.posX - item.posX;
            double dy = player.posY + 1.0D - item.posY;
            double dz = player.posZ - item.posZ;
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (dist < radius && dist > 0.01D)
            {
                item.motionX += dx / dist * 0.15D;
                item.motionY += dy / dist * 0.15D;
                item.motionZ += dz / dist * 0.15D;
                item.velocityChanged = true;
            }
        }

        return stack;
    }
}
