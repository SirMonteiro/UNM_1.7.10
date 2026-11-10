package Oneric.MNC.common;

import Oneric.MNC.item.ItemDoor_MNC;
import Oneric.MNC.item.ItemDoor_MNC_Hidden;
import Oneric.MNC.item.ItemDreizack;
import Oneric.MNC.item.ItemFoodHellPilz;
import Oneric.MNC.item.ItemGG;
import Oneric.MNC.item.ItemNetherArrow;
import Oneric.MNC.item.ItemNetherBow;
import Oneric.MNC.item.ItemNetherBucket;
import Oneric.MNC.item.ItemPickaxeGrav;
import Oneric.MNC.item.ItemPlumbumHammer;
import Oneric.MNC.item.MNCItem;
import Oneric.MNC.item.MNCItemArmor;
import Oneric.MNC.item.MNCItemAxe;
import Oneric.MNC.item.MNCItemHoe;
import Oneric.MNC.item.MNCItemPickaxe;
import Oneric.MNC.item.MNCItemSeeds;
import Oneric.MNC.item.MNCItemSpade;
import Oneric.MNC.item.MNCItemSword;
import Oneric.MNC.item.MNCItem_epic;
import Oneric.MNC.item.MNCItem_rare;
import Oneric.MNC.item.MNCItem_uncommon;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class MNCItems
{
    // Items
    public static Item HellPilz_Samen;
    public static Item HellPilz;
    public static Item GGold_i;
    public static Item GGlowstone_i;
    public static Item GDoorItem;
    public static Item NDoorItem;
    public static Item GDoorHiddenItem;
    public static Item NDoorHiddenItem;
    public static Item netheringot;
    public static Item epicingot;
    public static Item NArrow;
    public static Item Netherstick;
    public static Item strange;
    public static Item FireGem;

    public static Item NetherSword;
    public static Item NetherPickaxe;
    public static Item NetherShoevel;
    public static Item NetherAxe;
    public static Item NetherHoe;
    public static Item GlowSword;
    public static Item GlowPickaxe;
    public static Item GlowShoevel;
    public static Item GlowAxe;
    public static Item GlowHoe;
    public static Item diablo;

    public static Item NetherHelmet;
    public static Item NetherPlate;
    public static Item NetherLeg;
    public static Item NetherBoots;
    public static Item GlowHelmet;
    public static Item GlowPlate;
    public static Item GlowLeg;
    public static Item GlowBoots;

    public static Item Stature_i;
    public static Item NBow;
    public static Item NBucketEmpty;
    public static Item NBucketFull;

    public static Item NWDoorItem;
    public static Item ingotPlumbum;
    public static Item hammerPlumbum;

    public static Item pickaxeGrav;

    public MNCItems()
    {
        HellPilz_Samen = reg(new MNCItemSeeds(MNCBlocks.HellPilz_Block, Blocks.netherrack).setUnlocalizedName("mnc_hellPilzSeed").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials));
        HellPilz = reg(new ItemFoodHellPilz(1, 200F, false).setUnlocalizedName("mnc_NetherMushroom").setMaxStackSize(16).setCreativeTab(CreativeTabs.tabFood));

        GGold_i = reg(new ItemGG(MNCBlocks.GGold).setUnlocalizedName("mnc_growing-Gold").setCreativeTab(CreativeTabs.tabMaterials));
        GGlowstone_i = reg(new ItemGG(MNCBlocks.GGlowstone).setUnlocalizedName("mnc_growing-Glowstone").setCreativeTab(CreativeTabs.tabMaterials));
        GDoorItem = reg(new ItemDoor_MNC(MNCBlocks.GDoor).setUnlocalizedName("mnc_gDoor"));
        NDoorItem = reg(new ItemDoor_MNC(MNCBlocks.NDoor).setUnlocalizedName("mnc_nDoor"));
        GDoorHiddenItem = reg(new ItemDoor_MNC_Hidden(MNCBlocks.GDoorHidden).setUnlocalizedName("mnc_gHDoor"));
        NDoorHiddenItem = reg(new ItemDoor_MNC_Hidden(MNCBlocks.NDoorHidden).setUnlocalizedName("mnc_nHDoor"));
        netheringot = reg(new MNCItem().setUnlocalizedName("mnc_ignot1").setCreativeTab(CreativeTabs.tabMaterials));
        epicingot = reg(new MNCItem().setUnlocalizedName("mnc_ignot2").setCreativeTab(CreativeTabs.tabMaterials));
        NArrow = reg(new ItemNetherArrow().setUnlocalizedName("mnc_NArrow").setCreativeTab(CreativeTabs.tabCombat));
        Netherstick = reg(new MNCItem_uncommon().setUnlocalizedName("mnc_Netherstick").setCreativeTab(CreativeTabs.tabMaterials));
        strange = reg(new MNCItem_rare().setUnlocalizedName("mnc_strange").setCreativeTab(CreativeTabs.tabMaterials));
        FireGem = reg(new MNCItem_epic().setUnlocalizedName("mnc_FireGem").setCreativeTab(CreativeTabs.tabMaterials));

        NetherSword = reg(new MNCItemSword(mod_netherbr.epicNetherT, false).setUnlocalizedName("mnc_nSword").setCreativeTab(CreativeTabs.tabCombat));
        NetherPickaxe = reg(new MNCItemPickaxe(mod_netherbr.epicNetherT).setUnlocalizedName("mnc_nPickaxe").setCreativeTab(CreativeTabs.tabTools));
        NetherShoevel = reg(new MNCItemSpade(mod_netherbr.epicNetherT).setUnlocalizedName("mnc_nShoevel").setCreativeTab(CreativeTabs.tabTools));
        NetherAxe = reg(new MNCItemAxe(mod_netherbr.epicNetherT).setUnlocalizedName("mnc_nAxe").setCreativeTab(CreativeTabs.tabTools));
        NetherHoe = reg(new MNCItemHoe(mod_netherbr.epicNetherT).setUnlocalizedName("mnc_nHoe").setCreativeTab(CreativeTabs.tabTools));
        GlowSword = reg(new MNCItemSword(mod_netherbr.glowstoneT, true).setUnlocalizedName("mnc_gSword").setCreativeTab(CreativeTabs.tabCombat));
        GlowPickaxe = reg(new MNCItemPickaxe(mod_netherbr.glowstoneT).setUnlocalizedName("mnc_gPickaxe").setCreativeTab(CreativeTabs.tabTools));
        GlowShoevel = reg(new MNCItemSpade(mod_netherbr.glowstoneT).setUnlocalizedName("mnc_gShoevel").setCreativeTab(CreativeTabs.tabTools));
        GlowAxe = reg(new MNCItemAxe(mod_netherbr.glowstoneT).setUnlocalizedName("mnc_gAxe").setCreativeTab(CreativeTabs.tabTools));
        GlowHoe = reg(new MNCItemHoe(mod_netherbr.glowstoneT).setUnlocalizedName("mnc_gHoe").setCreativeTab(CreativeTabs.tabTools));
        diablo = reg(new ItemDreizack(mod_netherbr.diabloT).setUnlocalizedName("mnc_diablo").setCreativeTab(CreativeTabs.tabCombat));

        NetherHelmet = reg(new MNCItemArmor(mod_netherbr.epicNetherA, mod_netherbr.proxy.armor_nether, 0).setUnlocalizedName("mnc_nHelmet").setCreativeTab(CreativeTabs.tabCombat));
        NetherPlate = reg(new MNCItemArmor(mod_netherbr.epicNetherA, mod_netherbr.proxy.armor_nether, 1).setUnlocalizedName("mnc_nPlate").setCreativeTab(CreativeTabs.tabCombat));
        NetherLeg = reg(new MNCItemArmor(mod_netherbr.epicNetherA, mod_netherbr.proxy.armor_nether, 2).setUnlocalizedName("mnc_nLeg").setCreativeTab(CreativeTabs.tabCombat));
        NetherBoots = reg(new MNCItemArmor(mod_netherbr.epicNetherA, mod_netherbr.proxy.armor_nether, 3).setUnlocalizedName("mnc_nBoots").setCreativeTab(CreativeTabs.tabCombat));

        GlowHelmet = reg(new MNCItemArmor(mod_netherbr.glowstoneA, mod_netherbr.proxy.armor_glowstone, 0).setUnlocalizedName("mnc_gHelmet").setCreativeTab(CreativeTabs.tabCombat));
        GlowPlate = reg(new MNCItemArmor(mod_netherbr.glowstoneA, mod_netherbr.proxy.armor_glowstone, 1).setUnlocalizedName("mnc_gPlate").setCreativeTab(CreativeTabs.tabCombat));
        GlowLeg = reg(new MNCItemArmor(mod_netherbr.glowstoneA, mod_netherbr.proxy.armor_glowstone, 2).setUnlocalizedName("mnc_gLeg").setCreativeTab(CreativeTabs.tabCombat));
        GlowBoots = reg(new MNCItemArmor(mod_netherbr.glowstoneA, mod_netherbr.proxy.armor_glowstone, 3).setUnlocalizedName("mnc_gBoots").setCreativeTab(CreativeTabs.tabCombat));

        Stature_i = reg(new ItemGG(MNCBlocks.Stature).setUnlocalizedName("mnc_Statue").setCreativeTab(CreativeTabs.tabDecorations));
        NBow = reg(new ItemNetherBow().setUnlocalizedName("mnc_NBow").setCreativeTab(CreativeTabs.tabCombat));
        NBucketEmpty = reg(new ItemNetherBucket(null).setUnlocalizedName("mnc_NBucketEmpty").setCreativeTab(CreativeTabs.tabMisc));
        NBucketFull = reg(new ItemNetherBucket(MNCBlocks.SchleimFliess).setUnlocalizedName("mnc_NBucketFull").setCreativeTab(CreativeTabs.tabMisc));

        NWDoorItem = reg(new ItemDoor_MNC(MNCBlocks.NWDoor).setUnlocalizedName("mnc_NWDoorItem"));
        ingotPlumbum = reg(new MNCItem().setUnlocalizedName("mnc_ignotPb").setCreativeTab(CreativeTabs.tabMaterials));
        hammerPlumbum = reg(new ItemPlumbumHammer().setUnlocalizedName("mnc_hammerPb").setCreativeTab(CreativeTabs.tabTools));

        pickaxeGrav = reg(new ItemPickaxeGrav(mod_netherbr.gravityT).setUnlocalizedName("mnc_pickaxeGrav").setCreativeTab(CreativeTabs.tabTools));
    }

    private static Item reg(Item item)
    {
        String name = item.getUnlocalizedName().substring(5);
        GameRegistry.registerItem(item, name);
        item.setTextureName(mod_netherbr.getId() + ":" + name);
        return item;
    }
}
