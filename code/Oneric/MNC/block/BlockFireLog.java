package Oneric.MNC.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import Oneric.MNC.common.mod_netherbr;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFireLog extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon tree_top;

    public BlockFireLog()
    {
        super(Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(Block.soundTypeWood);
    }

    public BlockFireLog(int par1)
    {
        this();
    }

    @Override
    public int getRenderType()
    {
        return 31;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block block, int meta)
    {
        byte var7 = 4;
        int var8 = var7 + 1;

        if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
        {
            for (int var9 = -var7; var9 <= var7; ++var9)
            {
                for (int var10 = -var7; var10 <= var7; ++var10)
                {
                    for (int var11 = -var7; var11 <= var7; ++var11)
                    {
                        Block var12 = par1World.getBlock(par2 + var9, par3 + var10, par4 + var11);

                        if (var12 != null)
                        {
                            var12.beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
                        }
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int i, int j)
    {
    	if (i == 0 || i == 1)
    	{
    		return this.tree_top;
    	}
    	else
		{
			return this.blockIcon;
		}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.tree_top = par1IconRegister.registerIcon(mod_netherbr.getId() + ":mnc_FireLogTop");
        this.blockIcon = par1IconRegister.registerIcon(mod_netherbr.getId() + ":mnc_FireLogSide");
    }

    @Override
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }

    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 1, limitToValidMetadata(par1));
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
    	Random rand = new Random();
    	if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == MNCItems.NetherAxe)
    	{
             if (rand.nextInt(100) == 0)
             {
            	 dropBlockAsItem(world, x, y, z, new ItemStack(MNCItems.FireGem, 1, 0));
             }
             else
             {
            	 switch (world.rand.nextInt(20))
               	 {
               	 case 0:
               		dropBlockAsItem(world, x, y, z, new ItemStack(Items.magma_cream, 1, 0));
               		break;
               	 case 1:
               		dropBlockAsItem(world, x, y, z, new ItemStack(Items.blaze_powder, 1, 0));
               		break;
               	 }
             }
    	}
    	super.harvestBlock(world, player, x, y, z, meta);
    }
}
