package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelLostSoul extends ModelBase
{
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer End;

    public ModelLostSoul()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.Head.setRotationPoint(-3.0F, 16.0F, -8.0F);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);

        this.Body = new ModelRenderer(this, 35, 0);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6);
        this.Body.setRotationPoint(-2.0F, 18.0F, -1.0F);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);

        this.End = new ModelRenderer(this, 0, 20);
        this.End.addBox(0.0F, 0.0F, 0.0F, 3, 3, 6);
        this.End.setRotationPoint(1.0F, 21.0F, 4.0F);
        this.End.setTextureSize(64, 32);
        this.End.mirror = true;
        this.setRotation(this.End, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Head.render(f5);
        this.Body.render(f5);
        this.End.render(f5);
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
        this.End.rotateAngleY = (MathHelper.sin((f2 * 3.141593F) * 0.04F) * 0.5F) - 0.5F;
    }
}
