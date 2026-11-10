package Oneric.MNC.block;

import java.util.Random;

import Oneric.MNC.common.MNCBlocks;
import Oneric.MNC.common.mod_netherbr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPentagram extends MNCBlockContainer {

	private Class TileEntityClass;

	@SideOnly(Side.CLIENT)
	private IIcon textureSide;

	public BlockPentagram(Class classt) {
		super(Material.rock);
		this.setBlockBounds(0, 0, 0, 1, 0.8F, 1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		TileEntityClass = classt;
	}

	public BlockPentagram(int par1, Class classt) {
		this(classt);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity tile_entity = world.getTileEntity(x, y, z);
		if (tile_entity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(mod_netherbr.mod, 0, world, x, y, z);
		return true;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int i, int par2) {
		if (i == 0) {
			return MNCBlocks.NetherBlock.getIcon(i, 0);
		}
		if (i == 1) {
			return this.blockIcon;
		} else {
			return this.textureSide;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		this.blockIcon = registry.registerIcon(mod_netherbr.getId() + ":mnc_hellAltarTop");
		this.textureSide = registry.registerIcon(mod_netherbr.getId() + ":mnc_hellAltarSide");
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		try {
			return (TileEntity) TileEntityClass.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		this.dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();
		TileEntity tile_entity = world.getTileEntity(x, y, z);

		if (!(tile_entity instanceof IInventory)) {
			return;
		}

		IInventory inventory = (IInventory) tile_entity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.6F + 0.1F;
				float ry = rand.nextFloat() * 0.6F + 0.1F;
				float rz = rand.nextFloat() * 0.6F + 0.1F;

				EntityItem entity_item = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound()) {
					entity_item.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.5F;
				entity_item.motionX = rand.nextGaussian() * factor;
				entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
				entity_item.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entity_item);
				item.stackSize = 0;
			}
		}
	}

	public static void updateFurnaceBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
	}
}
