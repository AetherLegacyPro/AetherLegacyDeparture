package com.gildedgames.the_aether.blocks.ancient.enchanter;
import net.minecraft.init.*;
import net.minecraft.item.*;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntityAncientEnchanter;

public class AetherEnchantmentsAncientEnchanter
{
    public static void init() {
    	//Food
    	 addEnchantment(new ItemStack(ItemsAether.blueberry, 1, 0), new ItemStack(ItemsAether.enchanted_blueberry, 1, 0), 2);
    	 addEnchantment(new ItemStack(ItemsAether.grapes, 1, 0), new ItemStack(ItemsAether.enchanted_grapes, 1, 0), 2);
    	 addEnchantment(new ItemStack(ItemsAether.orange, 1, 0), new ItemStack(ItemsAether.enchanted_orange, 1, 0), 2);
    	 addEnchantment(new ItemStack(ItemsAether.raspberry, 1, 0), new ItemStack(ItemsAether.enchanted_raspberry, 1, 0), 2);
    	 addEnchantment(new ItemStack(ItemsAether.strawberry, 1, 0), new ItemStack(ItemsAether.enchanted_strawberry, 1, 0), 3);    	 
    	 addEnchantment(new ItemStack(ItemsAether.raw_thunderlo, 1, 0), new ItemStack(ItemsAether.enchanted_thunderlo, 1, 0), 2);
    	 addEnchantment(new ItemStack(ItemsAether.wynberry, 1, 0), new ItemStack(ItemsAether.rainbow_strawberry, 1, 0), 3);
    	 addEnchantment(new ItemStack(Items.porkchop, 1, 0), new ItemStack(Items.cooked_porkchop, 1, 0), 3);
    	 addEnchantment(new ItemStack(Items.beef, 1, 0), new ItemStack(Items.cooked_beef, 1, 0), 3);
    	 addEnchantment(new ItemStack(ItemsAether.raw_aerwhale, 1, 0), new ItemStack(ItemsAether.enchanted_aerwhale, 1, 0), 3);
    	 addEnchantment(new ItemStack(BlocksAether.enchanted_holystone, 1, 0), new ItemStack(ItemsAether.healing_stone, 1, 0), 6);
    	 addEnchantment(new ItemStack(BlocksAether.enchanted_agiosite, 1, 0), new ItemStack(ItemsAether.dexterity_stone, 1, 0), 6);
    	 addEnchantment(new ItemStack(BlocksAether.enchanted_deific, 1, 0), new ItemStack(ItemsAether.agility_stone, 1, 0), 6);
    	 addEnchantment(new ItemStack(BlocksAether.hellfire_stone, 1, 0), new ItemStack(ItemsAether.flame_stone, 1, 0), 6);
    	 addEnchantment(new ItemStack(BlocksAether.zanite_block, 1, 0), new ItemStack(ItemsAether.strength_stone, 1, 0), 6);
    	 addEnchantment(new ItemStack(BlocksAether.aerogel, 1, 0), new ItemStack(ItemsAether.resistance_stone, 1, 0), 12);
    	 addEnchantment(new ItemStack(ItemsAether.uncooked_fruit_stew_bowl, 1, 0), new ItemStack(ItemsAether.enchanted_fruit_stew, 1, 0), 9);
    	 addEnchantment(new ItemStack(ItemsAether.aerca_tooth, 1, 0), new ItemStack(ItemsAether.aerca_powder, 1, 0), 10);
    	 
    	 //Ores
    	 addEnchantment(new ItemStack(ItemsAether.raw_gravitite, 1, 0), new ItemStack(BlocksAether.enchanted_gravitite, 1, 0), 10);
    	 addEnchantment(new ItemStack(BlocksAether.gravitite_ore, 1, 0), new ItemStack(BlocksAether.enchanted_gravitite, 1, 0), 10);
    	 addEnchantment(new ItemStack(BlocksAether.aetheral_gravitite_ore, 1, 0), new ItemStack(BlocksAether.enchanted_gravitite, 1, 0), 10);
    	 addEnchantment(new ItemStack(BlocksAether.agiosite_gravitite_ore, 1, 0), new ItemStack(BlocksAether.enchanted_gravitite, 1, 0), 10);
    	 addEnchantment(new ItemStack(BlocksAether.deific_gravitite_ore, 1, 0), new ItemStack(BlocksAether.enchanted_gravitite, 1, 0), 10);  	 
    	 addEnchantment(new ItemStack(ItemsAether.arkenium_chunk, 1, 0), new ItemStack(ItemsAether.arkenium_ingot, 1, 0), 20);
    	 addEnchantment(new ItemStack(BlocksAether.zanite_ore, 1, 0), new ItemStack(ItemsAether.zanite_gemstone, 1, 0), 13);
    	 addEnchantment(new ItemStack(BlocksAether.aetheral_zanite_ore, 1, 0), new ItemStack(ItemsAether.zanite_gemstone, 1, 0), 13);
    	 addEnchantment(new ItemStack(BlocksAether.agiosite_zanite_ore, 1, 0), new ItemStack(ItemsAether.zanite_gemstone, 1, 0), 13);
    	 addEnchantment(new ItemStack(BlocksAether.deific_zanite_ore, 1, 0), new ItemStack(ItemsAether.zanite_gemstone, 1, 0), 13); 	 
    	 addEnchantment(new ItemStack(BlocksAether.arkenium_ore, 1, 0), new ItemStack(ItemsAether.arkenium_fragement, 1, 0), 10);
    	 addEnchantment(new ItemStack(BlocksAether.arkenium_ore, 1, 1), new ItemStack(ItemsAether.arkenium_fragement, 1, 0), 10);    	 
    	 addEnchantment(new ItemStack(BlocksAether.aetheral_arkenium_ore, 1, 0), new ItemStack(ItemsAether.arkenium_fragement, 1, 0), 10);
    	 addEnchantment(new ItemStack(BlocksAether.agiosite_arkenium_ore, 1, 0), new ItemStack(ItemsAether.arkenium_fragement, 1, 0), 10);
    	 addEnchantment(new ItemStack(BlocksAether.deific_arkenium_ore, 1, 0), new ItemStack(ItemsAether.arkenium_fragement, 1, 0), 10);    	 
    	 addEnchantment(new ItemStack(BlocksAether.continuum_ore, 1, 0), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.aetheral_continuum_ore, 1, 0), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.agiosite_continuum_ore, 1, 0), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.deific_continuum_ore, 1, 0), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.continuum_ore, 1, 1), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.aetheral_continuum_ore, 1, 1), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.agiosite_continuum_ore, 1, 1), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.deific_continuum_ore, 1, 1), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8); 
    	 addEnchantment(new ItemStack(BlocksAether.continuum_ore, 1, 1), new ItemStack(ItemsAether.continuum_gemstone, 1, 0), 8);
    	 addEnchantment(new ItemStack(BlocksAether.primeval_artifact, 1, 0), new ItemStack(ItemsAether.enchanted_divineral, 1, 0), 24);
    	 
    	//Misc
    	 addEnchantment(new ItemStack(ItemsAether.skyroot_bucket, 1, 2), new ItemStack(ItemsAether.skyroot_bucket.setContainerItem(null), 1, 3), 5);
    	 addEnchantment(new ItemStack(BlocksAether.quicksoil, 1, 1), new ItemStack(BlocksAether.quicksoil_glass, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.quicksoil, 1, 0), new ItemStack(BlocksAether.quicksoil_glass, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.golden_oak_log, 1, 1), new ItemStack(ItemsAether.mimicry_ambrosium_shard, 1, 0), 1);
    	 addEnchantment(new ItemStack(BlocksAether.golden_oak_log, 1, 0), new ItemStack(ItemsAether.mimicry_ambrosium_shard, 1, 0), 1);
    	 addEnchantment(new ItemStack(BlocksAether.agiosite, 1, 1), new ItemStack(BlocksAether.enchanted_agiosite, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.agiosite, 1, 0), new ItemStack(BlocksAether.enchanted_agiosite, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.aetheral_stone, 1, 1), new ItemStack(BlocksAether.enchanted_aetheral_stone, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.aetheral_stone, 1, 0), new ItemStack(BlocksAether.enchanted_aetheral_stone, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.deific, 1, 1), new ItemStack(BlocksAether.enchanted_deific, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.deific, 1, 0), new ItemStack(BlocksAether.enchanted_deific, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.frozen_quicksoil, 1, 0), new ItemStack(BlocksAether.frozen_quicksoil_glass, 1, 0), 2);
    	 addEnchantment(new ItemStack(BlocksAether.frozen_quicksoil, 1, 1), new ItemStack(BlocksAether.frozen_quicksoil_glass, 1, 0), 2);
    	 addEnchantment(new ItemStack(Items.record_11, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_13, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_blocks, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_far, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_mall, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_mellohi, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_stal, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_strad, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_wait, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_ward, 1, 0), new ItemStack(ItemsAether.aether_tune, 1, 0), 12);
    	 addEnchantment(new ItemStack(Items.record_cat, 1, 0), new ItemStack(ItemsAether.legacy, 1, 0), 12);
    }
    
    public static void addEnchantment(final ItemStack from, final ItemStack to, final int i) {
        TileEntityAncientEnchanter.addEnchantment(from, to, i);
    }
    
    public static void addEnchantment(final ItemStack from, final ItemStack to, final int i, final boolean limit) {
    	TileEntityAncientEnchanter.addEnchantment(from, to, i, limit);
    }
    
    public static void addEnchantment(final ItemStack from, final ItemStack to, final int i, final boolean limit, final boolean repairing) {
    	TileEntityAncientEnchanter.addEnchantment(from, to, i, limit, repairing);
    }
    
    public static void addEnchantment(final AetherEnchantmentAncientEnchanter enchantment) {
    	TileEntityAncientEnchanter.addEnchantment(enchantment);
    }
}

