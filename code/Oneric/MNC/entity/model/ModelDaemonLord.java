package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Simple box based model for the Daemon Lord: a large horned humanoid boss.
 * Extends ModelBase directly so it can be passed to any renderer whose
 * constructor takes a ModelBase (e.g. RenderMNCBiped). Client-only (1.7.10).
 */
@SideOnly(Side.CLIENT)
public class ModelDaemonLord extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer hornRight;
    public ModelRenderer hornLeft;
    public ModelRenderer body;
    public ModelRenderer rightArm;
    public ModelRenderer leftArm;
    public ModelRenderer rightLeg;
    public ModelRenderer leftLeg;

    public ModelDaemonLord()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-5F, -10F, -5F, 10, 10, 10);
        this.head.setRotationPoint(0F, 0F, 0F);
        this.head.setTextureSize(64, 64);

        // Two horns sprouting from the top corners of the head.
        this.hornRight = new ModelRenderer(this, 32, 0);
        this.hornRight.addBox(-1.5F, -8F, -1.5F, 3, 8, 3);
        this.hornRight.setRotationPoint(-4F, -10F, 0F);
        this.hornRight.setTextureSize(64, 64);
        this.hornRight.rotateAngleZ = -0.35F;

        this.hornLeft = new ModelRenderer(this, 32, 0);
        this.hornLeft.addBox(-1.5F, -8F, -1.5F, 3, 8, 3);
        this.hornLeft.setRotationPoint(4F, -10F, 0F);
        this.hornLeft.setTextureSize(64, 64);
        this.hornLeft.rotateAngleZ = 0.35F;

        this.body = new ModelRenderer(this, 16, 20);
        this.body.addBox(-7F, 0F, -4F, 14, 14, 8);
        this.body.setRotationPoint(0F, 0F, 0F);
        this.body.setTextureSize(64, 64);

        this.rightArm = new ModelRenderer(this, 40, 16);
        this.rightArm.addBox(-5F, -2F, -2.5F, 5, 18, 5);
        this.rightArm.setRotationPoint(-7F, 2F, 0F);
        this.rightArm.setTextureSize(64, 64);

        this.leftArm = new ModelRenderer(this, 40, 16);
        this.leftArm.addBox(0F, -2F, -2.5F, 5, 18, 5);
        this.leftArm.setRotationPoint(7F, 2F, 0F);
        this.leftArm.setTextureSize(64, 64);

        this.rightLeg = new ModelRenderer(this, 0, 22);
        this.rightLeg.addBox(-3.5F, 0F, -3.5F, 7, 14, 7);
        this.rightLeg.setRotationPoint(-4F, 14F, 0F);
        this.rightLeg.setTextureSize(64, 64);

        this.leftLeg = new ModelRenderer(this, 0, 22);
        this.leftLeg.addBox(-3.5F, 0F, -3.5F, 7, 14, 7);
        this.leftLeg.setRotationPoint(4F, 14F, 0F);
        this.leftLeg.setTextureSize(64, 64);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.render(scale);
        this.hornRight.render(scale);
        this.hornLeft.render(scale);
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
        this.hornRight.rotateAngleY = this.head.rotateAngleY;
        this.hornRight.rotateAngleX = this.head.rotateAngleX;
        this.hornLeft.rotateAngleY = this.head.rotateAngleY;
        this.hornLeft.rotateAngleX = this.head.rotateAngleX;
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
