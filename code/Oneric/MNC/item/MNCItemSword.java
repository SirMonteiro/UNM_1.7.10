package Oneric.MNC.item;

import java.util.Random;
import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.packets.MNCNetwork;
import Oneric.MNC.packets.ThunderMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class MNCItemSword extends ItemSword
{
    private final boolean thunder;

    public MNCItemSword(Item.ToolMaterial material, boolean thunder)
    {
        super(material);
        this.thunder = thunder;
    }

    public MNCItemSword(int id, Item.ToolMaterial material, boolean thunder)
    {
        this(material, thunder);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registry)
    {
        this.itemIcon = registry.registerIcon(mod_netherbr.getId() + ":" + this.getUnlocalizedName().substring(5));
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if (this.thunder)
        {
            target.setFire(5);
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

        if (this.thunder)
        {
            MovingObjectPosition mop = player.rayTrace(200.0D, 1.0F);

            if (mop != null)
            {
                double blockHitX = mop.blockX;
                double blockHitY = mop.blockY;
                double blockHitZ = mop.blockZ;

                Random rand = world.rand;
                world.playSound(blockHitX + 0.5D, blockHitY + 0.5D, blockHitZ + 0.5D,
                        "ambient.weather.thunder", 0.5F, (rand.nextFloat() * 0.25F) + 0.6F, false);
                world.playSound(blockHitX + 0.5D, blockHitY + 0.5D, blockHitZ + 0.5D,
                        "random.explode", 2.0F, 0.5F + (rand.nextFloat() * 0.2F), false);

                if (world.isRemote)
                {
                    MNCNetwork.INSTANCE.sendToServer(new ThunderMessage(blockHitX, blockHitY, blockHitZ));
                }
                else
                {
                    world.addWeatherEffect(new EntityLightningBolt(world, blockHitX, blockHitY, blockHitZ));
                }

                world.setRainStrength(1.0F);
                stack.damageItem(1, player);
            }
        }

        return stack;
    }
}
