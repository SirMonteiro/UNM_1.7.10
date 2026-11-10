package Oneric.MNC.block;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MNCBlockFlowing extends BlockLiquid
{
    public MNCBlockFlowing(Material material)
    {
        super(material);
    }

    public MNCBlockFlowing(int id, Material material)
    {
        this(material);
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z)
    {
        return this.getMaterial() != Material.lava;
    }

    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return 0x00ee11;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int meta = world.getBlockMetadata(x, y, z);
        Block still = MNCBlocks.SchleimStill != null ? MNCBlocks.SchleimStill : this;
        world.setBlock(x, y, z, still, meta, 2);
    }
}
