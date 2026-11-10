package Oneric.MNC.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Generic biped renderer shared by the Ice Pigman and the Daemon Lord.
 * <p>
 * The two-argument constructor used by {@code ClientProxyMNC} binds the default
 * "pigman2" skin. A three-argument overload allows a different texture path to
 * be supplied when a distinct skin is wanted, while keeping the required
 * {@code (ModelBase, float)} signature intact. Client-only (1.7.10).
 */
@SideOnly(Side.CLIENT)
public class RenderMNCBiped extends RenderLiving
{
    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("onerics_mnc:textures/entity/pigman2.png");

    private final ResourceLocation texture;

    public RenderMNCBiped(ModelBase model, float shadowSize)
    {
        this(model, shadowSize, DEFAULT_TEXTURE);
    }

    /** Convenience overload so a caller can pick a different entity texture. */
    public RenderMNCBiped(ModelBase model, float shadowSize, String texturePath)
    {
        this(model, shadowSize, new ResourceLocation(texturePath));
    }

    public RenderMNCBiped(ModelBase model, float shadowSize, ResourceLocation texture)
    {
        super(model, shadowSize);
        this.texture = texture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.texture;
    }
}
