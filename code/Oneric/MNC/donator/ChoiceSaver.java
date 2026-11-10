package Oneric.MNC.donator;

import Oneric.MNC.common.mod_netherbr;
import Oneric.MNC.donator.choices.DonatorChoice;

/**
 * Stores the donator's chosen cosmetic (cape / horn) in the mod config.
 */
public class ChoiceSaver
{
    public static synchronized DonatorChoice loadChoice(EnumChoiceType type)
    {
        if (mod_netherbr.config == null)
        {
            return null;
        }

        String def = type == EnumChoiceType.CAPE ? "CAPE RED" : "HORN CLASSIC";
        String name = mod_netherbr.config.get("donator", type.name + "Choice", def).getString();
        DonatorChoice choice = DonatorChoice.getChoiceByName(name);

        if (choice != null && choice.isType(type))
        {
            return choice;
        }

        return null;
    }

    public static synchronized boolean saveChoice(EnumChoiceType type, DonatorChoice choice)
    {
        if (mod_netherbr.config == null)
        {
            return false;
        }

        mod_netherbr.config.get("donator", type.name + "Choice", "").set(choice == null ? "" : choice.name);
        mod_netherbr.config.save();
        return true;
    }
}