package Oneric.MNC.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDungeon extends ItemBlock {

    public ItemBlockDungeon(Block block) {
        super(block);
        setUnlocalizedName("DungeonStone");
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int i = itemstack.getItemDamage();
        if (i > 1) {
            i = 1;
        }
        return getUnlocalizedName() + i;
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }
}
