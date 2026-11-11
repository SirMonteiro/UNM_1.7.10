package Oneric.MNC.entity.render;

import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.entity.EntityIcePigman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMNCBiped extends RenderBiped
{
    private static final ResourceLocation TEXTURE_ICE_PIGMAN = new ResourceLocation(mod_netherbr.getId(), "textures/entity/IcePigman.png");

    public RenderMNCBiped(ModelBiped model, float shadowSize)
    {
        super(model, shadowSize);
    }

    public RenderMNCBiped(ModelBiped model, float shadowSize, float p_i1259_3_)
    {
        super(model, shadowSize, p_i1259_3_);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        if (entity instanceof EntityIcePigman)
        {
            return TEXTURE_ICE_PIGMAN;
        }
        return TEXTURE_ICE_PIGMAN;
    }
}
