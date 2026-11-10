package Oneric.MNC.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayerMP;

/** Small 1.7.10 replacement for the removed FML PacketDispatcher helper. */
public class PacketDispatcher
{
    public static void sendPacketToServer(IMessage message)
    {
        MNCNetwork.INSTANCE.sendToServer(message);
    }

    public static void sendPacketToAllPlayers(IMessage message)
    {
        MNCNetwork.INSTANCE.sendToAll(message);
    }

    public static void sendPacketToPlayer(IMessage message, EntityPlayerMP player)
    {
        MNCNetwork.INSTANCE.sendTo(message, player);
    }
}