package Oneric.MNC.item;

import Oneric.MNC.common.MNCItems;
import Oneric.MNC.entity.EntityNetherArrow;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class ItemNetherBow extends ItemBow
{
    public ItemNetherBow()
    {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(500);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int useCount)
    {
        boolean creative = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;
        ItemStack ammo = this.findAmmo(player);

        if (ammo != null || creative)
        {
            int charge = this.getMaxItemUseDuration(stack) - useCount;
            float power = (float)charge / 20.0F;
            power = (power * power + power * 2.0F) / 3.0F;

            if ((double)power < 0.1D)
            {
                return;
            }

            if (power > 1.0F)
            {
                power = 1.0F;
            }

            EntityNetherArrow arrow = new EntityNetherArrow(world, player, power * 2.0F);

            if (power == 1.0F)
            {
                arrow.setIsCritical(true);
            }

            int powerEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

            if (powerEnchant > 0)
            {
                arrow.setDamage(arrow.getDamage() + (double)powerEnchant * 0.5D + 0.5D);
            }

            int punchEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

            if (punchEnchant > 0)
            {
                arrow.setKnockbackStrength(punchEnchant);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
            {
                arrow.setFire(100);
            }

            stack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + power * 0.5F);

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(arrow);
            }
        }
    }

    private ItemStack findAmmo(EntityPlayer player)
    {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack stack = player.inventory.getStackInSlot(i);

            if (stack != null && stack.getItem() == MNCItems.NArrow)
            {
                return stack;
            }
        }

        return null;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        boolean creative = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (creative || this.findAmmo(player) != null)
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }
}
