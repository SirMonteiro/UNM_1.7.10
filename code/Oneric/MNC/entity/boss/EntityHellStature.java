package Oneric.MNC.entity.boss;

import Oneric.MNC.common.BossNameGen;
import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.entity.EnumBoss;
import Oneric.MNC.entity.IBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Mini-boss statue mob. Was previously an entirely commented-out 1.6.4
 * remnant; revived here for 1.7.10.
 *
 * Port notes (1.6.4 -> 1.7.10):
 *  - this.health / this.entityId / this.moveSpeed no longer exist. Use
 *    getHealth(), getEntityId() and the movementSpeed attribute instead.
 *  - getAttackStrength(Entity) / getMaxHealth() overrides are gone; attack
 *    damage is supplied through SharedMonsterAttributes.attackDamage.
 *  - getBossType() returns EnumBoss (not Enum&lt;EnumBoss&gt;).
 */
public class EntityHellStature extends EntityMob implements IBoss
{
    private String bossName;

    private int maxBossHealth;

    public EntityHellStature(World world)
    {
        super(world);

        this.bossName = BossNameGen.generate();
        this.maxBossHealth = 1000;
        this.experienceValue = 50;

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        // NOTE: applyEntityAttributes() is invoked from super(world), before
        // the constructor body assigns maxBossHealth, so use the literal here.
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    // ------------------------------------------------------------------
    // IBoss
    // ------------------------------------------------------------------

    @Override
    public int getBossHP()
    {
        return (int)this.getHealth();
    }

    @Override
    public int getBossMaxHP()
    {
        return this.maxBossHealth;
    }

    @Override
    public String getBossName()
    {
        return this.bossName;
    }

    @Override
    public EnumBoss getBossType()
    {
        return EnumBoss.MiniBoss;
    }

    @Override
    public int getBossEntityID()
    {
        return this.getEntityId();
    }

    @Override
    public Entity getBossEntity()
    {
        return this;
    }

    @Override
    public boolean isCurrentBoss()
    {
        return this.equals(mod_netherbr.currentBoss);
    }
}
