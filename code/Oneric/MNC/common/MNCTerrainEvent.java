package Oneric.MNC.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import Oneric.MNC.biomes.MNCGenLayer;

public class MNCTerrainEvent {
	
	@SubscribeEvent
	public void onInitBiomeGenerators(WorldTypeEvent.InitBiomeGens event)
	{
		event.newBiomeGens = MNCGenLayer.initializeAllBiomeGenerators(event.seed, event.worldType);
	}
}
