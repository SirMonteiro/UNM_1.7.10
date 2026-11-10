package Oneric.MNC.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

/**
 * The Nether Bow's projectile.
 *
 * All damage / knockback / critical state lives in EntityArrow in 1.7.10 and
 * the mod's ItemNetherBow calls those setters directly. The delegating
 * overrides below document (and pin) the real 1.7.10 method names on this
 * class; setFire(int) is inherited from net.minecraft.entity.Entity.
 */
public class EntityNetherArrow extends EntityArrow
{
    /** Required by Forge when reconstructing the entity from a spawn packet. */
    public EntityNetherArrow(World world)
    {
        super(world);
    }

    public EntityNetherArrow(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityNetherArrow(World world, EntityLivingBase shooter, float speed)
    {
        super(world, shooter, speed);
    }

    @Override
    public void setDamage(double damage)
    {
        super.setDamage(damage);
    }

    @Override
    public double getDamage()
    {
        return super.getDamage();
    }

    @Override
    public void setKnockbackStrength(int knockbackStrength)
    {
        super.setKnockbackStrength(knockbackStrength);
    }

    @Override
    public void setIsCritical(boolean critical)
    {
        super.setIsCritical(critical);
    }

    @Override
    public void setFire(int seconds)
    {
        super.setFire(seconds);
    }
}
