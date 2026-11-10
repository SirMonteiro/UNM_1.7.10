package Oneric.MNC.entity.boss;

import Oneric.MNC.common.MNCItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** The main boss of the mod. A tough biped hostile mob. */
public class EntityDaemonLord extends EntityMob
{
    public EntityDaemonLord(World world)
    {
        super(world);
        this.setSize(1.0F, 3.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 100;

        this.setCurrentItemOrArmor(0, new ItemStack(MNCItems.NetherSword));

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.1D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    /** Bosses should not quietly vanish. */
    @Override
    protected boolean canDespawn()
    {
        return false;
    }
}
