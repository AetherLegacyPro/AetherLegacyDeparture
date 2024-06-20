package com.gildedgames.the_aether.blocks;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.block.ItemAetherSlab;
import com.gildedgames.the_aether.items.block.ItemBlockEnchanter;
import com.gildedgames.the_aether.items.block.ItemBlockFireProof;
import com.gildedgames.the_aether.items.block.ItemBlockMetadata;
import com.gildedgames.the_aether.items.block.ItemBlockRarity;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenBlueSkyrootTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenCrystalTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenDarkBlueSkyrootTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenDivineTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenGreatrootTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenGreatwoodTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenOakTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenPurpleSkyrootTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenSkyrootTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenVoidTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenWisprootTree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import com.gildedgames.the_aether.blocks.container.BlockAetherEnchantmentTable;
import com.gildedgames.the_aether.blocks.container.BlockAetherTNT;
import com.gildedgames.the_aether.blocks.container.BlockAmplifier;
import com.gildedgames.the_aether.blocks.container.BlockDivineEnchantmentTable;
import com.gildedgames.the_aether.blocks.container.BlockElysianChest;
import com.gildedgames.the_aether.blocks.container.BlockEnchanter;
import com.gildedgames.the_aether.blocks.container.BlockFreezer;
import com.gildedgames.the_aether.blocks.container.BlockIncubator;
import com.gildedgames.the_aether.blocks.container.BlockProtector;
import com.gildedgames.the_aether.blocks.container.BlockSkyrootChest;
import com.gildedgames.the_aether.blocks.container.BlockSkyrootWorkbench;
import com.gildedgames.the_aether.blocks.container.BlockSunAltar;
import com.gildedgames.the_aether.blocks.container.BlockTreasureChestBreakable;
import com.gildedgames.the_aether.blocks.decorative.BlockAerogel;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFence;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceGate;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceGateDivineOak;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceGateGoldenOak;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceGateGreatroot;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceGateVoid;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceGateWisproot;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceGreatroot;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceVoid;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherFenceWisproot;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherLadder;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherLever;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherSlab;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherStairs;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherTrapdoor;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherWall;
import com.gildedgames.the_aether.blocks.decorative.BlockAmbrosiumTorch;
import com.gildedgames.the_aether.blocks.decorative.BlockAuralitePillar;
import com.gildedgames.the_aether.blocks.decorative.BlockCrystallizedGenesisStone;
import com.gildedgames.the_aether.blocks.decorative.BlockDivineOakFence;
import com.gildedgames.the_aether.blocks.decorative.BlockFrozenQuicksoilGlass;
import com.gildedgames.the_aether.blocks.decorative.BlockGoldenOakBookshelf;
import com.gildedgames.the_aether.blocks.decorative.BlockGoldenOakFence;
import com.gildedgames.the_aether.blocks.decorative.BlockInfernoTorch;
import com.gildedgames.the_aether.blocks.decorative.BlockPresent;
import com.gildedgames.the_aether.blocks.decorative.BlockQuicksoilGlass;
import com.gildedgames.the_aether.blocks.decorative.BlockSkyrootBookshelf;
import com.gildedgames.the_aether.blocks.decorative.BlockSkyrootPlanks;
import com.gildedgames.the_aether.blocks.decorative.BlockZanite;
import com.gildedgames.the_aether.blocks.dungeon.BlockAceninum;
import com.gildedgames.the_aether.blocks.dungeon.BlockAceninumCluster;
import com.gildedgames.the_aether.blocks.dungeon.BlockDiamondAercloud;
import com.gildedgames.the_aether.blocks.dungeon.BlockDungeonBase;
import com.gildedgames.the_aether.blocks.dungeon.BlockDungeonBaseOsmium;
import com.gildedgames.the_aether.blocks.dungeon.BlockDungeonTrap;
import com.gildedgames.the_aether.blocks.dungeon.BlockDungeonTrap2;
import com.gildedgames.the_aether.blocks.dungeon.BlockGenesisStone2;
import com.gildedgames.the_aether.blocks.dungeon.BlockLightGenesisStone2;
import com.gildedgames.the_aether.blocks.dungeon.BlockMimicChest;
import com.gildedgames.the_aether.blocks.dungeon.BlockMimicElysian;
import com.gildedgames.the_aether.blocks.dungeon.BlockPillar;
import com.gildedgames.the_aether.blocks.dungeon.BlockTreasureChest;
import com.gildedgames.the_aether.blocks.elysian_totem.BlockElysianTotem;
import com.gildedgames.the_aether.blocks.natural.BlockAercloud;
import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer;
import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer2;
import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer3;
import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer4;
import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer5;
import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer6;
import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer7;
import com.gildedgames.the_aether.blocks.natural.BlockAetherDirt;
import com.gildedgames.the_aether.blocks.natural.BlockAetherFlower;
import com.gildedgames.the_aether.blocks.natural.BlockAetherGrass;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLeaves;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLog;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLogDivine;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLogGreatroot;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLogVoid;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLogWisproot;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOre;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOreAetheral;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOreAgiosite;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOreDeific;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOreNew;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOreNewAetheral;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOreNewAgiosite;
import com.gildedgames.the_aether.blocks.natural.BlockAetherOreNewDeific;
import com.gildedgames.the_aether.blocks.natural.BlockAetherTallGrass;
import com.gildedgames.the_aether.blocks.natural.BlockArcticGrass;
import com.gildedgames.the_aether.blocks.natural.BlockArcticTallGrass;
import com.gildedgames.the_aether.blocks.natural.BlockArkeniumPile;
import com.gildedgames.the_aether.blocks.natural.BlockAuralite;
import com.gildedgames.the_aether.blocks.natural.BlockAuraliteCluster;
import com.gildedgames.the_aether.blocks.natural.BlockBerryBush;
import com.gildedgames.the_aether.blocks.natural.BlockBerryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockBlackberryBush;
import com.gildedgames.the_aether.blocks.natural.BlockBlackberryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockBuddingAuralite;
import com.gildedgames.the_aether.blocks.natural.BlockContinuumPile;
import com.gildedgames.the_aether.blocks.natural.BlockDivineAetherFlower;
import com.gildedgames.the_aether.blocks.natural.BlockDivineAetherTallGrass;
import com.gildedgames.the_aether.blocks.natural.BlockDivineGrass;
import com.gildedgames.the_aether.blocks.natural.BlockDivineralPile;
import com.gildedgames.the_aether.blocks.natural.BlockEmpyreanOre;
import com.gildedgames.the_aether.blocks.natural.BlockEnchantedAetherFlower;
import com.gildedgames.the_aether.blocks.natural.BlockEnchantedAetherGrass;
import com.gildedgames.the_aether.blocks.natural.BlockEnchantedAetherTallGrass;
import com.gildedgames.the_aether.blocks.natural.BlockFrozenQuicksoil;
import com.gildedgames.the_aether.blocks.natural.BlockGoldenOakNewLog;
import com.gildedgames.the_aether.blocks.natural.BlockGrapeTreeMature;
import com.gildedgames.the_aether.blocks.natural.BlockGrapeTreeStage1;
import com.gildedgames.the_aether.blocks.natural.BlockGrapeTreeStage2;
import com.gildedgames.the_aether.blocks.natural.BlockGravititePile;
import com.gildedgames.the_aether.blocks.natural.BlockHolystone;
import com.gildedgames.the_aether.blocks.natural.BlockInebriationAercloud;
import com.gildedgames.the_aether.blocks.natural.BlockLargeOrangeTree;
import com.gildedgames.the_aether.blocks.natural.BlockMatureOrangeTree;
import com.gildedgames.the_aether.blocks.natural.BlockMediumBerryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockMediumBlackberryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockMediumOrangeTree;
import com.gildedgames.the_aether.blocks.natural.BlockMediumRaspberryBush;
import com.gildedgames.the_aether.blocks.natural.BlockMediumStrawberryBush;
import com.gildedgames.the_aether.blocks.natural.BlockQuicksoil;
import com.gildedgames.the_aether.blocks.natural.BlockRaspberryBush;
import com.gildedgames.the_aether.blocks.natural.BlockRaspberryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockSmallBerryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockSmallBlackberryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockSmallOrangeTree;
import com.gildedgames.the_aether.blocks.natural.BlockSmallRaspberryBush;
import com.gildedgames.the_aether.blocks.natural.BlockSmallStrawberryBush;
import com.gildedgames.the_aether.blocks.natural.BlockSnowyTallGrass;
import com.gildedgames.the_aether.blocks.natural.BlockStrawberryBush;
import com.gildedgames.the_aether.blocks.natural.BlockStrawberryBushStem;
import com.gildedgames.the_aether.blocks.natural.BlockVerdantGrass;
import com.gildedgames.the_aether.blocks.natural.BlockVoidSapling;
import com.gildedgames.the_aether.blocks.natural.BlockZanitePile;
import com.gildedgames.the_aether.blocks.portal.BlockAetherPortal;
import com.gildedgames.the_aether.blocks.util.BlockFloating;
import com.gildedgames.the_aether.blocks.ancient.enchanter.BlockAncientEnchanter;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlocksAether {

	//World Generation
	public static Block aether_grass, arctic_grass, verdant_grass, enchanted_aether_grass, aether_dirt, quicksoil;
	public static Block holystone, holystone_brick, aetheral_stone, agiosite, deific;	
	public static Block mossy_holystone, bloodmoss_stone, azure_holystone, notch_holystone;
	public static Block glowing_icestone;
	public static Block aercloud, pink_aercloud, green_aercloud, purple_aercloud;
	
	//Decoration Blocks
	public static Block enchanted_holystone, enchanted_aetheral_stone, enchanted_agiosite, enchanted_deific;	
	public static Block aetheral_stone_bricks, agiosite_bricks, deific_bricks, carved_caelestia_stone;	
	public static Block enchanted_deific_bricks, enchanted_agiosite_bricks, enchanted_aetheral_stone_bricks, enchanted_holystone_bricks;	
	public static Block aerogel, quicksoil_glass, frozen_quicksoil_glass;	
	public static Block enchanted_gravitite, zanite_block, arkenium_block, ambrosium_block, continuum_block, divineral_block, reinforced_arkenium_block;
	public static Block ambrosium_torch, ethereal_torch, inferno_torch, holystone_lever;
	public static Block zanite_trapdoor;
	public static Block aercloud_layer, aercloud_layer_2, aercloud_layer_3, aercloud_layer_4; 
	public static Block aercloud_layer_5, aercloud_layer_6, aercloud_layer_7;
	public static Block auralite_pillar;
	
	//Aether Ores
	public static Block ambrosium_ore, zanite_ore, gravitite_ore, arkenium_ore, continuum_ore, icestone, primeval_artifact;	
	public static Block aetheral_ambrosium_ore, aetheral_zanite_ore, aetheral_gravitite_ore, aetheral_arkenium_ore, aetheral_continuum_ore;	
	public static Block agiosite_ambrosium_ore, agiosite_zanite_ore, agiosite_gravitite_ore, agiosite_arkenium_ore, agiosite_continuum_ore;	
	public static Block deific_ambrosium_ore, deific_zanite_ore, deific_gravitite_ore, deific_arkenium_ore, deific_continuum_ore;
	public static Block empyrean_ore, empyrean_ore_2, empyrean_block, charged_empyrean_block;
	public static Block block_of_auralite, budding_auralite, auralite_cluster_1, auralite_cluster_2;
	
	//Aether Wood or Leaves
	public static Block skyroot_leaves, golden_oak_leaves, crystal_leaves, green_light_skyroot_leaves, blue_light_skyroot_leaves, void_leaves;
	public static Block purple_skyroot_leaves, blue_skyroot_leaves, dark_blue_skyroot_leaves, divine_oak_leaves;
	public static Block golden_oak_fruit_leaves, crystal_fruit_leaves, purple_skyroot_fruit_leaves, void_fruit_leaves;
	public static Block holiday_leaves, decorated_holiday_leaves, present;
	public static Block skyroot_log, golden_oak_log, golden_oak_new_log, greatroot_log, wisproot_log, void_log, divine_oak_log;	
	public static Block skyroot_planks, golden_oak_planks, greatroot_planks, wisproot_planks, void_planks, divine_oak_planks;
	public static Block skyroot_bookshelf, golden_oak_bookshelf;
	public static Block skyroot_fence, golden_oak_fence, greatroot_fence, wisproot_fence, void_fence, divine_oak_fence;
	public static Block skyroot_fence_gate, golden_oak_gate, greatroot_fence_gate, wisproot_fence_gate, void_fence_gate, divine_oak_gate;
	public static Block skyroot_trapdoor, golden_oak_trapdoor, wisproot_trapdoor, greatroot_trapdoor, void_trapdoor;
	public static Block skyroot_ladder;
	
	//Aether Crops
	public static Block aether_farmland, enchanted_aether_farmland;
	public static Block small_blueberry_bush, medium_blueberry_bush, berry_bush_stem, berry_bush;
	public static Block small_raspberry_bush, medium_raspberry_bush, raspberry_bush_stem, raspberry_bush;
	public static Block small_strawberry_bush, medium_strawberry_bush, strawberry_bush_stem, strawberry_bush;
	public static Block small_blackberry_bush, medium_blackberry_bush, blackberry_bush_stem, blackberry_bush;
	public static Block small_orange_tree, medium_orange_tree, large_orange_tree, mature_orange_tree;
	public static Block grape_tree_stage_one, grape_tree_stage_two, grape_tree_mature;
	
	//Aether Flowers, Grasses, or Saplings
	public static Block purple_flower, white_flower, white_rose, aechor_sprout, blue_swingtip, neverbloom;	
	public static Block quickshoot, enchanted_quickshoot, aether_tulips, enchanted_aether_tulips, enchanted_bloom;	
	public static Block aether_tallgrass, arctic_tallgrass, snowy_tallgrass, enchanted_aether_tallgrass, burstblossom, carrion_flower, moonlit_bloom;
	public static Block divine_aether_tallgrass, divine_stalk, divine_lily, divine_bloom;
	public static Block skyroot_sapling, golden_oak_sapling, crystal_sapling, void_sapling, divine_oak_sapling;	
	public static Block purple_skyroot_sapling, blue_skyroot_sapling, dark_blue_skyroot_sapling, wisproot_sapling, greatroot_sapling;	
	
	//Tile Entities & Animated Blocks
	public static Block enchanter, freezer, incubator, amplifier;
	public static Block aether_portal, coldfire, hellfire;
	public static Block chest_mimic, treasure_chest, treasure_chest_breakable, skyroot_chest, elysian_chest, elysian_mimic;
	public static Block skyroot_bed, aether_tnt;
	public static Block sun_altar, skyroot_workbench;	 
	public static Block ancient_enchanter, aether_enchantment_table, divine_enchantment_table;
	public static Block elysian_totem;
	
	//Bronze Dungeon Exclusive Blocks
	public static Block carved_stone, ancient_carved_stone, divine_carved_stone, mythic_carved_stone;
	public static Block locked_carved_stone, locked_ancient_carved_stone, locked_divine_carved_stone, locked_mythic_carved_stone;
	public static Block sentry_stone, ancient_sentry_stone, divine_sentry_stone, mythic_sentry_stone;
	public static Block locked_sentry_stone, locked_ancient_sentry_stone, locked_divine_sentry_stone, locked_mythic_sentry_stone;
	public static Block carved_trap, divine_carved_trap;
	
	//Cobalt Dungeon Exclusive Blocks
	public static Block frozen_quicksoil;
	public static Block oblitus_stone, oblitus_stone_2, cracked_oblitus_stone, cracked_oblitus_stone_2;
	
	//Silver Dungeon Exclusive Blocks
	public static Block pillar, pillar_top;	
	public static Block angelic_stone, ancient_angelic_stone, divine_angelic_stone, mythic_angelic_stone;
	public static Block light_angelic_stone, ancient_light_angelic_stone, divine_light_angelic_stone, mythic_light_angelic_stone;
	public static Block locked_angelic_stone, locked_ancient_angelic_stone, locked_divine_angelic_stone, locked_mythic_angelic_stone; 
	public static Block locked_light_angelic_stone, locked_ancient_light_angelic_stone, locked_divine_light_angelic_stone, locked_mythic_light_angelic_stone;
	public static Block angelic_trap, ancient_angelic_trap, divine_angelic_trap;
		
	//Gold Dungeon Exclusive Blocks
	public static Block luminous_stone, arctic_glowstone, amethyst_glowstone;
	public static Block hellfire_stone, ancient_hellfire_stone, divine_hellfire_stone, mythic_hellfire_stone;
	public static Block light_hellfire_stone, ancient_light_hellfire_stone, divine_light_hellfire_stone, mythic_light_hellfire_stone;		
	public static Block locked_hellfire_stone, locked_ancient_hellfire_stone, locked_divine_hellfire_stone;
	public static Block locked_light_hellfire_stone, locked_ancient_light_hellfire_stone, locked_divine_light_hellfire_stone;
	public static Block hellfire_trap, ancient_hellfire_trap;
	
	//Osminum Dungeon Exclusive Blocks
	public static Block caelestia_stone, storm_aercloud, inebriation_aercloud;
	public static Block fuse_stone; 
	public static Block creeping_stone;
	public static Block locked_fuse_stone;
	public static Block locked_creeping_stone;			
	public static Block fuse_trap, fuse_trap_2;
	
	//Palladium Dungeon Exclusive Blocks
	public static Block genesis_stone, light_genesis_stone, crystallized_genesis_stone, divine_grass; 
	public static Block genesis_stone_2, light_genesis_stone_2, genesis_trap;
	public static Block genesis_stairs, genesis_slab, genesis_double_slab, genesis_wall;
	public static Block block_of_aceninum, aceninum_cluster;
	public static Block diamond_aercloud, cer_scales_block, elysian_scales_block;
	public static Block zanite_pile, continuum_pile, gravitite_pile, arkenium_pile, divineral_pile;
	
	//Aether Stairs
	public static Block carved_stairs, angelic_stairs, hellfire_stairs, fuse_stairs, skyroot_stairs, mossy_holystone_stairs, holystone_stairs;
	public static Block holystone_brick_stairs, aerogel_stairs, golden_oak_stairs, greatroot_stairs, wisproot_stairs, agiosite_stairs; 
	public static Block enchanted_agiosite_stairs, aetheral_stone_stairs, enchanted_aetheral_stone_stairs, void_stairs, deific_stairs;
	public static Block enchanted_deific_stairs, enchanted_holystone_stairs, enchanted_holystone_brick_stairs, caelestia_stone_stairs;	
	public static Block ancient_carved_stone_stairs, divine_carved_stone_stairs, ancient_angelic_stone_stairs, divine_angelic_stone_stairs, divine_hellfire_stone_stairs;	
	public static Block mythic_carved_stone_stairs, mythic_angelic_stone_stairs, mythic_hellfire_stone_stairs, oblitus_stairs, divine_oak_stairs;	
	public static Block aetheral_stone_brick_stairs, agiosite_brick_stairs, deific_brick_stairs, carved_caelestia_stone_stairs;
	public static Block enchanted_agiosite_brick_stairs, enchanted_deific_brick_stairs, enchanted_aetheral_stone_brick_stairs;
	public static Block ancient_hellfire_stairs;
	
	//Aether Slabs
	public static Block carved_slab, angelic_slab, hellfire_slab, fuse_slab, skyroot_slab, holystone_slab, holystone_brick_slab, divine_oak_slab;
	public static Block mossy_holystone_slab, aerogel_slab, greatroot_slab, wisproot_slab, agiosite_slab, agiosite_brick_slab, aetheral_stone_brick_slab;
	public static Block enchanted_agiosite_slab, aetheral_stone_slab, enchanted_aetheral_stone_slab, void_slab, deific_slab, deific_brick_slab;
	public static Block enchanted_deific_slab, enchanted_holystone_slab, enchanted_holystone_brick_slab, caelestia_stone_slab, carved_caelestia_stone_slab;
	public static Block enchanted_agiosite_brick_slab, ancient_carved_stone_slab, divine_carved_stone_slab, golden_oak_slab, divine_hellfire_stone_slab;
	public static Block mythic_carved_stone_slab, divine_angelic_stone_slab, mythic_angelic_stone_slab, oblitus_stone_slab, enchanted_deific_brick_slab;
	public static Block enchanted_aetheral_stone_brick_slab, ancient_angelic_stone_slab, ancient_hellfire_stone_slab;
	
	//Aether Double Slabs
	public static Block carved_double_slab, angelic_double_slab, hellfire_double_slab, fuse_double_slab, skyroot_double_slab, holystone_double_slab;
	public static Block holystone_brick_double_slab, mossy_holystone_double_slab, aerogel_double_slab, greatroot_double_slab, aetheral_stone_brick_double_slab;
	public static Block wisproot_double_slab, agiosite_double_slab, enchanted_agiosite_double_slab, aetheral_stone_double_slab, divine_oak_double_slab;
	public static Block enchanted_aetheral_stone_double_slab, void_double_slab, deific_double_slab, agiosite_brick_double_slab, deific_brick_double_slab;
	public static Block enchanted_deific_double_slab, enchanted_holystone_double_slab, enchanted_holystone_brick_double_slab, caelestia_stone_double_slab; 
	public static Block carved_caelestia_stone_double_slab, enchanted_agiosite_brick_double_slab, divine_carved_stone_double_slab;
	public static Block golden_oak_double_slab, divine_hellfire_stone_double_slab, mythic_carved_stone_double_slab, oblitus_stone_double_slab;
	public static Block ancient_carved_stone_double_slab, divine_angelic_stone_double_slab, mythic_angelic_stone_double_slab, enchanted_deific_brick_double_slab;
	public static Block enchanted_aetheral_stone_brick_double_slab, ancient_angelic_stone_double_slab, ancient_hellfire_stone_double_slab;
	
	//Aether Walls
	public static Block holystone_wall, mossy_holystone_wall, holystone_brick_wall, carved_wall, angelic_wall, hellfire_wall, fuse_wall, aerogel_wall;
	public static Block agiosite_wall, agiosite_brick_wall, enchanted_agiosite_wall, aetheral_stone_wall, enchanted_aetheral_stone_wall, aetheral_stone_brick_wall;
	public static Block deific_wall, deific_brick_wall, enchanted_deific_wall, enchanted_holystone_wall, enchanted_holystone_brick_wall, caelestia_stone_wall, carved_caelestia_stone_wall;
	public static Block mythic_carved_stone_wall, mythic_angelic_stone_wall, enchanted_agiosite_brick_wall, oblitus_wall, enchanted_deific_brick_wall;
	public static Block skyroot_log_wall, golden_oak_log_wall, wisproot_log_wall, greatroot_log_wall, void_log_wall, enchanted_aetheral_stone_brick_wall;	
	public static Block ancient_carved_stone_wall, divine_carved_stone_wall, divine_angelic_stone_wall, divine_hellfire_stone_wall, divine_oak_log_wall;
	public static Block ancient_angelic_stone_wall, ancient_hellfire_wall;
	
	//Not Complete or Not Implemented
				
	public static int AncientEnchanterRenderId;
	public static int SkyrootChestRenderId;
	public static int ElysianChestRenderId;
	public static int TreasureChestBreakbleRenderId;
	public static int AuraliteClusterRenderId;
	public static int ElysianTotemRenderId;
	
	public static void initialization() {
		
		//Building Blocks
		aether_grass = registerMeta("aether_grass", new BlockAetherGrass());
		arctic_grass = registerMeta("arctic_grass", new BlockArcticGrass());
		verdant_grass = registerMeta("verdant_grass", new BlockVerdantGrass());
		divine_grass = registerMeta("divine_grass", new BlockDivineGrass());
		enchanted_aether_grass = registerRarity("enchanted_aether_grass", new BlockEnchantedAetherGrass(), EnumRarity.rare);
		aether_dirt = registerMeta("aether_dirt", new BlockAetherDirt());
		aether_farmland = registerMeta("aether_farmland", new BlockAetherFarmland());
		enchanted_aether_farmland = registerRarity("enchanted_aether_farmland", new BlockEnchantedAetherFarmland(), EnumRarity.rare);		
		
		holystone = registerMeta("holystone", new BlockHolystone());
		holystone_brick = register("holystone_brick", new BlockAether(Material.rock, Aether.find("holystone_brick")).setHardness(0.5F).setResistance(10.0F));
		enchanted_holystone = registerMeta("enchanted_holystone", new BlockEnchantedAgiosite().setBlockTextureName(Aether.findHighlands("agiosite")));
		enchanted_holystone_bricks = register("enchanted_holystone_bricks", new BlockAether(Material.rock, Aether.find("enchanted_holystone_bricks")).setHardness(0.6F).setResistance(14.0F));			
		aetheral_stone = registerMeta("aetheral_stone", new BlockHolystone().setBlockTextureName(Aether.find("aetheral_stone")).setHardness(3.0F).setResistance(5.0F));
		aetheral_stone_bricks = register("aetheral_stone_bricks", new BlockAether(Material.rock, Aether.find("aetheral_stone_brick")).setHardness(3.0F).setResistance(5.0F));
		enchanted_aetheral_stone = registerMeta("enchanted_aetheral_stone", new BlockEnchantedAetheralStone().setHardness(3.0F).setResistance(5.0F));
		enchanted_aetheral_stone_bricks = register("enchanted_aetheral_stone_bricks", new BlockAether(Material.rock, Aether.find("enchanted_aetheral_stone_brick")).setHardness(3.0F).setResistance(5.0F));
		deific = registerMeta("deific", new BlockHolystone().setBlockTextureName(Aether.find("deific")).setHardness(5.5F).setResistance(7.0F));
		deific_bricks = register("deific_bricks", new BlockAether(Material.rock, Aether.find("deific_bricks")).setHardness(5.5F).setResistance(7.0F));
		enchanted_deific = registerMeta("enchanted_deific", new BlockEnchantedAetheralStone().setBlockTextureName(Aether.find("enchanted_deific")).setHardness(5.5F).setResistance(7.0F));
		enchanted_deific_bricks = register("enchanted_deific_bricks", new BlockAether(Material.rock, Aether.find("enchanted_deific_bricks")).setHardness(5.5F).setResistance(7.0F));
		agiosite = registerMeta("agiosite", new BlockAgiosite().setHardness(4.0F).setResistance(6.0F));
		agiosite_bricks = register("agiosite_bricks", new BlockAether(Material.rock, Aether.find("agiosite_stone_bricks")).setHardness(4.0F).setResistance(6.0F));
		enchanted_agiosite = registerMeta("enchanted_agiosite", new BlockEnchantedAgiosite().setBlockTextureName(Aether.find("enchanted_agiosite")).setHardness(4.0F).setResistance(6.0F));
		enchanted_agiosite_bricks = register("enchanted_agiosite_bricks", new BlockAether(Material.rock, Aether.find("enchanted_agiosite_stone_bricks")).setHardness(4.0F).setResistance(6.0F));
		caelestia_stone = registerMeta("caelestia_stone", new BlockHolystone().setResistance(124.0F).setBlockTextureName(Aether.find("caelestia_stone")));
		carved_caelestia_stone = registerMeta("carved_caelestia_stone", new BlockCarvedCaelestia().setResistance(18.0F).setHardness(0.7F).setResistance(12.0F));
		aerogel = registerRarity("aerogel", new BlockAerogel(), ItemsAether.aether_loot);
		quicksoil = registerMeta("quicksoil", new BlockQuicksoil());
		quicksoil_glass = registerRarity("quicksoil_glass", new BlockQuicksoilGlass(), EnumRarity.rare);
		frozen_quicksoil = registerMeta("frozen_quicksoil", new BlockFrozenQuicksoil());
		frozen_quicksoil_glass = registerRarity("frozen_quicksoil_glass", new BlockFrozenQuicksoilGlass(), EnumRarity.rare);
		glowing_icestone = register("glowing_icestone", new BlockGlowingIcestone());
		luminous_stone = registerMeta("luminous_stone", new BlockLuminousStone().setBlockTextureName(Aether.find("luminous_stone")));
		arctic_glowstone = registerMeta("arctic_glowstone", new BlockLuminousStone().setBlockTextureName(Aether.find("arctic_glowstone")));
		amethyst_glowstone = registerMeta("amethyst_glowstone", new BlockLuminousStone().setBlockTextureName(Aether.find("amethyst_glowstone")));
		cer_scales_block = registerRarity("cer_scales_block", new BlockAerScalesBlock(), ItemsAether.aether_loot);
		elysian_scales_block = registerRarity("elysian_scales_block", new BlockElysianScalesBlock(), ItemsAether.divine_aether_loot).setBlockTextureName(Aether.find("elysian_scales_block"));
		ambrosium_torch = register("ambrosium_torch", new BlockAmbrosiumTorch().setBlockTextureName(Aether.find("ambrosium_torch")));
		ethereal_torch = register("ethereal_torch", new BlockEtherealTorch().setBlockTextureName(Aether.find("ethereal_torch")));
		inferno_torch = register("hellfire_torch", new BlockInfernoTorch().setBlockTextureName(Aether.find("hellfire_torch")));
		holystone_lever = registerMeta("holystone_lever", new BlockAetherLever(Material.circuits).setBlockTextureName(Aether.find("holystone_lever")));
		ambrosium_block = register("ambrosium_block", new BlockAmbrosium());
		zanite_block = register("zanite_block", new BlockZanite());
		enchanted_gravitite = registerRarity("enchanted_gravitite", new BlockFloating(Material.rock, true).setHardness(5.0F).setBlockTextureName(Aether.find("enchanted_gravitite")), EnumRarity.rare);
		arkenium_block = register("arkenium_block", new BlockArkenium());		
		reinforced_arkenium_block = registerRarity("reinforced_arkenium_block", new BlockReinforcedArkenium().setBlockTextureName(Aether.find("reinforced_arkenium_block")), EnumRarity.rare);		
		empyrean_block = registerRarity("empyrean_block", new BlockReinforcedArkenium().setBlockTextureName(Aether.find("empyrean_block")), EnumRarity.rare);
		charged_empyrean_block = registerRarity("charged_empyrean_block", new BlockReinforcedArkenium().setBlockTextureName(Aether.find("charged_empyrean_block")), EnumRarity.rare);		
		continuum_block = register("continuum_block", new BlockContinuum());
		divineral_block = registerItemBlock("divineral_block", new BlockDivineral(), ItemBlockFireProof.class);

		//Ores
		ambrosium_ore = register("ambrosium_ore", new BlockAetherOre(0).setBlockTextureName(Aether.find("ambrosium_ore")));
		zanite_ore = register("zanite_ore", new BlockAetherOre(1).setBlockTextureName(Aether.find("zanite_ore")));
		gravitite_ore = register("gravitite_ore", new BlockFloating(Material.rock, false).setHardness(5.0F).setBlockTextureName(Aether.find("gravitite_ore")));
		arkenium_ore = register("arkenium_ore", new BlockAetherOreNew(1).setBlockTextureName(Aether.find("arkenium_ore")));
		continuum_ore = register("continuum_ore", new BlockAetherOreNew(1).setBlockTextureName(Aether.findII("continuumOre")));
		
		aetheral_ambrosium_ore = register("aetheral_ambrosium_ore", new BlockAetherOreAetheral(0).setBlockTextureName(Aether.find("aetheral_ambrosium_ore")));
		aetheral_zanite_ore = register("aetheral_zanite_ore", new BlockAetherOreAetheral(1).setBlockTextureName(Aether.find("aetheral_zanite_ore")));
		aetheral_gravitite_ore = register("aetheral_gravitite_ore", new BlockFloating(Material.rock, false).setHardness(5.0F).setBlockTextureName(Aether.find("aetheral_gravitite_ore")));
		aetheral_arkenium_ore = register("aetheral_arkenium_ore", new BlockAetherOreNewAetheral(1).setBlockTextureName(Aether.find("aetheral_arkenium_ore")));
		aetheral_continuum_ore = register("aetheral_continuum_ore", new BlockAetherOreNewAetheral(1).setBlockTextureName(Aether.find("aetheral_continuum_ore")));
		
		agiosite_ambrosium_ore = register("agiosite_ambrosium_ore", new BlockAetherOreAgiosite(0).setBlockTextureName(Aether.find("agiosite_ambrosium_ore")));
		agiosite_zanite_ore = register("agiosite_zanite_ore", new BlockAetherOreAgiosite(1).setBlockTextureName(Aether.find("agiosite_zanite_ore")));
		agiosite_gravitite_ore = register("agiosite_gravitite_ore", new BlockFloating(Material.rock, false).setHardness(5.0F).setBlockTextureName(Aether.find("agiosite_gravitite_ore")));
		agiosite_arkenium_ore = register("agiosite_arkenium_ore", new BlockAetherOreNewAgiosite(1).setBlockTextureName(Aether.find("agiosite_arkenium_ore")));
		agiosite_continuum_ore = register("agiosite_continuum_ore", new BlockAetherOreNewAgiosite(1).setBlockTextureName(Aether.find("agiosite_continuum_ore")));
		
		deific_ambrosium_ore = register("deific_ambrosium_ore", new BlockAetherOreDeific(0).setBlockTextureName(Aether.find("deific_ambrosium_ore")));
		deific_zanite_ore = register("deific_zanite_ore", new BlockAetherOreDeific(1).setBlockTextureName(Aether.find("deific_zanite_ore")));
		deific_gravitite_ore = register("deific_gravitite_ore", new BlockFloating(Material.rock, false).setHardness(5.0F).setBlockTextureName(Aether.find("deific_gravitite_ore")));
		deific_arkenium_ore = register("deific_arkenium_ore", new BlockAetherOreNewDeific(1).setBlockTextureName(Aether.find("deific_arkenium_ore")));
		deific_continuum_ore = register("deific_continuum_ore", new BlockAetherOreNewDeific(1).setBlockTextureName(Aether.find("deific_continuum_ore")));
		
		empyrean_ore = registerRarity("empyrean_ore", new BlockEmpyreanOre(), ItemsAether.scaled_aether_loot).setBlockTextureName(Aether.find("empyrean_ore"));
		empyrean_ore_2 = registerRarity("empyrean_ore_2", new BlockEmpyreanOre(), ItemsAether.scaled_aether_loot).setBlockTextureName(Aether.find("empyrean_ore_2"));
		
		icestone = register("icestone", new BlockIcestone());
		primeval_artifact = registerRarity("primeval_artifact", new BlockPrimevalDebris(), ItemsAether.divine_aether_loot);
		
		block_of_auralite = registerRarity("block_of_auralite", new BlockAuralite().setBlockTextureName(Aether.find("block_of_auralite")), ItemsAether.scaled_aether_loot);
		budding_auralite = registerRarity("budding_auralite", new BlockBuddingAuralite().setBlockTextureName(Aether.find("budding_auralite")), ItemsAether.scaled_aether_loot);
		auralite_pillar = registerRarity("auralite_pillar", new BlockAuralitePillar().setBlockTextureName(Aether.find("block_of_auralite")), ItemsAether.scaled_aether_loot);
		auralite_cluster_1 = registerRarity("auralite_cluster_1", new BlockAuraliteCluster(0), ItemsAether.scaled_aether_loot);
		auralite_cluster_2 = registerRarity("auralite_cluster_2", new BlockAuraliteCluster(1), ItemsAether.scaled_aether_loot);
		
		block_of_aceninum = registerRarity("block_of_aceninum", new BlockAceninum().setBlockTextureName(Aether.find("block_of_aceninum")), ItemsAether.divine_aether_loot);
		aceninum_cluster = registerRarity("aceninum_cluster", new BlockAceninumCluster(0), ItemsAether.divine_aether_loot);
		
		//Aerclouds
		aercloud = registerMeta("aercloud", new BlockAercloud());
		aercloud_layer = registerMeta("aercloud_layer", new BlockAercloudLayer());
		aercloud_layer_2 = registerMeta("aercloud_layer_2", new BlockAercloudLayer2());
		aercloud_layer_3 = registerMeta("aercloud_layer_3", new BlockAercloudLayer3());
		aercloud_layer_4 = registerMeta("aercloud_layer_4", new BlockAercloudLayer4());
		aercloud_layer_5 = registerMeta("aercloud_layer_5", new BlockAercloudLayer5());
		aercloud_layer_6 = registerMeta("aercloud_layer_6", new BlockAercloudLayer6());
		aercloud_layer_7 = registerMeta("aercloud_layer_7", new BlockAercloudLayer7());		
		pink_aercloud = registerMeta("pink_aercloud", new BlockPinkAercloud());
		storm_aercloud = register("storm_aercloud", new BlockStormAercloud());
		green_aercloud = registerMeta("green_aercloud", new BlockGreenAercloud());
		purple_aercloud = registerMeta("purple_aercloud", new BlockPurpleAercloud());
		inebriation_aercloud = registerMeta("inebriation_aercloud", new BlockInebriationAercloud());
		diamond_aercloud = registerMeta("diamond_aercloud", new BlockDiamondAercloud());
		
		//Plants or Leaves
		skyroot_leaves = register("skyroot_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("skyroot_leaves")));
		golden_oak_leaves = register("golden_oak_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("golden_oak_leaves")));
		golden_oak_fruit_leaves = register("golden_oak_fruit_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("golden_oak_fruit_leaves")));
		crystal_leaves = register("crystal_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("crystal_leaves")));
		crystal_fruit_leaves = register("crystal_fruit_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("crystal_fruit_leaves")));
		holiday_leaves = register("holiday_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("holiday_leaves")));
		decorated_holiday_leaves = register("decorated_holiday_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("decorated_holiday_leaves")));	
		purple_skyroot_fruit_leaves = register("purple_skyroot_fruit_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("purple_fruit_leaves")));
		purple_skyroot_leaves = register("purple_skyroot_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("purple_crystal_leaves")));
		blue_skyroot_leaves = register("blue_skyroot_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("blue_skyroot_leaves")));
		dark_blue_skyroot_leaves = register("dark_blue_skyroot_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("darkblue_skyroot_leaves")));
		green_light_skyroot_leaves = register("green_light_skyroot_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("green_light_skyroot_leaves")));
		blue_light_skyroot_leaves = register("blue_light_skyroot_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("blue_light_skyroot_leaves")));
		void_leaves = register("void_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("void_leaves")));
		void_fruit_leaves = register("void_fruit_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("void_fruit_leaves")));
		divine_oak_leaves = register("divine_oak_leaves", new BlockAetherLeaves().setBlockTextureName(Aether.find("divine_oak_leaves")));

		skyroot_log = registerMeta("skyroot_log", new BlockAetherLog()).setBlockTextureName(Aether.find("skyroot_log"));
		golden_oak_log = registerHidden("golden_oak_log", new BlockAetherLog().setBlockTextureName(Aether.find("golden_oak_log")));
		golden_oak_new_log = registerMeta("golden_oak_new_log", new BlockGoldenOakNewLog().setBlockTextureName(Aether.find("golden_oak_log_new")));	
		greatroot_log = registerMeta("greatroot_log", new BlockAetherLogGreatroot().setBlockTextureName(Aether.findHighlands("logs/greatroot_log")));
		wisproot_log = registerMeta("wisproot_log", new BlockAetherLogWisproot().setBlockTextureName(Aether.findHighlands("logs/wisproot_log")));
		void_log = registerMeta("void_log", new BlockAetherLogVoid().setBlockTextureName(Aether.find("void_log")));
		divine_oak_log = registerMeta("divine_oak_log", new BlockAetherLogDivine().setBlockTextureName(Aether.find("divine_log")));
		skyroot_planks = register("skyroot_planks", new BlockSkyrootPlanks());
		golden_oak_planks = register("golden_oak_planks", new BlockGoldenOakPlanks());
		greatroot_planks = register("greatroot_planks", new BlockGreatrootPlanks());
		wisproot_planks = register("wisproot_planks", new BlockWisprootPlanks());
		void_planks = register("void_planks", new BlockVoidPlanks());
		divine_oak_planks = register("divine_oak_planks", new BlockDivinePlanks());
		
		skyroot_trapdoor = registerMeta("skyroot_trapdoor", new BlockAetherTrapdoor(Material.wood).setBlockTextureName(Aether.findII("skyrootTrapDoor")));
		golden_oak_trapdoor = registerMeta("golden_oak_trapdoor", new BlockAetherTrapdoor(Material.wood).setBlockTextureName(Aether.find("golden_oak_trapdoor")));
		wisproot_trapdoor = registerMeta("wisproot_trapdoor", new BlockAetherTrapdoor(Material.wood).setBlockTextureName(Aether.find("wisproot_trapdoor")));
		greatroot_trapdoor = registerMeta("greatroot_trapdoor", new BlockAetherTrapdoor(Material.wood).setBlockTextureName(Aether.find("greatroot_trapdoor")));
		void_trapdoor = registerMeta("void_trapdoor", new BlockAetherTrapdoor(Material.wood).setBlockTextureName(Aether.find("void_trapdoor")));
		zanite_trapdoor = registerMeta("zanite_trapdoor", new BlockAetherTrapdoor(Material.iron).setBlockTextureName(Aether.find("zanite_trapdoor")));		
		skyroot_ladder = registerMeta("skyroot_ladder", new BlockAetherLadder(Material.wood).setBlockTextureName(Aether.findII("skyrootLadder")));
		
		small_blueberry_bush = registerHidden("small_blueberry_bush", new BlockSmallBerryBushStem().setBlockTextureName(Aether.find("small_blueberry_bush")));
		medium_blueberry_bush = registerHidden("medium_blueberry_bush", new BlockMediumBerryBushStem().setBlockTextureName(Aether.find("medium_blueberry_bush")));
		berry_bush_stem = registerHidden("berry_bush_stem", new BlockBerryBushStem());
		berry_bush = register("berry_bush", new BlockBerryBush());
		
		small_raspberry_bush = registerHidden("small_raspberry_bush", new BlockSmallRaspberryBush().setBlockTextureName(Aether.find("small_blueberry_bush")));
		medium_raspberry_bush = registerHidden("medium_raspberry_bush", new BlockMediumRaspberryBush().setBlockTextureName(Aether.find("medium_blueberry_bush")));
		raspberry_bush_stem = registerHidden("raspberry_bush_stem", new BlockRaspberryBushStem());
		raspberry_bush = register("raspberry_bush", new BlockRaspberryBush());
		
		small_strawberry_bush = registerHidden("small_strawberry_bush", new BlockSmallStrawberryBush().setBlockTextureName(Aether.find("small_blueberry_bush")));
		medium_strawberry_bush = registerHidden("medium_strawberry_bush", new BlockMediumStrawberryBush().setBlockTextureName(Aether.find("medium_blueberry_bush")));
		strawberry_bush_stem = registerHidden("strawberry_bush_stem", new BlockStrawberryBushStem());
		strawberry_bush = register("strawberry_bush", new BlockStrawberryBush());
		
		small_blackberry_bush = registerHidden("small_blackberry_bush", new BlockSmallBlackberryBushStem().setBlockTextureName(Aether.find("small_blueberry_bush")));
		medium_blackberry_bush = registerHidden("medium_blackberry_bush", new BlockMediumBlackberryBushStem().setBlockTextureName(Aether.find("medium_blueberry_bush")));
		blackberry_bush_stem = registerHidden("blackberry_bush_stem", new BlockBlackberryBushStem());
		blackberry_bush = register("blackberry_bush", new BlockBlackberryBush());
		
		small_orange_tree = registerHidden("small_orange_tree", new BlockSmallOrangeTree().setBlockTextureName(Aether.find("small_orange_tree")));
		medium_orange_tree = registerHidden("medium_orange_tree", new BlockMediumOrangeTree().setBlockTextureName(Aether.find("medium_orange_tree")));
		large_orange_tree = registerHidden("large_orange_tree", new BlockLargeOrangeTree().setBlockTextureName(Aether.find("large_orange_tree")));
		mature_orange_tree = register("mature_orange_tree", new BlockMatureOrangeTree().setBlockTextureName(Aether.find("orange_tree_mature")));
		
		grape_tree_stage_one = registerHidden("grape_tree_stage_one", new BlockGrapeTreeStage1().setBlockTextureName(Aether.find("grape_tree_stage_one")));
		grape_tree_stage_two = registerHidden("grape_tree_stage_two", new BlockGrapeTreeStage2().setBlockTextureName(Aether.find("grape_tree_stage_two")));
		grape_tree_mature = register("grape_tree_mature", new BlockGrapeTreeMature().setBlockTextureName(Aether.find("grape_tree_mature")));
		
		aether_tallgrass = register("aether_tallgrass", new BlockAetherTallGrass().setBlockTextureName(Aether.find("aether_grass")));
		arctic_tallgrass = register("arctic_tallgrass", new BlockArcticTallGrass().setBlockTextureName(Aether.find("arctic_tallgrass")));
		snowy_tallgrass = register("snowy_tallgrass", new BlockSnowyTallGrass().setBlockTextureName(Aether.find("snowy_tallgrass")));
		enchanted_aether_tallgrass = register("enchanted_aether_tallgrass", new BlockEnchantedAetherTallGrass().setBlockTextureName(Aether.find("enchanted_aether_grass")));
		divine_aether_tallgrass = register("divine_aether_tallgrass", new BlockDivineAetherTallGrass().setBlockTextureName(Aether.find("divine_grass")));
		purple_flower = register("purple_flower", new BlockAetherFlower().setBlockTextureName(Aether.find("purple_flower")));
		white_flower = register("white_flower", new BlockAetherFlower().setBlockTextureName(Aether.find("white_flower")));
		white_rose = register("white_rose", new BlockAetherFlower().setBlockTextureName("aether:flowers/white_rose"));// could re-findII this
		aechor_sprout = register("aechor_sprout", new BlockAetherFlower().setBlockTextureName(Aether.find("aechor_sprout")));
		blue_swingtip = register("blue_swingtip", new BlockAetherFlower().setBlockTextureName(Aether.find("blue_swingtip")));
		neverbloom = register("neverbloom", new BlockAetherFlower().setBlockTextureName(Aether.find("neverbloom")));	
		burstblossom = register("burstblossom", new BlockAetherFlower().setBlockTextureName(Aether.find("burstblossom")));
		carrion_flower = register("carrion_flower", new BlockAetherFlower().setBlockTextureName(Aether.find("carrion_flower")));
		moonlit_bloom = register("moonlit_bloom", new BlockAetherFlower().setBlockTextureName(Aether.find("moonlit_bloom")));		
		quickshoot = register("quickshoot", new BlockAetherFlower().setBlockTextureName(Aether.find("quickshoot")));
		enchanted_quickshoot = register("enchanted_quickshoot", new BlockEnchantedAetherFlower().setBlockTextureName(Aether.find("enchanted_quickshoot")));
		aether_tulips = register("aether_tulips", new BlockAetherFlower().setBlockTextureName(Aether.find("aether_tulips")));
		enchanted_aether_tulips = register("enchanted_aether_tulips", new BlockEnchantedAetherFlower().setBlockTextureName(Aether.find("enchanted_aether_tulips")));
		enchanted_bloom = register("enchanted_bloom", new BlockEnchantedAetherFlower().setBlockTextureName(Aether.find("enchanted_bloom")));		
		divine_stalk = register("divine_stalk", new BlockDivineAetherFlower().setBlockTextureName(Aether.find("divine_stalk")));
		divine_bloom = register("divine_bloom", new BlockDivineAetherFlower().setBlockTextureName(Aether.find("divine_bloom")));
		divine_lily = register("divine_lily", new BlockDivineAetherFlower().setBlockTextureName(Aether.find("divine_lily")));
		
		skyroot_sapling = register("skyroot_sapling", new BlockAetherSapling(new AetherGenSkyrootTree(false)).setBlockTextureName(Aether.find("skyroot_sapling")));
		golden_oak_sapling = register("golden_oak_sapling", new BlockAetherSapling(new AetherGenOakTree()).setBlockTextureName(Aether.find("golden_oak_sapling")));
		crystal_sapling = register("crystal_sapling", new BlockAetherSapling(new AetherGenCrystalTree()).setBlockTextureName(Aether.find("crystal_sapling")));		
		purple_skyroot_sapling = register("purple_skyroot_sapling", new BlockAetherSapling(new AetherGenPurpleSkyrootTree(false)).setBlockTextureName(Aether.find("purple_crystal_sapling")));
		blue_skyroot_sapling = register("blue_skyroot_sapling", new BlockAetherSapling(new AetherGenBlueSkyrootTree(false)).setBlockTextureName(Aether.find("blue_skyroot_sapling")));
		dark_blue_skyroot_sapling = register("dark_blue_skyroot_sapling", new BlockAetherSapling(new AetherGenDarkBlueSkyrootTree(false)).setBlockTextureName(Aether.find("darkblue_crystal_sapling")));
		wisproot_sapling = register("wisproot_sapling", new BlockAetherSapling(new AetherGenWisprootTree(BlocksAether.blue_light_skyroot_leaves, 8, true)).setBlockTextureName(Aether.find("wisproot_sapling")));
		greatroot_sapling = register("greatroot_sapling", new BlockAetherSapling(new AetherGenGreatrootTree(BlocksAether.green_light_skyroot_leaves, 20, true)).setBlockTextureName(Aether.find("greatroot_sapling")));
		void_sapling = register("void_sapling", new BlockVoidSapling(new AetherGenVoidTree(true)).setBlockTextureName(Aether.find("void_sapling")));		
		divine_oak_sapling = register("divine_oak_sapling", new BlockAetherSapling(new AetherGenDivineTree(BlocksAether.divine_oak_leaves, 40, true)).setBlockTextureName(Aether.find("divine_oak_sapling")));
		
		enchanter = registerEnchanter("enchanter", new BlockEnchanter());
		freezer = registerMeta("freezer", new BlockFreezer());
		incubator = registerMeta("incubator", new BlockIncubator());
		sun_altar = registerItemBlock("sun_altar", new BlockSunAltar(), ItemBlockFireProof.class);
		amplifier = register("amplifier", new BlockAmplifier());
		ancient_enchanter = register("ancient_enchanter", new BlockAncientEnchanter());
		aether_enchantment_table = register("aether_enchantment_table", new BlockAetherEnchantmentTable().setLightOpacity(3));
		divine_enchantment_table = registerRarity("divine_enchantment_table", new BlockDivineEnchantmentTable(), ItemsAether.divine_aether_loot).setLightOpacity(3);
		elysian_totem = register("elysian_totem", new BlockElysianTotem());
		present = register("present", new BlockPresent());	
		skyroot_workbench = register("skyroot_workbench", new BlockSkyrootWorkbench());
		skyroot_chest = register("skyroot_chest", new BlockSkyrootChest(0));
		chest_mimic = register("chest_mimic", new BlockMimicChest());
		treasure_chest = register("treasure_chest", new BlockTreasureChest());
		treasure_chest_breakable = register("treasure_chest_breakable", new BlockTreasureChestBreakable(0));
		elysian_chest = register("elysian_chest", new BlockElysianChest(0));
		elysian_mimic = register("elysian_mimic", new BlockMimicElysian());
		aether_tnt = register("aether_tnt", new BlockAetherTNT());		
		aether_portal = register("aether_portal", new BlockAetherPortal()).setCreativeTab(null);
		coldfire = registerHidden("coldfire", new BlockColdFire().setCreativeTab(null));
		hellfire = registerHidden("hellfire", new BlockHellFire().setCreativeTab(null));
		
		mossy_holystone = registerMeta("mossy_holystone", new BlockHolystone().setBlockTextureName(Aether.find("mossy_holystone")));
		bloodmoss_stone = registerMeta("bloodmoss_stone", new BlockHolystone().setBlockTextureName(Aether.find("bloodmoss_stone")));
		azure_holystone = registerMeta("azure_holystone", new BlockHolystone().setBlockTextureName(Aether.find("azure_holystone")));
		notch_holystone = registerMeta("notch_holystone", new BlockHolystone().setBlockTextureName(Aether.find("notch_holystone")));
		pillar = register("pillar", new BlockPillar("pillar_top", "pillar_side"));
		pillar_top = register("pillar_top", new BlockPillar("pillar_top", "pillar_carved"));
		
		carved_stone = register("carved_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("carved_stone")));
		sentry_stone = register("sentry_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("sentry_stone")));
		ancient_carved_stone = register("ancient_carved_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("ancient_carved_stone")));
		ancient_sentry_stone = register("ancient_sentry_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("ancient_sentry_stone")));
		divine_carved_stone = register("divine_carved_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.findII("divineCarvedStone")));
		divine_sentry_stone = register("divine_sentry_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.findII("divineSentryStone")));	
		mythic_carved_stone = register("mythic_carved_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("mythic_carved_stone")));
		mythic_sentry_stone = register("mythic_sentry_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("mythic_sentry_stone")));
		
		oblitus_stone = registerHidden("oblitus_stone", new BlockOblitusStone().setResistance(20.0F).setBlockTextureName(Aether.find("oblitus_stone")).setCreativeTab(null));
		oblitus_stone_2 = register("oblitus_stone_2", new BlockOblitusStone2().setResistance(20.0F).setBlockTextureName(Aether.find("oblitus_stone")));
		cracked_oblitus_stone = registerHidden("cracked_oblitus_stone", new BlockCrackedOblitusStone().setBlockTextureName(Aether.find("cracked_oblitus_stone")).setCreativeTab(null));
		cracked_oblitus_stone_2 = register("cracked_oblitus_stone_2", new BlockCrackedOblitusStone2().setBlockTextureName(Aether.find("cracked_oblitus_stone")));
		
		angelic_stone = register("angelic_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("angelic_stone")));
		light_angelic_stone = register("light_angelic_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("light_angelic_stone")));		
		ancient_angelic_stone = register("ancient_angelic_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("ancient_angelic_stone")));
		ancient_light_angelic_stone = register("ancient_light_angelic_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("ancient_light_angelic_stone")));		
		divine_angelic_stone = register("divine_angelic_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("divine_angelic_stone")));
		divine_light_angelic_stone = register("divine_light_angelic_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("divine_light_angelic_stone")));		
		mythic_angelic_stone = register("mythic_angelic_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("mythic_angelic_stone")));
		mythic_light_angelic_stone = register("mythic_light_angelic_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("mythic_light_angelic_stone")));		
			
		hellfire_stone = register("hellfire_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("hellfire_stone")));
		light_hellfire_stone = register("light_hellfire_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("light_hellfire_stone")));	
		ancient_hellfire_stone = register("ancient_hellfire_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("ancient_hellfire_stone")));
		ancient_light_hellfire_stone = register("ancient_light_hellfire_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("ancient_light_hellfire_stone")));	
		divine_hellfire_stone = register("divine_hellfire_stone", new BlockDungeonBase(false).setBlockTextureName(Aether.find("divine_hellfire_stone")));
		divine_light_hellfire_stone = register("divine_light_hellfire_stone", new BlockDungeonBase(true).setBlockTextureName(Aether.find("divine_light_hellfire_stone")));
		
		fuse_stone = register("fuse_stone", new BlockDungeonBaseOsmium(false).setResistance(15.0F).setBlockTextureName(Aether.find("fuse_stone")));
		creeping_stone = register("creeping_stone", new BlockDungeonBaseOsmium(true).setResistance(15.0F).setBlockTextureName(Aether.find("creeping_stone")));
		
		
		genesis_stone = registerHidden("genesis_stone", new BlockGenesisStone());
		light_genesis_stone = registerHidden("light_genesis_stone", new BlockLightGenesisStone());
		genesis_stone_2 = register("genesis_stone_2", new BlockGenesisStone2());
		light_genesis_stone_2 = register("light_genesis_stone_2", new BlockLightGenesisStone2());
		crystallized_genesis_stone = register("crystallized_genesis_stone", new BlockCrystallizedGenesisStone().setBlockTextureName(Aether.find("crystallized_genesis_stone")));
		
		zanite_pile = registerMeta("zanite_pile", new BlockZanitePile());		
		arkenium_pile = registerMeta("arkenium_pile", new BlockArkeniumPile());
		continuum_pile = registerMeta("continuum_pile", new BlockContinuumPile());
		gravitite_pile = registerMeta("gravitite_pile", new BlockGravititePile());
		divineral_pile = registerMeta("divineral_pile", new BlockDivineralPile());
		
		locked_carved_stone = register("locked_carved_stone", new BlockDungeonBase(carved_stone, false).setBlockTextureName(Aether.find("carved_stone"))).setCreativeTab(null);
		locked_sentry_stone = register("locked_sentry_stone", new BlockDungeonBase(sentry_stone, false).setBlockTextureName(Aether.find("sentry_stone"))).setCreativeTab(null);
		locked_ancient_carved_stone = register("locked_ancient_carved_stone", new BlockDungeonBase(ancient_carved_stone, false).setBlockTextureName(Aether.find("ancient_carved_stone"))).setCreativeTab(null);
		locked_ancient_sentry_stone = register("locked_ancient_sentry_stone", new BlockDungeonBase(ancient_sentry_stone, false).setBlockTextureName(Aether.find("ancient_sentry_stone"))).setCreativeTab(null);
		locked_divine_carved_stone = register("locked_divine_carved_stone", new BlockDungeonBase(divine_carved_stone, false).setBlockTextureName(Aether.findII("divineCarvedStone"))).setCreativeTab(null);
		locked_divine_sentry_stone = register("locked_divine_sentry_stone", new BlockDungeonBase(divine_sentry_stone, false).setBlockTextureName(Aether.findII("divineSentryStone"))).setCreativeTab(null);	
		locked_mythic_carved_stone = register("locked_mythic_carved_stone", new BlockDungeonBase(mythic_carved_stone, false).setBlockTextureName(Aether.find("mythic_carved_stone"))).setCreativeTab(null);
		locked_mythic_sentry_stone = register("locked_mythic_sentry_stone", new BlockDungeonBase(mythic_sentry_stone, false).setBlockTextureName(Aether.find("mythic_sentry_stone"))).setCreativeTab(null);
		
		locked_angelic_stone = register("locked_angelic_stone", new BlockDungeonBase(angelic_stone, false).setBlockTextureName(Aether.find("angelic_stone"))).setCreativeTab(null);
		locked_light_angelic_stone = register("locked_light_angelic_stone", new BlockDungeonBase(light_angelic_stone, true).setBlockTextureName(Aether.find("light_angelic_stone"))).setCreativeTab(null);		
		locked_ancient_angelic_stone = register("locked_ancient_angelic_stone", new BlockDungeonBase(ancient_angelic_stone, false).setBlockTextureName(Aether.find("ancient_angelic_stone"))).setCreativeTab(null);
		locked_ancient_light_angelic_stone = register("locked_ancient_light_angelic_stone", new BlockDungeonBase(ancient_light_angelic_stone, true).setBlockTextureName(Aether.find("ancient_light_angelic_stone"))).setCreativeTab(null);		
		locked_divine_angelic_stone = register("locked_divine_angelic_stone", new BlockDungeonBase(divine_angelic_stone, false).setBlockTextureName(Aether.find("divine_angelic_stone"))).setCreativeTab(null);
		locked_divine_light_angelic_stone = register("locked_divine_light_angelic_stone", new BlockDungeonBase(divine_light_angelic_stone, true).setBlockTextureName(Aether.find("divine_light_angelic_stone"))).setCreativeTab(null);	
		locked_mythic_angelic_stone = register("locked_mythic_angelic_stone", new BlockDungeonBase(mythic_angelic_stone, false).setBlockTextureName(Aether.find("mythic_angelic_stone"))).setCreativeTab(null);
		locked_mythic_light_angelic_stone = register("locked_mythic_light_angelic_stone", new BlockDungeonBase(mythic_light_angelic_stone, true).setBlockTextureName(Aether.find("mythic_light_angelic_stone"))).setCreativeTab(null);
		
		locked_hellfire_stone = register("locked_hellfire_stone", new BlockDungeonBase(hellfire_stone, false).setBlockTextureName(Aether.find("hellfire_stone"))).setCreativeTab(null);
		locked_light_hellfire_stone = register("locked_light_hellfire_stone", new BlockDungeonBase(light_hellfire_stone, true).setBlockTextureName(Aether.find("light_hellfire_stone"))).setCreativeTab(null);	
		locked_ancient_hellfire_stone = register("locked_ancient_hellfire_stone", new BlockDungeonBase(ancient_hellfire_stone, false).setBlockTextureName(Aether.find("ancient_hellfire_stone"))).setCreativeTab(null);
		locked_ancient_light_hellfire_stone = register("locked_ancient_light_hellfire_stone", new BlockDungeonBase(ancient_light_hellfire_stone, true).setBlockTextureName(Aether.find("ancient_light_hellfire_stone"))).setCreativeTab(null);					
		locked_divine_hellfire_stone = register("locked_divine_hellfire_stone", new BlockDungeonBase(divine_hellfire_stone, false).setBlockTextureName(Aether.find("divine_hellfire_stone"))).setCreativeTab(null);
		locked_divine_light_hellfire_stone = register("locked_divine_light_hellfire_stone", new BlockDungeonBase(divine_light_hellfire_stone, true).setBlockTextureName(Aether.find("divine_light_hellfire_stone"))).setCreativeTab(null);
		
		
		locked_fuse_stone = register("locked_fuse_stone", new BlockDungeonBaseOsmium(fuse_stone, false).setResistance(15.0F).setBlockTextureName(Aether.find("fuse_stone"))).setResistance(15.0F).setCreativeTab(null);
		locked_creeping_stone = register("locked_creeping_stone", new BlockDungeonBaseOsmium(creeping_stone, true).setResistance(15.0F).setBlockTextureName(Aether.find("creeping_stone"))).setResistance(15.0F).setCreativeTab(null);
			
		carved_trap = register("carved_trap", new BlockDungeonTrap(carved_stone).setBlockTextureName(Aether.find("carved_stone"))).setCreativeTab(null);
		divine_carved_trap = register("divine_carved_trap", new BlockDungeonTrap(divine_carved_stone).setBlockTextureName(Aether.findII("divineCarvedStone"))).setCreativeTab(null);

		angelic_trap = register("angelic_trap", new BlockDungeonTrap(angelic_stone).setBlockTextureName(Aether.find("angelic_stone"))).setCreativeTab(null);		
		ancient_angelic_trap = register("ancient_angelic_trap", new BlockDungeonTrap(ancient_angelic_stone).setBlockTextureName(Aether.find("ancient_angelic_stone"))).setCreativeTab(null);		
		divine_angelic_trap = register("divine_angelic_trap", new BlockDungeonTrap(divine_angelic_stone).setBlockTextureName(Aether.find("divine_angelic_stone"))).setCreativeTab(null);

		fuse_trap = register("fuse_trap", new BlockDungeonTrap(locked_fuse_stone).setResistance(15.0F).setBlockTextureName(Aether.find("fuse_stone"))).setCreativeTab(null);
		fuse_trap_2 = register("fuse_trap_2", new BlockDungeonTrap2(locked_creeping_stone).setResistance(15.0F).setBlockTextureName(Aether.find("creeping_stone"))).setCreativeTab(null);
		
		hellfire_trap = register("hellfire_trap", new BlockDungeonTrap(hellfire_stone).setBlockTextureName(Aether.find("hellfire_stone"))).setCreativeTab(null);
		genesis_trap = register("genesis_trap", new BlockDungeonTrap(genesis_stone).setBlockTextureName(Aether.find("genesis_stone"))).setCreativeTab(null);
		skyroot_fence = register("skyroot_fence", new BlockAetherFence());
		greatroot_fence = register("greatroot_fence", new BlockAetherFenceGreatroot());
		wisproot_fence = register("wisproot_fence", new BlockAetherFenceWisproot());
		void_fence = register("void_fence", new BlockAetherFenceVoid());
		golden_oak_fence = register("golden_oak_fence", new BlockGoldenOakFence());
		divine_oak_fence = register("divine_oak_fence", new BlockDivineOakFence());
		skyroot_fence_gate = register("skyroot_fence_gate", new BlockAetherFenceGate());
		greatroot_fence_gate = register("greatroot_fence_gate", new BlockAetherFenceGateGreatroot());
		wisproot_fence_gate = register("wisproot_fence_gate", new BlockAetherFenceGateWisproot());
		void_fence_gate = register("void_fence_gate", new BlockAetherFenceGateVoid());
		golden_oak_gate = register("golden_oak_gate", new BlockAetherFenceGateGoldenOak());
		divine_oak_gate = register("divine_oak_gate", new BlockAetherFenceGateDivineOak());
		
		
		skyroot_log_wall = register("skyroot_log_wall", new BlockAetherWall(skyroot_log));
		golden_oak_log_wall = register("golden_oak_log_wall", new BlockAetherWall(golden_oak_new_log));
		wisproot_log_wall = register("wisproot_log_wall", new BlockAetherWall(wisproot_log));
		greatroot_log_wall = register("greatroot_log_wall", new BlockAetherWall(greatroot_log));
		void_log_wall = register("void_log_wall", new BlockAetherWall(void_log));
		divine_oak_log_wall = register("divine_oak_log_wall", new BlockAetherWall(divine_oak_log));
		carved_wall = register("carved_wall", new BlockAetherWall(carved_stone));
		oblitus_wall = register("oblitus_wall", new BlockAetherWall(oblitus_stone));
		angelic_wall = register("angelic_wall", new BlockAetherWall(angelic_stone));
		hellfire_wall = register("hellfire_wall", new BlockAetherWall(hellfire_stone));
		fuse_wall = register("fuse_wall", new BlockAetherWall(fuse_stone));
		ancient_hellfire_wall = register("ancient_hellfire_wall", new BlockAetherWall(ancient_hellfire_stone));
		divine_hellfire_stone_wall = register("divine_hellfire_stone_wall", new BlockAetherWall(divine_hellfire_stone));
		genesis_wall = register("genesis_wall", new BlockAetherWall(genesis_stone));
		holystone_wall = register("holystone_wall", new BlockAetherWall(holystone));
		holystone_brick_wall = register("holystone_brick_wall", new BlockAetherWall(holystone_brick));
		mossy_holystone_wall = register("mossy_holystone_wall", new BlockAetherWall(mossy_holystone));
		aerogel_wall = registerRarity("aerogel_wall", new BlockAetherWall(aerogel), ItemsAether.aether_loot);
		agiosite_wall = register("agiosite_wall", new BlockAetherWall(agiosite));
		agiosite_brick_wall = register("agiosite_brick_wall", new BlockAetherWall(agiosite_bricks));
		enchanted_agiosite_wall = register("enchanted_agiosite_wall", new BlockAetherWall(enchanted_agiosite));
		enchanted_agiosite_brick_wall = register("enchanted_agiosite_brick_wall", new BlockAetherWall(enchanted_agiosite_bricks)); 
		aetheral_stone_wall = register("aetheral_stone_wall", new BlockAetherWall(aetheral_stone));
		aetheral_stone_brick_wall = register("aetheral_stone_brick_wall", new BlockAetherWall(aetheral_stone_bricks));
		enchanted_aetheral_stone_wall = register("enchanted_aetheral_stone_wall", new BlockAetherWall(enchanted_aetheral_stone));
		enchanted_aetheral_stone_brick_wall = register("enchanted_aetheral_stone_brick_wall", new BlockAetherWall(enchanted_aetheral_stone_bricks));
		deific_wall = register("deific_wall", new BlockAetherWall(deific));
		deific_brick_wall = register("deific_brick_wall", new BlockAetherWall(deific_bricks));
		enchanted_deific_wall = register("enchanted_deific_wall", new BlockAetherWall(enchanted_deific));
		enchanted_deific_brick_wall = register("enchanted_deific_brick_wall", new BlockAetherWall(enchanted_deific_bricks));
		enchanted_holystone_wall = register("enchanted_holystone_wall", new BlockAetherWall(enchanted_holystone));
		enchanted_holystone_brick_wall = register("enchanted_holystone_brick_wall", new BlockAetherWall(enchanted_holystone_bricks));
		caelestia_stone_wall = register("caelestia_stone_wall", new BlockAetherWall(caelestia_stone));	
		carved_caelestia_stone_wall = register("carved_caelestia_stone_wall", new BlockAetherWall(carved_caelestia_stone));
		ancient_carved_stone_wall = register("ancient_carved_stone_wall", new BlockAetherWall(ancient_carved_stone));
		divine_carved_stone_wall = register("divine_carved_stone_wall", new BlockAetherWall(divine_carved_stone));
		mythic_carved_stone_wall = register("mythic_carved_stone_wall", new BlockAetherWall(mythic_carved_stone));	
		ancient_angelic_stone_wall = register("ancient_angelic_stone_wall", new BlockAetherWall(ancient_angelic_stone));		
		divine_angelic_stone_wall = register("divine_angelic_stone_wall", new BlockAetherWall(divine_angelic_stone));
		mythic_angelic_stone_wall = register("mythic_angelic_stone_wall", new BlockAetherWall(mythic_angelic_stone));
		
		
		carved_stairs = register("carved_stairs", new BlockAetherStairs(carved_stone));
		oblitus_stairs = register("oblitus_stairs", new BlockAetherStairs(oblitus_stone));
		angelic_stairs = register("angelic_stairs", new BlockAetherStairs(angelic_stone));
		hellfire_stairs = register("hellfire_stairs", new BlockAetherStairs(hellfire_stone));
		fuse_stairs = register("fuse_stairs", new BlockAetherStairs(fuse_stone));
		ancient_hellfire_stairs = register("ancient_hellfire_stairs", new BlockAetherStairs(ancient_hellfire_stone));
		divine_hellfire_stone_stairs = register("divine_hellfire_stone_stairs", new BlockAetherStairs(divine_hellfire_stone));
		genesis_stairs = register("genesis_stairs", new BlockAetherStairs(genesis_stone));
		skyroot_stairs = register("skyroot_stairs", new BlockAetherStairs(skyroot_planks));
		golden_oak_stairs = register("golden_oak_stairs", new BlockAetherStairs(golden_oak_planks));
		greatroot_stairs = register("greatroot_stairs", new BlockAetherStairs(greatroot_planks));
		wisproot_stairs = register("wisproot_stairs", new BlockAetherStairs(wisproot_planks));
		void_stairs = register("void_stairs", new BlockAetherStairs(void_planks));
		divine_oak_stairs = register("divine_oak_stairs", new BlockAetherStairs(divine_oak_planks));
		agiosite_stairs = register("agiosite_stairs", new BlockAetherStairs(agiosite));
		agiosite_brick_stairs = register("agiosite_brick_stairs", new BlockAetherStairs(agiosite_bricks));
		enchanted_agiosite_stairs = register("enchanted_agiosite_stairs", new BlockAetherStairs(enchanted_agiosite));
		enchanted_agiosite_brick_stairs = register("enchanted_agiosite_brick_stairs", new BlockAetherStairs(enchanted_agiosite_bricks));
		holystone_stairs = register("holystone_stairs", new BlockAetherStairs(holystone));
		holystone_brick_stairs = register("holystone_brick_stairs", new BlockAetherStairs(holystone_brick));
		mossy_holystone_stairs = register("mossy_holystone_stairs", new BlockAetherStairs(mossy_holystone));
		aerogel_stairs = registerRarity("aerogel_stairs", new BlockAetherStairs(aerogel), ItemsAether.aether_loot);
		aetheral_stone_stairs = register("aetheral_stone_stairs", new BlockAetherStairs(aetheral_stone));
		aetheral_stone_brick_stairs = register("aetheral_stone_bricks_stairs", new BlockAetherStairs(aetheral_stone_bricks));
		enchanted_aetheral_stone_stairs = register("enchanted_aetheral_stone_stairs", new BlockAetherStairs(enchanted_aetheral_stone));
		enchanted_aetheral_stone_brick_stairs = register("enchanted_aetheral_stone_brick_stairs", new BlockAetherStairs(enchanted_aetheral_stone_bricks));		
		deific_stairs = register("deific_stairs", new BlockAetherStairs(deific));
		deific_brick_stairs = register("deific_brick_stairs", new BlockAetherStairs(deific_bricks));
		enchanted_deific_stairs = register("enchanted_deific_stairs", new BlockAetherStairs(enchanted_deific));		
		enchanted_deific_brick_stairs = register("enchanted_deific_brick_stairs", new BlockAetherStairs(enchanted_deific_bricks));		
		enchanted_holystone_stairs = register("enchanted_holystone_stairs", new BlockAetherStairs(enchanted_holystone));
		enchanted_holystone_brick_stairs = register("enchanted_holystone_brick_stairs", new BlockAetherStairs(enchanted_holystone_bricks));
		caelestia_stone_stairs = register("caelestia_stone_stairs", new BlockAetherStairs(caelestia_stone));
		carved_caelestia_stone_stairs = register("carved_caelestia_stone_stairs", new BlockAetherStairs(carved_caelestia_stone));
		ancient_carved_stone_stairs = register("ancient_carved_stone_stairs", new BlockAetherStairs(ancient_carved_stone));
		divine_carved_stone_stairs = register("divine_carved_stone_stairs", new BlockAetherStairs(divine_carved_stone));
		mythic_carved_stone_stairs = register("mythic_carved_stone_stairs", new BlockAetherStairs(mythic_carved_stone));	
		ancient_angelic_stone_stairs = register("ancient_angelic_stone_stairs", new BlockAetherStairs(ancient_angelic_stone));	
		divine_angelic_stone_stairs = register("divine_angelic_stone_stairs", new BlockAetherStairs(divine_angelic_stone));
		mythic_angelic_stone_stairs = register("mythic_angelic_stone_stairs", new BlockAetherStairs(mythic_angelic_stone));
		
		skyroot_double_slab = register("skyroot_double_slab", new BlockAetherSlab("skyroot_double_slab", true, Material.wood).setBlockTextureName(Aether.find("skyroot_planks")).setHardness(2.0F).setResistance(5.0F)).setCreativeTab(null);
		carved_double_slab = register("carved_double_slab", new BlockAetherSlab("carved_double_slab", true, Material.rock).setBlockTextureName(Aether.find("carved_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		angelic_double_slab = register("angelic_double_slab", new BlockAetherSlab("angelic_double_slab", true, Material.rock).setBlockTextureName(Aether.find("angelic_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		hellfire_double_slab = register("hellfire_double_slab", new BlockAetherSlab("hellfire_double_slab", true, Material.rock).setBlockTextureName(Aether.find("hellfire_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		holystone_double_slab = register("holystone_double_slab", new BlockAetherSlab("holystone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("holystone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		mossy_holystone_double_slab = register("mossy_holystone_double_slab", new BlockAetherSlab("mossy_holystone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("mossy_holystone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		holystone_brick_double_slab = register("holystone_brick_double_slab", new BlockAetherSlab("holystone_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("holystone_brick")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		aerogel_double_slab = register("aerogel_double_slab", new BlockAetherSlab("aerogel_double_slab", true, Material.rock).setBlockTextureName(Aether.find("aerogel")).setHardness(2.0F).setResistance(2000F).setLightOpacity(3).setStepSound(Block.soundTypeMetal)).setCreativeTab(null);
		skyroot_slab = registerSlab("skyroot_slab", new BlockAetherSlab("skyroot_slab", false, Material.wood).setBlockTextureName(Aether.find("skyroot_planks")).setHardness(2.0F).setResistance(5.0F), skyroot_double_slab);
		carved_slab = registerSlab("carved_slab", new BlockAetherSlab("carved_slab", false, Material.rock).setBlockTextureName(Aether.find("carved_stone")).setHardness(0.5F).setResistance(10.0F), carved_double_slab);
		angelic_slab = registerSlab("angelic_slab", new BlockAetherSlab("angelic_slab", false, Material.rock).setBlockTextureName(Aether.find("angelic_stone")).setHardness(0.5F).setResistance(10.0F), angelic_double_slab);
		hellfire_slab = registerSlab("hellfire_slab", new BlockAetherSlab("hellfire_slab", false, Material.rock).setBlockTextureName(Aether.find("hellfire_stone")).setHardness(0.5F).setResistance(10.0F), hellfire_double_slab);
		fuse_double_slab = register("fuse_double_slab", new BlockAetherSlab("fuse_double_slab", true, Material.rock).setBlockTextureName(Aether.find("fuse_stone")).setHardness(0.5F).setResistance(10.0F)).setCreativeTab(null);
		fuse_slab = registerSlab("fuse_slab", new BlockAetherSlab("fuse_slab", false, Material.rock).setBlockTextureName(Aether.find("fuse_stone")).setHardness(0.5F).setResistance(10.0F), fuse_double_slab);
		holystone_slab = registerSlab("holystone_slab", new BlockAetherSlab("holystone_slab", false, Material.rock).setBlockTextureName(Aether.find("holystone")).setHardness(0.5F).setResistance(10.0F), holystone_double_slab);
		mossy_holystone_slab = registerSlab("mossy_holystone_slab", new BlockAetherSlab("mossy_holystone_slab", false, Material.rock).setBlockTextureName(Aether.find("mossy_holystone")).setHardness(0.5F).setResistance(10.0F), mossy_holystone_double_slab);
		holystone_brick_slab = registerSlab("holystone_brick_slab", new BlockAetherSlab("holystone_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("holystone_brick")).setHardness(0.5F).setResistance(10.0F), holystone_brick_double_slab);
		aerogel_slab = registerSlab("aerogel_slab", new BlockAetherSlab("aerogel_slab", false, Material.rock).setBlockTextureName(Aether.find("aerogel")).setHardness(0.5F).setResistance(2000F).setLightOpacity(3).setStepSound(Block.soundTypeMetal), aerogel_double_slab);
		skyroot_bookshelf = register("skyroot_bookshelf", new BlockSkyrootBookshelf());
		golden_oak_bookshelf = register("golden_oak_bookshelf", new BlockGoldenOakBookshelf());
		skyroot_bed = registerBed("skyroot_bed", new BlockSkyrootBed().setBlockTextureName(Aether.find("skyroot_bed")));
		genesis_double_slab = register("genesis_double_slab", new BlockAetherSlab("genesis_double_slab", true, Material.rock).setBlockTextureName(Aether.find("genesis_stone")).setHardness(3.5F).setResistance(20.0F)).setCreativeTab(null);
		genesis_slab = registerSlab("genesis_slab", new BlockAetherSlab("genesis_slab", false, Material.rock).setBlockTextureName(Aether.find("genesis_stone")).setHardness(3.5F).setResistance(20.0F), genesis_double_slab);
		agiosite_double_slab = register("agiosite_double_slab", new BlockAetherSlab("agiosite_double_slab", true, Material.rock).setBlockTextureName(Aether.find("agiosite")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		agiosite_slab = registerSlab("agiosite_slab", new BlockAetherSlab("agiosite_slab", false, Material.rock).setBlockTextureName(Aether.find("agiosite")).setHardness(0.5F).setResistance(10.0F), agiosite_double_slab);		
		agiosite_brick_double_slab = register("agiosite_brick_double_slab", new BlockAetherSlab("agiosite_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("agiosite_stone_bricks")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		agiosite_brick_slab = registerSlab("agiosite_brick_slab", new BlockAetherSlab("agiosite_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("agiosite_stone_bricks")).setHardness(0.5F).setResistance(10.0F), agiosite_brick_double_slab);		
		enchanted_agiosite_double_slab = register("enchanted_agiosite_double_slab", new BlockAetherSlab("enchanted_agiosite_double_slab", true, Material.rock).setBlockTextureName(Aether.find("enchanted_agiosite")).setHardness(4.0F).setResistance(6.0F)).setCreativeTab(null);
		enchanted_agiosite_slab = registerSlab("enchanted_agiosite_slab", new BlockAetherSlab("enchanted_agiosite_slab", false, Material.rock).setBlockTextureName(Aether.find("enchanted_agiosite")).setHardness(4.0F).setResistance(6.0F), enchanted_agiosite_double_slab);
		enchanted_agiosite_brick_double_slab = register("enchanted_agiosite_brick_double_slab", new BlockAetherSlab("enchanted_agiosite_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("enchanted_agiosite_stone_bricks")).setHardness(4.0F).setResistance(6.0F)).setCreativeTab(null);
		enchanted_agiosite_brick_slab = registerSlab("enchanted_agiosite_brick_slab", new BlockAetherSlab("enchanted_agiosite_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("enchanted_agiosite_stone_bricks")).setHardness(4.0F).setResistance(6.0F), enchanted_agiosite_brick_double_slab);			
		aetheral_stone_double_slab = register("aetheral_stone_double_slab", new BlockAetherSlab("aetheral_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("aetheral_stone")).setHardness(3.0F).setResistance(5.0F)).setCreativeTab(null);
		aetheral_stone_slab = registerSlab("aetheral_stone_slab", new BlockAetherSlab("aetheral_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("aetheral_stone")).setHardness(3.0F).setResistance(5.0F), aetheral_stone_double_slab);			
		aetheral_stone_brick_double_slab = register("aetheral_stone_brick_double_slab", new BlockAetherSlab("aetheral_stone_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("aetheral_stone_brick")).setHardness(3.0F).setResistance(5.0F)).setCreativeTab(null);
		aetheral_stone_brick_slab = registerSlab("aetheral_stone_brick_slab", new BlockAetherSlab("aetheral_stone_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("aetheral_stone_brick")).setHardness(3.0F).setResistance(5.0F), aetheral_stone_brick_double_slab);			
		enchanted_aetheral_stone_double_slab = register("enchanted_aetheral_stone_double_slab", new BlockAetherSlab("enchanted_aetheral_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("enchanted_aetheral_stone")).setHardness(3.0F).setResistance(5.0F)).setCreativeTab(null);
		enchanted_aetheral_stone_slab = registerSlab("enchanted_aetheral_stone_slab", new BlockAetherSlab("enchanted_aetheral_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("enchanted_aetheral_stone")).setHardness(3F).setResistance(5.0F), enchanted_aetheral_stone_double_slab);	
		enchanted_aetheral_stone_brick_double_slab = register("enchanted_aetheral_stone_brick_double_slab", new BlockAetherSlab("enchanted_aetheral_stone_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("enchanted_aetheral_stone_brick")).setHardness(3.0F).setResistance(5.0F)).setCreativeTab(null);
		enchanted_aetheral_stone_brick_slab = registerSlab("enchanted_aetheral_stone_brick_slab", new BlockAetherSlab("enchanted_aetheral_stone_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("enchanted_aetheral_stone_brick")).setHardness(3.0F).setResistance(5.0F), enchanted_aetheral_stone_brick_double_slab);		
		caelestia_stone_double_slab = register("caelestia_stone_double_slab", new BlockAetherSlab("caelestia_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("caelestia_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		caelestia_stone_slab = registerSlab("caelestia_stone_slab", new BlockAetherSlab("caelestia_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("caelestia_stone")).setHardness(0.5F).setResistance(10.0F), caelestia_stone_double_slab);
		carved_caelestia_stone_double_slab = register("carved_caelestia_stone_double_slab", new BlockAetherSlab("carved_caelestia_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("carved_caelestia_stone_top")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		carved_caelestia_stone_slab = registerSlab("carved_caelestia_stone_slab", new BlockAetherSlab("carved_caelestia_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("carved_caelestia_stone_top")).setHardness(0.5F).setResistance(10.0F), carved_caelestia_stone_double_slab);		
		ancient_carved_stone_double_slab = register("ancient_carved_stone_double_slab", new BlockAetherSlab("ancient_carved_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("ancient_carved_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		ancient_carved_stone_slab = registerSlab("ancient_carved_stone_slab", new BlockAetherSlab("ancient_carved_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("ancient_carved_stone")).setHardness(0.5F).setResistance(10.0F), ancient_carved_stone_double_slab);	
		divine_carved_stone_double_slab = register("divine_carved_stone_double_slab", new BlockAetherSlab("divine_carved_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.findII("divineCarvedStone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		divine_carved_stone_slab = registerSlab("divine_carved_stone_slab", new BlockAetherSlab("divine_carved_stone_slab", false, Material.rock).setBlockTextureName(Aether.findII("divineCarvedStone")).setHardness(0.5F).setResistance(10.0F), divine_carved_stone_double_slab);
		
		mythic_carved_stone_double_slab = register("mythic_carved_stone_double_slab", new BlockAetherSlab("mythic_carved_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("mythic_carved_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		mythic_carved_stone_slab = registerSlab("mythic_carved_stone_slab", new BlockAetherSlab("mythic_carved_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("mythic_carved_stone")).setHardness(0.5F).setResistance(10.0F), mythic_carved_stone_double_slab);			
		ancient_angelic_stone_double_slab = register("ancient_angelic_stone_double_slab", new BlockAetherSlab("ancient_angelic_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("ancient_angelic_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		ancient_angelic_stone_slab = registerSlab("ancient_angelic_stone_slab", new BlockAetherSlab("ancient_angelic_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("ancient_angelic_stone")).setHardness(0.5F).setResistance(10.0F), ancient_angelic_stone_double_slab);		
		divine_angelic_stone_double_slab = register("divine_angelic_stone_double_slab", new BlockAetherSlab("divine_angelic_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("divine_angelic_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		divine_angelic_stone_slab = registerSlab("divine_angelic_stone_slab", new BlockAetherSlab("divine_angelic_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("divine_angelic_stone")).setHardness(0.5F).setResistance(10.0F), divine_angelic_stone_double_slab);		
		mythic_angelic_stone_double_slab = register("mythic_angelic_stone_double_slab", new BlockAetherSlab("mythic_angelic_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("mythic_angelic_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		mythic_angelic_stone_slab = registerSlab("mythic_angelic_stone_slab", new BlockAetherSlab("mythic_angelic_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("mythic_angelic_stone")).setHardness(0.5F).setResistance(10.0F), mythic_angelic_stone_double_slab);		
		ancient_hellfire_stone_double_slab = register("ancient_hellfire_stone_double_slab", new BlockAetherSlab("ancient_hellfire_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("ancient_hellfire_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		ancient_hellfire_stone_slab = registerSlab("ancient_hellfire_stone_slab", new BlockAetherSlab("ancient_hellfire_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("ancient_hellfire_stone")).setHardness(0.5F).setResistance(10.0F), ancient_hellfire_stone_double_slab);		
		divine_hellfire_stone_double_slab = register("divine_hellfire_stone_double_slab", new BlockAetherSlab("divine_hellfire_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("divine_hellfire_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		divine_hellfire_stone_slab = registerSlab("divine_hellfire_stone_slab", new BlockAetherSlab("divine_hellfire_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("divine_hellfire_stone")).setHardness(0.5F).setResistance(10.0F), divine_hellfire_stone_double_slab);			
		oblitus_stone_double_slab = register("oblitus_stone_double_slab", new BlockAetherSlab("oblitus_stone_double_slab", true, Material.rock).setBlockTextureName(Aether.find("oblitus_stone")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		oblitus_stone_slab = registerSlab("oblitus_stone_slab", new BlockAetherSlab("oblitus_stone_slab", false, Material.rock).setBlockTextureName(Aether.find("oblitus_stone")).setHardness(0.5F).setResistance(10.0F), oblitus_stone_double_slab);	
		
		
		golden_oak_double_slab = register("golden_oak_double_slab", new BlockAetherSlab("golden_oak_double_slab", true, Material.rock).setBlockTextureName(Aether.find("golden_oak_planks")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		golden_oak_slab = registerSlab("golden_oak_slab", new BlockAetherSlab("golden_oak_slab", false, Material.rock).setBlockTextureName(Aether.find("golden_oak_planks")).setHardness(0.5F).setResistance(10.0F), golden_oak_double_slab);
		
		divine_oak_double_slab = register("divine_oak_double_slab", new BlockAetherSlab("divine_oak_double_slab", true, Material.rock).setBlockTextureName(Aether.find("divine_oak_planks")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		divine_oak_slab = registerSlab("divine_oak_slab", new BlockAetherSlab("divine_oak_slab", false, Material.rock).setBlockTextureName(Aether.find("divine_oak_planks")).setHardness(0.5F).setResistance(10.0F), divine_oak_double_slab);
		
		greatroot_double_slab = register("greatroot_double_slab", new BlockAetherSlab("greatroot_double_slab", true, Material.wood).setBlockTextureName(Aether.find("greatroot_planks")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		greatroot_slab = registerSlab("greatroot_slab", new BlockAetherSlab("greatroot_slab", false, Material.wood).setBlockTextureName(Aether.find("greatroot_planks")).setHardness(2.0F).setResistance(5.0F), greatroot_double_slab);
		wisproot_double_slab = register("wisproot_double_slab", new BlockAetherSlab("wisproot_double_slab", true, Material.wood).setBlockTextureName(Aether.find("wisproot_planks")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		wisproot_slab = registerSlab("wisproot_slab", new BlockAetherSlab("wisproot_slab", false, Material.wood).setBlockTextureName(Aether.find("wisproot_planks")).setHardness(2.0F).setResistance(5.0F), wisproot_double_slab);
		void_double_slab = register("void_double_slab", new BlockAetherSlab("void_double_slab", true, Material.wood).setBlockTextureName(Aether.find("void_planks")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		void_slab = registerSlab("void_slab", new BlockAetherSlab("void_slab", false, Material.wood).setBlockTextureName(Aether.find("void_planks")).setHardness(2.0F).setResistance(5.0F), void_double_slab);
		
		deific_double_slab = register("deific_double_slab", new BlockAetherSlab("deific_double_slab", true, Material.rock).setBlockTextureName(Aether.find("deific")).setHardness(5.5F).setResistance(7.0F)).setCreativeTab(null);
		deific_slab = registerSlab("deific_slab", new BlockAetherSlab("deific_slab", false, Material.rock).setBlockTextureName(Aether.find("deific")).setHardness(5.5F).setResistance(7.0F), deific_double_slab);
		deific_brick_double_slab = register("deific_brick_double_slab", new BlockAetherSlab("deific_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("deific_bricks")).setHardness(5.5F).setResistance(7.0F)).setCreativeTab(null);
		deific_brick_slab = registerSlab("deific_brick_slab", new BlockAetherSlab("deific_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("deific_bricks")).setHardness(5.5F).setResistance(7.0F), deific_brick_double_slab);
		enchanted_deific_double_slab = register("enchanted_deific_double_slab", new BlockAetherSlab("enchanted_deific_double_slab", true, Material.rock).setBlockTextureName(Aether.find("enchanted_deific")).setHardness(5.5F).setResistance(7.0F)).setCreativeTab(null);
		enchanted_deific_slab = registerSlab("enchanted_deific_slab", new BlockAetherSlab("enchanted_deific_slab", false, Material.rock).setBlockTextureName(Aether.find("enchanted_deific")).setHardness(5.5F).setResistance(7.0F), enchanted_deific_double_slab);			
		enchanted_deific_brick_double_slab = register("enchanted_deific_brick_double_slab", new BlockAetherSlab("enchanted_deific_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("enchanted_deific_bricks")).setHardness(5.5F).setResistance(7.0F)).setCreativeTab(null);
		enchanted_deific_brick_slab = registerSlab("enchanted_deific_brick_slab", new BlockAetherSlab("enchanted_deific_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("enchanted_deific_bricks")).setHardness(5.5F).setResistance(7.0F), enchanted_deific_brick_double_slab);		
		enchanted_holystone_double_slab = register("enchanted_holystone_double_slab", new BlockAetherSlab("enchanted_holystone_double_slab", true, Material.rock).setBlockTextureName(Aether.findHighlands("agiosite")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		enchanted_holystone_slab = registerSlab("enchanted_holystone_slab", new BlockAetherSlab("enchanted_holystone_slab", false, Material.rock).setBlockTextureName(Aether.findHighlands("agiosite")).setHardness(2.0F).setResistance(5.0F), enchanted_holystone_double_slab);
		enchanted_holystone_brick_double_slab = register("enchanted_holystone_brick_double_slab", new BlockAetherSlab("enchanted_holystone_brick_double_slab", true, Material.rock).setBlockTextureName(Aether.find("enchanted_holystone_bricks")).setHardness(2.0F).setResistance(10.0F)).setCreativeTab(null);
		enchanted_holystone_brick_slab = registerSlab("enchanted_holystone_brick_slab", new BlockAetherSlab("enchanted_holystone_brick_slab", false, Material.rock).setBlockTextureName(Aether.find("enchanted_holystone_bricks")).setHardness(2.0F).setResistance(5.0F), enchanted_holystone_brick_double_slab);
	
	}

	public static void initializeHarvestLevels()
	{
		BlocksAether.aether_grass.setHarvestLevel("shovel", 0);
		BlocksAether.arctic_grass.setHarvestLevel("shovel", 0);
		BlocksAether.verdant_grass.setHarvestLevel("shovel", 0);
		BlocksAether.divine_grass.setHarvestLevel("shovel", 0);
		BlocksAether.enchanted_aether_grass.setHarvestLevel("shovel", 0);
		BlocksAether.aether_dirt.setHarvestLevel("shovel", 0);
		BlocksAether.holystone.setHarvestLevel("pickaxe", 0);
		BlocksAether.azure_holystone.setHarvestLevel("pickaxe", 1);
		BlocksAether.agiosite.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_agiosite_bricks.setHarvestLevel("pickaxe", 0);
		BlocksAether.deific.setHarvestLevel("pickaxe", 1);
		BlocksAether.notch_holystone.setHarvestLevel("pickaxe", 0);
		BlocksAether.deific_bricks.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_deific_bricks.setHarvestLevel("pickaxe", 1);
		BlocksAether.aetheral_stone_bricks.setHarvestLevel("pickaxe", 0);
		BlocksAether.caelestia_stone.setHarvestLevel("pickaxe", 2);
		BlocksAether.carved_caelestia_stone.setHarvestLevel("pickaxe", 2);
		BlocksAether.luminous_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.cer_scales_block.setHarvestLevel("pickaxe", 3);
		BlocksAether.enchanted_deific.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_holystone.setHarvestLevel("pickaxe", 1);
		BlocksAether.mossy_holystone.setHarvestLevel("pickaxe", 0);
		BlocksAether.bloodmoss_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.divine_carved_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_sentry_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.aetheral_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.deific_double_slab.setHarvestLevel("pickaxe", 1);
		BlocksAether.deific_slab.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_deific_double_slab.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_deific_slab.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_holystone_double_slab.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_holystone_slab.setHarvestLevel("pickaxe", 1);
		BlocksAether.agiosite_bricks.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_aetheral_stone_bricks.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_holystone_bricks.setHarvestLevel("pickaxe", 1);
		BlocksAether.oblitus_stone.setHarvestLevel("pickaxe", 2);
		BlocksAether.cracked_oblitus_stone.setHarvestLevel("pickaxe", 2);
		BlocksAether.divine_hellfire_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_light_hellfire_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_angelic_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_light_angelic_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.divineral_block.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_carved_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_sentry_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_carved_stone_double_slab.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_angelic_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_angelic_stone_wall.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_angelic_stone_stairs.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_light_angelic_stone.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_angelic_stone_double_slab.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_angelic_stone_slab.setHarvestLevel("pickaxe", 3);	
		BlocksAether.divine_angelic_stone_double_slab.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_angelic_stone_slab.setHarvestLevel("pickaxe", 3);		
		BlocksAether.skyroot_log_wall.setHarvestLevel("axe", 0);
		BlocksAether.wisproot_log_wall.setHarvestLevel("axe", 0);
		BlocksAether.golden_oak_log_wall.setHarvestLevel("axe", 0);
		BlocksAether.greatroot_log_wall.setHarvestLevel("axe", 0);
		BlocksAether.void_log_wall.setHarvestLevel("axe", 0);
		
		BlocksAether.enchanted_aetheral_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.light_genesis_stone.setHarvestLevel("pickaxe", 2);
		BlocksAether.genesis_double_slab.setHarvestLevel("pickaxe", 2);
		BlocksAether.holystone_brick.setHarvestLevel("pickaxe", 0);
		BlocksAether.aercloud.setHarvestLevel("shovel", 0);
		BlocksAether.quicksoil.setHarvestLevel("shovel", 0);
		BlocksAether.icestone.setHarvestLevel("pickaxe", 1);
		BlocksAether.glowing_icestone.setHarvestLevel("pickaxe", 1);
		BlocksAether.ambrosium_ore.setHarvestLevel("pickaxe", 0);
		BlocksAether.ambrosium_block.setHarvestLevel("pickaxe", 0);
		BlocksAether.arkenium_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.zanite_ore.setHarvestLevel("pickaxe", 1);
		BlocksAether.gravitite_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.continuum_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.continuum_block.setHarvestLevel("pickaxe", 3);
		BlocksAether.primeval_artifact.setHarvestLevel("pickaxe", 3);
		BlocksAether.skyroot_log.setHarvestLevel("axe", 0);
		BlocksAether.void_log.setHarvestLevel("axe", 0);
		BlocksAether.void_planks.setHarvestLevel("axe", 0);
		BlocksAether.skyroot_planks.setHarvestLevel("axe", 0);
		BlocksAether.skyroot_trapdoor.setHarvestLevel("axe", 0);
		BlocksAether.aerogel.setHarvestLevel("pickaxe", 3);
		BlocksAether.enchanted_gravitite.setHarvestLevel("pickaxe", 2);
		BlocksAether.zanite_block.setHarvestLevel("pickaxe", 1);
		BlocksAether.arkenium_block.setHarvestLevel("pickaxe", 2);
		BlocksAether.berry_bush_stem.setHarvestLevel("axe", 0);
		BlocksAether.raspberry_bush_stem.setHarvestLevel("axe", 0);
		BlocksAether.grape_tree_stage_one.setHarvestLevel("axe", 0);
		BlocksAether.grape_tree_stage_two.setHarvestLevel("axe", 0);
		BlocksAether.grape_tree_mature.setHarvestLevel("axe", 0);
		BlocksAether.enchanter.setHarvestLevel("pickaxe", 0);
		BlocksAether.freezer.setHarvestLevel("pickaxe", 0);
		BlocksAether.incubator.setHarvestLevel("pickaxe", 0);
		BlocksAether.carved_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.fuse_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.creeping_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.angelic_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.hellfire_stone.setHarvestLevel("pickaxe", 0);
		BlocksAether.chest_mimic.setHarvestLevel("axe", 0);
		BlocksAether.pillar.setHarvestLevel("pickaxe", 0);
		BlocksAether.pillar_top.setHarvestLevel("pickaxe", 0);
		BlocksAether.skyroot_fence.setHarvestLevel("axe", 0);
		BlocksAether.skyroot_fence_gate.setHarvestLevel("axe", 0);
		BlocksAether.greatroot_fence.setHarvestLevel("axe", 0);
		BlocksAether.greatroot_fence_gate.setHarvestLevel("axe", 0);
		BlocksAether.wisproot_fence.setHarvestLevel("axe", 0);
		BlocksAether.wisproot_fence_gate.setHarvestLevel("axe", 0);	
		BlocksAether.void_fence.setHarvestLevel("axe", 0);
		BlocksAether.void_fence_gate.setHarvestLevel("axe", 0);	
		BlocksAether.carved_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.angelic_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.genesis_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.hellfire_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.holystone_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.holystone_brick_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.mossy_holystone_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.aerogel_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.agiosite_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_agiosite_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.aetheral_stone_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_aetheral_stone_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.deific_wall.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_deific_wall.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_holystone_wall.setHarvestLevel("pickaxe", 1);
		BlocksAether.caelestia_stone_wall.setHarvestLevel("pickaxe", 2);
		BlocksAether.carved_caelestia_stone_wall.setHarvestLevel("pickaxe", 2);
		BlocksAether.enchanted_agiosite_brick_wall.setHarvestLevel("pickaxe", 0);
		BlocksAether.divine_carved_stone_wall.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_carved_stone_wall.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_hellfire_stone_wall.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_angelic_stone_wall.setHarvestLevel("pickaxe", 3);
		BlocksAether.golden_oak_fence.setHarvestLevel("axe", 0);
		BlocksAether.golden_oak_gate.setHarvestLevel("axe", 0);
		BlocksAether.zanite_trapdoor.setHarvestLevel("pickaxe", 2);
		BlocksAether.ancient_enchanter.setHarvestLevel("pickaxe", 3);
		BlocksAether.wisproot_trapdoor.setHarvestLevel("axe", 0);
		BlocksAether.void_trapdoor.setHarvestLevel("axe", 0);
		BlocksAether.greatroot_trapdoor.setHarvestLevel("axe", 0);
		BlocksAether.golden_oak_trapdoor.setHarvestLevel("axe", 0);
		BlocksAether.skyroot_ladder.setHarvestLevel("axe", 0);
		BlocksAether.aetheral_ambrosium_ore.setHarvestLevel("pickaxe", 0);
		BlocksAether.aetheral_zanite_ore.setHarvestLevel("pickaxe", 1);
		BlocksAether.aetheral_arkenium_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.aetheral_gravitite_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.aetheral_continuum_ore.setHarvestLevel("pickaxe", 2);		
		BlocksAether.agiosite_ambrosium_ore.setHarvestLevel("pickaxe", 0);
		BlocksAether.agiosite_zanite_ore.setHarvestLevel("pickaxe", 1);
		BlocksAether.agiosite_arkenium_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.agiosite_gravitite_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.agiosite_continuum_ore.setHarvestLevel("pickaxe", 2);
		
		BlocksAether.deific_ambrosium_ore.setHarvestLevel("pickaxe", 0);
		BlocksAether.deific_zanite_ore.setHarvestLevel("pickaxe", 1);
		BlocksAether.deific_arkenium_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.deific_gravitite_ore.setHarvestLevel("pickaxe", 2);
		BlocksAether.deific_continuum_ore.setHarvestLevel("pickaxe", 2);
		
		
		BlocksAether.carved_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.angelic_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.hellfire_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.genesis_stairs.setHarvestLevel("pickaxe", 2);
		BlocksAether.skyroot_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.greatroot_stairs.setHarvestLevel("axe", 0);
		BlocksAether.wisproot_stairs.setHarvestLevel("axe", 0);
		BlocksAether.void_stairs.setHarvestLevel("axe", 0);
		BlocksAether.mossy_holystone_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.holystone_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.holystone_brick_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.aerogel_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.agiosite_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_agiosite_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.aetheral_stone_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_aetheral_stone_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_agiosite_brick_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.deific_stairs.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_deific_stairs.setHarvestLevel("pickaxe", 1);
		BlocksAether.enchanted_holystone_stairs.setHarvestLevel("pickaxe", 1);
		BlocksAether.aetheral_stone_brick_stairs.setHarvestLevel("pickaxe", 0);
		BlocksAether.deific_brick_stairs.setHarvestLevel("pickaxe", 1);
		BlocksAether.agiosite_brick_stairs.setHarvestLevel("pickaxe", 1);
		BlocksAether.caelestia_stone_stairs.setHarvestLevel("pickaxe", 2);
		BlocksAether.carved_caelestia_stone_stairs.setHarvestLevel("pickaxe", 2);
		BlocksAether.divine_carved_stone_stairs.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_carved_stone_stairs.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_angelic_stone_stairs.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_hellfire_stone_stairs.setHarvestLevel("pickaxe", 3);
		
		
		BlocksAether.skyroot_double_slab.setHarvestLevel("axe", 0);
		BlocksAether.greatroot_double_slab.setHarvestLevel("axe", 0);
		BlocksAether.wisproot_double_slab.setHarvestLevel("axe", 0);
		BlocksAether.void_double_slab.setHarvestLevel("axe", 0);
		BlocksAether.carved_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.angelic_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.hellfire_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.holystone_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.mossy_holystone_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.holystone_brick_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.aerogel_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.agiosite_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_agiosite_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.aetheral_stone_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_aetheral_stone_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.caelestia_stone_double_slab.setHarvestLevel("pickaxe", 2);
		BlocksAether.carved_caelestia_stone_double_slab.setHarvestLevel("pickaxe", 2);
		BlocksAether.enchanted_agiosite_brick_double_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.divine_carved_stone_double_slab.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_hellfire_stone_double_slab.setHarvestLevel("pickaxe", 3);
		
		BlocksAether.skyroot_slab.setHarvestLevel("axe", 0);
		BlocksAether.greatroot_slab.setHarvestLevel("axe", 0);
		BlocksAether.wisproot_slab.setHarvestLevel("axe", 0);
		BlocksAether.void_slab.setHarvestLevel("axe", 0);
		BlocksAether.carved_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.angelic_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.hellfire_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.genesis_slab.setHarvestLevel("pickaxe", 2);
		BlocksAether.holystone_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.mossy_holystone_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.holystone_brick_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.aerogel_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.agiosite_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_agiosite_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.aetheral_stone_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.enchanted_aetheral_stone_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.caelestia_stone_slab.setHarvestLevel("pickaxe", 2);
		BlocksAether.carved_caelestia_stone_slab.setHarvestLevel("pickaxe", 2);
		BlocksAether.enchanted_agiosite_brick_slab.setHarvestLevel("pickaxe", 0);
		BlocksAether.divine_carved_stone_slab.setHarvestLevel("pickaxe", 3);
		BlocksAether.mythic_carved_stone_slab.setHarvestLevel("pickaxe", 3);
		BlocksAether.divine_hellfire_stone_slab.setHarvestLevel("pickaxe", 3);
		
		BlocksAether.sun_altar.setHarvestLevel("pickaxe", 0);
		BlocksAether.skyroot_bookshelf.setHarvestLevel("axe", 0);
		BlocksAether.skyroot_bed.setHarvestLevel("axe", 0);
		BlocksAether.genesis_stone.setHarvestLevel("pickaxe", 2);
		BlocksAether.pink_aercloud.setHarvestLevel("pickaxe", 0);
		BlocksAether.storm_aercloud.setHarvestLevel("pickaxe", 0);
		BlocksAether.green_aercloud.setHarvestLevel("pickaxe", 0);
		BlocksAether.purple_aercloud.setHarvestLevel("pickaxe", 0);
	}

	public static boolean isGood(Block block) {
		return block == Blocks.air || block == aercloud;
	}

	public static Block registerSlab(String name, Block slab1, Block slab2) {
		slab1.setBlockName(name);
		slab1.setCreativeTab(AetherCreativeTabs.blocks);

		GameRegistry.registerBlock(slab1, ItemAetherSlab.class, name, (BlockAetherSlab) slab1, (BlockAetherSlab) slab2, false);

		return slab1;
	}

	public static Block register(String name, Block block) {
		block.setBlockName(name);
		block.setCreativeTab(AetherCreativeTabs.blocks);

		GameRegistry.registerBlock(block, name);

		return block;
	}
	
	public static Block registerHidden(String name, Block block) {
		block.setBlockName(name);

		GameRegistry.registerBlock(block, name);

		return block;
	}
	
	public static Block registerItemBlock(String name, final Block block, final Class<? extends ItemBlock> itemBlockClass) {
	 	block.setBlockName(name);
		block.setCreativeTab(AetherCreativeTabs.blocks);
		
        GameRegistry.registerBlock(block, (Class)itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
        return block;
    }

	public static Block registerRarity(String name, Block block, EnumRarity rarity) {
		block.setBlockName(name);
		block.setCreativeTab(AetherCreativeTabs.blocks);

		GameRegistry.registerBlock(block, ItemBlockRarity.class, name, rarity);

		return block;
	}

	public static Block registerMeta(String name, Block block) {
		block.setBlockName(name);
		block.setCreativeTab(AetherCreativeTabs.blocks);

		GameRegistry.registerBlock(block, ItemBlockMetadata.class, name);

		return block;
	}

	public static Block registerBed(String name, Block block) {
		block.setBlockName(name);

		GameRegistry.registerBlock(block, name);

		return block;
	}

	public static Block registerEnchanter(String name, Block block) {
		block.setBlockName(name);
		block.setCreativeTab(AetherCreativeTabs.blocks);

		GameRegistry.registerBlock(block, ItemBlockEnchanter.class, name);

		return block;
	}
	
	public static interface ISubBlocksBlock {
		Class<? extends ItemBlock> getItemBlockClass();
	}

}
