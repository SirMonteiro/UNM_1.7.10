package Oneric.MNC.common;
import net.minecraft.init.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class MNCAchievementPage extends AchievementPage{

	public MNCAchievementPage(String name, Achievement[] achievements) {
		super(name, achievements);
		
	}
	
	
	/*
	public int bgGetSprite(Random random, int i, int j)
    {
        int k = Blocks.sand.blockIndexInTexture;
        int l = random.nextInt(1 + j) + j / 2;

        if (l > 37 || j == 35)
        {
            k = MNCBlocks.NetherGold.blockIndexInTexture;
        }
        else if (l == 22)
        {
            k = /*random.nextInt(2) == 0 ? *\Blocks.glowstone.blockIndexInTexture/* : BlockHolystone.sprMossy*\;
        }
        else if (l == 10)
        {
            k = Blocks.lava.blockIndexInTexture;
        }
        else if (l == 8)
        {
            k = MNCBlocks.Netherplanks.blockIndexInTexture;
        }
        else if (l > 4)
        {
            k = Blocks.netherrack.blockIndexInTexture;
        }
        else if (l > 0)
        {
            k = Blocks.soul_sand.blockIndexInTexture;
        }

        return k;
    }
    */

}
