package Oneric.MNC.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

/**
 * Diablo, the trident ("foice"). 1.6.4-faithful: a plain very strong sword.
 * The port previously added setFire() to hitEntity; that behaviour does NOT
 * exist in the original 1.6.4 mod and has been removed.
 */
public class ItemDreizack extends ItemSword
{
    public ItemDreizack(Item.ToolMaterial material)
    {
        super(material);
    }
}
