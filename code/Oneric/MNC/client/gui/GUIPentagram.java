package Oneric.MNC.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;


public class GUIPentagram extends GuiScreen {


	private static final ResourceLocation HELLALTAR_TEXTURE = new ResourceLocation("onerics_mnc", "textures/gui/hellaltar.png");

	private int page = 0;
	private GuiButton disable;



    public GUIPentagram()
    {

    }



    public void initGui()
    {

    }

    private void tabs()
    {

    	/*controlList.add(new GuiButton(3, width / 2 - 91, height / 2 - 100, 78, 20, "Altar"));
    	controlList.add(disable = new GuiButton(4, width / 2 - 10, height / 2 - 100, 78, 20, "Controlls !"));
    	disable.drawButton = true;
    	disable.enabled = false;*/

    }



    protected void actionPerformed(GuiButton guibutton)
    {
    	// Button handling for the pentagram/altar pages used to live here but was
    	// disabled in the original sources (kept for reference).
    }



    public boolean doesGuiPauseGame()
    {

        return false;

    }


    public void onGuiClosed()
    {



    }


    public void drawScreen(int i, int j, float f)
    {
    	//////DEFAULT//////
        drawDefaultBackground();
        drawGradientRect(0, 0, width - 0, height - 0, 0x50500000, 0x550000);

        int x = width / 2;
        int y = height / 2;

        switch(page){

        case 0:
        //////PAGE1//////
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(HELLALTAR_TEXTURE);
        this.drawTexturedModalRect(x - 90, y - 80, 0, 0, 176, 166);


        GL11.glPushMatrix();
        this.fontRendererObj.drawString("hell altar",x - 85, y - 76 , 0xdddddd);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glScalef(1.6F, 1.6F, 1.6F);
        this.drawCenteredString(this.fontRendererObj, "Coming soon !", x - 60, y / 2 + 10, 0x44ff11);

        GL11.glPopMatrix();

        break;

        case 1:
        //////PAGE2//////
        GL11.glPushMatrix();
        GL11.glScalef(1.4F, 1.4F, 1.4F);
       // drawCenteredString(fontRenderer, "Controls (still in progress)", x - 50, y / 2 - 25, 0x44ff11);

        GL11.glPopMatrix();
        break;

        default:

        break;
        }

        super.drawScreen(i, j, f);
    }
}