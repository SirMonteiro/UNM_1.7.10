package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Simple box based ghost model for the Ice Ghost: a head, a tapered body and
 * two drifting "tails". Client-only (1.7.10).
 */
@SideOnly(Side.CLIENT)
public class ModelIceGhost extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer tailRight;
    public ModelRenderer tailLeft;

    public ModelIceGhost()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4F, -8F, -4F, 8, 8, 8);
        this.head.setRotationPoint(0F, 0F, 0F);
        this.head.setTextureSize(64, 32);

        this.body = new ModelRenderer(this, 0, 16);
        this.body.addBox(-5F, 0F, -3F, 10, 10, 6);
        this.body.setRotationPoint(0F, 0F, 0F);
        this.body.setTextureSize(64, 32);

        this.tailRight = new ModelRenderer(this, 32, 16);
        this.tailRight.addBox(-1F, 0F, -1F, 2, 6, 2);
        this.tailRight.setRotationPoint(-3F, 10F, 0F);
        this.tailRight.setTextureSize(64, 32);

        this.tailLeft = new ModelRenderer(this, 32, 16);
        this.tailLeft.addBox(-1F, 0F, -1F, 2, 6, 2);
        this.tailLeft.setRotationPoint(3F, 10F, 0F);
        this.tailLeft.setTextureSize(64, 32);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.render(scale);
        this.body.render(scale);
        this.tailRight.render(scale);
        this.tailLeft.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.tailRight.rotateAngleX = MathHelper.sin(ageInTicks * 0.1F) * 0.15F;
        this.tailLeft.rotateAngleX = MathHelper.sin(ageInTicks * 0.1F + 1.0F) * 0.15F;
    }
}
