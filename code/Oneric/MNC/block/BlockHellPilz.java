package Oneric.MNC.block;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.MNCItems;
import Oneric.MNC.common.mod_netherbr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockHellPilz extends BlockBush
{
    @SideOnly(Side.CLIENT)
    private IIcon[] stageTextures;

    public BlockHellPilz()
    {
        super(Material.plants);
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    public BlockHellPilz(int id)
    {
        this();
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Nether;
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground)
    {
        return ground == Blocks.netherrack || ground == MNCBlocks.Blackstone;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        Block ground = world.getBlock(x, y - 1, z);
        return ground == Blocks.netherrack || ground == MNCBlocks.Blackstone;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        Block ground = world.getBlock(x, y - 1, z);
        return ground == Blocks.netherrack || ground == MNCBlocks.Blackstone;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);

        if (world.getBlockLightValue(x, y + 1, z) >= 2)
        {
            int stage = world.getBlockMetadata(x, y, z);

            if (stage < 7)
            {
                float rate = this.getGrowthRate(world, x, y, z);

                if (rand.nextInt((int)(25.0F / rate) + 1) == 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, stage + 1, 2);
                }
            }
        }
    }

    public void fertilize(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta < 6)
        {
            if (meta < 4)
            {
                if (world.rand.nextInt(meta + 1) == 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
                    world.spawnParticle("largeexplode", x + 0.5D, y + 0.2D, z + 0.5D, 0.0D, 0.0D, 0.0D);
                }
                else
                {
                    world.spawnParticle("mobSpell", x + 0.5D, y + 0.2D, z + 0.5D, 0.0D, 0.0D, 0.0D);
                }
            }
            else
            {
                if (world.rand.nextInt(meta * 2) == 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
                    world.spawnParticle("largeexplode", x + 0.5D, y + 0.2D, z + 0.5D, 0.0D, 0.0D, 0.0D);
                }
                else
                {
                    world.spawnParticle("mobSpell", x + 0.5D, y + 0.2D, z + 0.5D, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        else
        {
            if (world.rand.nextInt(100) == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, 7, 2);
                world.spawnParticle("largeexplode", x + 0.5D, y + 0.2D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                world.spawnParticle("mobSpell", x + 0.5D, y + 0.2D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private float getGrowthRate(World world, int x, int y, int z)
    {
        float f = 1.0F;
        Block xm = world.getBlock(x - 1, y, z);
        Block xp = world.getBlock(x + 1, y, z);
        Block zm = world.getBlock(x, y, z - 1);
        Block zp = world.getBlock(x, y, z + 1);

        Block xzm = world.getBlock(x - 1, y, z - 1);
        Block xzp = world.getBlock(x + 1, y, z + 1);
        Block xpm = world.getBlock(x + 1, y, z - 1);
        Block xmp = world.getBlock(x - 1, y, z + 1);

        boolean flagX = xm == this || xp == this;
        boolean flagZ = zm == this || zp == this;
        boolean flagDiag = xzm == this || xzp == this || xpm == this || xmp == this;

        for (int ix = x - 1; ix <= x + 1; ++ix)
        {
            for (int iz = z - 1; iz <= z + 1; ++iz)
            {
                Block ground = world.getBlock(ix, y - 1, iz);
                float rate = 0.0F;

                if (ground == Blocks.netherrack || ground == MNCBlocks.Blackstone)
                {
                    rate = 1.0F;
                    if (world.getBlockMetadata(ix, y - 1, iz) > 0)
                    {
                        rate = 3.0F;
                    }
                }

                if (ix != x || iz != z)
                {
                    rate /= 4.0F;
                }

                f += rate;
            }
        }

        if (flagDiag || (flagX && flagZ))
        {
            f /= 2.0F;
        }

        return f;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        if (meta >= 0 && meta <= 2)
        {
            return this.stageTextures[0];
        }
        if (meta >= 3 && meta <= 6)
        {
            return this.stageTextures[1];
        }
        if (meta == 7)
        {
            return this.stageTextures[2];
        }
        return this.stageTextures[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry)
    {
        this.stageTextures = new IIcon[3];
        for (int i = 0; i < this.stageTextures.length; ++i)
        {
            int stage = i + 1;
            this.stageTextures[i] = registry.registerIcon(mod_netherbr.getId() + ":mnc_HellPilz-" + stage);
        }
    }

    public IIcon getTexture(int i)
    {
        return this.stageTextures[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.getBlockTextureFromSideAndMetadata(side, meta);
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(world, x, y, z, meta, chance, 0);

        if (world.isRemote)
        {
            return;
        }

        int count = 3 + fortune;
        for (int j = 0; j < count; ++j)
        {
            if (world.rand.nextInt(15) <= meta)
            {
                float f = 0.7F;
                float f1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5F;
                float f2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5F;
                float f3 = world.rand.nextFloat() * f + (1.0F - f) * 0.5F;
                EntityItem item = new EntityItem(world, (double)x + f1, (double)y + f2, (double)z + f3, new ItemStack(MNCItems.HellPilz_Samen));
                item.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(item);
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        if (meta == 7)
        {
            return MNCItems.HellPilz;
        }
        return null;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 1 + rand.nextInt(2);
    }

    @Override
    public Item getItem(World world, int x, int y, int z)
    {
        return MNCItems.HellPilz_Samen;
    }
}
