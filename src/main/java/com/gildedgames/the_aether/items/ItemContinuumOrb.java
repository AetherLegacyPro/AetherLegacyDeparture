package com.gildedgames.the_aether.items;

import java.util.Random;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemContinuumOrb extends Item {

	public ItemContinuumOrb() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(AetherCreativeTabs.material);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player) {
		PlayerAether playerAether = PlayerAether.get(player);
		ItemStack heldItem = player.getHeldItem();
			
		player.triggerAchievement(AchievementsAether.continuum_orb);
			int rand = (int)(1 + Math.random() * 191);
			String ItemStack;
			switch (rand) {
				case 1:
					return new ItemStack(ItemsAether.aechor_petal);
				case 2:
					return new ItemStack(ItemsAether.aer_cape);
				case 3:
					return new ItemStack(ItemsAether.aether_portal_frame);
				case 4:
					return new ItemStack(ItemsAether.aether_tune);
				case 5:
					return new ItemStack(ItemsAether.agility_boots);
				case 6:
					return new ItemStack(ItemsAether.agility_cape);
				case 7:
					return new ItemStack(ItemsAether.agility_stone);
				case 8:
					return new ItemStack(ItemsAether.ambrosium_shard);
				case 9:
					return new ItemStack(ItemsAether.arkenium_axe);
				case 10:
					return new ItemStack(ItemsAether.arkenium_boots);
				case 11:
					return new ItemStack(ItemsAether.arkenium_chestplate);
				case 12:
					return new ItemStack(ItemsAether.arkenium_chunk);
				case 13:
					return new ItemStack(ItemsAether.arkenium_fragement);
				case 14:
					return new ItemStack(ItemsAether.arkenium_gloves);
				case 15:
					return new ItemStack(ItemsAether.arkenium_helmet);
				case 16:
					return new ItemStack(ItemsAether.arkenium_ingot);
				case 17:
					return new ItemStack(ItemsAether.arkenium_leggings);
				case 18:
					return new ItemStack(ItemsAether.arkenium_pickaxe);
				case 19:
					return new ItemStack(ItemsAether.arkenium_shovel);
				case 20:
					return new ItemStack(ItemsAether.arkenium_sword);
				case 21:
					return new ItemStack(ItemsAether.ascending_dawn);
				case 22:
					return new ItemStack(ItemsAether.battle_sentry_hammer);
				case 23:
					return new ItemStack(ItemsAether.black_cape);
				case 24:
					return new ItemStack(ItemsAether.blue_cape);
				case 25:
					return new ItemStack(ItemsAether.diamond_pendant);
				case 26:
					return new ItemStack(ItemsAether.blueberry);
				case 27:
					return new ItemStack(ItemsAether.brown_cape);
				case 28:
					return new ItemStack(ItemsAether.candy_cane);
				case 29:
					return new ItemStack(ItemsAether.candy_cane_sword);
				case 30:
					return new ItemStack(ItemsAether.cer_scales);
				case 31:
					return new ItemStack(ItemsAether.chain_gloves);
				case 32:
					return new ItemStack(ItemsAether.zarnillys_scales);
				case 33:
					return new ItemStack(ItemsAether.cloud_staff);
				case 34:
					return new ItemStack(ItemsAether.confractus_staff);
				case 35:
					return new ItemStack(ItemsAether.continuum_axe);
				case 36:
					return new ItemStack(ItemsAether.continuum_boots);
				case 37:
					return new ItemStack(ItemsAether.continuum_chestplate);
				case 38:
					return new ItemStack(ItemsAether.continuum_gemstone);
				case 39:
					return new ItemStack(ItemsAether.continuum_gloves);
				case 40:
					return new ItemStack(ItemsAether.continuum_helmet);
				case 41:
					return new ItemStack(ItemsAether.continuum_leggings);
				case 42:
					return new ItemStack(ItemsAether.continuum_pickaxe);	
				case 43:
					return new ItemStack(ItemsAether.continuum_shovel);
				case 44:
					return new ItemStack(ItemsAether.continuum_sword);
				case 45:
					return new ItemStack(ItemsAether.cyan_cape);
				case 46:
					return new ItemStack(ItemsAether.cyro_rod);
				case 47:
					return new ItemStack(ItemsAether.dart);
				case 48:
					return new ItemStack(ItemsAether.dart_shooter);
				case 49:
					return new ItemStack(ItemsAether.dexterity_stone);
				case 50:
					return new ItemStack(ItemsAether.diamond_gloves);
				case 51:
					return new ItemStack(ItemsAether.divineral_axe);
				case 52:
					return new ItemStack(ItemsAether.divineral_boots);
				case 53:
					return new ItemStack(ItemsAether.divineral_chestplate);
				case 54:
					return new ItemStack(ItemsAether.divineral_gloves);
				case 55:
					return new ItemStack(ItemsAether.divineral_helmet);
				case 56:
					return new ItemStack(ItemsAether.divineral_ingot);
				case 57:
					return new ItemStack(ItemsAether.divineral_leggings);
				case 58:
					return new ItemStack(ItemsAether.divineral_pickaxe);
				case 59:
					return new ItemStack(ItemsAether.enchanted_aerwhale);
				case 60:
					return new ItemStack(ItemsAether.enchanted_blueberry);
				case 61:
					return new ItemStack(ItemsAether.enchanted_divineral);
				case 62:
					return new ItemStack(ItemsAether.valkyrie_nugget);
				case 63:
					return new ItemStack(ItemsAether.zarnillys_scales);
				case 64:
					return new ItemStack(ItemsAether.false_wings);
				case 65:
					return new ItemStack(ItemsAether.flame_stone);
				case 66:
					return new ItemStack(ItemsAether.flaming_stone);
				case 67:
					return new ItemStack(ItemsAether.flaming_sword);
				case 68:
					return new ItemStack(ItemsAether.gingerbread_man);
				case 69:
					return new ItemStack(ItemsAether.golden_amber);
				case 70:
					return new ItemStack(ItemsAether.golden_feather);
				case 71:
					return new ItemStack(ItemsAether.golden_gloves);
				case 72:
					return new ItemStack(ItemsAether.divineral_pendant);
				case 73:
					return new ItemStack(ItemsAether.golden_pendant);
				case 74:
					return new ItemStack(ItemsAether.golden_ring);
				case 75:
					return new ItemStack(ItemsAether.gravitite_axe);
				case 76:
					return new ItemStack(ItemsAether.gravitite_boots);
				case 77:
					return new ItemStack(ItemsAether.gravitite_chestplate);
				case 78:
					return new ItemStack(ItemsAether.gravitite_gloves);
				case 79:
					return new ItemStack(ItemsAether.gravitite_helmet);
				case 80:
					return new ItemStack(ItemsAether.gravitite_leggings);
				case 81:
					return new ItemStack(ItemsAether.gravitite_pickaxe);
				case 82:
					return new ItemStack(ItemsAether.gravitite_shield);
				case 83:
					return new ItemStack(ItemsAether.gravitite_shovel);
				case 84:
					return new ItemStack(ItemsAether.gravitite_sword);			
				case 85:
					return new ItemStack(ItemsAether.gray_cape);
				case 86:
					return new ItemStack(ItemsAether.green_cape);
				case 87:
					return new ItemStack(ItemsAether.gummy_swet);	
				case 88:
					return new ItemStack(ItemsAether.healing_matrix);
				case 89:
					return new ItemStack(ItemsAether.healing_stone);
				case 90:
					return new ItemStack(ItemsAether.holy_sword);
				case 91:
					return new ItemStack(ItemsAether.holystone_axe);
				case 92:
					return new ItemStack(ItemsAether.holystone_pickaxe);
				case 93:
					return new ItemStack(ItemsAether.holystone_shovel);
				case 94:
					return new ItemStack(ItemsAether.holystone_sword);
				case 95:
					return new ItemStack(ItemsAether.ice_pendant);
				case 96:
					return new ItemStack(ItemsAether.ice_ring);
				case 97:
					return new ItemStack(ItemsAether.invisibility_cape);
				case 98:
					return new ItemStack(ItemsAether.aceninum_shard);
				case 99:
					return new ItemStack(ItemsAether.iron_bubble);
				case 100:
					return new ItemStack(ItemsAether.iron_gloves);
				case 101:
					return new ItemStack(ItemsAether.iron_pendant);
				case 102:
					return new ItemStack(ItemsAether.iron_ring);
				case 103:
					return new ItemStack(ItemsAether.jeb_hammer);
				case 104:
					return new ItemStack(ItemsAether.jeb_shield);
				case 105:
					return new ItemStack(ItemsAether.leather_gloves);
				case 106:
					return new ItemStack(ItemsAether.life_shard);
				case 107:
					return new ItemStack(ItemsAether.light_blue_cape);
				case 108:
					return new ItemStack(ItemsAether.light_gray_cape);
				case 109:
					return new ItemStack(ItemsAether.lightning_knife);
				case 110:
					return new ItemStack(ItemsAether.lightning_sword);
				case 111:
					return new ItemStack(ItemsAether.lime_cape);
				case 112:
					return new ItemStack(ItemsAether.lore_book);
				case 113:
					return new ItemStack(ItemsAether.magenta_cape);
				case 114:
					return new ItemStack(ItemsAether.moa_egg);
				case 115:
					return new ItemStack(ItemsAether.nature_staff);
				case 116:
					return new ItemStack(ItemsAether.neptune_boots);
				case 117:
					return new ItemStack(ItemsAether.neptune_chestplate);
				case 118:
					return new ItemStack(ItemsAether.neptune_gloves);
				case 119:
					return new ItemStack(ItemsAether.neptune_helmet);
				case 120:
					return new ItemStack(ItemsAether.neptune_leggings);
				case 121:
					return new ItemStack(ItemsAether.notch_hammer);
				case 122:
					return new ItemStack(ItemsAether.obsidian_boots);
				case 123:
					return new ItemStack(ItemsAether.obsidian_chestplate);
				case 124:
					return new ItemStack(ItemsAether.obsidian_gloves);
				case 125:
					return new ItemStack(ItemsAether.obsidian_helmet);
				case 126:
					return new ItemStack(ItemsAether.obsidian_leggings);
				case 127:
					return new ItemStack(ItemsAether.orange);
				case 128:
					return new ItemStack(ItemsAether.orange_cape);
				case 129:
					return new ItemStack(ItemsAether.phoenix_boots);		
				case 130:
					return new ItemStack(ItemsAether.phoenix_bow);
				case 131:
					return new ItemStack(ItemsAether.phoenix_cape);
				case 132:
					return new ItemStack(ItemsAether.phoenix_chestplate);
				case 133:
					return new ItemStack(ItemsAether.phoenix_gloves);
				case 134:
					return new ItemStack(ItemsAether.phoenix_helmet);
				case 135:
					return new ItemStack(ItemsAether.phoenix_leggings);
				case 136:
					return new ItemStack(ItemsAether.pig_slayer);
				case 137:
					return new ItemStack(ItemsAether.pink_cape);
				case 138:
					return new ItemStack(ItemsAether.purple_cape);
				case 139:
					return new ItemStack(ItemsAether.raspberry);
				case 140:
					return new ItemStack(ItemsAether.raw_aerwhale);
				case 141:
					return new ItemStack(ItemsAether.red_cape);
				case 142:
					return new ItemStack(ItemsAether.regeneration_stone);
				case 143:
					return new ItemStack(ItemsAether.repulsion_shield);
				case 144:
					return new ItemStack(ItemsAether.resistance_stone);
				case 145:
					return new ItemStack(ItemsAether.sentry_boots);
				case 146:
					return new ItemStack(ItemsAether.sentry_shield);
				case 147:
					return new ItemStack(ItemsAether.skyroot_axe);
				case 148:
					return new ItemStack(ItemsAether.skyroot_bucket);
				case 149:
					return new ItemStack(ItemsAether.skyroot_pickaxe);
				case 150:
					return new ItemStack(ItemsAether.skyroot_shovel);
				case 151:
					return new ItemStack(ItemsAether.skyroot_stick);
				case 152:
					return new ItemStack(ItemsAether.skyroot_sword);
				case 153:
					return new ItemStack(ItemsAether.strength_stone);
				case 154:
					return new ItemStack(ItemsAether.swet_ball);
				case 155:
					return new ItemStack(ItemsAether.swet_cape);
				case 156:
					return new ItemStack(ItemsAether.swetty_helmet);
				case 157:
					return new ItemStack(ItemsAether.valkyrie_axe);
				case 158:
					return new ItemStack(ItemsAether.valkyrie_boots);
				case 159:
					return new ItemStack(ItemsAether.valkyrie_cape);	
				case 160:
					return new ItemStack(ItemsAether.valkyrie_chestplate);
				case 161:
					return new ItemStack(ItemsAether.valkyrie_gloves);
				case 162:
					return new ItemStack(ItemsAether.valkyrie_helmet);
				case 163:
					return new ItemStack(ItemsAether.valkyrie_ingot);
				case 164:
					return new ItemStack(ItemsAether.valkyrie_lance);
				case 165:
					return new ItemStack(ItemsAether.valkyrie_leggings);
				case 166:
					return new ItemStack(ItemsAether.valkyrie_nugget);
				case 167:
					return new ItemStack(ItemsAether.valkyrie_pickaxe);
				case 168:
					return new ItemStack(ItemsAether.valkyrie_shovel);
				case 169:
					return new ItemStack(ItemsAether.victory_medal);
				case 170:
					return new ItemStack(ItemsAether.vampire_blade);
				case 171:
					return new ItemStack(ItemsAether.void_tomato);
				case 172:
					return new ItemStack(ItemsAether.welcoming_skies);
				case 173:
					return new ItemStack(ItemsAether.white_apple);
				case 174:
					return new ItemStack(ItemsAether.white_cape);
				case 175:
					return new ItemStack(ItemsAether.wynberry);
				case 176:
					return new ItemStack(ItemsAether.yellow_cape);
				case 177:
					return new ItemStack(ItemsAether.zanite_axe);
				case 178:
					return new ItemStack(ItemsAether.zanite_boots);
				case 179:
					return new ItemStack(ItemsAether.zanite_chestplate);	
				case 180:
					return new ItemStack(ItemsAether.zanite_gemstone);
				case 181:
					return new ItemStack(ItemsAether.zanite_gloves);
				case 182:
					return new ItemStack(ItemsAether.zanite_helmet);
				case 183:
					return new ItemStack(ItemsAether.zanite_leggings);
				case 184:
					return new ItemStack(ItemsAether.zanite_pendant);
				case 185:
					return new ItemStack(ItemsAether.zanite_pickaxe);
				case 186:
					return new ItemStack(ItemsAether.zanite_ring);
				case 187:
					return new ItemStack(ItemsAether.zanite_shield);
				case 188:
					return new ItemStack(ItemsAether.zanite_shovel);
				case 189:
					return new ItemStack(ItemsAether.zanite_sword);	
				case 190:
					return new ItemStack(Blocks.dragon_egg);
				case 191:
					return new ItemStack(Items.diamond);	
					
					
					
					
					
					
			}
			
			return null;
		
		
	}

}