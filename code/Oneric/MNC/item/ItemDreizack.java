package Oneric.MNC.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/** Diablo, the trident. Behaves like a very nasty sword. */
public class ItemDreizack extends ItemSword
{
    public ItemDreizack(Item.ToolMaterial material)
    {
        super(material);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.setFire(10);
        return super.hitEntity(stack, target, attacker);
    }
}
