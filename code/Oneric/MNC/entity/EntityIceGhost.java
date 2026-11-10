package Oneric.MNC.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Flying icy hostile mob. See EntityLostSoul for why a flying mob extends
 * EntityMob here and reimplements the no-gravity movement itself.
 */
public class EntityIceGhost extends EntityMob
{
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;

    public EntityIceGhost(World world)
    {
        super(world);
        this.setSize(0.8F, 1.8F);
        this.experienceValue = 8;

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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    /** No fall damage for a flying mob. */
    @Override
    protected void fall(float distance) {}

    @Override
    protected void updateFallState(double distanceFallenThisTick, boolean onGroundIn) {}

    @Override
    public boolean isOnLadder()
    {
        return false;
    }

    /** Flight movement without the gravity term (EntityFlying behaviour). */
    @Override
    public void moveEntityWithHeading(float strafe, float forward)
    {
        if (this.isInWater())
        {
            this.moveFlying(strafe, forward, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.handleLavaMovement())
        {
            this.moveFlying(strafe, forward, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float friction = 0.91F;

            if (this.onGround)
            {
                friction = this.worldObj.getBlock(MathHelper.floor_double(this.posX),
                        MathHelper.floor_double(this.boundingBox.minY) - 1,
                        MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
            }

            float groundFactor = 0.16277136F / (friction * friction * friction);
            this.moveFlying(strafe, forward, this.onGround ? 0.1F * groundFactor : 0.02F);
            friction = 0.91F;

            if (this.onGround)
            {
                friction = this.worldObj.getBlock(MathHelper.floor_double(this.posX),
                        MathHelper.floor_double(this.boundingBox.minY) - 1,
                        MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)friction;
            this.motionY *= (double)friction;
            this.motionZ *= (double)friction;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double dx = this.posX - this.prevPosX;
        double dz = this.posZ - this.prevPosZ;
        float swing = MathHelper.sqrt_double(dx * dx + dz * dz) * 4.0F;

        if (swing > 1.0F)
        {
            swing = 1.0F;
        }

        this.limbSwingAmount += (swing - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    /** Hover toward targets and leave a cold particle trail. */
    @Override
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            --this.heightOffsetUpdateTime;

            if (this.heightOffsetUpdateTime <= 0)
            {
                this.heightOffsetUpdateTime = 100;
                this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
            }

            if (this.getAttackTarget() != null
                    && this.getAttackTarget().posY + (double)this.getAttackTarget().getEyeHeight()
                    > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset)
            {
                this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            }
        }

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        if (this.worldObj.isRemote && this.rand.nextInt(4) == 0)
        {
            this.worldObj.spawnParticle("snowshovel",
                    this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                    this.posY + this.rand.nextDouble() * (double)this.height,
                    this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                    0.0D, 0.0D, 0.0D);
        }

        super.onLivingUpdate();
    }
}
