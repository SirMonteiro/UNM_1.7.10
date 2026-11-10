package Oneric.MNC.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Renderer for the Harpye (a bat-like creature).
 * <p>
 * {@code ClientProxyMNC} constructs this with no arguments, so the no-arg
 * constructor supplies a vanilla {@link ModelBat} and the Erinnyen skin.
 * Client-only (1.7.10).
 */
@SideOnly(Side.CLIENT)
public class RenderHarpye extends RenderLiving
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("onerics_mnc:textures/entity/Erinnyen.png");

    public RenderHarpye()
    {
        super(new ModelBat(), 0.25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}
