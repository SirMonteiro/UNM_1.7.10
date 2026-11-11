package Oneric.MNC.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.dimensions.BiomeGenCrazy;
import Oneric.MNC.world.gen.WorldGenDeadTree;
import Oneric.MNC.world.gen.WorldGenFireTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;

public class BlockFireSapling extends BlockBush
{
    public BlockFireSapling()
    {
        super(Material.plants);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setStepSound(Block.soundTypeGrass);
    }

    public BlockFireSapling(int par1)
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
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Nether;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block)
    {
        return block == Blocks.soul_sand || block == Blocks.netherrack || block == MNCBlocks.deadGrass || block == MNCBlocks.deadDirt;
    }

    @Override
    public boolean canPlaceBlockAt(World wld, int x, int y, int z)
    {
        Block b = wld.getBlock(x, y - 1, z);
        return b == Blocks.soul_sand || b == Blocks.netherrack || b == MNCBlocks.deadGrass || b == MNCBlocks.deadDirt;
    }

    @Override
    public boolean canBlockStay(World wld, int x, int y, int z)
    {
        Block b = wld.getBlock(x, y - 1, z);
        return b == Blocks.soul_sand || b == Blocks.netherrack || b == MNCBlocks.deadGrass || b == MNCBlocks.deadDirt;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 3 && par5Random.nextInt(7) == 0)
            {
                int var6 = par1World.getBlockMetadata(par2, par3, par4);

                if ((var6 & 8) == 0)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 8, 2);
                }
                else
                {
                    this.growTree(par1World, par2, par3, par4, par5Random);
                }
            }
        }
    }

    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        BiomeGenBase biome = par1World.getBiomeGenForCoords(par2, par4);
        if (biome == BiomeGenBase.hell || biome instanceof BiomeGenCrazy)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4) & 3;
            WorldGenerator var7 = new WorldGenFireTree(true);

            if (biome instanceof BiomeGenCrazy)
            {
                if (par1World.rand.nextInt(75) != 0)
                {
                    var7 = new WorldGenDeadTree(true);
                }
            }

            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);

            if (!var7.generate(par1World, par5Random, par2, par3, par4))
            {
                par1World.setBlock(par2, par3, par4, this, var6, 2);
            }
        }
    }
}
