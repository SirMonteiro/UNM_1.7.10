package Oneric.MNC.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStature extends ItemBlock {

    public ItemBlockStature(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        String[] types = {"0", "1", "2", "3"};
        int meta = is.getItemDamage();
        if (meta < 0 || meta >= types.length) meta = 0;
        return getUnlocalizedName() + types[meta];
    }
}
