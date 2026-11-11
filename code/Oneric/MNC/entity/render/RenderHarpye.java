package Oneric.MNC.entity.render;

import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.entity.EntityHarpye;
import Oneric.MNC.entity.model.ModelHarpye;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHarpye extends RenderLiving
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(mod_netherbr.getId(), "textures/entity/Erinnyen.png");
    private final float scale;

    public RenderHarpye()
    {
        super(new ModelHarpye(), 0.25F);
        this.scale = 1.5F;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime)
    {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}
