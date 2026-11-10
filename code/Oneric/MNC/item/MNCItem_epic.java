package Oneric.MNC.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class MNCItem_epic extends MNCItem
{
    @SideOnly(Side.CLIENT)
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.epic;
    }
}
