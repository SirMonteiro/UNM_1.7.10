package Oneric.MNC.item;

import Oneric.MNC.common.MNCItems;
import Oneric.MNC.entity.EntityNetherArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNetherBow extends MNCItem_rare
{
    public int cooldown = 0;

    public ItemNetherBow()
    {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setMaxDamage(3000);
    }

    public ItemNetherBow(int id)
    {
        this();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if ((player.capabilities.isCreativeMode || player.inventory.hasItem(MNCItems.NArrow)) && this.cooldown == 0)
        {
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote)
            {
                world.spawnEntityInWorld(new EntityNetherArrow(world, player));
                stack.damageItem(1, player);
                this.cooldown = 16;
                if (!player.capabilities.isCreativeMode)
                {
                    player.inventory.consumeInventoryItem(MNCItems.NArrow);
                }
            }
        }
        return stack;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (this.cooldown > 0)
        {
            this.cooldown--;
        }
        if (this.cooldown < 0)
        {
            this.cooldown = 0;
        }
    }
}
