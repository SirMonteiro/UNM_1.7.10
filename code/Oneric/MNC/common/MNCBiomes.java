package Oneric.MNC.common;

import Oneric.MNC.biomes.biome_Blackstone;
import Oneric.MNC.biomes.biome_HellPilz;
import Oneric.MNC.biomes.biome_IceHell;
import Oneric.MNC.dimensions.BiomeGenCrazy;
import Oneric.MNC.dimensions.WorldProviderCrazy;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;

public class MNCBiomes {

	public static BiomeGenBase biome_Blackstone;
	public static BiomeGenBase biome_HellPilz;
	public static BiomeGenBase biome_IceHell;
	public static int CrazyDimID;
	public static BiomeGenBase[] CrazyBiomeList;
	public static BiomeGenCrazy crazy;
	public static BiomeGenBase[] rareBiomes;

	public MNCBiomes()
	{
		//Blackstone
		biome_Blackstone = new biome_Blackstone(25);
    	
    	//HellPilz
    	biome_HellPilz = new biome_HellPilz(30);
    	
    	rareBiomes = new BiomeGenBase[]{biome_Blackstone, biome_HellPilz};
    	
    	//IceHell
    	biome_IceHell = new biome_IceHell(26);
    	BiomeManager.addSpawnBiome(biome_IceHell);
		
    	crazy = new BiomeGenCrazy();
    	
    	CrazyDimID = DimensionManager.getNextFreeDimId();
    	CrazyBiomeList = new BiomeGenBase[256];
    	CrazyBiomeList[0] = crazy;
    	DimensionManager.registerProviderType(12, WorldProviderCrazy.class, true);
    	DimensionManager.registerDimension(CrazyDimID, 12);
	}
}
