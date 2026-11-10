package Oneric.MNC.block;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MNCBlockStationary extends BlockLiquid
{
    public MNCBlockStationary(Material material)
    {
        super(material);
        this.setTickRandomly(true);
    }

    public MNCBlockStationary(int id, Material material)
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
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        super.onNeighborBlockChange(world, x, y, z, neighbor);
        if (world.getBlock(x, y, z) == this)
        {
            int meta = world.getBlockMetadata(x, y, z);
            Block flowing = MNCBlocks.SchleimFliess != null ? MNCBlocks.SchleimFliess : this;
            world.setBlock(x, y, z, flowing, meta, 2);
            world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            world.scheduleBlockUpdate(x, y, z, flowing, this.tickRate(world));
        }
    }
}
