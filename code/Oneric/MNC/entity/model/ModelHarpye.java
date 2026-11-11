package Oneric.MNC.entity.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelHarpye extends ModelBase
{
    private ModelRenderer HarpyeHead;
    private ModelRenderer HarpyeBody;
    private ModelRenderer HarpyeRightWing;
    private ModelRenderer HarpyeLeftWing;
    private ModelRenderer HarpyeOuterRightWing;
    private ModelRenderer HarpyeOuterLeftWing;

    public ModelHarpye()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.HarpyeHead = new ModelRenderer(this, 0, 0);
        this.HarpyeHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);

        ModelRenderer var1 = new ModelRenderer(this, 24, 0);
        var1.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
        this.HarpyeHead.addChild(var1);

        ModelRenderer var2 = new ModelRenderer(this, 24, 0);
        var2.mirror = true;
        var2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
        this.HarpyeHead.addChild(var2);

        this.HarpyeBody = new ModelRenderer(this, 0, 16);
        this.HarpyeBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
        this.HarpyeBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);

        this.HarpyeRightWing = new ModelRenderer(this, 42, 0);
        this.HarpyeRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);

        this.HarpyeOuterRightWing = new ModelRenderer(this, 24, 16);
        this.HarpyeOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
        this.HarpyeOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);

        this.HarpyeLeftWing = new ModelRenderer(this, 42, 0);
        this.HarpyeLeftWing.mirror = true;
        this.HarpyeLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);

        this.HarpyeOuterLeftWing = new ModelRenderer(this, 24, 16);
        this.HarpyeOuterLeftWing.mirror = true;
        this.HarpyeOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
        this.HarpyeOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);

        this.HarpyeBody.addChild(this.HarpyeRightWing);
        this.HarpyeBody.addChild(this.HarpyeLeftWing);
        this.HarpyeRightWing.addChild(this.HarpyeOuterRightWing);
        this.HarpyeLeftWing.addChild(this.HarpyeOuterLeftWing);
    }

    public int getHarpyeSize()
    {
        return 36;
    }

    @Override
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.HarpyeHead.rotateAngleX = par6 / (180.0F / (float)Math.PI);
        this.HarpyeHead.rotateAngleY = (float)Math.PI - (par5 / (180.0F / (float)Math.PI));
        this.HarpyeHead.rotateAngleZ = (float)Math.PI;
        this.HarpyeHead.setRotationPoint(0.0F, -2.0F, 0.0F);

        this.HarpyeRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
        this.HarpyeLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
        this.HarpyeBody.rotateAngleX = (float)Math.PI;
        this.HarpyeRightWing.rotateAngleX = -0.15707964F;
        this.HarpyeRightWing.rotateAngleY = -1.2566371F;
        this.HarpyeOuterRightWing.rotateAngleY = -1.7278761F;
        this.HarpyeLeftWing.rotateAngleX = this.HarpyeRightWing.rotateAngleX;
        this.HarpyeLeftWing.rotateAngleY = -this.HarpyeRightWing.rotateAngleY;
        this.HarpyeOuterLeftWing.rotateAngleY = -this.HarpyeOuterRightWing.rotateAngleY;

        this.HarpyeHead.rotateAngleX = par6 / (180.0F / (float)Math.PI);
        this.HarpyeHead.rotateAngleY = par5 / (180.0F / (float)Math.PI);
        this.HarpyeHead.rotateAngleZ = 0.0F;
        this.HarpyeHead.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.HarpyeRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HarpyeLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HarpyeBody.rotateAngleX = 0.7853982F + (MathHelper.cos(par4 * 0.1F) * 0.15F);
        this.HarpyeBody.rotateAngleY = 0.0F;
        this.HarpyeRightWing.rotateAngleY = MathHelper.cos(par4 * 1.3F) * (float)Math.PI * 0.25F;
        this.HarpyeLeftWing.rotateAngleY = -this.HarpyeRightWing.rotateAngleY;
        this.HarpyeOuterRightWing.rotateAngleY = this.HarpyeRightWing.rotateAngleY * 0.5F;
        this.HarpyeOuterLeftWing.rotateAngleY = (-this.HarpyeRightWing.rotateAngleY) * 0.5F;

        this.HarpyeHead.render(par7);
        this.HarpyeBody.render(par7);
    }
}
