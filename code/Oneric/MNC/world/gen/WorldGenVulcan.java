package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVulcan extends WorldGenerator
{
    private static final double stopEinAchtel = Math.sin(Math.toRadians(45.0D));
    private final int height;
    private final int radiusBottom;
    private final int radiusTop;
    private final int thickness;
    private final Random rand = new Random();

    public WorldGenVulcan(int h, int radBottom, int radTop, int thick)
    {
        this.height = h;
        this.radiusBottom = radBottom;
        this.radiusTop = radTop;
        this.thickness = thick;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        this.generate(world, x, y, z);
        return true;
    }

    public void generate(World world, int x, int y, int z)
    {
        for (int h = 0; h <= this.height; ++h)
        {
            int radius = (int)((double)this.radiusBottom - (((double)h / (double)this.height) * (double)(this.radiusBottom - this.radiusTop)));
            int dicke = (int)((double)this.thickness - (((double)h / (double)this.height) * ((double)this.thickness - 1.0D)));

            this.generateCircleRandom(x, y + h, z, radius, world, Blocks.stone, h);

            if (h == 0)
            {
                this.generateCircleToGround(x, y + h, z, radius, world, Blocks.stone);
            }
            else
            {
                this.generateCircle(x, y + h, z, radius, world, Blocks.stone);
            }

            if (dicke > 1)
            {
                for (int i = 1; i < dicke - 1; ++i)
                {
                    this.generateCircle(x, y + h, z, radius - i, world, Blocks.stone);
                }
            }
        }
    }

    private void generateCircle(int x, int y, int z, int radius, World world, Block block)
    {
        for (int yT = 0; (double)yT <= stopEinAchtel * (double)radius; ++yT)
        {
            int xT = (int)(Math.sqrt((double)(radius * radius - yT * yT)) + 0.5D);
            this.setAllCircleBlocks(world, x, y, z, xT, yT, block);
        }
    }

    private void generateCircleToGround(int x, int y, int z, int radius, World world, Block block)
    {
        for (int yT = 0; (double)yT <= stopEinAchtel * (double)radius; ++yT)
        {
            int xT = (int)(Math.sqrt((double)(radius * radius - yT * yT)) + 0.5D);
            this.setAllCircleBlocksToBottom(world, x, y, z, xT, yT, block);
        }
    }

    private void generateCircleRandom(int x, int y, int z, int radius, World world, Block block, int h)
    {
        for (int yT = 0; (double)yT <= stopEinAchtel * (double)radius; ++yT)
        {
            int xT = (int)(Math.sqrt((double)(radius * radius - yT * yT)) + 0.5D);
            this.setAllCircleOutBlocksWithRandomness(world, x, y, z, xT, yT, block, h);
        }
    }

    private void setAllCircleBlocks(World world, int xM, int yM, int zM, int x, int y, Block block)
    {
        world.setBlock(xM + x, yM, zM + y, block);
        world.setBlock(xM + y, yM, zM + x, block);
        world.setBlock(xM - x, yM, zM + y, block);
        world.setBlock(xM - y, yM, zM + x, block);
        world.setBlock(xM + x, yM, zM - y, block);
        world.setBlock(xM + y, yM, zM - x, block);
        world.setBlock(xM - x, yM, zM - y, block);
        world.setBlock(xM - y, yM, zM - x, block);
    }

    private void setAllCircleBlocksToBottom(World world, int xM, int yM, int zM, int x, int y, Block block)
    {
        this.setBlockRowToBottom(world, xM + x, yM, zM + y, block);
        this.setBlockRowToBottom(world, xM + y, yM, zM + x, block);
        this.setBlockRowToBottom(world, xM - x, yM, zM + y, block);
        this.setBlockRowToBottom(world, xM - y, yM, zM + x, block);
        this.setBlockRowToBottom(world, xM + x, yM, zM - y, block);
        this.setBlockRowToBottom(world, xM + y, yM, zM - x, block);
        this.setBlockRowToBottom(world, xM - x, yM, zM - y, block);
        this.setBlockRowToBottom(world, xM - y, yM, zM - x, block);
    }

    private void setAllCircleOutBlocksWithRandomness(World world, int xM, int yM, int zM, int x, int y, Block block, int h)
    {
        int flag1 = this.randomness(h);
        int flag2 = this.randomness(h);
        if (flag1 != 0 || flag2 != 0)
        {
            this.setBlockRowToBottom(world, xM + x + flag1, yM, zM + y + flag2, block);
        }

        int flag3 = this.randomness(h);
        int flag4 = this.randomness(h);
        if (flag3 != 0 || flag4 != 0)
        {
            this.setBlockRowToBottom(world, xM + y + flag3, yM, zM + x + flag4, block);
        }

        int flag5 = this.randomness(h);
        int flag6 = this.randomness(h);
        if (flag5 != 0 || flag6 != 0)
        {
            this.setBlockRowToBottom(world, (xM - x) - flag5, yM, zM + y + flag6, block);
        }

        int flag7 = this.randomness(h);
        int flag8 = this.randomness(h);
        if (flag7 != 0 || flag8 != 0)
        {
            this.setBlockRowToBottom(world, (xM - y) - flag7, yM, zM + x + flag8, block);
        }

        int flag9 = this.randomness(h);
        int flag10 = this.randomness(h);
        if (flag9 != 0 || flag10 != 0)
        {
            this.setBlockRowToBottom(world, xM + x + flag9, yM, (zM - y) - flag10, block);
        }

        int flag11 = this.randomness(h);
        int flag12 = this.randomness(h);
        if (flag11 != 0 || flag12 != 0)
        {
            this.setBlockRowToBottom(world, xM + y + flag11, yM, (zM - x) - flag12, block);
        }

        int flag13 = this.randomness(h);
        int flag14 = this.randomness(h);
        if (flag13 != 0 || flag14 != 0)
        {
            this.setBlockRowToBottom(world, (xM - x) - flag13, yM, (zM - y) - flag14, block);
        }

        int flag15 = this.randomness(h);
        int flag16 = this.randomness(h);
        if (flag15 != 0 || flag16 != 0)
        {
            this.setBlockRowToBottom(world, (xM - y) - flag15, yM, (zM - x) - flag16, block);
        }
    }

    private int randomness(int h)
    {
        int denom = this.radiusBottom - this.radiusTop;
        if (denom == 0) denom = 1;
        int div = this.height / denom;
        if (div <= 0) div = 1;

        if ((this.height - h) + (this.rand.nextInt(div) / 2) != 0)
        {
            return this.rand.nextInt(2);
        }
        return 0;
    }

    private void setBlockRowToBottom(World world, int x, int y, int z, Block block)
    {
        int i = 0;
        do
        {
            world.setBlock(x, y - i, z, block);
            ++i;
            if (!world.isAirBlock(x, y - i, z) && !world.getBlock(x, y - i, z).getMaterial().isLiquid())
            {
                return;
            }
        }
        while (y - i > 0);
    }
}
