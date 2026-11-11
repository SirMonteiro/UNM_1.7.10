package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class Buildings extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        return false;
    }

    public boolean isBoxEmpty(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide)
    {
        if (xWide < 0) { xO += xWide; xWide *= -1; }
        if (yWide < 0) { yO += yWide; yWide *= -1; }
        if (zWide < 0) { zO += zWide; zWide *= -1; }

        for (int x = xO; x < xO + xWide; ++x)
        {
            for (int y = yO; y < yO + yWide; ++y)
            {
                for (int z = zO; z < zO + zWide; ++z)
                {
                    if (!world.isAirBlock(x, y, z))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBoxFilledWith(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, Block[] blocks)
    {
        if (xWide < 0) { xO += xWide; xWide *= -1; }
        if (yWide < 0) { yO += yWide; yWide *= -1; }
        if (zWide < 0) { zO += zWide; zWide *= -1; }

        for (int x = xO; x < xO + xWide; ++x)
        {
            for (int y = yO; y < yO + yWide; ++y)
            {
                for (int z = zO; z < zO + zWide; ++z)
                {
                    Block current = world.getBlock(x, y, z);
                    boolean match = false;
                    for (Block b : blocks)
                    {
                        if (current == b)
                        {
                            match = true;
                            break;
                        }
                    }
                    if (!match)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBoxFilledWith(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, int[] ids)
    {
        Block[] blocks = new Block[ids.length];
        for (int i = 0; i < ids.length; ++i)
        {
            blocks[i] = Block.getBlockById(ids[i]);
        }
        return isBoxFilledWith(world, xO, yO, zO, xWide, yWide, zWide, blocks);
    }

    public boolean isBoxSolid(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide)
    {
        if (xWide < 0) { xO += xWide; xWide *= -1; }
        if (yWide < 0) { yO += yWide; yWide *= -1; }
        if (zWide < 0) { zO += zWide; zWide *= -1; }

        for (int x = xO; x < xO + xWide; ++x)
        {
            for (int y = yO; y < yO + yWide; ++y)
            {
                for (int z = zO; z < zO + zWide; ++z)
                {
                    if (world.isAirBlock(x, y, z))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void setBox(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, Block block, int meta)
    {
        if (xWide < 0) { xO += xWide; xWide *= -1; }
        if (yWide < 0) { yO += yWide; yWide *= -1; }
        if (zWide < 0) { zO += zWide; zWide *= -1; }

        for (int x = xO; x < xO + xWide; ++x)
        {
            for (int y = yO; y < yO + yWide; ++y)
            {
                for (int z = zO; z < zO + zWide; ++z)
                {
                    world.setBlock(x, y, z, block, meta, 2);
                }
            }
        }
    }

    public void setBox(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, int blockID, int meta)
    {
        setBox(world, xO, yO, zO, xWide, yWide, zWide, Block.getBlockById(blockID), meta);
    }

    public void setBoxHollow(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, Block block, int meta)
    {
        setBox(world, xO, yO, zO, xWide, yWide, zWide, Blocks.air, 0);
        setBox(world, xO, yO, zO, xWide, 1, zWide, block, meta);
        setBox(world, xO, (yO + yWide) - 1, zO, xWide, 1, zWide, block, meta);
        setBox(world, xO, yO, zO, 1, yWide, zWide, block, meta);
        setBox(world, (xO + xWide) - 1, yO, zO, 1, yWide, zWide, block, meta);
        setBox(world, xO, yO, zO, xWide, yWide, 1, block, meta);
        setBox(world, xO, yO, (zO + zWide) - 1, xWide, yWide, 1, block, meta);
    }

    public void setBoxHollow(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, int blockID, int meta)
    {
        setBoxHollow(world, xO, yO, zO, xWide, yWide, zWide, Block.getBlockById(blockID), meta);
    }

    public void setTunnelX(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, Block block, int meta)
    {
        setBox(world, xO, yO, zO, xWide, yWide, zWide, Blocks.air, 0);
        setBox(world, xO, yO, zO, xWide, 1, zWide, block, meta);
        setBox(world, xO, (yO + yWide) - 1, zO, xWide, 1, zWide, block, meta);
        setBox(world, xO, yO, zO, xWide, yWide, 1, block, meta);
        setBox(world, xO, yO, (zO + zWide) - 1, xWide, yWide, 1, block, meta);
    }

    public void setTunnelX(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, int blockID, int meta)
    {
        setTunnelX(world, xO, yO, zO, xWide, yWide, zWide, Block.getBlockById(blockID), meta);
    }

    public void setTunnelY(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, Block block, int meta)
    {
        setBox(world, xO, yO, zO, xWide, yWide, zWide, Blocks.air, 0);
        setBox(world, xO, yO, zO, 1, yWide, zWide, block, meta);
        setBox(world, (xO + xWide) - 1, yO, zO, 1, yWide, zWide, block, meta);
        setBox(world, xO, yO, zO, xWide, yWide, 1, block, meta);
        setBox(world, xO, yO, (zO + zWide) - 1, xWide, yWide, 1, block, meta);
    }

    public void setTunnelY(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, int blockID, int meta)
    {
        setTunnelY(world, xO, yO, zO, xWide, yWide, zWide, Block.getBlockById(blockID), meta);
    }

    public void setTunnelZ(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, Block block, int meta)
    {
        setBox(world, xO, yO, zO, xWide, yWide, zWide, Blocks.air, 0);
        setBox(world, xO, yO, zO, xWide, 1, zWide, block, meta);
        setBox(world, xO, (yO + yWide) - 1, zO, xWide, 1, zWide, block, meta);
        setBox(world, xO, yO, zO, 1, yWide, zWide, block, meta);
        setBox(world, (xO + xWide) - 1, yO, zO, 1, yWide, zWide, block, meta);
    }

    public void setTunnelZ(World world, int xO, int yO, int zO, int xWide, int yWide, int zWide, int blockID, int meta)
    {
        setTunnelZ(world, xO, yO, zO, xWide, yWide, zWide, Block.getBlockById(blockID), meta);
    }
}
