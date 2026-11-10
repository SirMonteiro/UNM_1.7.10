package Oneric.MNC.block;

import net.minecraft.init.Blocks;
import Oneric.MNC.common.MNCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockEpicNether extends MNCBlock {

	public BlockEpicNether() {
		super(Material.iron);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockEpicNether(int par1) {
		this();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.getBlock(x, y + 1, z) == Blocks.fire) {
			MNCBlocks.PortalCrazy.tryToCreatePortal(world, x, y + 1, z);
			return true;
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbourBlock) {
		if (world.getBlock(x, y + 1, z) == Blocks.fire) {
			MNCBlocks.PortalCrazy.tryToCreatePortal(world, x, y + 1, z);
		}
	}
}
