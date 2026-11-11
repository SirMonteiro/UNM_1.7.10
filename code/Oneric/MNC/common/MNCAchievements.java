package Oneric.MNC.common;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.AchievementPage;

public class MNCAchievements
{

	public static Achievement enterTheNether;
	public static Achievement enterTheFallenSky;

	public MNCAchievements()
	{
		enterTheNether = register(new Achievement("achievement.enterTheNether", "enterTheNether", 0, 0, Blocks.netherrack, null));
		enterTheFallenSky = register(new Achievement("achievement.enterTheFallenSky", "enterTheFallenSky", 2, 1, MNCBlocks.deadGrass, enterTheNether));

		MNCAchievementPage acPage = new MNCAchievementPage("Ultimate Nether", new Achievement[]
				{
					enterTheNether, enterTheFallenSky
				});

		AchievementPage.registerAchievementPage(acPage);
	}

	/**
	 * Registers the achievement through StatBase so the call is unambiguous in
	 * environments whose Achievement class carries a non-bridge covariant
	 * registerStat() overload. Runtime dispatch still runs Achievement#registerStat.
	 */
	private static Achievement register(Achievement achievement)
	{
		((StatBase) achievement).registerStat();
		return achievement;
	}

}
