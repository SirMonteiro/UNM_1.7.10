package Oneric.MNC.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import Oneric.MNC.client.gui.donator.GuiDonator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class MNCKeyHandler {

	public static KeyBinding guiBinding = new KeyBinding("Donator Gui", Keyboard.KEY_U, "key.categories.misc");

	public MNCKeyHandler()
	{

	}


	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if(guiBinding.isPressed())
		{
			Minecraft mc = Minecraft.getMinecraft();

			if(mc.currentScreen == null && mc.thePlayer != null)
			{
				mc.displayGuiScreen(new GuiDonator(mc.thePlayer));
			}
		}
	}

}