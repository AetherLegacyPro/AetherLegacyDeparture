package com.gildedgames.the_aether.world;

import com.gildedgames.the_aether.AetherConfig;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;

import com.gildedgames.the_aether.world.biome.AetherBiome;
import com.gildedgames.the_aether.world.gen.MapGenAncientGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenAncientSilverDungeon;
import com.gildedgames.the_aether.world.gen.MapGenDivineGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenDivineSilverDungeon;
import com.gildedgames.the_aether.world.gen.MapGenGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenLargeColdAercloud;
import com.gildedgames.the_aether.world.gen.MapGenSilverDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentAncientGoldenDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentAncientGoldenIsland;
import com.gildedgames.the_aether.world.gen.components.ComponentAncientGoldenIslandStub;
import com.gildedgames.the_aether.world.gen.components.ComponentAncientSilverDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentDivineGoldenDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentDivineGoldenDungeonIsland;
import com.gildedgames.the_aether.world.gen.components.ComponentDivineGoldenIslandStub;
import com.gildedgames.the_aether.world.gen.components.ComponentDivineSilverDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentGoldenDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentGoldenIsland;
import com.gildedgames.the_aether.world.gen.components.ComponentGoldenIslandStub;
import com.gildedgames.the_aether.world.gen.components.ComponentLargeColdAercloud;
import com.gildedgames.the_aether.world.gen.components.ComponentSilverDungeon;

public class AetherWorld {

	public static BiomeGenBase aether_biome = new AetherBiome();

	public static void initialization() {
		MapGenStructureIO.registerStructure(MapGenSilverDungeon.Start.class, "aether_legacy:silver_dungeon_start");
		MapGenStructureIO.registerStructure(MapGenAncientSilverDungeon.Start.class, "aether_legacy:ancient_silver_dungeon_start");
		MapGenStructureIO.registerStructure(MapGenDivineSilverDungeon.Start.class, "aether_legacy:divine_silver_dungeon_start");
		
		MapGenStructureIO.registerStructure(MapGenGoldenDungeon.Start.class, "aether_legacy:golden_dungeon_start");
		MapGenStructureIO.registerStructure(MapGenAncientGoldenDungeon.Start.class, "aether_legacy:ancient_golden_dungeon_start");
		MapGenStructureIO.registerStructure(MapGenDivineGoldenDungeon.Start.class, "aether_legacy:divine_golden_dungeon_start");

		MapGenStructureIO.registerStructure(MapGenLargeColdAercloud.Start.class, "aether_legacy:large_cold_aercloud_start");

		MapGenStructureIO.func_143031_a(ComponentLargeColdAercloud.class, "aether_legacy:large_cold_aercloud_component");
		
		MapGenStructureIO.func_143031_a(ComponentSilverDungeon.class, "aether_legacy:silver_dungeon_component");
		MapGenStructureIO.func_143031_a(ComponentAncientSilverDungeon.class, "aether_legacy:ancient_silver_dungeon_component");
		MapGenStructureIO.func_143031_a(ComponentDivineSilverDungeon.class, "aether_legacy:divine_silver_dungeon_component");
		
		MapGenStructureIO.func_143031_a(ComponentGoldenDungeon.class, "aether_legacy:golden_dungeon_component");
		MapGenStructureIO.func_143031_a(ComponentGoldenIsland.class, "aether_legacy:golden_island_component");
		MapGenStructureIO.func_143031_a(ComponentGoldenIslandStub.class, "aether_legacy:golden_island_stub_component");
		
		MapGenStructureIO.func_143031_a(ComponentAncientGoldenDungeon.class, "aether_legacy:ancient_golden_dungeon_component");
		MapGenStructureIO.func_143031_a(ComponentAncientGoldenIsland.class, "aether_legacy:ancient_golden_island_component");
		MapGenStructureIO.func_143031_a(ComponentAncientGoldenIslandStub.class, "aether_legacy:ancient_golden_island_stub_component");
		
		MapGenStructureIO.func_143031_a(ComponentDivineGoldenDungeon.class, "aether_legacy:divine_golden_dungeon_component");
		MapGenStructureIO.func_143031_a(ComponentDivineGoldenDungeonIsland.class, "aether_legacy:divine_golden_island_component");
		MapGenStructureIO.func_143031_a(ComponentDivineGoldenIslandStub.class, "aether_legacy:divine_golden_island_stub_component");

		DimensionManager.registerProviderType(AetherConfig.getAetherDimensionID(), AetherWorldProvider.class, false);
		DimensionManager.registerDimension(AetherConfig.getAetherDimensionID(), AetherConfig.getAetherDimensionID());
		//aether_dimension_type = DimensionType.register("AetherI", "_aetherI", AetherConfig.getAetherDimensionID(), AetherWorldProvider.class, false);
	}

}