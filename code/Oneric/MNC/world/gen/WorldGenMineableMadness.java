package Oneric.MNC.world.gen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/** Ore vein generator used by the Fallen Sky chunk provider. */
public class WorldGenMineableMadness extends WorldGenerator
{
    private final Block ore;
    private final int numberOfBlocks;

    public WorldGenMineableMadness(Block ore, int numberOfBlocks)
    {
        this.ore = ore;
        this.numberOfBlocks = numberOfBlocks;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        float angle = random.nextFloat() * (float)Math.PI;
        double radius = (double)this.numberOfBlocks / 8.0D;
        int minX = (int)Math.floor((double)x + Math.sin((double)angle) * radius);
        int maxX = (int)Math.floor((double)x - Math.sin((double)angle) * radius);
        int minZ = (int)Math.floor((double)z + Math.cos((double)angle) * radius);
        int maxZ = (int)Math.floor((double)z - Math.cos((double)angle) * radius);
        int minY = y + random.nextInt(3) - 2;
        int maxY = y + random.nextInt(3) - 2;

        for (int i = 0; i < this.numberOfBlocks; ++i)
        {
            float progress = (float)i / (float)this.numberOfBlocks;
            double cx = (double)minX + (double)(maxX - minX) * (double)progress;
            double cy = (double)minY + (double)(maxY - minY) * (double)progress;
            double cz = (double)minZ + (double)(maxZ - minZ) * (double)progress;
            double size = (double)((Math.sin((double)(progress * (float)Math.PI)) + 1.0F) * (double)random.nextFloat() * 0.5D + 0.5D);

            int bx = (int)Math.floor(cx);
            int by = (int)Math.floor(cy);
            int bz = (int)Math.floor(cz);

            for (int dx = -1; dx <= 1; ++dx)
            {
                for (int dy = -1; dy <= 1; ++dy)
                {
                    for (int dz = -1; dz <= 1; ++dz)
                    {
                        if ((double)(dx * dx + dy * dy + dz * dz) >= size * size)
                        {
                            continue;
                        }

                        Block target = world.getBlock(bx + dx, by + dy, bz + dz);

                        if (target.isReplaceableOreGen(world, bx + dx, by + dy, bz + dz, net.minecraft.init.Blocks.stone))
                        {
                            world.setBlock(bx + dx, by + dy, bz + dz, this.ore, 0, 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}