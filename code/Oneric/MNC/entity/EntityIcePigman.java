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
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Biped hostile mob that wields the mod's glowstone sword. Extends EntityMob
 * so the RenderMNCBiped (a RenderBiped-style renderer) can render it.
 */
public class EntityIcePigman extends EntityMob
{
    public EntityIcePigman(World world)
    {
        super(world);
        this.setSize(0.6F, 1.8F);
        this.experienceValue = 10;

        this.setCurrentItemOrArmor(0, new ItemStack(MNCItems.GlowSword));

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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.28D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    // 1.6.4 sounds (vanilla zombie pigman, as in the original mod).
    @Override
    protected String getLivingSound()
    {
        return "mob.zombiepig.zpig";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.zombiepig.zpighurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.zombiepig.zpigdeath";
    }

    // 1.6.4 drops: gold nuggets + (when not burning) packed ice.
    @Override
    protected void dropFewItems(boolean recentlyHit, int looting)
    {
        int count = this.rand.nextInt(2 + looting);

        for (int i = 0; i < count; ++i)
        {
            this.dropItem(Items.gold_nugget, 1);
        }

        if (!this.isBurning())
        {
            count = this.rand.nextInt(2 + looting);

            for (int i = 0; i < count; ++i)
            {
                this.dropItem(Items.snowball, 1);
            }
        }
    }

    @Override
    protected Item getDropItem()
    {
        return Items.gold_nugget;
    }
}
