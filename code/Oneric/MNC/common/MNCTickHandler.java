package Oneric.MNC.common;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ChatComponentText;

public class MNCTickHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event)
	{
		if (event.phase != TickEvent.Phase.END) return;

		if (mod_netherbr.currentBoss != null)
		{
			mod_netherbr.renderManager.renderBossHP();
			if (mod_netherbr.currentBoss.getBossHP() <= 0)
			{
				mod_netherbr.currentBoss = null;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		if (event.phase != TickEvent.Phase.END) return;

		Minecraft mc = Minecraft.getMinecraft();
		if (mc == null) return;

		GuiScreen guiscreen = mc.currentScreen;
		if (guiscreen != null)
		{
			onTickInGUI(guiscreen);
		}
		else
		{
			onTickInGame();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onTickInGUI(GuiScreen guiscreen)
	{
		if (guiscreen instanceof GuiMainMenu || guiscreen instanceof GuiSelectWorld || guiscreen instanceof GuiScreenServerList)
		{
			if (mod_netherbr.updateCheck && mod_netherbr.newVersionAviable)
			{
				if (!mod_netherbr.reportNewVersion)
				{
					mod_netherbr.reportNewVersion = true;
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void onTickInGame()
	{
		if (mod_netherbr.updateCheck && mod_netherbr.newVersionAviable)
		{
			EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
			if (player != null)
			{
				if (mod_netherbr.reportNewVersion)
				{
					player.addChatMessage(new ChatComponentText("A new Version of the 'UltimateNetherMod' is available ! "));
					mod_netherbr.reportNewVersion = false;
				}
			}
		}
	}
}
