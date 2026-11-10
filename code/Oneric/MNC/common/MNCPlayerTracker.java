package Oneric.MNC.common;

import Oneric.MNC.donator.EnumChoiceType;
import Oneric.MNC.donator.choices.DonatorChoice;
import Oneric.MNC.packets.DonatorPacketSender;
import Oneric.MNC.packets.PacketDispatcher;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class MNCPlayerTracker {

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server == null) return;
		ServerConfigurationManager confMan = server.getConfigurationManager();
		if (confMan == null) return;

		String playerUsername = player.getCommandSenderName();

		for (int var4 = 0; var4 < confMan.playerEntityList.size(); ++var4)
		{
			EntityPlayerMP cPlayer = (EntityPlayerMP)confMan.playerEntityList.get(var4);
			String cUsername = cPlayer.getCommandSenderName();

			if (mod_netherbr.donatorList.checkDonator(cUsername))
			{
				if (player instanceof EntityPlayerMP)
				{
					PacketDispatcher.sendPacketToPlayer(DonatorPacketSender.sendAddDonatorPacket(cUsername), (EntityPlayerMP)player);
					PacketDispatcher.sendPacketToPlayer(DonatorPacketSender.sendDonatorChoiceAdd(cUsername, ((DonatorChoice)mod_netherbr.donatorChoices.capeChoices.get(0)).name), (EntityPlayerMP)player);
					PacketDispatcher.sendPacketToPlayer(DonatorPacketSender.sendDonatorChoiceAdd(cUsername, ((DonatorChoice)mod_netherbr.donatorChoices.hornChoices.get(0)).name), (EntityPlayerMP)player);
				}
				PacketDispatcher.sendPacketToPlayer(DonatorPacketSender.sendDonatorRequest(playerUsername), cPlayer);
			}

			PacketDispatcher.sendPacketToPlayer(DonatorPacketSender.sendAddDonatorPacket(playerUsername), cPlayer);
			if (mod_netherbr.donatorList.iDonator && mod_netherbr.donatorList.getDonatorByName(playerUsername) != null)
			{
				if (mod_netherbr.donatorList.getDonatorByName(playerUsername).hasChooosen(EnumChoiceType.CAPE))
					PacketDispatcher.sendPacketToPlayer(DonatorPacketSender.sendDonatorChoiceAdd(playerUsername, mod_netherbr.donatorList.getDonatorByName(playerUsername).getChoiceForType(EnumChoiceType.CAPE).name), cPlayer);
				if (mod_netherbr.donatorList.getDonatorByName(playerUsername).hasChooosen(EnumChoiceType.HORN))
					PacketDispatcher.sendPacketToPlayer(DonatorPacketSender.sendDonatorChoiceAdd(playerUsername, mod_netherbr.donatorList.getDonatorByName(playerUsername).getChoiceForType(EnumChoiceType.HORN).name), cPlayer);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
		EntityPlayer player = event.player;
		String username = player.getCommandSenderName();
		if (mod_netherbr.donatorList.isDonator(username))
		{
			PacketDispatcher.sendPacketToAllPlayers(DonatorPacketSender.sendRemoveDonator(username));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		EntityPlayer player = event.player;
		if (player != null && mod_netherbr.achievementsEnabled)
		{
			if (player.dimension == -1)
			{
				mod_netherbr.proxy.triggerAchievement(MNCAchievements.enterTheNether, player);
			}
			else if (player.dimension == MNCBiomes.CrazyDimID)
			{
				mod_netherbr.proxy.triggerAchievement(MNCAchievements.enterTheFallenSky, player);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
	}
}
