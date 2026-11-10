package Oneric.MNC.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class MNCItemSword extends ItemSword
{
    /** Glowstone weapons set their victim on fire */
    private final boolean burning;

    public MNCItemSword(Item.ToolMaterial material, boolean burning)
    {
        super(material);
        this.burning = burning;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if (this.burning)
        {
            target.setFire(5);
        }
        return super.hitEntity(stack, target, attacker);
    }
}
