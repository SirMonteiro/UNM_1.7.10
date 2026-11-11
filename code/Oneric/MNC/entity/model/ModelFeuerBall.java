package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelFeuerBall extends ModelBase
{
    public ModelRenderer Box1;
    public ModelRenderer Box2;
    public ModelRenderer Box3;
    public ModelRenderer Box4;

    public ModelFeuerBall()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Box1 = new ModelRenderer(this, 0, 0);
        this.Box1.addBox(-2.0F, -2.0F, -2.0F, 5, 5, 5);
        this.Box1.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Box1.setTextureSize(64, 32);
        this.Box1.mirror = true;
        this.setRotation(this.Box1, 0.0F, -0.7853982F, -0.7853982F);

        this.Box2 = new ModelRenderer(this, 0, 0);
        this.Box2.mirror = true;
        this.Box2.addBox(-2.0F, -2.0F, -2.0F, 5, 5, 5);
        this.Box2.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Box2.setTextureSize(64, 32);
        this.setRotation(this.Box2, 0.0F, 0.7853982F, 0.7853982F);
        this.Box2.mirror = false;

        this.Box3 = new ModelRenderer(this, 0, 0);
        this.Box3.addBox(-2.0F, -2.0F, -2.0F, 5, 5, 5);
        this.Box3.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Box3.setTextureSize(64, 32);
        this.Box3.mirror = true;
        this.setRotation(this.Box3, 0.0F, 0.0F, 0.0F);

        this.Box4 = new ModelRenderer(this, 0, 0);
        this.Box4.addBox(-2.0F, -2.0F, -2.0F, 5, 5, 5);
        this.Box4.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Box4.setTextureSize(64, 32);
        this.Box4.mirror = true;
        this.setRotation(this.Box4, 2.082002F, (float)Math.PI, -1.115358F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Box1.render(f5);
        this.Box2.render(f5);
        this.Box3.render(f5);
        this.Box4.render(f5);
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
    }
}
