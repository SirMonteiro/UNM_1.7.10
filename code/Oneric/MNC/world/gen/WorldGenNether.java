package Oneric.MNC.world.gen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNether extends WorldGenerator
{
    private final Block minableBlock;
    private final int numberOfBlocks;

    public WorldGenNether(Block block, int numberOfBlocks)
    {
        this.minableBlock = block;
        this.numberOfBlocks = numberOfBlocks;
    }

    public WorldGenNether(int blockId, int numberOfBlocks)
    {
        this(Block.getBlockById(blockId), numberOfBlocks);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        float f = rand.nextFloat() * (float)Math.PI;
        double d0 = (float)(x + 8) + (MathHelper.sin(f) * (float)this.numberOfBlocks) / 8.0F;
        double d1 = (float)(x + 8) - (MathHelper.sin(f) * (float)this.numberOfBlocks) / 8.0F;
        double d2 = (float)(z + 8) + (MathHelper.cos(f) * (float)this.numberOfBlocks) / 8.0F;
        double d3 = (float)(z + 8) - (MathHelper.cos(f) * (float)this.numberOfBlocks) / 8.0F;
        double d4 = y + rand.nextInt(3) - 2;
        double d5 = y + rand.nextInt(3) - 2;

        for (int i = 0; i <= this.numberOfBlocks; ++i)
        {
            double d6 = d0 + (d1 - d0) * (double)i / (double)this.numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double)i / (double)this.numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double)i / (double)this.numberOfBlocks;
            double d9 = rand.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double d10 = (double)(MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
            int xMin = MathHelper.floor_double(d6 - d10 / 2.0D);
            int yMin = MathHelper.floor_double(d7 - d11 / 2.0D);
            int zMin = MathHelper.floor_double(d8 - d10 / 2.0D);
            int xMax = MathHelper.floor_double(d6 + d10 / 2.0D);
            int yMax = MathHelper.floor_double(d7 + d11 / 2.0D);
            int zMax = MathHelper.floor_double(d8 + d10 / 2.0D);

            for (int bx = xMin; bx <= xMax; ++bx)
            {
                double d12 = ((double)bx + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int by = yMin; by <= yMax; ++by)
                    {
                        double d13 = ((double)by + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int bz = zMin; bz <= zMax; ++bz)
                            {
                                double d14 = ((double)bz + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.getBlock(bx, by, bz) == Blocks.netherrack)
                                {
                                    world.setBlock(bx, by, bz, this.minableBlock, 0, 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
