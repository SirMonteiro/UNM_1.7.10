package Oneric.MNC.block;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MNCBlockFlowing extends BlockLiquid
{
    private int numAdjacentSources;
    private final boolean[] isOptimalFlowDirection = new boolean[4];
    private final int[] flowCost = new int[4];

    public MNCBlockFlowing(Material material)
    {
        super(material);
    }

    public MNCBlockFlowing(int id, Material material)
    {
        this(material);
    }

    private void updateFlow(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        Block still = MNCBlocks.SchleimStill != null ? MNCBlocks.SchleimStill : this;
        world.setBlock(x, y, z, still, meta, 2);
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
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int decay = this.func_149804_e(world, x, y, z);
        byte step = 1;

        if (this.blockMaterial == Material.lava && !world.provider.isHellWorld)
        {
            step = 2;
        }

        boolean flag = true;
        int tickRate = this.tickRate(world);

        if (decay > 0)
        {
            byte initialMin = -100;
            this.numAdjacentSources = 0;
            int smallest = this.getSmallestFlowDecay(world, x - 1, y, z, initialMin);
            smallest = this.getSmallestFlowDecay(world, x + 1, y, z, smallest);
            smallest = this.getSmallestFlowDecay(world, x, y, z - 1, smallest);
            smallest = this.getSmallestFlowDecay(world, x, y, z + 1, smallest);
            int newDecay = smallest + step;

            if (newDecay >= 8 || smallest < 0)
            {
                newDecay = -1;
            }

            if (this.func_149804_e(world, x, y + 1, z) >= 0)
            {
                int aboveDecay = this.func_149804_e(world, x, y + 1, z);

                if (aboveDecay >= 8)
                {
                    newDecay = aboveDecay;
                }
                else
                {
                    newDecay = aboveDecay + 8;
                }
            }

            if (this.numAdjacentSources >= 2 && this.blockMaterial == Material.water)
            {
                if (world.getBlock(x, y - 1, z).getMaterial().isSolid())
                {
                    newDecay = 0;
                }
                else if (world.getBlock(x, y - 1, z).getMaterial() == this.blockMaterial && world.getBlockMetadata(x, y - 1, z) == 0)
                {
                    newDecay = 0;
                }
            }

            if (this.blockMaterial == Material.lava && decay < 8 && newDecay < 8 && newDecay > decay && rand.nextInt(4) != 0)
            {
                newDecay = decay;
                flag = false;
            }

            if (newDecay == decay)
            {
                if (flag)
                {
                    this.updateFlow(world, x, y, z);
                }
            }
            else
            {
                decay = newDecay;

                if (newDecay < 0)
                {
                    world.setBlockToAir(x, y, z);
                }
                else
                {
                    world.setBlockMetadataWithNotify(x, y, z, newDecay, 2);
                    world.scheduleBlockUpdate(x, y, z, this, tickRate);
                    world.notifyBlocksOfNeighborChange(x, y, z, this);
                }
            }
        }
        else
        {
            this.updateFlow(world, x, y, z);
        }

        if (this.liquidCanDisplaceBlock(world, x, y - 1, z))
        {
            if (this.blockMaterial == Material.lava && world.getBlock(x, y - 1, z).getMaterial() == Material.water)
            {
                world.setBlock(x, y - 1, z, Blocks.stone);
                this.func_149799_m(world, x, y - 1, z);
                return;
            }

            if (decay >= 8)
            {
                this.flowIntoBlock(world, x, y - 1, z, decay);
            }
            else
            {
                this.flowIntoBlock(world, x, y - 1, z, decay + 8);
            }
        }
        else if (decay >= 0 && (decay == 0 || this.blockBlocksFlow(world, x, y - 1, z)))
        {
            boolean[] optimalDirs = this.getOptimalFlowDirections(world, x, y, z);
            int spreadDecay = decay + step;

            if (decay >= 8)
            {
                spreadDecay = 1;
            }

            if (spreadDecay >= 8)
            {
                return;
            }

            if (optimalDirs[0])
            {
                this.flowIntoBlock(world, x - 1, y, z, spreadDecay);
            }

            if (optimalDirs[1])
            {
                this.flowIntoBlock(world, x + 1, y, z, spreadDecay);
            }

            if (optimalDirs[2])
            {
                this.flowIntoBlock(world, x, y, z - 1, spreadDecay);
            }

            if (optimalDirs[3])
            {
                this.flowIntoBlock(world, x, y, z + 1, spreadDecay);
            }
        }
    }

    private void flowIntoBlock(World world, int x, int y, int z, int meta)
    {
        if (this.liquidCanDisplaceBlock(world, x, y, z))
        {
            Block block = world.getBlock(x, y, z);

            if (block != Blocks.air)
            {
                if (this.blockMaterial == Material.lava)
                {
                    this.func_149799_m(world, x, y, z);
                }
                else
                {
                    block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
                }
            }

            world.setBlock(x, y, z, this, meta, 2);
        }
    }

    private int calculateFlowCost(World world, int x, int y, int z, int recurse, int fromDir)
    {
        int minCost = 1000;

        for (int dir = 0; dir < 4; ++dir)
        {
            if ((dir != 0 || fromDir != 1) && (dir != 1 || fromDir != 0) && (dir != 2 || fromDir != 3) && (dir != 3 || fromDir != 2))
            {
                int nx = x;
                int nz = z;

                if (dir == 0)
                {
                    nx = x - 1;
                }

                if (dir == 1)
                {
                    ++nx;
                }

                if (dir == 2)
                {
                    nz = z - 1;
                }

                if (dir == 3)
                {
                    ++nz;
                }

                if (!this.blockBlocksFlow(world, nx, y, nz) && (world.getBlock(nx, y, nz).getMaterial() != this.blockMaterial || world.getBlockMetadata(nx, y, nz) != 0))
                {
                    if (!this.blockBlocksFlow(world, nx, y - 1, nz))
                    {
                        return recurse;
                    }

                    if (recurse < 4)
                    {
                        int cost = this.calculateFlowCost(world, nx, y, nz, recurse + 1, dir);

                        if (cost < minCost)
                        {
                            minCost = cost;
                        }
                    }
                }
            }
        }

        return minCost;
    }

    private boolean[] getOptimalFlowDirections(World world, int x, int y, int z)
    {
        for (int dir = 0; dir < 4; ++dir)
        {
            this.flowCost[dir] = 1000;
            int nx = x;
            int nz = z;

            if (dir == 0)
            {
                nx = x - 1;
            }

            if (dir == 1)
            {
                ++nx;
            }

            if (dir == 2)
            {
                nz = z - 1;
            }

            if (dir == 3)
            {
                ++nz;
            }

            if (!this.blockBlocksFlow(world, nx, y, nz) && (world.getBlock(nx, y, nz).getMaterial() != this.blockMaterial || world.getBlockMetadata(nx, y, nz) != 0))
            {
                if (this.blockBlocksFlow(world, nx, y - 1, nz))
                {
                    this.flowCost[dir] = this.calculateFlowCost(world, nx, y, nz, 1, dir);
                }
                else
                {
                    this.flowCost[dir] = 0;
                }
            }
        }

        int lowestCost = this.flowCost[0];

        for (int i = 1; i < 4; ++i)
        {
            if (this.flowCost[i] < lowestCost)
            {
                lowestCost = this.flowCost[i];
            }
        }

        for (int i = 0; i < 4; ++i)
        {
            this.isOptimalFlowDirection[i] = this.flowCost[i] == lowestCost;
        }

        return this.isOptimalFlowDirection;
    }

    private boolean blockBlocksFlow(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        return block != Blocks.wooden_door && block != Blocks.iron_door && block != Blocks.standing_sign && block != Blocks.wall_sign && block != Blocks.reeds ? (block.getMaterial() == Material.air ? false : (block.getMaterial() == Material.portal ? true : block.getMaterial().blocksMovement())) : true;
    }

    protected int getSmallestFlowDecay(World world, int x, int y, int z, int currentDecay)
    {
        int decay = this.func_149804_e(world, x, y, z);

        if (decay < 0)
        {
            return currentDecay;
        }
        else
        {
            if (decay == 0)
            {
                ++this.numAdjacentSources;
            }

            if (decay >= 8)
            {
                decay = 0;
            }

            return currentDecay >= 0 && decay >= currentDecay ? currentDecay : decay;
        }
    }

    private boolean liquidCanDisplaceBlock(World world, int x, int y, int z)
    {
        Material material = world.getBlock(x, y, z).getMaterial();
        return material == this.blockMaterial ? false : (material == Material.lava ? false : !this.blockBlocksFlow(world, x, y, z));
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);

        if (world.getBlock(x, y, z) == this)
        {
            world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
        }
    }
}
