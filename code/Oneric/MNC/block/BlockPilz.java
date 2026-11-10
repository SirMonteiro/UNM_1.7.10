package Oneric.MNC.block;

import java.util.Random;

import Oneric.MNC.common.MNCItems;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockPilz extends MNCBlock {

	public BlockPilz() {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockPilz(int par1) {
		this();
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return MNCItems.HellPilz_Samen;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		Random rand = new Random();
		return rand.nextInt(5);
	}
}
