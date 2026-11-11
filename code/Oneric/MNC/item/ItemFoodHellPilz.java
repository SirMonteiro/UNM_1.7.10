package Oneric.MNC.item;

import Oneric.MNC.common.mod_netherbr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;

public class ItemFoodHellPilz extends ItemFood
{
    public ItemFoodHellPilz(int healAmount, float saturationModifier, boolean isWolfFood)
    {
        super(healAmount, saturationModifier, isWolfFood);
        this.setPotionEffect(Potion.fireResistance.id, 22, 3, 1.0F);
        this.setAlwaysEdible();
        this.setMaxStackSize(16);
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    public ItemFoodHellPilz(int id, int healAmount, float saturationModifier, boolean isWolfFood)
    {
        this(healAmount, saturationModifier, isWolfFood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registry)
    {
        this.itemIcon = registry.registerIcon(mod_netherbr.getId() + ":" + this.getUnlocalizedName().substring(5));
    }
}
