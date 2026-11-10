package Oneric.MNC.packets;

import Oneric.MNC.common.mod_netherbr;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * 1.7.10 network channel (Netty / SimpleNetworkWrapper) replacing the old
 * Packet250CustomPayload based system.
 */
public class MNCNetwork
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("onerics_mnc");

    public static void init()
    {
        INSTANCE.registerMessage(DonatorMessage.Handler.class, DonatorMessage.class, 0, Side.SERVER);
        INSTANCE.registerMessage(DonatorMessage.Handler.class, DonatorMessage.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(ThunderMessage.Handler.class, ThunderMessage.class, 1, Side.SERVER);
    }
}