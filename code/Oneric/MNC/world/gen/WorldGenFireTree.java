package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenFireTree extends WorldGenAbstractTree
{
    static final byte[] otherCoordPairs = new byte[]{2, 0, 0, 1, 2, 1};
    Random rand;
    World worldObj;
    int[] basePos;
    int heightLimit;
    int height;
    double heightAttenuation;
    double branchDensity;
    double branchSlope;
    double scaleWidth;
    double leafDensity;
    int trunkSize;
    int heightLimitLimit;
    int leafDistanceLimit;
    int[][] leafNodes;

    public WorldGenFireTree(boolean doBlockNotify)
    {
        super(doBlockNotify);
        this.rand = new Random();
        this.basePos = new int[]{0, 0, 0};
        this.heightLimit = 0;
        this.heightAttenuation = 0.618D;
        this.branchDensity = 1.0D;
        this.branchSlope = 0.381D;
        this.scaleWidth = 1.3D;
        this.leafDensity = 0.8D;
        this.trunkSize = 1;
        this.heightLimitLimit = 15;
        this.leafDistanceLimit = 3;
    }

    void generateLeafNodeList()
    {
        this.height = (int)((double)this.heightLimit * this.heightAttenuation);

        if (this.height >= this.heightLimit)
        {
            this.height = this.heightLimit - 1;
        }

        int count = (int)(1.382D + Math.pow(this.leafDensity * (double)this.heightLimit / 13.0D, 2.0D));

        if (count < 1)
        {
            count = 1;
        }

        int[][] nodes = new int[count * this.heightLimit][4];
        int nodeY = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
        int totalNodes = 1;
        int branchY = this.basePos[1] + this.height;
        int deltaY = nodeY - this.basePos[1];
        nodes[0][0] = this.basePos[0];
        nodes[0][1] = nodeY;
        nodes[0][2] = this.basePos[2];
        nodes[0][3] = branchY;
        --nodeY;

        while (deltaY >= 0)
        {
            float lSize = this.layerSize(deltaY);

            if (lSize < 0.0F)
            {
                --nodeY;
                --deltaY;
            }
            else
            {
                for (int i = 0; i < count; ++i)
                {
                    double radius = this.scaleWidth * (double)lSize * ((double)this.rand.nextFloat() + 0.328D);
                    double angle = (double)this.rand.nextFloat() * 2.0D * Math.PI;
                    int nx = MathHelper.floor_double(radius * Math.sin(angle) + (double)this.basePos[0] + 0.5D);
                    int nz = MathHelper.floor_double(radius * Math.cos(angle) + (double)this.basePos[2] + 0.5D);
                    int[] pos1 = new int[]{nx, nodeY, nz};
                    int[] pos2 = new int[]{nx, nodeY + this.leafDistanceLimit, nz};

                    if (this.checkBlockLine(pos1, pos2) == -1)
                    {
                        int[] trunkPos = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
                        double dist = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - pos1[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - pos1[2]), 2.0D));
                        double slope = dist * this.branchSlope;

                        if ((double)pos1[1] - slope > (double)branchY)
                        {
                            trunkPos[1] = branchY;
                        }
                        else
                        {
                            trunkPos[1] = (int)((double)pos1[1] - slope);
                        }

                        if (this.checkBlockLine(trunkPos, pos1) == -1)
                        {
                            nodes[totalNodes][0] = nx;
                            nodes[totalNodes][1] = nodeY;
                            nodes[totalNodes][2] = nz;
                            nodes[totalNodes][3] = trunkPos[1];
                            ++totalNodes;
                        }
                    }
                }

                --nodeY;
                --deltaY;
            }
        }

        this.leafNodes = new int[totalNodes][4];
        System.arraycopy(nodes, 0, this.leafNodes, 0, totalNodes);
    }

    void genTreeLayer(int x, int y, int z, float size, byte axis, Block block)
    {
        int range = (int)((double)size + 0.618D);
        byte axis1 = otherCoordPairs[axis];
        byte axis2 = otherCoordPairs[axis + 3];
        int[] origin = new int[]{x, y, z};
        int[] pos = new int[]{0, 0, 0};
        pos[axis] = origin[axis];

        for (int d1 = -range; d1 <= range; ++d1)
        {
            pos[axis1] = origin[axis1] + d1;

            for (int d2 = -range; d2 <= range; ++d2)
            {
                double dist = Math.pow((double)Math.abs(d1) + 0.5D, 2.0D) + Math.pow((double)Math.abs(d2) + 0.5D, 2.0D);

                if (dist <= (double)(size * size))
                {
                    pos[axis2] = origin[axis2] + d2;
                    Block current = this.worldObj.getBlock(pos[0], pos[1], pos[2]);

                    if (current == Blocks.air || current == MNCBlocks.FireLeaf)
                    {
                        this.setBlockAndNotifyAdequately(this.worldObj, pos[0], pos[1], pos[2], block, 0);
                    }
                }
            }
        }
    }

    float layerSize(int y)
    {
        if ((double)y < (double)this.heightLimit * 0.3D)
        {
            return -1.618F;
        }

        float half = (float)this.heightLimit / 2.0F;
        float dist = (float)this.heightLimit / 2.0F - (float)y;
        float radius;

        if (dist == 0.0F)
        {
            radius = half;
        }
        else if (Math.abs(dist) >= half)
        {
            radius = 0.0F;
        }
        else
        {
            radius = (float)Math.sqrt(Math.pow((double)Math.abs(half), 2.0D) - Math.pow((double)Math.abs(dist), 2.0D));
        }

        return radius * 0.5F;
    }

    float leafSize(int y)
    {
        if (y < 0 || y >= this.leafDistanceLimit)
        {
            return -1.0F;
        }
        return (y == 0 || y == this.leafDistanceLimit - 1) ? 2.0F : 3.0F;
    }

    void generateLeafNode(int x, int y, int z)
    {
        int top = y + this.leafDistanceLimit;

        for (int ly = y; ly < top; ++ly)
        {
            float size = this.leafSize(ly - y);
            this.genTreeLayer(x, ly, z, size, (byte)1, MNCBlocks.FireLeaf);
        }
    }

    void placeBlockLine(int[] start, int[] end, Block block)
    {
        int[] delta = new int[]{0, 0, 0};
        byte primaryAxis = 0;

        for (byte axis = 0; axis < 3; ++axis)
        {
            delta[axis] = end[axis] - start[axis];

            if (Math.abs(delta[axis]) > Math.abs(delta[primaryAxis]))
            {
                primaryAxis = axis;
            }
        }

        if (delta[primaryAxis] != 0)
        {
            byte axis1 = otherCoordPairs[primaryAxis];
            byte axis2 = otherCoordPairs[primaryAxis + 3];
            byte step = delta[primaryAxis] > 0 ? (byte)1 : (byte)-1;
            double slope1 = (double)delta[axis1] / (double)delta[primaryAxis];
            double slope2 = (double)delta[axis2] / (double)delta[primaryAxis];
            int[] pos = new int[]{0, 0, 0};
            int totalSteps = delta[primaryAxis] + step;

            for (int s = 0; s != totalSteps; s += step)
            {
                pos[primaryAxis] = MathHelper.floor_double((double)(start[primaryAxis] + s) + 0.5D);
                pos[axis1] = MathHelper.floor_double((double)start[axis1] + (double)s * slope1 + 0.5D);
                pos[axis2] = MathHelper.floor_double((double)start[axis2] + (double)s * slope2 + 0.5D);
                this.setBlockAndNotifyAdequately(this.worldObj, pos[0], pos[1], pos[2], block, 0);
            }
        }
    }

    void generateLeaves()
    {
        for (int[] node : this.leafNodes)
        {
            this.generateLeafNode(node[0], node[1], node[2]);
        }
    }

    boolean leafNodeNeedsBase(int y)
    {
        return (double)y >= (double)this.heightLimit * 0.2D;
    }

    void generateTrunk()
    {
        int x = this.basePos[0];
        int y1 = this.basePos[1];
        int y2 = this.basePos[1] + this.height;
        int z = this.basePos[2];
        int[] start = new int[]{x, y1, z};
        int[] end = new int[]{x, y2, z};
        this.placeBlockLine(start, end, MNCBlocks.FireLog);

        if (this.trunkSize == 2)
        {
            start[0]++; end[0]++;
            this.placeBlockLine(start, end, MNCBlocks.FireLog);
            start[2]++; end[2]++;
            this.placeBlockLine(start, end, MNCBlocks.FireLog);
            start[0]--; end[0]--;
            this.placeBlockLine(start, end, MNCBlocks.FireLog);
        }
    }

    void generateLeafNodeBases()
    {
        int[] start = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};

        for (int[] node : this.leafNodes)
        {
            int[] end = new int[]{node[0], node[1], node[2]};
            start[1] = node[3];
            int dist = start[1] - this.basePos[1];

            if (this.leafNodeNeedsBase(dist))
            {
                this.placeBlockLine(start, end, MNCBlocks.FireLog);
            }
        }
    }

    int checkBlockLine(int[] start, int[] end)
    {
        int[] delta = new int[]{0, 0, 0};
        byte primaryAxis = 0;

        for (byte axis = 0; axis < 3; ++axis)
        {
            delta[axis] = end[axis] - start[axis];

            if (Math.abs(delta[axis]) > Math.abs(delta[primaryAxis]))
            {
                primaryAxis = axis;
            }
        }

        if (delta[primaryAxis] == 0)
        {
            return -1;
        }

        byte axis1 = otherCoordPairs[primaryAxis];
        byte axis2 = otherCoordPairs[primaryAxis + 3];
        byte step = delta[primaryAxis] > 0 ? (byte)1 : (byte)-1;
        double slope1 = (double)delta[axis1] / (double)delta[primaryAxis];
        double slope2 = (double)delta[axis2] / (double)delta[primaryAxis];
        int[] pos = new int[]{0, 0, 0};
        int s = 0;
        int totalSteps = delta[primaryAxis] + step;

        while (s != totalSteps)
        {
            pos[primaryAxis] = start[primaryAxis] + s;
            pos[axis1] = MathHelper.floor_double((double)start[axis1] + (double)s * slope1);
            pos[axis2] = MathHelper.floor_double((double)start[axis2] + (double)s * slope2);
            Block block = this.worldObj.getBlock(pos[0], pos[1], pos[2]);

            if (block != Blocks.air && block != MNCBlocks.FireLeaf)
            {
                break;
            }

            s += step;
        }

        if (s == totalSteps)
        {
            return -1;
        }

        return Math.abs(s);
    }

    boolean validTreeLocation()
    {
        int[] start = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] end = new int[]{this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
        Block ground = this.worldObj.getBlock(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);

        if (ground != Blocks.soul_sand && ground != Blocks.netherrack && ground != MNCBlocks.deadGrass && ground != MNCBlocks.deadDirt)
        {
            return false;
        }

        int maxClear = this.checkBlockLine(start, end);

        if (maxClear == -1)
        {
            return true;
        }
        if (maxClear < 6)
        {
            return false;
        }

        this.heightLimit = maxClear;
        return true;
    }

    public void setScale(double heightScale, double widthScale, double leafScale)
    {
        this.heightLimitLimit = (int)(heightScale * 12.0D);

        if (heightScale > 0.5D)
        {
            this.leafDistanceLimit = 5;
        }

        this.scaleWidth = widthScale;
        this.leafDensity = leafScale;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        this.worldObj = world;
        long seed = rand.nextLong();
        this.rand.setSeed(seed);
        this.basePos[0] = x;
        this.basePos[1] = y;
        this.basePos[2] = z;

        if (this.heightLimit == 0)
        {
            this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
        }

        if (!this.validTreeLocation())
        {
            return false;
        }

        this.generateLeafNodeList();
        this.generateLeaves();
        this.generateTrunk();
        this.generateLeafNodeBases();
        return true;
    }
}
