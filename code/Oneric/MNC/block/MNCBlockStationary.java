package Oneric.MNC.block;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
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
        return this.blockMaterial != Material.lava;
    }

    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return 60945;
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

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (this.blockMaterial == Material.lava)
        {
            int count = rand.nextInt(3);

            for (int i = 0; i < count; ++i)
            {
                int nx = x + rand.nextInt(3) - 1;
                int ny = y + 1;
                int nz = z + rand.nextInt(3) - 1;

                if (world.isAirBlock(nx, ny, nz))
                {
                    if (this.isFlammable(world, nx - 1, ny, nz) || this.isFlammable(world, nx + 1, ny, nz)
                            || this.isFlammable(world, nx, ny, nz - 1) || this.isFlammable(world, nx, ny, nz + 1)
                            || this.isFlammable(world, nx, ny - 1, nz) || this.isFlammable(world, nx, ny + 1, nz))
                    {
                        world.setBlock(nx, ny, nz, Blocks.fire);
                        return;
                    }
                }
                else if (world.getBlock(nx, ny, nz).getMaterial().blocksMovement())
                {
                    return;
                }
            }

            if (count == 0)
            {
                for (int i = 0; i < 3; ++i)
                {
                    int fx = (x + rand.nextInt(3)) - 1;
                    int fz = (z + rand.nextInt(3)) - 1;

                    if (world.isAirBlock(fx, y + 1, fz) && this.isFlammable(world, fx, y, fz))
                    {
                        world.setBlock(fx, y + 1, fz, Blocks.fire);
                    }
                }
            }
        }
    }

    private boolean isFlammable(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z).getMaterial().getCanBurn();
    }
}
