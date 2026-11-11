package Oneric.MNC.world.gen;

import java.util.Random;
import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.entity.boss.EntityDaemonLord;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonGenDaemon extends Buildings
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (this.isBoxSolid(world, x - 1, y - 1, z - 1, 15, 10, 15) && !this.isBoxSolid(world, x + 13, y, z + 4, 8, 6, 5))
        {
            this.setTunnelX(world, x + 12, y, z + 4, 8, 6, 5, MNCBlocks.dungeonStone, 0);
            this.setBoxHollow(world, x, y, z, 13, 8, 13, MNCBlocks.dungeonStone, 1);
            this.setBox(world, x + 12, y + 1, z + 5, 1, 4, 3, Blocks.air, 0);
            this.setCenter(world, x + 6, y + 1, z + 6);
            this.spawnBoss(world, x + 6, y + 2, z + 6);
            return true;
        }
        return false;
    }

    private void setCenter(World world, int x, int y, int z)
    {
        world.setBlock(x, y, z, MNCBlocks.dungeonStone, 1, 2);
        world.setBlock(x + 1, y, z, MNCBlocks.dungeonStone, 1, 2);
        world.setBlock(x - 1, y, z, MNCBlocks.dungeonStone, 1, 2);
        world.setBlock(x, y, z + 1, MNCBlocks.dungeonStone, 1, 2);
        world.setBlock(x, y, z - 1, MNCBlocks.dungeonStone, 1, 2);
    }

    private void spawnBoss(World world, int x, int y, int z)
    {
        EntityDaemonLord boss = new EntityDaemonLord(world, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D);
        boss.setLocationAndAngles((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, 0.0F, 0.0F);
        boss.setDungeon(x, y, z);
        world.spawnEntityInWorld(boss);
    }
}
