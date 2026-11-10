package Oneric.MNC.block;

import Oneric.MNC.common.mod_netherbr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockTrapDoor_MNC extends BlockTrapDoor
{
	public BlockTrapDoor_MNC(Material par2Material) {
		super(par2Material);
	}

	public BlockTrapDoor_MNC(int par1, Material par2Material) {
		this(par2Material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry)
	{
		this.blockIcon = registry.registerIcon(mod_netherbr.getId() + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
