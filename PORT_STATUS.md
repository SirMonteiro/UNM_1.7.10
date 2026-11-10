# PORT STATUS — Ultimate Nether Mod 1.6.4 → Forge 1.7.10

**Status:** **PORT COMPLETE & BUILD SUCCESSFUL** (2026-11-10)
- `compileJava`: **0 errors**
- `./gradlew build --no-daemon`: **BUILD SUCCESSFUL**
- Final reobfuscated deliverable: `build/libs/UltimateNetherMod-1.8.6.jar` (350 KB, verified with `unzip -l` and `javap` SRG reobfuscation check).

---

## 1. Deliverables
- **Reobfuscated Production Jar:** `build/libs/UltimateNetherMod-1.8.6.jar`
- **Dev Jar (deobf names):** `build/libs/UltimateNetherMod-1.8.6-dev.jar`
- **Sources Jar:** `build/libs/UltimateNetherMod-1.8.6-sources.jar`

---

## 2. Toolchain
- **Gradle:** 7.4.2 wrapper
- **ForgeGradle:** patched `com.anatawa12.forge:ForgeGradle:1.2-1.1.2-SNAPSHOT` in `~/.m2/repository` (fixes JDK zlib compressed size bug during `applyBinPatches`)
- **JDK:** OpenJDK 8 (`/usr/lib/jvm/java-8-openjdk`)
- **Forge Target:** `1.7.10-10.13.4.1614-1.7.10`

---

## 3. Scope of Port & Architecture Changes

### A. Missing Class Reconstruction (~54 classes)
- **`code/Oneric/MNC/item/` (21 classes):** `MNCItem`, `MNCItem_uncommon`, `MNCItem_rare`, `MNCItem_epic`, `MNCItemSword`, `MNCItemPickaxe`, `MNCItemSpade`, `MNCItemAxe`, `MNCItemHoe`, `MNCItemArmor`, `MNCItemSeeds`, `ItemFoodHellPilz`, `ItemGG`, `ItemDoor_MNC`, `ItemDoor_MNC_Hidden`, `ItemNetherArrow`, `ItemNetherBow`, `ItemNetherBucket`, `ItemDreizack`, `ItemPlumbumHammer`, `ItemPickaxeGrav`.
- **`code/Oneric/MNC/packets/` (5 classes):** `MNCNetwork` (`SimpleNetworkWrapper` channel `onerics_mnc`), `DonatorMessage` + Handler, `ThunderMessage` + Handler, `DonatorPacketSender`, `PacketDispatcher`.
- **`code/Oneric/MNC/world/gen/` (5 classes):** `WorldGenFireTree`, `WorldGenDeadTree`, `WorldGenClouds`, `WorldGenMineableMadness`, `MNCWorldGen` (`IWorldGenerator`).
- **`code/Oneric/MNC/entity/`:** `IBoss`, `EnumBoss`, `EntitySlaughter`, `EntityLostSoul`, `EntityIceGhost`, `EntityIcePigman`, `EntityNetherArrow`, `EntityDaemonLord`, `EntityHellStature`.
- **`code/Oneric/MNC/entity/model/` (5 classes):** `ModelSlaughter`, `ModelLostSoul`, `ModelIceGhost`, `ModelDaemonLord`, `ModelFeuerBall`.
- **`code/Oneric/MNC/entity/render/` (6 classes):** `RenderSlaughter`, `RenderLostSoul`, `RenderIceGhost`, `RenderMNCBiped`, `RenderHarpye`, `RenderFeuerBall`.

