package Oneric.MNC.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

/** Client -> server request to strike lightning at a position. */
public class ThunderMessage implements IMessage
{
    private double x;
    private double y;
    private double z;

    public ThunderMessage()
    {
    }

    public ThunderMessage(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
    }

    public static class Handler implements IMessageHandler<ThunderMessage, IMessage>
    {
        @Override
        public IMessage onMessage(ThunderMessage message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;

            if (player != null)
            {
                World world = player.worldObj;
                world.addWeatherEffect(new EntityLightningBolt(world, message.x, message.y, message.z));
            }

            return null;
        }
    }
}