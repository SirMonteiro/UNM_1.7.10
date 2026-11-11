package Oneric.MNC.entity.model;

import Oneric.MNC.entity.boss.EntityDaemonLord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelDaemonLord extends ModelBiped
{
    public ModelRenderer Horn1Right;
    public ModelRenderer Horn1Left;
    public ModelRenderer Horn2Left;
    public ModelRenderer Horn2Right;
    public ModelRenderer WingLeft;
    public ModelRenderer WingRight;

    public ModelDaemonLord()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Horn1Right = new ModelRenderer(this, 56, 16);
        this.Horn1Right.addBox(4.0F, -6.0F, -1.0F, 2, 2, 2);
        this.Horn1Right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Horn1Right.setTextureSize(64, 32);
        this.Horn1Right.mirror = true;
        this.setRotation(this.Horn1Right, 0.0F, 0.0F, 0.0F);

        this.Horn1Left = new ModelRenderer(this, 56, 16);
        this.Horn1Left.addBox(-6.0F, -6.0F, -1.0F, 2, 2, 2);
        this.Horn1Left.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Horn1Left.setTextureSize(64, 32);
        this.Horn1Left.mirror = true;
        this.setRotation(this.Horn1Left, 0.0F, 0.0F, 0.0F);

        this.Horn2Left = new ModelRenderer(this, 56, 7);
        this.Horn2Left.addBox(-8.0F, -10.0F, -1.0F, 2, 6, 2);
        this.Horn2Left.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Horn2Left.setTextureSize(64, 32);
        this.Horn2Left.mirror = true;
        this.setRotation(this.Horn2Left, 0.0F, 0.0F, 0.0F);

        this.Horn2Right = new ModelRenderer(this, 56, 7);
        this.Horn2Right.addBox(6.0F, -10.0F, -1.0F, 2, 6, 2);
        this.Horn2Right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Horn2Right.setTextureSize(64, 32);
        this.Horn2Right.mirror = true;
        this.setRotation(this.Horn2Right, 0.0F, 0.0F, 0.0F);

        this.WingLeft = new ModelRenderer(this, 24, 0);
        this.WingLeft.addBox(0.0F, 0.0F, 0.0F, 20, 5, 0);
        this.WingLeft.setRotationPoint(0.0F, 2.0F, 2.0F);
        this.WingLeft.setTextureSize(64, 32);
        this.WingLeft.mirror = false;
        this.setRotation(this.WingLeft, 0.1115358F, -0.3717861F, -0.2602503F);

        this.WingRight = new ModelRenderer(this, 24, 0);
        this.WingRight.mirror = true;
        this.WingRight.addBox(-20.0F, 0.0F, 0.0F, 20, 5, 0);
        this.WingRight.setRotationPoint(0.0F, 2.0F, 2.0F);
        this.WingRight.setTextureSize(64, 32);
        this.setRotation(this.WingRight, 0.1115358F, 0.37179F, 0.260246F);
        this.WingRight.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.isChild)
        {
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / 2.0F, 1.5F / 2.0F, 1.5F / 2.0F);
            GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
            this.bipedHead.render(f5);
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            GL11.glScalef(1.0F / 2.0F, 1.0F / 2.0F, 1.0F / 2.0F);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            this.bipedBody.render(f5);
            this.bipedRightArm.render(f5);
            this.bipedLeftArm.render(f5);
            this.bipedRightLeg.render(f5);
            this.bipedLeftLeg.render(f5);
            GL11.glPopMatrix();
        }
        else
        {
            this.bipedHead.render(f5);
            this.bipedBody.render(f5);
            this.bipedRightArm.render(f5);
            this.bipedLeftArm.render(f5);
            this.bipedRightLeg.render(f5);
            this.bipedLeftLeg.render(f5);
        }

        this.Horn1Right.render(f5);
        this.Horn1Left.render(f5);
        this.Horn2Left.render(f5);
        this.Horn2Right.render(f5);
        this.WingLeft.render(f5);
        this.WingRight.render(f5);
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

        if (ent instanceof EntityDaemonLord)
        {
            EntityDaemonLord daemonLord = (EntityDaemonLord) ent;
            if (!daemonLord.awake)
            {
                this.bipedHead.rotateAngleX = 0.0F;
                this.bipedHead.rotateAngleY = 0.0F;
                this.bipedHead.rotateAngleZ = 0.0F;
            }
        }

        this.WingRight.rotateAngleY = 0.37179F + (MathHelper.sin(f2 * 3.141593F * 0.04F) * 0.35F);
        this.WingLeft.rotateAngleY = -0.37179F - (MathHelper.sin(f2 * 3.141593F * 0.04F) * 0.35F);

        this.Horn1Right.rotateAngleX = this.bipedHead.rotateAngleX;
        this.Horn1Right.rotateAngleY = this.bipedHead.rotateAngleY;
        this.Horn1Right.rotateAngleZ = this.bipedHead.rotateAngleZ;

        this.Horn2Right.rotateAngleX = this.bipedHead.rotateAngleX;
        this.Horn2Right.rotateAngleY = this.bipedHead.rotateAngleY;
        this.Horn2Right.rotateAngleZ = this.bipedHead.rotateAngleZ;

        this.Horn1Left.rotateAngleX = this.bipedHead.rotateAngleX;
        this.Horn1Left.rotateAngleY = this.bipedHead.rotateAngleY;
        this.Horn1Left.rotateAngleZ = this.bipedHead.rotateAngleZ;

        this.Horn2Left.rotateAngleX = this.bipedHead.rotateAngleX;
        this.Horn2Left.rotateAngleY = this.bipedHead.rotateAngleY;
        this.Horn2Left.rotateAngleZ = this.bipedHead.rotateAngleZ;
    }
}
