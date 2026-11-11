package Oneric.MNC.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.MinecraftForge;
import Oneric.MNC.common.CommonProxyMNC;
import Oneric.MNC.common.MNCItems;
import Oneric.MNC.common.MNCSoundManager;
import Oneric.MNC.common.MNCTickHandler;
import Oneric.MNC.common.StaturRender;
import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.entity.EntityFeuerBall;
import Oneric.MNC.entity.EntityHarpye;
import Oneric.MNC.entity.EntityIceBall;
import Oneric.MNC.entity.EntityIceGhost;
import Oneric.MNC.entity.EntityIcePigman;
import Oneric.MNC.entity.EntityLostSoul;
import Oneric.MNC.entity.EntityNetherArrow;
import Oneric.MNC.entity.EntitySlaughter;
import Oneric.MNC.entity.effect.MNCEntityLightningBolt;
import Oneric.MNC.entity.model.ModelFeuerBall;
import Oneric.MNC.entity.model.ModelIceGhost;
import Oneric.MNC.entity.model.ModelLostSoul;
import Oneric.MNC.entity.model.ModelSlaughter;
import Oneric.MNC.entity.render.RenderFeuerBall;
import Oneric.MNC.entity.render.RenderHarpye;
import Oneric.MNC.entity.render.RenderIceGhost;
import Oneric.MNC.entity.render.RenderLostSoul;
import Oneric.MNC.entity.render.RenderMNCBiped;
import Oneric.MNC.entity.render.RenderSlaughter;

public class ClientProxyMNC extends CommonProxyMNC {

	private Minecraft mc = Minecraft.getMinecraft();

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderThings()
	{
		ClientRegistry.registerKeyBinding(MNCKeyHandler.guiBinding);
		FMLCommonHandler.instance().bus().register(new MNCKeyHandler());

		RenderingRegistry.registerEntityRenderingHandler(EntityLostSoul.class, new RenderLostSoul(new ModelLostSoul(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySlaughter.class, new RenderSlaughter(new ModelSlaughter(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityIceGhost.class, new RenderIceGhost(new ModelIceGhost(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityIcePigman.class, new RenderMNCBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityHarpye.class, new RenderHarpye());
		RenderingRegistry.registerEntityRenderingHandler(MNCEntityLightningBolt.class, new RenderLightningBolt());

		RenderingRegistry.registerEntityRenderingHandler(EntityFeuerBall.class, new RenderFeuerBall(new ModelFeuerBall(), 1.0F));

		armor_nether = RenderingRegistry.addNewArmourRendererPrefix("nether");
		armor_glowstone = RenderingRegistry.addNewArmourRendererPrefix("glowstone");

		ClientRegistry.registerTileEntity(Oneric.MNC.common.TileEntityStatur.class, "Stature", new StaturRender());

		FMLCommonHandler.instance().bus().register(new MNCTickHandler());
	}

	public void initRenderPlayerAPI()
	{
	}

	public void registerPostRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherArrow.class, new RenderSnowball(MNCItems.NArrow, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityIceBall.class, new RenderSnowball(MNCItems.NArrow, 1));
	}

	@Override
	public void loadSounds()
	{
	}

	@Override
	public void registerSounds()
	{
		MinecraftForge.EVENT_BUS.register(new MNCSoundManager());
	}

	public void triggerAchievement(Achievement ach, EntityPlayer player)
	{
		try
		{
			if (player instanceof EntityClientPlayerMP)
			{
				EntityClientPlayerMP cp = (EntityClientPlayerMP)player;
				if (!cp.getStatFileWriter().hasAchievementUnlocked(ach))
				{
					if (ach.parentAchievement == null || cp.getStatFileWriter().hasAchievementUnlocked(ach.parentAchievement))
					{
						player.triggerAchievement(ach);
						player.worldObj.playSoundAtEntity(player, mod_netherbr.getId() + ":achievement.achiev", 1, 1);
					}
				}
			}
			else
			{
				player.triggerAchievement(ach);
			}
		}
		catch (Throwable ignored) {}
	}

	public String getClientSessionUsername()
	{
		return Minecraft.getMinecraft().getSession().getUsername();
	}
}
