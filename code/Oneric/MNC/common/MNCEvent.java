package Oneric.MNC.common;

import java.util.Random;

import Oneric.MNC.block.BlockFireSapling;
import Oneric.MNC.block.BlockHellPilz;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class MNCEvent {

	@SubscribeEvent
	public void bonemealUsed(BonemealEvent event)
	{
		if (event.world.getBlock(event.x, event.y, event.z) == MNCBlocks.HellPilz_Block)
		{
			((BlockHellPilz)MNCBlocks.HellPilz_Block).fertilize(event.world, event.x, event.y, event.z);
		}

		if (event.world.getBlock(event.x, event.y, event.z) == MNCBlocks.FireSapling && event.world.rand.nextBoolean())
		{
			((BlockFireSapling)MNCBlocks.FireSapling).growTree(event.world, event.x, event.y, event.z, new Random());
		}
	}
}
