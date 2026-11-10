package Oneric.MNC.block;

import Oneric.MNC.common.mod_netherbr;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class MNCBlock extends Block {

	public MNCBlock(Material material) {
		super(material);
		this.setHardness(1.0F);
		this.setResistance(1.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public MNCBlock(int par1, Material par2Material) {
		this(par2Material);
	}

	@Override
	public void registerBlockIcons(IIconRegister registry)
	{
		this.blockIcon = registry.registerIcon(mod_netherbr.getId() + ":" + (this.getUnlocalizedName().substring(5)));
	}

	@Override
	public Block setBlockName(String name)
	{
		this.setBlockTextureName(name);
		return super.setBlockName(name);
	}
}
