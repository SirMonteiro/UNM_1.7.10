package Oneric.MNC.item;

import Oneric.MNC.common.mod_netherbr;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class MNCItemArmor extends ItemArmor
{
    public MNCItemArmor(ItemArmor.ArmorMaterial material, int renderIndex, int armorType)
    {
        super(material, renderIndex, armorType);
    }

    /**
     * 1.7.10 resolves worn armour textures through this Forge hook. Without the
     * override the renderer falls back to "minecraft:textures/models/armor/...",
     * where our nether/glowstone sheets do not exist, so the armour is shown with
     * the missing-texture (white) look.
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (type != null)
        {
            return null;
        }

        String setName;

        if (this.getArmorMaterial() == mod_netherbr.glowstoneA)
        {
            setName = "glowstone";
        }
        else if (this.getArmorMaterial() == mod_netherbr.epicNetherA)
        {
            setName = "nether";
        }
        else
        {
            return null;
        }

        return mod_netherbr.getId() + ":textures/models/armor/" + setName + "_" + (slot == 2 ? 2 : 1) + ".png";
    }
}
