package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Simple box based model for the Lost Soul: a small floating ghost made of a
 * compact body and a slightly larger head. Client-only (1.7.10).
 */
@SideOnly(Side.CLIENT)
public class ModelLostSoul extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;

    public ModelLostSoul()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4F, -8F, -4F, 8, 8, 8);
        this.head.setRotationPoint(0F, 0F, 0F);
        this.head.setTextureSize(64, 32);

        this.body = new ModelRenderer(this, 0, 16);
        this.body.addBox(-3F, 0F, -2F, 6, 8, 4);
        this.body.setRotationPoint(0F, 0F, 0F);
        this.body.setTextureSize(64, 32);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.render(scale);
        this.body.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        // Gentle bobbing so the ghost reads as floating rather than walking.
        this.body.rotateAngleZ = MathHelper.sin(ageInTicks * 0.08F) * 0.05F;
    }
}
