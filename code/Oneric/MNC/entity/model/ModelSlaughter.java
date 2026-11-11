package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelSlaughter extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer Kapuze1;
    public ModelRenderer Kapuze2;
    public ModelRenderer Kapuze3;
    public ModelRenderer Kapuze4;
    public ModelRenderer Kapuze5;
    public ModelRenderer Shape1;

    public ModelSlaughter()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 6, 7, 6);
        this.head.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);

        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);

        this.rightarm = new ModelRenderer(this, 40, 16);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -1.003826F, 0.0F, 0.0F);

        this.leftarm = new ModelRenderer(this, 40, 16);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -1.003822F, 0.0F, 0.0F);

        this.Kapuze1 = new ModelRenderer(this, 30, 0);
        this.Kapuze1.addBox(0.0F, -1.0F, 0.0F, 8, 1, 8);
        this.Kapuze1.setRotationPoint(-4.0F, 0.0F, -5.0F);
        this.Kapuze1.setTextureSize(64, 32);
        this.Kapuze1.mirror = true;
        this.setRotation(this.Kapuze1, 0.0F, 0.0F, 0.0F);

        this.Kapuze2 = new ModelRenderer(this, 30, 0);
        this.Kapuze2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
        this.Kapuze2.setRotationPoint(4.0F, -9.0F, -5.0F);
        this.Kapuze2.setTextureSize(64, 32);
        this.Kapuze2.mirror = true;
        this.setRotation(this.Kapuze2, 0.0F, 0.0F, 1.570796F);

        this.Kapuze3 = new ModelRenderer(this, 30, 0);
        this.Kapuze3.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
        this.Kapuze3.setRotationPoint(-3.0F, -9.0F, -5.0F);
        this.Kapuze3.setTextureSize(64, 32);
        this.Kapuze3.mirror = true;
        this.setRotation(this.Kapuze3, 0.0F, 0.0F, 1.570796F);

        this.Kapuze4 = new ModelRenderer(this, 30, 0);
        this.Kapuze4.addBox(0.0F, 0.0F, -8.0F, 6, 1, 8);
        this.Kapuze4.setRotationPoint(-3.0F, -9.0F, 3.0F);
        this.Kapuze4.setTextureSize(64, 32);
        this.Kapuze4.mirror = true;
        this.setRotation(this.Kapuze4, 0.0743572F, 0.0F, 0.0F);

        this.Kapuze5 = new ModelRenderer(this, 39, 0);
        this.Kapuze5.addBox(0.0F, 0.0F, 0.0F, 6, 7, 1);
        this.Kapuze5.setRotationPoint(-3.0F, -8.0F, 2.0F);
        this.Kapuze5.setTextureSize(64, 32);
        this.Kapuze5.mirror = true;
        this.setRotation(this.Kapuze5, 0.0F, 0.0F, 0.0F);

        this.Shape1 = new ModelRenderer(this, 0, 18);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 12, 0);
        this.Shape1.setRotationPoint(-4.0F, 0.0F, 2.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.2230717F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.Kapuze1.render(f5);
        this.Kapuze2.render(f5);
        this.Kapuze3.render(f5);
        this.Kapuze4.render(f5);
        this.Kapuze5.render(f5);
        this.Shape1.render(f5);
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
