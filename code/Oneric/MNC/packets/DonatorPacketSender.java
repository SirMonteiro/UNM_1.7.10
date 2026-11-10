package Oneric.MNC.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**
 * Factory for the donator protocol messages. All methods return an
 * {@link IMessage} ready to be handed to {@link MNCNetwork#INSTANCE}.
 */
public class DonatorPacketSender
{
    public static IMessage sendAddDonatorPacket(String username)
    {
        return new DonatorMessage(DonatorMessage.ADD_DONATOR, username, "");
    }

    public static IMessage sendRemoveDonator(String username)
    {
        return new DonatorMessage(DonatorMessage.REMOVE_DONATOR, username, "");
    }

    public static IMessage sendDonatorChoiceAdd(String username, String choiceName)
    {
        return new DonatorMessage(DonatorMessage.ADD_CHOICE, username, choiceName);
    }

    public static IMessage sendDonatorChoiceRemove(String username, String choiceTypeName)
    {
        return new DonatorMessage(DonatorMessage.REMOVE_CHOICE, username, choiceTypeName);
    }

    public static IMessage sendServer_DonatorAddChoice(String username, String choiceName)
    {
        return new DonatorMessage(DonatorMessage.SERVER_ADD_CHOICE, username, choiceName);
    }

    public static IMessage sendServer_DonatorRemoveChoice(String username, String choiceTypeName)
    {
        return new DonatorMessage(DonatorMessage.SERVER_REMOVE_CHOICE, username, choiceTypeName);
    }

    public static IMessage sendRequest(String username)
    {
        return new DonatorMessage(DonatorMessage.REQUEST, username, "");
    }

    public static IMessage sendDonatorRequest(String username)
    {
        return sendRequest(username);
    }
}