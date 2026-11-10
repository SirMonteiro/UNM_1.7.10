package Oneric.MNC.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBiomes;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.dimensions.TeleporterCrazy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockPortalCrazy extends BlockPortal
{
	private int cooldown = 0;
	private final int cooldownMAX = 800;

    public BlockPortalCrazy()
    {
        super();
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public BlockPortalCrazy(int par1)
    {
        this();
    }

    @Override
    @SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry)
	{
		this.blockIcon = registry.registerIcon(mod_netherbr.getId() + ":" + (this.getUnlocalizedName().substring(5)));
	}

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting.getDifficultyId())
        {
            int var6;

            for (var6 = par3; !par1World.doesBlockHaveSolidTopSurface(par1World, par2, var6, par4) && var6 > 0; --var6)
            {
                ;
            }

            if (var6 > 0 && !par1World.getBlock(par2, var6 + 1, par4).isNormalCube())
            {
                Entity var7 = ItemMonsterPlacer.spawnCreature(par1World, 66, (double)par2 + 0.5D, (double)var6 + 1.1D, (double)par4 + 0.5D);

                if (var7 != null)
                {
                    var7.timeUntilPortal = var7.getPortalCooldown();
                }
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float var5;
        float var6;

        if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) != this && par1IBlockAccess.getBlock(par2 + 1, par3, par4) != this)
        {
            var5 = 0.125F;
            var6 = 0.5F;
            this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
        }
        else
        {
            var5 = 0.5F;
            var6 = 0.125F;
            this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
        }
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
    {
        byte var5 = 0;
        byte var6 = 0;

        if (par1World.getBlock(par2 - 1, par3, par4) == MNCBlocks.NetherBlock || par1World.getBlock(par2 + 1, par3, par4) == MNCBlocks.NetherBlock)
        {
            var5 = 1;
        }

        if (par1World.getBlock(par2, par3, par4 - 1) == MNCBlocks.NetherBlock || par1World.getBlock(par2, par3, par4 + 1) == MNCBlocks.NetherBlock)
        {
            var6 = 1;
        }

        if (var5 == var6)
        {
            return false;
        }
        else
        {
            if (par1World.getBlock(par2 - var5, par3, par4 - var6) == Blocks.air)
            {
                par2 -= var5;
                par4 -= var6;
            }

            int var7;
            int var8;

            for (var7 = -1; var7 <= 2; ++var7)
            {
                for (var8 = -1; var8 <= 3; ++var8)
                {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;

                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3)
                    {
                        Block var10 = par1World.getBlock(par2 + var5 * var7, par3 + var8, par4 + var6 * var7);

                        if (var9)
                        {
                            if (var10 != MNCBlocks.NetherBlock)
                            {
                                return false;
                            }
                        }
                        else if (var10 != Blocks.air && var10 != Blocks.fire)
                        {
                            return false;
                        }
                    }
                }
            }

            for (var7 = 0; var7 < 2; ++var7)
            {
                for (var8 = 0; var8 < 3; ++var8)
                {
                    par1World.setBlock(par2 + var5 * var7, par3 + var8, par4 + var6 * var7, MNCBlocks.PortalCrazy, 0, 2);
                }
            }

            return true;
        }
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block neighbor)
    {
        byte var6 = 0;
        byte var7 = 1;

        if (par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this)
        {
            var6 = 1;
            var7 = 0;
        }

        int var8;

        for (var8 = par3; par1World.getBlock(par2, var8 - 1, par4) == this; --var8)
        {
            ;
        }

        if (par1World.getBlock(par2, var8 - 1, par4) != MNCBlocks.NetherBlock)
        {
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
        }
        else
        {
            int var9;

            for (var9 = 1; var9 < 4 && par1World.getBlock(par2, var8 + var9, par4) == this; ++var9)
            {
                ;
            }

            if (var9 == 3 && par1World.getBlock(par2, var8 + var9, par4) == MNCBlocks.NetherBlock)
            {
                boolean var10 = par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this;
                boolean var11 = par1World.getBlock(par2, par3, par4 - 1) == this || par1World.getBlock(par2, par3, par4 + 1) == this;

                if (var10 && var11)
                {
                    par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
                }
                else
                {
                    if ((par1World.getBlock(par2 + var6, par3, par4 + var7) != MNCBlocks.NetherBlock || par1World.getBlock(par2 - var6, par3, par4 - var7) != this) && (par1World.getBlock(par2 - var6, par3, par4 - var7) != MNCBlocks.NetherBlock || par1World.getBlock(par2 + var6, par3, par4 + var7) != this))
                    {
                        par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
                    }
                }
            }
            else
            {
                par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par1IBlockAccess.getBlock(par2, par3, par4) == this)
        {
            return false;
        }
        else
        {
            boolean var6 = par1IBlockAccess.getBlock(par2 - 1, par3, par4) == this && par1IBlockAccess.getBlock(par2 - 2, par3, par4) != this;
            boolean var7 = par1IBlockAccess.getBlock(par2 + 1, par3, par4) == this && par1IBlockAccess.getBlock(par2 + 2, par3, par4) != this;
            boolean var8 = par1IBlockAccess.getBlock(par2, par3, par4 - 1) == this && par1IBlockAccess.getBlock(par2, par3, par4 - 2) != this;
            boolean var9 = par1IBlockAccess.getBlock(par2, par3, par4 + 1) == this && par1IBlockAccess.getBlock(par2, par3, par4 + 2) != this;
            boolean var10 = var6 || var7;
            boolean var11 = var8 || var9;
            return var10 && par5 == 4 ? true : (var10 && par5 == 5 ? true : (var11 && par5 == 2 ? true : var11 && par5 == 3));
        }
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	if (par5Entity instanceof EntityLivingBase)
    	{
    		EntityLivingBase entity = (EntityLivingBase)par5Entity;
    		entity.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 10, true));
    		entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 20, 1, true));
    	}

    	if (cooldown >= cooldownMAX)
    	{
    		if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null)
      	    {
      	        if (par5Entity instanceof EntityPlayerMP)
      	        {
      	            EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
      	            if (par5Entity.dimension != MNCBiomes.CrazyDimID)
      	            {
      	        		thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, MNCBiomes.CrazyDimID, new TeleporterCrazy(thePlayer.mcServer.worldServerForDimension(MNCBiomes.CrazyDimID)));
      	            }
      	            else
      	            {
      	        		thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterCrazy(thePlayer.mcServer.worldServerForDimension(0)));
      	            }
      	        }
      	        else
      	        {
      	            if (par5Entity.timeUntilPortal <= 0)
                    {
                        this.teleportEntity(par5Entity);
                    }
      	        }
      	    }
    		cooldown = 0;
    	}
    	else
    	{
    		cooldown++;
    	}
    }

    public void teleportEntity(Entity var0)
    {
        if (!var0.worldObj.isRemote && !var0.isDead)
        {
            int var2 = var0.dimension;
            int var8;

            if (var2 == 0)
            {
                var8 = MNCBiomes.CrazyDimID;
            }
            else if (var2 == MNCBiomes.CrazyDimID)
            {
                var8 = 0;
            }
            else
            {
                var8 = MNCBiomes.CrazyDimID;
            }

            var0.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer var3 = MinecraftServer.getServer();
            WorldServer var4 = var3.worldServerForDimension(var2);
            WorldServer var5 = var3.worldServerForDimension(var8);
            TeleporterCrazy var6 = new TeleporterCrazy(MinecraftServer.getServer().worldServerForDimension(var8));
            var0.dimension = var8;
            var0.worldObj.removeEntity(var0);
            var0.isDead = false;
            var0.worldObj.theProfiler.startSection("reposition");
            var3.getConfigurationManager().transferEntityToWorld(var0, var2, var4, var5, var6);
            var0.worldObj.theProfiler.endStartSection("reloading");
            Entity var7 = EntityList.createEntityByName(EntityList.getEntityString(var0), var5);

            if (var7 != null)
            {
                var7.copyDataFrom(var0, true);
                var7.timeUntilPortal = var0.getPortalCooldown();
                var5.spawnEntityInWorld(var7);
            }

            var0.isDead = true;
            var0.worldObj.theProfiler.endSection();
            var4.resetUpdateEntityTick();
            var5.resetUpdateEntityTick();
            var0.worldObj.theProfiler.endSection();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par5Random.nextInt(100) == 0)
        {
            par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int var6 = 0; var6 < 4; ++var6)
        {
            double var7 = (double)((float)par2 + par5Random.nextFloat());
            double var9 = (double)((float)par3 + par5Random.nextFloat());
            double var11 = (double)((float)par4 + par5Random.nextFloat());
            double var13 = 0.0D;
            double var15 = 0.0D;
            double var17 = 0.0D;
            int var19 = par5Random.nextInt(2) * 2 - 1;
            var13 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            var15 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            var17 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;

            if (par1World.getBlock(par2 - 1, par3, par4) != this && par1World.getBlock(par2 + 1, par3, par4) != this)
            {
                var7 = (double)par2 + 0.5D + 0.25D * (double)var19;
                var13 = (double)(par5Random.nextFloat() * 2.0F * (float)var19);
            }
            else
            {
                var11 = (double)par4 + 0.5D + 0.25D * (double)var19;
                var17 = (double)(par5Random.nextFloat() * 2.0F * (float)var19);
            }

            par1World.spawnParticle("portal", var7, var9, var11, var13, var15, var17);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return Item.getItemById(0);
    }
}
