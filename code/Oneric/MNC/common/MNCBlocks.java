package Oneric.MNC.common;

import Oneric.MNC.block.BlackStone;
import Oneric.MNC.block.BlockDeadGrass;
import Oneric.MNC.block.BlockDoor_MNC;
import Oneric.MNC.block.BlockDoor_MNC_Hidden;
import Oneric.MNC.block.BlockDungeonStone;
import Oneric.MNC.block.BlockEnchantedSoulSand;
import Oneric.MNC.block.BlockEpicNether;
import Oneric.MNC.block.BlockFenceGate_MNC;
import Oneric.MNC.block.BlockFence_MNC;
import Oneric.MNC.block.BlockFireLeaf;
import Oneric.MNC.block.BlockFireLog;
import Oneric.MNC.block.BlockFireSapling;
import Oneric.MNC.block.BlockGGlowstone;
import Oneric.MNC.block.BlockGrowGold;
import Oneric.MNC.block.BlockHellPilz;
import Oneric.MNC.block.BlockMud;
import Oneric.MNC.block.BlockNetherGold;
import Oneric.MNC.block.BlockNetherTNT;
import Oneric.MNC.block.BlockOrePlumbum;
import Oneric.MNC.block.BlockPentagram;
import Oneric.MNC.block.BlockPilz;
import Oneric.MNC.block.BlockPortalCrazy;
import Oneric.MNC.block.BlockSlab;
import Oneric.MNC.block.BlockSmog;
import Oneric.MNC.block.BlockStature;
import Oneric.MNC.block.BlockTrapDoor_MNC;
import Oneric.MNC.block.BlockTreppen;
import Oneric.MNC.block.BlockWall_MNC;
import Oneric.MNC.block.ItemBlockDungeon;
import Oneric.MNC.block.ItemBlockStature;
import Oneric.MNC.block.MNCBlock;
import Oneric.MNC.block.MNCBlockFlowing;
import Oneric.MNC.block.MNCBlockStationary;
import Oneric.MNC.block.MNCBlockTreppen;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;

public class MNCBlocks
{
	public static Block NetherGold;
	public static Block NetherBlock;
	public static Block Pilz;
	public static Block Pilz2;
	public static Block HellPilz_Block;
	public static Block Blackstone;
	public static Block ESSand;
	public static Block GGold;
	public static Block GGlowstone;
	public static Block Smog;
	public static Block GSlab;
	public static Block OSlab;
	public static Block GStair;
	public static Block OStair;
	public static Block GFence;
	public static Block GFenceGate;
	public static Block NFence_NEW;
	public static Block NFenceGate;
	public static Block OFence;
	public static Block OFenceGate;
	public static Block HellAltar;
	public static Block GTrapDoor;
	public static Block NTrapDoor;
	public static Block GDoor;
	public static Block NDoor;
	public static Block GDoorHidden;
	public static Block NDoorHidden;
	public static Block Stature;
	public static Block Mud;
	public static Block SchleimStill;
	public static Block SchleimFliess;
	public static Block nTNT;
	public static Block FireLog;
	public static Block FireLeaf;
	public static Block FireSapling;
	public static Block Netherplanks;
	public static Block NWDoor;
	public static Block NWFence;
	public static Block NWFenceGate;
	public static Block NWStairs;
	public static Block NWSlab;
	public static Block NWall;
	public static Block GWall;
	public static Block OWall;
	public static Block NWWall;
	public static BlockPortalCrazy PortalCrazy;
	public static Block deadDirt;
	public static Block deadGrass;
	public static Block DeadStone;
	public static Block orePlumbum;
	public static Block dungeonStone;

	private static <T extends Block> T reg(T b, String name) {
		GameRegistry.registerBlock(b, name);
		return b;
	}

	private static <T extends Block> T reg(T b, Class<? extends ItemBlock> itemClass, String name) {
		GameRegistry.registerBlock(b, itemClass, name);
		return b;
	}

