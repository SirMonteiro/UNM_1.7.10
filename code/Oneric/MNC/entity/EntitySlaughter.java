package Oneric.MNC.entity;

import Oneric.MNC.common.MNCItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
 * Hostile melee mob. In 1.6.4 this extended the (obfuscated) EntityMob;
 * ported straight to net.minecraft.entity.monster.EntityMob for 1.7.10.
 */
public class EntitySlaughter extends EntityMob
{
    public EntitySlaughter(World world)
    {
        super(world);
        this.setSize(0.9F, 2.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 10;

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    // 1.6.4 sounds (vanilla enderman, as in the original mod).
    @Override
    protected String getLivingSound()
    {
        return "mob.endermen.stare";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.endermen.hit";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.endermen.scream";
    }

    @Override
    protected float getSoundVolume()
    {
        return 2.0F;
    }

    // 1.6.4 drops: 1/5 chance of Diablo, plus 2 "A very strange thing".
    @Override
    protected void dropFewItems(boolean recentlyHit, int looting)
    {
        if (this.rand.nextInt(5) == 1)
        {
            this.dropItem(MNCItems.diablo, 1);
        }

        this.dropItem(MNCItems.strange, 2);
    }

    @Override
    protected Item getDropItem()
    {
        return Items.rotten_flesh;
    }

    // 1.6.4 ambient particles: smoke / reddust (red floor particles) / bubbles.
    @Override
    public void onLivingUpdate()
    {
        if (this.worldObj.isRemote)
        {
            for (int i = 0; i < 2; ++i)
            {
                this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                this.worldObj.spawnParticle("reddust", this.posX, this.posY + 1.0D, this.posZ, 0.0D, 0.0D, 0.0D);
                this.worldObj.spawnParticle("bubbles", this.posX, this.posY + 1.0D, this.posZ, 0.0D, 0.0D, 0.0D);
            }

            this.worldObj.spawnParticle("reddust", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        super.onLivingUpdate();
    }
}
