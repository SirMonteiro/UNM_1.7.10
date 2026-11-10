package Oneric.MNC.common;

import Oneric.MNC.dimensions.BiomeGenCrazy;
import Oneric.MNC.entity.EntityFeuerBall;
import Oneric.MNC.entity.EntityHarpye;
import Oneric.MNC.entity.EntityIceBall;
import Oneric.MNC.entity.EntityIceGhost;
import Oneric.MNC.entity.EntityIcePigman;
import Oneric.MNC.entity.EntityLostSoul;
import Oneric.MNC.entity.EntityNetherArrow;
import Oneric.MNC.entity.EntitySlaughter;
import Oneric.MNC.entity.boss.EntityDaemonLord;
import Oneric.MNC.entity.effect.MNCEntityLightningBolt;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;

public class MNCEntities {

	public MNCEntities()
	{
		//Arrow
		EntityRegistry.registerModEntity(EntityNetherArrow.class, "NetherArrow", 1, mod_netherbr.mod, 250, 5, false);

		//IceBall
		EntityRegistry.registerModEntity(EntityIceBall.class, "IceBall", 2, mod_netherbr.mod, 250, 10, true);

		//LostSoul
		EntityRegistry.registerGlobalEntityID(EntityLostSoul.class, "LostSoul", EntityRegistry.findGlobalUniqueEntityId(), 0xcccccc, 0xeeeeee);
		EntityRegistry.addSpawn(EntityLostSoul.class, 50, 4, 9, EnumCreatureType.monster,
			BiomeGenBase.hell,
			BiomeGenBase.icePlains,
			MNCBiomes.biome_HellPilz,
			MNCBiomes.biome_IceHell
		);

		//Slaughter
		EntityRegistry.registerGlobalEntityID(EntitySlaughter.class, "Slaughter", EntityRegistry.findGlobalUniqueEntityId(), 0xdddddd, 0x115500);
		EntityRegistry.addSpawn(EntitySlaughter.class, 6, 1, 1, EnumCreatureType.monster,
			BiomeGenBase.hell,
			MNCBiomes.biome_Blackstone,
			MNCBiomes.biome_HellPilz
		);

		//IceGhost
		EntityRegistry.registerGlobalEntityID(EntityIceGhost.class, "IceGhost", EntityRegistry.findGlobalUniqueEntityId(), 0x99aaff, 0x0000dd);
		EntityRegistry.addSpawn(EntityIceGhost.class, 30, 3, 5, EnumCreatureType.monster, MNCBiomes.biome_IceHell);

		//IcePigman
		EntityRegistry.registerGlobalEntityID(EntityIcePigman.class, "IcePigman", EntityRegistry.findGlobalUniqueEntityId(), 0xff8899, 0x99aaff);
		EntityRegistry.addSpawn(EntityIcePigman.class, 79, 4, 8, EnumCreatureType.monster, MNCBiomes.biome_IceHell);

		//Harpye
		EntityRegistry.registerGlobalEntityID(EntityHarpye.class, "harpye", EntityRegistry.findGlobalUniqueEntityId(), 0x190707, 0x8a0808);
		if (MNCBiomes.crazy != null) {
			EntityRegistry.addSpawn(EntityHarpye.class, 79, 4, 8, EnumCreatureType.creature, MNCBiomes.crazy);
		}

		//LightningBolt
		EntityRegistry.registerGlobalEntityID(MNCEntityLightningBolt.class, "mnc_blitz", EntityRegistry.findGlobalUniqueEntityId());

		//DaemonLord
		EntityRegistry.registerGlobalEntityID(EntityDaemonLord.class, "DaemonLord", EntityRegistry.findGlobalUniqueEntityId(), 0xcccccc, 0xeeeeee);

		//FeuerBall
		EntityRegistry.registerGlobalEntityID(EntityFeuerBall.class, "FeuerBall", EntityRegistry.findGlobalUniqueEntityId(), 0xcccccc, 0xeeeeee);
	}
}
