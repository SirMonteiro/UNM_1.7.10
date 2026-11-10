package Oneric.MNC.common;

import Oneric.MNC.donator.DonatorList;
import Oneric.MNC.donator.choices.DonatorChoices;
import Oneric.MNC.entity.IBoss;
import Oneric.MNC.packets.MNCNetwork;
import Oneric.MNC.world.gen.MNCWorldGen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = "onerics_mnc", name = "The Ultimate Nether Mod", version = "1.8.6")
public class mod_netherbr {

	@SidedProxy(clientSide = "Oneric.MNC.client.ClientProxyMNC", serverSide = "Oneric.MNC.common.CommonProxyMNC")
	public static CommonProxyMNC proxy;

	@Instance("onerics_mnc")
	public static mod_netherbr mod;

	private GuiHandler guiHandler = new GuiHandler();

	public static IBoss currentBoss = null;

	public static final RenderManager renderManager = new RenderManager();

	public static final UpdateChecker updateChecker = new UpdateChecker();

	public static DonatorList donatorList = new DonatorList();

	public static DonatorChoices donatorChoices = new DonatorChoices();

	public static final String LOCATION_TEXTURES = "textures/";
	public static final String LOCATION_ARMOR = "textures/models/armor/";
	public static final String LOCATION_ENTITIES = "textures/entity/";
	public static final String LOCATION_GUI = "textures/gui/";

	public static Configuration config;

	// Legacy block IDs kept for any backwards compatibility references
	public static int NetherGoldID;
	public static int NetherBlockID;
	public static int PilzID;
	public static int Pilz2ID;
	public static int HellPilz_BlockID;
	public static int BlackstoneID;
	public static int ESSandID;
	public static int GGoldID;
	public static int GGlowstoneID;
	public static int GSlabID;
	public static int OSlabID;
	public static int GStairID;
	public static int OStairID;
	public static int GFenceID;
	public static int GFenceGateID;
	public static int NFenceGateID;
	public static int OFenceID;
	public static int OFenceGateID;
	public static int HellAltarID;
	public static int GTrapDoorID;
	public static int NTrapDoorID;
	public static int GDoorID;
	public static int NDoorID;
	public static int GDoorHiddenID;
	public static int NDoorHiddenID;
	public static int StatureID;
	public static int MudID;
	public static int SchleimStillID;
	public static int SchleimFliessID;
	public static int nTNTID;
	public static int FireLogID;
	public static int FireLeafID;
	public static int FireSaplingID;
	public static int NetherplanksID;
	public static int NWDoorID;
	public static int NWFenceID;
	public static int NWFenceGateID;
	public static int NWStairsID;
	public static int NWSlabID;
	public static int NWallID;
	public static int GWallID;
	public static int OWallID;
	public static int NWWallID;
	public static int PortalCrazyID;
	public static int deadDirtID;
	public static int deadGrassID;
	public static int SmogID;
	public static int DeadStoneID;
	public static int orePlumbumID;
	public static int dungeonStoneID;

	// Legacy item IDs
	public static int HellPilz_SamenID;
	public static int HellPilzID;
	public static int GGold_iID;
	public static int GGlowstone_iID;
	public static int GDoorItemID;
	public static int NDoorItemID;
	public static int GDoorHiddenItemID;
	public static int NDoorHiddenItemID;
	public static int netheringotID;
	public static int epicingotID;
	public static int NArrowID;
	public static int NetherstickID;
	public static int strangeID;
	public static int FireGemID;
	public static int NetherSwordID;
	public static int NetherPickaxeID;
	public static int NetherShoevelID;
	public static int NetherAxeID;
	public static int NetherHoeID;
	public static int GlowSwordID;
	public static int GlowPickaxeID;
	public static int GlowShoevelID;
	public static int GlowAxeID;
	public static int GlowHoeID;
	public static int diabloID;
	public static int NetherHelmetID;
	public static int NetherPlateID;
	public static int NetherLegID;
	public static int NetherBootsID;
	public static int GlowHelmetID;
	public static int GlowPlateID;
	public static int GlowLegID;
	public static int GlowBootsID;
	public static int Stature_iID;
	public static int NBowID;
	public static int NBucketEmptyID;
	public static int NBucketFullID;
	public static int NWDoorItemID;
	public static int ingotPlumbumID;
	public static int hammerPlumbumID;
	public static int pickaxeGravID;

