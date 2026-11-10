package Oneric.MNC.block;

import java.util.Random;

import Oneric.MNC.common.MNCItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockTreppen extends BlockStairs
{
	boolean diamondrequires;

	public BlockTreppen(Block par2Block, boolean j) {
		super(par2Block, 0);
		diamondrequires = j;
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockTreppen(int par1, Block par2Block, boolean j) {
		this(par2Block, j);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 10;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
	{
		if (diamondrequires)
		{
			if (entityplayer.getCurrentEquippedItem() != null &&
			   (entityplayer.getCurrentEquippedItem().getItem() == Items.diamond_pickaxe ||
			    entityplayer.getCurrentEquippedItem().getItem() == MNCItems.NetherPickaxe))
			{
				super.harvestBlock(world, entityplayer, i, j, k, l);
			}
		}
		else
		{
			super.harvestBlock(world, entityplayer, i, j, k, l);
		}
	}
}
