package Oneric.MNC.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HellAltarRecipes
{
    private static final HellAltarRecipes smeltingBase = new HellAltarRecipes();

    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private Map<List<Integer>, ItemStack> metaSmeltingList = new HashMap<List<Integer>, ItemStack>();
    private Map<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final HellAltarRecipes smelting()
    {
        return smeltingBase;
    }

    private HellAltarRecipes()
    {
        this.addSmelting(Items.rotten_flesh, new ItemStack(Items.porkchop), 0.4F);

        this.addSmelting(Item.getItemFromBlock(Blocks.soul_sand), new ItemStack(MNCBlocks.ESSand), 1.0F);

        this.addSmelting(MNCItems.netheringot, new ItemStack(MNCItems.epicingot), 1.0F);
    }

    /**
     * Adds a smelting recipe.
     */
    public void addSmelting(Block par1, ItemStack par2ItemStack, float par3)
    {
        this.addSmelting(Item.getItemFromBlock(par1), par2ItemStack, par3);
    }

    /**
     * Adds a smelting recipe.
     */
    public void addSmelting(Item par1, ItemStack par2ItemStack, float par3)
    {
        this.smeltingList.put(new ItemStack(par1, 1, 32767), par2ItemStack);
        this.experienceList.put(par2ItemStack, Float.valueOf(par3));
    }

    /**
     * Add a metadata-sensitive HellAltar recipe.
     */
    @Deprecated
    public void addSmelting(Item item, int metadata, ItemStack itemstack)
    {
        addSmelting(item, metadata, itemstack, 0.0f);
    }

    /**
     * A metadata sensitive version of adding a HellAltar recipe.
     */
    public void addSmelting(Item item, int metadata, ItemStack itemstack, float experience)
    {
        metaSmeltingList.put(Arrays.asList(Item.getIdFromItem(item), metadata), itemstack);
        metaExperience.put(Arrays.asList(Item.getIdFromItem(item), metadata), experience);
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getSmeltingResult(ItemStack item)
    {
        if (item == null)
        {
            return null;
        }

        ItemStack ret = (ItemStack)metaSmeltingList.get(Arrays.asList(Item.getIdFromItem(item.getItem()), item.getItemDamage()));

        if (ret != null)
        {
            return ret;
        }

        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.matches(item, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean matches(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == 32767 || par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
    }

    /**
     * Returns the experience gained from smelting the given stack.
     */
    public float getExperience(ItemStack item)
    {
        if (item == null || item.getItem() == null)
        {
            return 0.0F;
        }

        float ret = -1.0F;
        List<Integer> key = Arrays.asList(Item.getIdFromItem(item.getItem()), item.getItemDamage());

        if (ret < 0.0F && metaExperience.containsKey(key))
        {
            ret = ((Float)metaExperience.get(key)).floatValue();
        }

        if (ret < 0.0F && experienceList.containsKey(item))
        {
            ret = ((Float)experienceList.get(item)).floatValue();
        }

        return ret < 0.0F ? 0.0F : ret;
    }
}