### B. Original 100 Source Files Ported
1. **Dimensions:**
   - `WorldTypeCrazy`: 1.7.10 WorldType constructor (`super("CRAZY")`).
   - `WorldChunkManagerCrazy`: Replaced removed `getTemperatures` with `getBiomesForGeneration`.
   - `BiomeGenCrazy`: Top/filler blocks converted to `Block`.
   - `TeleporterCrazy`: Cleaned inner PortalPosition allocation, `Blocks.air`, `getBlock(...).getMaterial()`.
   - `ChunkProviderCrazy`: Replaced `byte[]` arrays with `Block[]` (32768 elements), removed all `Aether*` dependencies, implemented complete 1.7.10 `IChunkProvider` (`func_147416_a`, etc.).
2. **Central Orchestration & Mod Lifecycle:**
   - `mod_netherbr`: Removed `@NetworkMod`, migrated to `@Mod.EventHandler`, initialized `MNCNetwork`, registered buses on `FMLCommonHandler.instance().bus()` and `MinecraftForge.EVENT_BUS`, removed hardcoded item/block IDs.
   - `MNCBlocks`: Replaced all constructor invocations to 1.7.10 signatures without numeric block IDs; registered all blocks via `GameRegistry.registerBlock(block, name)` and `GameRegistry.registerBlock(block, ItemBlock.class, name)`; stripped old `LanguageRegistry` calls.
   - `MNCItems`: Registered items with `GameRegistry.registerItem(item, name)`.
   - `MNCEntities`: Registered mod and global entities via `EntityRegistry`.
   - `MNCPlayerTracker` / `MNCConectionHandler`: Rewritten using `@SubscribeEvent` on `FMLCommonHandler.instance().bus()` / `cpw.mods.fml.common.gameevent.PlayerEvent`.
   - `MNCTickHandler`: Rewritten using `TickEvent.ClientTickEvent` and `RenderTickEvent`.
   - `MNCEvent`: Updated `@SubscribeEvent`, lowercase `event.x, event.y, event.z`.
   - `ClientProxyMNC`: Gutted RenderPlayerAPI; registered client renderers, keybindings, and ticks.
3. **Block Subsystem:**
   - Stripped `int id` parameters from all `Block` constructors (`super(Material)` or specific sub-signatures).
   - `MNCBlockFlowing` and `MNCBlockStationary`: Rewritten extending `net.minecraft.block.BlockLiquid` with proper `MNCBlocks.SchleimStill` / `MNCBlocks.SchleimFliess` references.
   - `BlockHellPilz`: Converted to extend `BlockBush`, properly overriding `canPlaceBlockOn`, `canBlockStay`, `canPlaceBlockAt`, and `getItemDropped`.
   - `BlockDeadGrass`: Implemented 1.7.10 `updateTick`, `getItemDropped`, and side-specific `getIcon`.
   - `BlockNetherGold`, `BlockPilz`, `BlackStone`: Fixed `isNormalCube` override (non-static) and `getItemDropped`.
   - `BlockNetherTNT`: Fixed `onNeighborBlockChange(World, int, int, int, Block)`, gravel icon delegation.
   - `BlockPentagram`: ITileEntityProvider `createNewTileEntity(World, int)`, container item drops.
   - `BlockStature`: `createNewTileEntity(World, int)`, `onBlockPlacedBy` using `MathHelper.floor_double`.
   - `BlockFireLeaf`: Implemented 1.7.10 `IShearable` with `IBlockAccess`.
   - `BlockFireLog`: Implemented `canSustainLeaves(IBlockAccess, int, int, int)`.
   - `ItemBlockDungeon` & `ItemBlockStature`: Constructors `super(Block)` and `getUnlocalizedName(ItemStack)`.
4. **Lang & Assets:**
   - `en_US.lang` and `de_DE.lang`: Added all block `tile.*.name` entries and entity `entity.*.name` entries. Converted to clean UTF-8 text with LF line endings.

---

## 4. Build Commands
```bash
cd /home/gabriel/projects/UNM_1.6.4
JAVA_HOME=/usr/lib/jvm/java-8-openjdk ./gradlew clean compileJava --no-daemon
JAVA_HOME=/usr/lib/jvm/java-8-openjdk ./gradlew build --no-daemon
```