	public static int MazeRarity = 300;
	public static int biomeRarity = 3;

	public static int achievementEnterTheHell = 2013;
	public static int achievementDefeatADaemon = 2014;
	public static int achievementEnterTheFallenSky = 2015;

	public static boolean achievementsEnabled = true;
	public static boolean updateCheck = true;
	public static boolean newVersionAviable = false;

	@SideOnly(Side.CLIENT)
	public static boolean reportNewVersion;

	public static Item.ToolMaterial epicNetherT = EnumHelper.addToolMaterial("epicnetherT", 3, 3000, 20F, 7, 10);
	public static Item.ToolMaterial glowstoneT = EnumHelper.addToolMaterial("glowstoneT", 2, 40, 8F, 3, 10);
	public static Item.ToolMaterial diabloT = EnumHelper.addToolMaterial("diablo", 20, 225, 15F, 10, 0);
	public static Item.ToolMaterial gravityT = EnumHelper.addToolMaterial("mnc_grav", 3, 600, 8F, 4, 0);

	public static ItemArmor.ArmorMaterial epicNetherA = EnumHelper.addArmorMaterial("epicnetherA", 34, new int[] {5, 11, 6, 4}, 10);
	public static ItemArmor.ArmorMaterial glowstoneA = EnumHelper.addArmorMaterial("glowstoneA", 7, new int[] {2, 5, 3, 1}, 25);

	@EventHandler
	public void preLoad(FMLPreInitializationEvent fmlpreinitevent)
	{
		mod = this;
		System.out.println("[UltimateNetherMod] Starting.....");

		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			reportNewVersion = false;
		}

		proxy.registerSounds();

		MNCNetwork.init();

		System.out.println("[UltimateNetherMod] Loading Config-File.....");
		this.setUpConfig(new Configuration(fmlpreinitevent.getSuggestedConfigurationFile()));

		proxy.initRenderPlayerAPI();

		new MNCBlocks();
		new MNCItems();

		if (achievementsEnabled)
		{
			new MNCAchievements();
		}

		MNCPlayerTracker playerTracker = new MNCPlayerTracker();
		FMLCommonHandler.instance().bus().register(playerTracker);

		MNCConectionHandler connectionHandler = new MNCConectionHandler();
		FMLCommonHandler.instance().bus().register(connectionHandler);
	}

	private void setUpConfig(Configuration config)
	{
		this.config = config;
		config.load();

		config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, "Higher value of the Rarity make structures rarer, deeper value more common");
		MazeRarity = config.get(Configuration.CATEGORY_GENERAL, "Maze Rarity", 300).getInt();
		biomeRarity = config.get(Configuration.CATEGORY_GENERAL, "Biome Rarity", 3).getInt();
		achievementDefeatADaemon = config.get(Configuration.CATEGORY_GENERAL, "achievementDefeatADaemon", 2014).getInt();
		achievementEnterTheHell = config.get(Configuration.CATEGORY_GENERAL, "achievementEnterTheHell", 2013).getInt();
		achievementEnterTheFallenSky = config.get(Configuration.CATEGORY_GENERAL, "achievementEnterTheFallenSky", 2015).getInt();

		achievementsEnabled = config.get(Configuration.CATEGORY_GENERAL, "enableAchievements", true).getBoolean(true);
		updateCheck = config.get(Configuration.CATEGORY_GENERAL, "checkForUpdates", true).getBoolean(true);

		config.save();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();
		updateChecker.checkForUpdates();

		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			this.donatorList.addMe(Minecraft.getMinecraft().getSession().getUsername());
		}

		new MNCBiomes();
		new MNCRecipes();

		proxy.registerPostRender();

		MinecraftForge.EVENT_BUS.register(new MNCEvent());
		MinecraftForge.TERRAIN_GEN_BUS.register(new MNCTerrainEvent());

		GameRegistry.registerWorldGenerator(new MNCWorldGen(), 0);

		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);

		new MNCEntities();
	}

	public static String getId()
	{
		return "onerics_mnc";
	}
}
