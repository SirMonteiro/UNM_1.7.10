package Oneric.MNC.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockDungeonStone extends MNCBlock {

	public BlockDungeonStone() {
		super(Material.rock);
		this.setResistance(2000F);
	}

	public BlockDungeonStone(int par1) {
		this();
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		float i = 0.8F;
		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
			i = 0.8F;
			break;
		case 1:
			i = -1.0F;
			break;
		}
		return i;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativetab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
}
