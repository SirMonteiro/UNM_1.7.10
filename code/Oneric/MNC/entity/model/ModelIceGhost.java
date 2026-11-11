package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelIceGhost extends ModelBase
{
    public ModelRenderer MainBody;
    public ModelRenderer Particle1;
    public ModelRenderer Particle2;
    public ModelRenderer Particle3;
    public ModelRenderer Particle4;
    public ModelRenderer Smoke1;
    public ModelRenderer Smoke2;
    public ModelRenderer Smoke3;
    public ModelRenderer Smoke4;

    public ModelIceGhost()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.MainBody = new ModelRenderer(this, 0, 0);
        this.MainBody.addBox(-5.0F, -5.0F, -5.0F, 11, 11, 11);
        this.MainBody.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.MainBody.setTextureSize(64, 32);
        this.MainBody.mirror = true;
        this.setRotation(this.MainBody, 0.0F, 0.0F, 0.0F);

        this.Particle1 = new ModelRenderer(this, 49, 0);
        this.Particle1.addBox(-10.0F, 0.0F, 0.0F, 3, 3, 3);
        this.Particle1.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Particle1.setTextureSize(64, 32);
        this.Particle1.mirror = true;
        this.setRotation(this.Particle1, 0.0F, 0.0F, 0.0F);

        this.Particle2 = new ModelRenderer(this, 49, 0);
        this.Particle2.addBox(0.0F, 0.0F, -10.0F, 3, 3, 3);
        this.Particle2.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Particle2.setTextureSize(64, 32);
        this.Particle2.mirror = true;
        this.setRotation(this.Particle2, 0.0F, 0.0F, 0.0F);

        this.Particle3 = new ModelRenderer(this, 49, 0);
        this.Particle3.addBox(0.0F, 0.0F, 10.0F, 3, 3, 3);
        this.Particle3.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Particle3.setTextureSize(64, 32);
        this.Particle3.mirror = true;
        this.setRotation(this.Particle3, 0.0F, 0.0F, 0.0F);

        this.Particle4 = new ModelRenderer(this, 49, 0);
        this.Particle4.addBox(10.0F, 0.0F, 0.0F, 3, 3, 3);
        this.Particle4.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Particle4.setTextureSize(64, 32);
        this.Particle4.mirror = true;
        this.setRotation(this.Particle4, 0.0F, 0.0F, 0.0F);

        this.Smoke1 = new ModelRenderer(this, 49, 15);
        this.Smoke1.addBox(-9.0F, -2.0F, -8.0F, 1, 5, 1);
        this.Smoke1.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Smoke1.setTextureSize(64, 32);
        this.Smoke1.mirror = true;
        this.setRotation(this.Smoke1, 0.0F, 0.0F, 0.0F);

        this.Smoke2 = new ModelRenderer(this, 49, 15);
        this.Smoke2.addBox(-9.0F, -2.0F, 8.0F, 1, 5, 1);
        this.Smoke2.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Smoke2.setTextureSize(64, 32);
        this.Smoke2.mirror = true;
        this.setRotation(this.Smoke2, 0.0F, 0.0F, 0.0F);

        this.Smoke3 = new ModelRenderer(this, 49, 15);
        this.Smoke3.addBox(9.0F, 0.0F, -8.0F, 1, 5, 1);
        this.Smoke3.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Smoke3.setTextureSize(64, 32);
        this.Smoke3.mirror = true;
        this.setRotation(this.Smoke3, 0.0F, 0.0F, 0.0F);

        this.Smoke4 = new ModelRenderer(this, 49, 15);
        this.Smoke4.addBox(9.0F, 0.0F, 8.0F, 1, 5, 1);
        this.Smoke4.setRotationPoint(-1.0F, 7.0F, -2.0F);
        this.Smoke4.setTextureSize(64, 32);
        this.Smoke4.mirror = true;
        this.setRotation(this.Smoke4, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.MainBody.render(f5);
        this.Particle1.render(f5);
        this.Particle2.render(f5);
        this.Particle3.render(f5);
        this.Particle4.render(f5);
        this.Smoke1.render(f5);
        this.Smoke2.render(f5);
        this.Smoke3.render(f5);
        this.Smoke4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
        this.Smoke1.rotateAngleY = (MathHelper.sin((f2 * 3.141593F) * 0.0958F) * 0.5F) - 0.9F;
        this.Smoke2.rotateAngleY = (MathHelper.sin((f2 * 3.141593F) * 0.0958F) * 0.5F) - 0.9F;
        this.Smoke3.rotateAngleY = (MathHelper.sin((f2 * 3.141593F) * 0.0958F) * 0.5F) - 0.9F;
        this.Smoke4.rotateAngleY = (MathHelper.sin((f2 * 3.141593F) * 0.0958F) * 0.5F) - 0.9F;
        this.Particle1.rotateAngleY = (MathHelper.sin(f2 * 3.141593F * 0.036F) * 0.5F) + 0.4F;
        this.Particle2.rotateAngleY = (MathHelper.sin(f2 * 3.141593F * 0.036F) * 0.5F) + 0.4F;
        this.Particle3.rotateAngleY = (MathHelper.sin(f2 * 3.141593F * 0.036F) * 0.5F) + 0.4F;
        this.Particle4.rotateAngleY = (MathHelper.sin(f2 * 3.141593F * 0.036F) * 0.5F) + 0.4F;
    }
}
