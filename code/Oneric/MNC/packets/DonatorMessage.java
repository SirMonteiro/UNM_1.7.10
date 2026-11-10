package Oneric.MNC.packets;

import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.donator.Donator;
import Oneric.MNC.donator.EnumChoiceType;
import Oneric.MNC.donator.choices.DonatorChoice;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;

/**
 * Donator / cosmetic synchronisation packet.
 * Replaces the old "MNC-Donator" Packet250CustomPayload protocol.
 */
public class DonatorMessage implements IMessage
{
    public static final byte ADD_DONATOR = 0;
    public static final byte REMOVE_DONATOR = 1;
    public static final byte ADD_CHOICE = 2;
    public static final byte REMOVE_CHOICE = 3;
    public static final byte SERVER_ADD_CHOICE = 4;
    public static final byte SERVER_REMOVE_CHOICE = 5;
    public static final byte REQUEST = 6;

    private byte op;
    private String user = "";
    private String value = "";

    public DonatorMessage()
    {
    }

    public DonatorMessage(byte op, String user, String value)
    {
        this.op = op;
        this.user = user == null ? "" : user;
        this.value = value == null ? "" : value;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.op = buf.readByte();
        this.user = readString(buf);
        this.value = readString(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(this.op);
        writeString(buf, this.user);
        writeString(buf, this.value);
    }

    private static void writeString(ByteBuf buf, String s)
    {
        byte[] data = s.getBytes(java.nio.charset.Charset.forName("UTF-8"));
        buf.writeInt(data.length);
        buf.writeBytes(data);
    }

    private static String readString(ByteBuf buf)
    {
        int len = buf.readInt();

        if (len < 0 || len > 32767)
        {
            return "";
        }

        byte[] data = new byte[len];
        buf.readBytes(data);
        return new String(data, java.nio.charset.Charset.forName("UTF-8"));
    }

    public static class Handler implements IMessageHandler<DonatorMessage, IMessage>
    {
        @Override
        public IMessage onMessage(DonatorMessage message, MessageContext ctx)
        {
            Side side = FMLCommonHandler.instance().getEffectiveSide();

            if (side == Side.SERVER)
            {
                handleServer(message, ctx);
            }
            else
            {
                handleClient(message);
            }

            return null;
        }

        private void handleServer(DonatorMessage message, MessageContext ctx)
        {
            if (message.op == SERVER_ADD_CHOICE)
            {
                DonatorChoice choice = DonatorChoice.getChoiceByName(message.value);

                if (choice != null)
                {
                    Donator donator = mod_netherbr.donatorList.getDonatorByName(message.user);

                    if (donator == null)
                    {
                        donator = new Donator(message.user);
                        mod_netherbr.donatorList.addDonator(donator);
                    }

                    donator.addChoice(choice);
                    MNCNetwork.INSTANCE.sendToAll(new DonatorMessage(ADD_CHOICE, message.user, message.value));
                }
            }
            else if (message.op == SERVER_REMOVE_CHOICE)
            {
                Donator donator = mod_netherbr.donatorList.getDonatorByName(message.user);

                if (donator != null)
                {
                    EnumChoiceType type = EnumChoiceType.getTypeByName(message.value);

                    if (type != null)
                    {
                        donator.removeChoiceForType(type);
                    }
                }

                MNCNetwork.INSTANCE.sendToAll(new DonatorMessage(REMOVE_CHOICE, message.user, message.value));
            }
            else if (message.op == REQUEST)
            {
                if (mod_netherbr.donatorList.isDonator(message.user))
                {
                    MNCNetwork.INSTANCE.sendTo(new DonatorMessage(ADD_DONATOR, message.user, ""), ctx.getServerHandler().playerEntity);
                }
            }
        }

        private void handleClient(DonatorMessage message)
        {
            if (message.op == ADD_DONATOR)
            {
                if (!mod_netherbr.donatorList.isDonator(message.user))
                {
                    mod_netherbr.donatorList.addDonator(new Donator(message.user));
                }
            }
            else if (message.op == REMOVE_DONATOR)
            {
                mod_netherbr.donatorList.removeDonator(message.user);
            }
            else if (message.op == ADD_CHOICE)
            {
                DonatorChoice choice = DonatorChoice.getChoiceByName(message.value);

                if (choice != null)
                {
                    Donator donator = mod_netherbr.donatorList.getDonatorByName(message.user);

                    if (donator == null)
                    {
                        donator = new Donator(message.user);
                        mod_netherbr.donatorList.addDonator(donator);
                    }

                    donator.addChoice(choice);
                }
            }
            else if (message.op == REMOVE_CHOICE)
            {
                Donator donator = mod_netherbr.donatorList.getDonatorByName(message.user);

                if (donator != null)
                {
                    EnumChoiceType type = EnumChoiceType.getTypeByName(message.value);

                    if (type != null)
                    {
                        donator.removeChoiceForType(type);
                    }
                }
            }
        }
    }
}