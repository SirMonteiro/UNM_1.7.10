package Oneric.MNC.entity;

import Oneric.MNC.common.MNCItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNetherArrow extends EntityThrowable
{
    public EntityNetherArrow(World world)
    {
        super(world);
    }

    public EntityNetherArrow(World world, EntityLivingBase shooter)
    {
        super(world, shooter);
    }

    public EntityNetherArrow(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        if (mop.entityHit != null)
        {
            float damage = 0.0F;
            if (mop.entityHit instanceof EntityLivingBase)
            {
                damage = 10.0F;
            }
            DamageSource source = this.getThrower() != null ? DamageSource.causeThrownDamage(this, this.getThrower()) : DamageSource.generic;
            mop.entityHit.attackEntityFrom(source, damage);
            mop.entityHit.setFire(8);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("largesmoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.fizz", 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));

            if (this.rand.nextInt(25) != 0)
            {
                double offX = (double)(this.worldObj.rand.nextFloat() * 0.7F) + (double)(1.0F - 0.7F) * 0.5D;
                double offY = (double)(this.worldObj.rand.nextFloat() * 0.7F) + (double)(1.0F - 0.7F) * 0.5D;
                double offZ = (double)(this.worldObj.rand.nextFloat() * 0.7F) + (double)(1.0F - 0.7F) * 0.5D;
                EntityItem item = new EntityItem(this.worldObj, this.posX + offX, this.posY + offY, this.posZ + offZ, new ItemStack(MNCItems.NArrow, 1));
                item.delayBeforeCanPickup = 10;
                this.worldObj.spawnEntityInWorld(item);
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setDouble("damage", 1.0D);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.setFire(1);
    }
}
