package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Simple box based model for the Slaughter: a broad, heavy humanoid brute.
 * Client-only (1.7.10 / Forge 10.13.4.1614).
 */
@SideOnly(Side.CLIENT)
public class ModelSlaughter extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightArm;
    public ModelRenderer leftArm;
    public ModelRenderer rightLeg;
    public ModelRenderer leftLeg;

    public ModelSlaughter()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-5F, -10F, -5F, 10, 10, 10);
        this.head.setRotationPoint(0F, 0F, 0F);
        this.head.setTextureSize(64, 64);

        this.body = new ModelRenderer(this, 16, 20);
        this.body.addBox(-8F, 0F, -5F, 16, 12, 10);
        this.body.setRotationPoint(0F, 0F, 0F);
        this.body.setTextureSize(64, 64);

        this.rightArm = new ModelRenderer(this, 40, 16);
        this.rightArm.addBox(-7F, -2F, -3F, 7, 16, 7);
        this.rightArm.setRotationPoint(-8F, 2F, 0F);
        this.rightArm.setTextureSize(64, 64);

        this.leftArm = new ModelRenderer(this, 40, 16);
        this.leftArm.addBox(0F, -2F, -3F, 7, 16, 7);
        this.leftArm.setRotationPoint(8F, 2F, 0F);
        this.leftArm.setTextureSize(64, 64);

        this.rightLeg = new ModelRenderer(this, 0, 22);
        this.rightLeg.addBox(-4F, 0F, -4F, 8, 12, 8);
        this.rightLeg.setRotationPoint(-4F, 12F, 0F);
        this.rightLeg.setTextureSize(64, 64);

        this.leftLeg = new ModelRenderer(this, 0, 22);
        this.leftLeg.addBox(-4F, 0F, -4F, 8, 12, 8);
        this.leftLeg.setRotationPoint(4F, 12F, 0F);
        this.leftLeg.setTextureSize(64, 64);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.render(scale);
        this.body.render(scale);
        this.rightArm.render(scale);
        this.leftArm.render(scale);
        this.rightLeg.render(scale);
        this.leftLeg.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
    }
}
