package Oneric.MNC.client;

import net.minecraft.client.entity.AbstractClientPlayer;

/**
 * Plain client-side helper.
 *
 * The 1.6.4 RenderPlayerAPI hook (api.player.render.RenderPlayerBase) is not
 * available in 1.7.10, so this class no longer extends it and no longer hooks
 * into player rendering. Donator capes/horns are therefore not rendered; the
 * class and its entry point are kept so existing references still resolve.
 */
public class MNCRenderPlayerBase
{

	public MNCRenderPlayerBase()
	{

	}


	/**
	 * Kept for source compatibility with the old RenderPlayerAPI signature.
	 * No-op in 1.7.10.
	 */
	public void afterRenderSpecials(AbstractClientPlayer player, float f)
	{

	}

}