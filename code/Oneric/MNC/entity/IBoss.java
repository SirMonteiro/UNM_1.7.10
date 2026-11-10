package Oneric.MNC.entity;

import net.minecraft.entity.Entity;

/** Boss entities tracked for the on-screen boss health bar. */
public interface IBoss
{
    int getBossHP();

    int getBossMaxHP();

    String getBossName();

    EnumBoss getBossType();

    int getBossEntityID();

    Entity getBossEntity();

    boolean isCurrentBoss();
}
