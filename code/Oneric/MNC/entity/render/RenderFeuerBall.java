package Oneric.MNC.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Renderer for the Feuer Ball projectile. Client-only (1.7.10).
 */
@SideOnly(Side.CLIENT)
public class RenderFeuerBall extends RenderLiving
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("onerics_mnc:textures/entity/feuerball.png");

    public RenderFeuerBall(ModelBase model, float shadowSize)
    {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}
