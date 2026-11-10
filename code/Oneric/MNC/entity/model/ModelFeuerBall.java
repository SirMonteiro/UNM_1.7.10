package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Simple box based fireball model: a small inner core surrounded by a slightly
 * larger shell, giving a cheap "flame" look. Client-only (1.7.10).
 */
@SideOnly(Side.CLIENT)
public class ModelFeuerBall extends ModelBase
{
    public ModelRenderer core;
    public ModelRenderer shell;

    public ModelFeuerBall()
    {
        this.textureWidth = 32;
        this.textureHeight = 32;

        this.core = new ModelRenderer(this, 0, 0);
        this.core.addBox(-2F, -2F, -2F, 4, 4, 4);
        this.core.setRotationPoint(0F, 0F, 0F);
        this.core.setTextureSize(32, 32);

        this.shell = new ModelRenderer(this, 0, 8);
        this.shell.addBox(-3F, -3F, -3F, 6, 6, 6);
        this.shell.setRotationPoint(0F, 0F, 0F);
        this.shell.setTextureSize(32, 32);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.core.render(scale);
        this.shell.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        // Slow tumble so the projectile looks alive while flying.
        this.core.rotateAngleY = ageInTicks * 0.05F;
        this.core.rotateAngleX = ageInTicks * 0.03F;
        this.shell.rotateAngleY = -ageInTicks * 0.04F;
    }
}
