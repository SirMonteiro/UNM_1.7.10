package Oneric.MNC.entity.boss;

import Oneric.MNC.common.BossNameGen;
import Oneric.MNC.common.MNCAchievements;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.entity.EntityFeuerBall;
import Oneric.MNC.entity.EnumBoss;
import Oneric.MNC.entity.IBoss;
import Oneric.MNC.entity.effect.MNCEntityLightningBolt;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * The main boss of the mod. A tough biped hostile mob located in Nether Dungeons.
 * Authentic port of 1.6.4 EntityDaemonLord to 1.7.10.
 */
public class EntityDaemonLord extends EntityMob implements IBoss
{
    private String bossName;
    private static int maxBossHealth = 700;
    public boolean boss;
    public boolean awake;
    public boolean critical;
    public int dungeonX;
    public int dungeonY;
    public int dungeonZ;
    public int flameCount;
    public int thunderboltCooldown;

    public EntityDaemonLord(World par1World)
    {
        super(par1World);
        this.bossName = BossNameGen.generate();
        this.boss = false;
        this.isImmuneToFire = true;
        this.setSize(1.0F, 3.0F);
        this.experienceValue = 100;

        this.setCurrentItemOrArmor(0, new ItemStack(MNCItems.NetherSword));

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.1D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    public EntityDaemonLord(World par1World, double x, double y, double z)
    {
        this(par1World);
        this.setPosition(x, y, z);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(700.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
    }

    public void setCritical()
    {
        if (!this.critical)
        {
            this.worldObj.playSoundAtEntity(this, "random.explode", 1.0F, 1.0F);
            this.critical = true;
        }
    }

    @Override
    public void onUpdate()
    {
        if (this.awake)
        {
            super.onUpdate();
            if (this.getBossHP() <= 200)
            {
                this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
                this.setCritical();
            }
        }
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (this.awake)
        {
            this.flameCount++;
            if (this.flameCount >= 50 + (this.getBossHP() / 2))
            {
                this.summonFire(1);
                this.flameCount = 0;
            }
            this.thunderboltCooldown++;
            EntityLivingBase target = this.getAttackTarget();
            if (this.thunderboltCooldown >= 100 + (this.getBossHP() / 2) && target != null)
            {
                this.worldObj.spawnEntityInWorld(new MNCEntityLightningBolt(this.worldObj, target.posX + this.randomInaccuracy(), target.posY + this.randomInaccuracy(), target.posZ + this.randomInaccuracy(), this));
                this.thunderboltCooldown = 0;
            }
        }
    }

    private double randomInaccuracy()
    {
        double i = (double)this.rand.nextInt(3) + this.rand.nextDouble() + 0.1D;
        return i + (2.0D * i * (double)(this.rand.nextInt(2) * (-1)));
    }

    @Override
    protected void fall(float distance)
    {
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float amount)
    {
        if (damageSource.getEntity() instanceof EntityPlayer)
        {
            if (this.boss)
            {
                super.attackEntityFrom(damageSource, amount);
            }
            else
            {
                mod_netherbr.currentBoss = this;
                this.boss = true;
                this.awake = true;
                this.setDungeon((int)this.posX, (int)this.posY, (int)this.posZ);
                ((EntityPlayer)damageSource.getEntity()).addChatMessage(new ChatComponentText("Foolish human, you'll die !"));
                this.setDoor(MNCBlocks.dungeonStone, 1);
                this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(700.0D);
            }

            boolean flag = super.attackEntityFrom(damageSource, amount);
            if (flag && this.getBossHP() <= 0)
            {
                this.rand.nextInt(3);
                this.isDead = true;
                if (this.boss)
                {
                    this.isDead = false;
                    mod_netherbr.currentBoss = null;
                    if (damageSource.getEntity() instanceof EntityPlayer)
                    {
                        EntityPlayer player = (EntityPlayer)damageSource.getEntity();
                        this.setDoor(Blocks.air, 0);
                        this.freeDungeonBlocks(this.dungeonX, this.dungeonY - 1, this.dungeonZ);
                        this.freeDungeonBlocks(this.dungeonX, this.dungeonY - 2, this.dungeonZ);
                        if (mod_netherbr.achievementsEnabled)
                        {
                            mod_netherbr.proxy.triggerAchievement(MNCAchievements.defeatDaemonLord, player);
                        }
                    }
                    this.boss = false;
                }
                this.setDead();
                mod_netherbr.currentBoss = null;
            }
            return flag;
        }
        return false;
    }

    public void summonFire(int anzahl)
    {
        for (int i = 0; i < anzahl; i++)
        {
            EntityFeuerBall fb = new EntityFeuerBall(this.worldObj, this.posX, this.posY, this.posZ);
            this.worldObj.spawnEntityInWorld(fb);
            System.out.println("--------------FEUERBALL!!--------------");
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setString("name", this.bossName);
        nbt.setBoolean("awake", this.awake);
        nbt.setBoolean("critical", this.critical);
        nbt.setInteger("dungeonX", this.dungeonX);
        nbt.setInteger("dungeonY", this.dungeonY);
        nbt.setInteger("dungeonZ", this.dungeonZ);
        nbt.setBoolean("Boss", this.boss);
        nbt.setBoolean("isCurrentBoss", this.isCurrentBoss());
        super.writeEntityToNBT(nbt);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.bossName = nbt.getString("name");
        this.awake = nbt.getBoolean("awake");
        this.critical = nbt.getBoolean("critical");
        this.dungeonX = nbt.getInteger("dungeonX");
        this.dungeonY = nbt.getInteger("dungeonY");
        this.dungeonZ = nbt.getInteger("dungeonZ");
        this.boss = nbt.getBoolean("Boss");
        if (nbt.getBoolean("isCurrentBoss"))
        {
            mod_netherbr.currentBoss = this;
        }
        super.readEntityFromNBT(nbt);
    }

    public void setDungeon(int x, int y, int z)
    {
        this.dungeonX = x;
        this.dungeonY = y;
        this.dungeonZ = z;
        System.out.println(x + "   " + y + "   " + z);
    }

    public void setDoor(Block block, int blockMeta)
    {
        this.worldObj.setBlock(this.dungeonX - 6, this.dungeonY, this.dungeonZ, block, blockMeta, 2);
        this.worldObj.setBlock(this.dungeonX + 6, this.dungeonY, this.dungeonZ, block, blockMeta, 2);
        this.worldObj.setBlock(this.dungeonX, this.dungeonY, this.dungeonZ - 6, block, blockMeta, 2);
        this.worldObj.setBlock(this.dungeonX, this.dungeonY, this.dungeonZ + 6, block, blockMeta, 2);
    }

    private void freeDungeonBlocks(int x, int y, int z)
    {
        if (this.worldObj.getBlock(x, y, z) == MNCBlocks.dungeonStone && this.worldObj.getBlockMetadata(x, y, z) == 1)
        {
            this.worldObj.setBlock(x, y, z, Blocks.air, 0, 2);
            freeDungeonBlocks(x + 1, y, z);
            freeDungeonBlocks(x - 1, y, z);
            freeDungeonBlocks(x, y + 1, z);
            freeDungeonBlocks(x, y - 1, z);
            freeDungeonBlocks(x, y, z + 1);
            freeDungeonBlocks(x, y, z - 1);
        }
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void knockBack(Entity par1Entity, float par2, double par3, double par5)
    {
        if (this.critical)
        {
            super.knockBack(par1Entity, par2, par3 / 2.0D, par5 / 2.0D);
        }
    }

    @Override
    public int getBossHP()
    {
        return (int)this.getHealth();
    }

    @Override
    public int getBossMaxHP()
    {
        return maxBossHealth;
    }

    @Override
    public String getBossName()
    {
        return this.bossName + ", the Daemon Lord";
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
        return this.equals(mod_netherbr.currentBoss) && this.boss;
    }
}
