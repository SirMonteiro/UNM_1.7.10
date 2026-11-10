package Oneric.MNC.block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.entity.EntityFireTreeFX;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockFireLeaf extends BlockLeavesBase implements IShearable
{
    int adjacentTreeBlocks[];

    public BlockFireLeaf()
    {
        super(Material.leaves, false);
        setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setStepSound(Block.soundTypeGrass);
    }

    public BlockFireLeaf(int i)
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
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getBlockColor()
    {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random rand)
    {
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && rand.nextInt(15) == 1)
        {
            double var6 = (double)((float)par2 + rand.nextFloat());
            double var8 = (double)par3 - 0.05D;
            double var10 = (double)((float)par4 + rand.nextFloat());
            par1World.spawnParticle("dripWater", var6, var8, var10, 0.0D, 0.0D, 0.0D);
        }

        if (rand.nextInt(5) == 0)
        {
            this.sparkle(par1World, par2, par3, par4);
        }
    }

    @SideOnly(Side.CLIENT)
    private void sparkle(World world, int i, int j, int k)
    {
    	Random random = new Random();
    	for (int l = 0; l < 4; l++)
        {
            double d = (double)(float)i + ((double)random.nextFloat() - 0.5D) * 10D;
            double d1 = (double)(float)j + ((double)random.nextFloat() - 0.5D) * 10D;
            double d2 = (double)(float)k + ((double)random.nextFloat() - 0.5D) * 10D;
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            EntityFireTreeFX entityFireTreeFX = new EntityFireTreeFX(world, d, d1, d2, d3, d4, d5);
            cpw.mods.fml.client.FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFireTreeFX);
        }
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block block, int meta)
    {
        byte var7 = 1;
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
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);

            if ((var6 & 8) != 0 && (var6 & 4) == 0)
            {
                byte var7 = 4;
                int var8 = var7 + 1;
                byte var9 = 32;
                int var10 = var9 * var9;
                int var11 = var9 / 2;

                if (this.adjacentTreeBlocks == null)
                {
                    this.adjacentTreeBlocks = new int[var9 * var9 * var9];
                }

                int var12;

                if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
                {
                    int var13;
                    int var14;
                    int var15;

                    for (var12 = -var7; var12 <= var7; ++var12)
                    {
                        for (var13 = -var7; var13 <= var7; ++var13)
                        {
                            for (var14 = -var7; var14 <= var7; ++var14)
                            {
                                Block block = par1World.getBlock(par2 + var12, par3 + var13, par4 + var14);

                                if (block != null && block.canSustainLeaves(par1World, par2 + var12, par3 + var13, par4 + var14))
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                                }
                                else if (block != null && block.isLeaves(par1World, par2 + var12, par3 + var13, par4 + var14))
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                                }
                                else
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                                }
                            }
                        }
                    }

                    for (var12 = 1; var12 <= 4; ++var12)
                    {
                        for (var13 = -var7; var13 <= var7; ++var13)
                        {
                            for (var14 = -var7; var14 <= var7; ++var14)
                            {
                                for (var15 = -var7; var15 <= var7; ++var15)
                                {
                                    if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1)
                                    {
                                        if (this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }
                                        if (this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
                                        }
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
                                        }
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
                                        }
                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];

                if (var12 >= 0)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & -9, 2);
                }
                else
                {
                    this.removeLeaves(par1World, par2, par3, par4);
                }
            }
        }
    }

    private void removeLeaves(World par1World, int par2, int par3, int par4)
    {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(MNCBlocks.FireSapling);
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            byte var8 = 20;

            if ((par5 & 3) == 3)
            {
                var8 = 40;
            }

            if (par1World.rand.nextInt(var8) == 0)
            {
                Item var9 = this.getItemDropped(par5, par1World.rand, par7);
                if (var9 != null)
                {
                    this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(var9, 1, this.damageDropped(par5)));
                }
            }

            if ((par5 & 3) == 0 && par1World.rand.nextInt(200) == 0)
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(Items.apple, 1, 0));
            }
        }
    }

    @Override
    public int damageDropped(int i)
    {
        return i & 3;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) 
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) 
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }

    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 2);
    }

    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}