	public MNCBlocks()
	{
		NetherGold = reg(new BlockNetherGold(Material.rock).setHardness(0.8F).setBlockName("mnc_goldOre").setCreativeTab(CreativeTabs.tabBlock), "mnc_goldOre");
		NetherBlock = reg(new BlockEpicNether().setHardness(1.3F).setBlockName("mnc_epicNetherBlock").setCreativeTab(CreativeTabs.tabBlock), "mnc_epicNetherBlock");
		Pilz = reg(new BlockPilz().setBlockName("mnc_netherMushroomBlockOne").setHardness(1.0F).setLightLevel(0.3F), "mnc_netherMushroomBlockOne");
		Pilz2 = reg(new BlockPilz().setBlockName("mnc_netherMushroomBlockTwo").setHardness(1.0F).setLightLevel(0.3F), "mnc_netherMushroomBlockTwo");
		HellPilz_Block = reg(new BlockHellPilz().setBlockName("mnc_HellPilz").setStepSound(Block.soundTypeWood).setHardness(1.3F), "mnc_HellPilz");
		Blackstone = reg(new BlackStone(Material.rock).setBlockName("mnc_BlackStone").setHardness(1.5F).setLightLevel(0.4F), "mnc_BlackStone");
		ESSand = reg(new BlockEnchantedSoulSand().setBlockName("mnc_ESS").setHardness(0.3F).setLightLevel(0.4F).setStepSound(Block.soundTypeSand), "mnc_ESS");
		GGold = reg(new BlockGrowGold(Material.rock).setBlockName("mnc_growingGold").setHardness(0.1F), "mnc_growingGold");
		GGlowstone = reg(new BlockGGlowstone(Material.rock).setBlockName("mnc_growingGlowstone").setHardness(0.2F).setLightLevel(1.0F), "mnc_growingGlowstone");
		Smog = reg(new BlockSmog().setBlockName("mnc_Smog"), "mnc_Smog");
		GSlab = reg(new BlockSlab(false).setBlockName("mnc_glowstoneSlab").setLightLevel(1F).setStepSound(Block.soundTypeGlass).setHardness(0.6F), "mnc_glowstoneSlab");
		OSlab = reg(new BlockSlab(true).setBlockName("mnc_obsidianSlab").setHardness(5F).setResistance(200F), "mnc_obsidianSlab");
		GStair = reg(new BlockTreppen(Blocks.glowstone, false).setBlockName("mnc_GStair").setLightLevel(1F), "mnc_GStair");
		OStair = reg(new BlockTreppen(Blocks.obsidian, true).setBlockName("mnc_OStair").setHardness(5F).setResistance(200F), "mnc_OStair");
		GFence = reg(new BlockFence_MNC(false).setBlockName("mnc_GFence").setLightLevel(1F).setHardness(0.6F).setStepSound(Block.soundTypeGlass), "mnc_GFence");
		GFenceGate = reg(new BlockFenceGate_MNC(false).setBlockName("mnc_GFenceGate").setLightLevel(1.0F).setHardness(0.6F).setStepSound(Block.soundTypeGlass), "mnc_GFenceGate");
		NFence_NEW = reg(new BlockFence_MNC(false).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("mnc_netherFence"), "mnc_netherFence");
		NFenceGate = reg(new BlockFenceGate_MNC(false).setBlockName("mnc_NFenceGate").setHardness(1.3F), "mnc_NFenceGate");
		OFence = reg(new BlockFence_MNC(true).setBlockName("mnc_OFence").setHardness(5F).setResistance(200F), "mnc_OFence");
		OFenceGate = reg(new BlockFenceGate_MNC(true).setBlockName("mnc_OFenceGate").setHardness(5F).setResistance(200F), "mnc_OFenceGate");

		GameRegistry.registerTileEntity(TileEntityHellAltar.class, "HellAltar");
		HellAltar = reg(new BlockPentagram(TileEntityHellAltar.class).setBlockName("mnc_HellAltar").setHardness(2F), "mnc_HellAltar");

		GTrapDoor = reg(new BlockTrapDoor_MNC(Material.rock).setBlockName("mnc_GTrapDoor").setLightLevel(1F).setStepSound(Block.soundTypeGlass), "mnc_GTrapDoor");
		NTrapDoor = reg(new BlockTrapDoor_MNC(Material.rock).setBlockName("mnc_NTrapDoor").setHardness(2F).setResistance(10F), "mnc_NTrapDoor");
		GDoor = reg(new BlockDoor_MNC(Material.glass).setBlockName("mnc_GDoor").setLightLevel(1F).setHardness(0.6F).setStepSound(Block.soundTypeGlass), "mnc_GDoor");
		NDoor = reg(new BlockDoor_MNC(Material.rock).setBlockName("mnc_NDoor").setHardness(2F).setResistance(10F), "mnc_NDoor");
		GDoorHidden = reg(new BlockDoor_MNC_Hidden(Material.glass).setBlockName("mnc_GDoorHidden").setLightLevel(1F).setHardness(0.6F).setStepSound(Block.soundTypeGlass), "mnc_GDoorHidden");
		NDoorHidden = reg(new BlockDoor_MNC_Hidden(Material.rock).setBlockName("mnc_NDoorHidden").setHardness(2F).setResistance(10F), "mnc_NDoorHidden");

		Stature = reg(new BlockStature(TileEntityStatur.class).setBlockName("mnc_Stature"), ItemBlockStature.class, "mnc_Stature");

		Mud = reg(new BlockMud().setBlockName("mnc_Mud").setHardness(1F).setResistance(2F).setStepSound(Block.soundTypeSand), "mnc_Mud");
		SchleimStill = reg(new MNCBlockStationary(Material.lava).setBlockName("mnc_Schleim1").setLightLevel(0.5F), "mnc_Schleim1");
		SchleimFliess = reg(new MNCBlockFlowing(Material.lava).setBlockName("mnc_Schleim2").setLightLevel(0.5F), "mnc_Schleim2");
		nTNT = reg(new BlockNetherTNT().setBlockName("mnc_nTNT").setStepSound(Block.soundTypeSand), "mnc_nTNT");
		FireLog = reg(new BlockFireLog().setBlockName("mnc_FireLog").setHardness(7F).setLightLevel(0.3F).setResistance(4F), "mnc_FireLog");
		FireLeaf = reg(new BlockFireLeaf().setBlockName("mnc_FireLeaf").setLightLevel(0.3F).setHardness(0.5F), "mnc_FireLeaf");
		FireSapling = reg(new BlockFireSapling().setBlockName("mnc_FireSapling").setLightLevel(0.3F).setHardness(0.5F), "mnc_FireSapling");
		Netherplanks = reg(new MNCBlock(Material.wood).setBlockName("mnc_NWPlanks").setHardness(6F).setResistance(3.5F).setStepSound(Block.soundTypeWood), "mnc_NWPlanks");
		NWDoor = reg(new BlockDoor_MNC(Material.wood).setBlockName("mnc_NWDoor").setHardness(5F).setResistance(3.4F).setStepSound(Block.soundTypeWood), "mnc_NWDoor");
		NWFence = reg(new BlockFence_MNC(Material.wood, false).setBlockName("mnc_NWFence").setHardness(6F).setResistance(3.5F).setStepSound(Block.soundTypeWood), "mnc_NWFence");
		NWFenceGate = reg(new BlockFenceGate_MNC(false).setBlockName("mnc_NWFenceG").setHardness(6F).setResistance(3.5F).setStepSound(Block.soundTypeWood), "mnc_NWFenceG");
		NWStairs = reg(new MNCBlockTreppen(Netherplanks, false).setBlockName("mnc_NWStairs").setHardness(6F).setResistance(3.5F).setStepSound(Block.soundTypeWood), "mnc_NWStairs");
		NWSlab = reg(new BlockSlab(false).setBlockName("mnc_NWSlab").setHardness(6F).setResistance(3.5F).setStepSound(Block.soundTypeWood), "mnc_NWSlab");
		NWall = reg(new BlockWall_MNC(Blocks.nether_brick, false).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("mnc_netherWall"), "mnc_netherWall");
		GWall = reg(new BlockWall_MNC(Blocks.glowstone, false).setBlockName("mnc_GWall").setLightLevel(1F).setStepSound(Block.soundTypeGlass).setHardness(0.6F), "mnc_GWall");
		OWall = reg(new BlockWall_MNC(Blocks.obsidian, true).setBlockName("mnc_OWall").setHardness(5F).setResistance(200F), "mnc_OWall");
		NWWall = reg(new BlockWall_MNC(Netherplanks, false).setHardness(6F).setResistance(3.5F).setStepSound(Block.soundTypeWood).setBlockName("mnc_NWWall"), "mnc_NWWall");
		PortalCrazy = reg((BlockPortalCrazy) new BlockPortalCrazy().setBlockName("mnc_portal").setStepSound(Block.soundTypeGlass), "mnc_portal");
		deadDirt = reg(new MNCBlock(Material.ground).setBlockName("mnc_deadDirt").setStepSound(Block.soundTypeGravel), "mnc_deadDirt");
		deadGrass = reg(new BlockDeadGrass().setBlockName("mnc_deadGrass").setStepSound(Block.soundTypeGravel), "mnc_deadGrass");
		DeadStone = reg(new MNCBlock(Material.rock).setBlockName("mnc_deadStone").setStepSound(Block.soundTypeStone).setHardness(1.5F).setResistance(2.0F), "mnc_deadStone");
		orePlumbum = reg(new BlockOrePlumbum(Material.rock).setBlockName("mnc_orePlumbum").setHardness(4F).setResistance(6F), "mnc_orePlumbum");
		dungeonStone = reg(new BlockDungeonStone().setBlockName("mnc_dungeonStone").setLightLevel(0.2F), ItemBlockDungeon.class, "mnc_dungeonStone");
	}
}
