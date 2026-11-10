package Oneric.MNC.common;

import Oneric.MNC.packets.DonatorPacketSender;
import Oneric.MNC.packets.PacketDispatcher;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;

public class MNCConectionHandler {

	@SubscribeEvent
	public void onClientDisconnected(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
	{
		try
		{
			if (Minecraft.getMinecraft() != null && !Minecraft.getMinecraft().isSingleplayer())
			{
				PacketDispatcher.sendPacketToAllPlayers(DonatorPacketSender.sendRemoveDonator(mod_netherbr.proxy.getClientSessionUsername()));
			}
		}
		catch (Throwable ignored) {}
	}

	@SubscribeEvent
	public void onClientConnected(FMLNetworkEvent.ClientConnectedToServerEvent event)
	{
		try
		{
			PacketDispatcher.sendPacketToAllPlayers(DonatorPacketSender.sendAddDonatorPacket(mod_netherbr.proxy.getClientSessionUsername()));
			PacketDispatcher.sendPacketToAllPlayers(DonatorPacketSender.sendDonatorChoiceAdd(mod_netherbr.proxy.getClientSessionUsername(), "CAPE BLUE"));
			PacketDispatcher.sendPacketToAllPlayers(DonatorPacketSender.sendDonatorChoiceAdd(mod_netherbr.proxy.getClientSessionUsername(), "HORN CLASSIC"));
			PacketDispatcher.sendPacketToAllPlayers(DonatorPacketSender.sendDonatorRequest(mod_netherbr.proxy.getClientSessionUsername()));
		}
		catch (Throwable ignored) {}
	}
}
