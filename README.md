# The Ultimate Nether Mod (Minecraft 1.7.10 Port)

An unofficial port of **The Ultimate Nether Mod** (`v1.8.6`) to **Minecraft 1.7.10 (Forge)**, originally created by [Oneric](https://github.com/TheOneric).

> **Port & AI Disclosure:**
> This is a port of **UNM 1.6.4 v1.8.6**, originally published on the [Minecraft Forum](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1285649-1-6-4-forge-the-ultimate-nether-mod-ssp-smp) ([Wayback Machine Archive](https://web.archive.org/web/20250627223330/https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1285649-1-6-4-forge-the-ultimate-nether-mod-ssp-smp)) with source code released on [GitHub](https://github.com/TheOneric/UNM_1.6.4).
> **This port was made using AI** to update the legacy 1.6.4 codebase to 1.7.10 Forge.

---

## About the Mod

Originally started back in Minecraft 1.2.5 under the name *More Nether Crafting Recipes* (which is why you will still see `MNC` prefixes in the internal IDs and code), Oneric expanded the project over several versions into a full Nether and dimension expansion. 

It adds new biomes, hostile and passive mobs, dungeons, procedural mazes, crop mechanics, gear tiers, and an extra dimension called **The Fallen Sky**.

---

## What It Adds

### Biomes & Dimensions
* **The Fallen Sky (Dimension):** A sky-themed dimension accessed by building a portal and lighting it with Flint and Steel. Home to Dead Stone, Dead Dirt, Dead Grass, Smog, Plumbum Ore, and Harpies.
* **Blackstone Biome:** Features Blackstone surface blocks that damage entities walking across them, alongside Giant Nether Mushrooms.
* **Ice-Hell Biome:** A frozen twist on the Nether where Snowmen, Ice Ghosts, and Ice Pigmen spawn.
* **Fire Trees:** Giant burning trees that generate in the Nether. Fire Logs can be crafted into Nether Planks, sticks, doors, fences, walls, slabs, and fence gates.
* **Mud Lakes & Nether Slime:** Hidden mud pools generate in snowy biomes—anything that steps into deep mud slowly sinks and suffocates. Nether Slime acts as a green, decorative alternative to lava.

### Structures
* **Dynamic Nether Mazes:** Generated procedurally so the layout changes every time. Corridor chests hold food, traps (including fake TNT) guard the hallways, and the central chamber houses a Statue alongside dungeon-tier loot. Maze rarity can be adjusted in the config.
* **Nether Dungeons:** Scattered across the Nether with custom loot chests.

### Mobs
* **The Slaughter:** A fast, heavy-hitting enemy with eerie audio cues. Has a 1-in-3 chance to drop his Scythe on death.
* **Lost Soul:** Floating, screaming spirits that remain passive until provoked. They can also transmute Iron into Gold.
* **Ice Pigman & Ice Ghost:** Inhabitants of the Ice-Hell biome. On **Hard** difficulty, hitting an Ice Pigman gives it a rare chance to call down a lightning bolt with its Glowstone Sword.
* **Harpye (Harpyen):** Passive flying creatures native to The Fallen Sky, functioning much like Overworld bats.

### Blocks, Crops & Workstations
* **The Hell Altar:** A furnace-style workstation fueled by Ghast Tears, Magma Cream, Blaze Rods, or *The Strange Thing*. Used to create **Enchanted Soul Sand**, which boosts your walking speed.
* **Growing Gold & Growing Glowstone:** Resource plants that grow on Blackstone next to lava, yielding gold nuggets and glowstone dust.
* **Nether Mushrooms:** Harvest Giant Nether Mushrooms with a pickaxe to get seeds, then grow them through three stages (supports bonemeal).
* **Hidden Doors & Decorative Blocks:** Smelt standard doors to craft hidden doors that blend in with surrounding blocks. Also adds Glowstone, Obsidian, and Nether Brick fences, gates, stairs, and slabs.
* **New Ores:** **Nether Gold** (found in the Nether) and **Plumbum Ore** (found in The Fallen Sky).

### Tools, Weapons & Armor
* **Glowstone Gear:** Matches gold in speed and strength, breaking after 26 uses. Right-clicking with the **Glowstone Sword** fires a lightning bolt.
* **Nether & Super Epic Nether Gear:** End-game equipment stronger than diamond, lasting up to 3,000 uses.
* **Scythe of the Slaughter:** Deals 10 hearts of damage (250 uses) and transforms a fully grown Nether Mushroom into a Giant Nether Mushroom on right-click.
* **Nether Bow & Nether Arrows:** Fires flaming arrows that ignite mobs on hit (3,000 uses with a short cooldown).
* **Plumbum Hammer:** Low direct damage, but inflicts poison on hit.

---

## Notes for the 1.7.10 Port

* **No More Numeric ID Conflicts:** In the original 1.6.4 release, players frequently ran into crashes if Blackstone generated with a block ID above 255. Because 1.7.10 uses string-based registries instead of manual numeric IDs, that crash no longer applies.
* **Legacy Quirks:** Because this port stays faithful to the 1.6.4 source code, some unfinished features from Oneric's original build remain as-is (for example, the Statue is primarily decorative, and legacy donator cape/cosmetic toggles reset when rejoining a world).
* **Reporting Port Bugs:** Since the 1.6.4-to-1.7.10 migration was assisted by AI, please open an issue on the repository if you encounter any crashes, missing textures, or broken recipes specific to 1.7.10.

---

## Original Links & Archival Credits

All original design, code, and assets belong to **Oneric**:
* **Original Minecraft Forum Thread:** [minecraftforum.net](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1285649-1-6-4-forge-the-ultimate-nether-mod-ssp-smp)
* **Archived Forum Post (Backup):** [web.archive.org](https://web.archive.org/web/20250627223330/https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1285649-1-6-4-forge-the-ultimate-nether-mod-ssp-smp)
* **Original 1.6.4 Source Code:** [github.com/TheOneric/UNM_1.6.4](https://github.com/TheOneric/UNM_1.6.4)
* **Unfinished 1.10 Rewrite:** [github.com/TheOneric/UNM](https://github.com/TheOneric/UNM)

---

## License & Modpacks

Per Oneric's open-source release terms:
* **Source Code:** Licensed under **LGPLv3 or later** (with the special linking exception for Minecraft).
* **Assets (Textures, Sounds, Models):** Original non-code resources are licensed under **CC-BY-SA 3.0 or later**.
* **Modpacks:** You are free to include this mod in any public or private modpack without asking for permission.