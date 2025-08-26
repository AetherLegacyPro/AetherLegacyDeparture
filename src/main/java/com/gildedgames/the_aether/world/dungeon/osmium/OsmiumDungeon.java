package com.gildedgames.the_aether.world.dungeon.osmium;

import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.world.AetherWorld;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class OsmiumDungeon implements IWorldGenerator  {
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == AetherConfig.getAetherDimensionID()) {
	         this.generateAether(world, rand, chunkX * 16, chunkZ * 16);
	      }
	   }
	
	int aether_random = AetherConfig.osmium_dungeon_rarity;
		
	public void generateAether(World world, Random rand, int x, int z) {
		if(Math.abs(x) < 2000 && Math.abs(z) < 2000) return;
		if(Math.abs(x) < 2000 || Math.abs(z) < 2000) return;
		
		int chunkX;
		int chunkZ;
		BiomeGenBase biome;
		Type[] biomeList;			
		chunkX = x * 16 + rand.nextInt(16) + 8;
		chunkZ = z * 16 + rand.nextInt(16) + 8;
		biome = world.getBiomeGenForCoords(x, z);
		biomeList = BiomeDictionary.getTypesForBiome(biome);		
		if ((biome == AetherWorld.aether_biome) && AetherConfig.osmium_dungeon_rarity != 0 && rand.nextInt(aether_random) == 0 && AetherConfig.osmium_dungeon_enable == true) {
			
		int x1 = x + rand.nextInt(16) + 8;
	  	int y1 = 128;
	  	int z1 = z + rand.nextInt(16) + 8;
	  	
	  	if (AetherConfig.enableLogReporting()) {
            System.out.println("Osmium Dungeon generated at (X:" + x1 + ", Z:" + z1 + ")");
            System.out.println("Large Structure is generating, be patient!");
        }
	  	
	  	new OsmiumDungeonGen1().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen2().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen3().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen4().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen5().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen6().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen7().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen8().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen9().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen10().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen11().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen12().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen13().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen14().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen15().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen16().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen17().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen18().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen19().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen20().generate(world, rand, x1, y1, z1);
	  	new OsmiumDungeonGen21().generate(world, rand, x1, y1, z1);
	  	
	  	new OsmiumDungeonStormAercloudGen1().generate(world, rand, x1 - 2, y1 + 27, z1 - 10);
	  	new OsmiumDungeonStormAercloudGen2().generate(world, rand, x1 - 2, y1 + 27, z1 - 10);
		
		}
	
	}
	
	public static ItemStack getOsmiumLoot(Random random) {
		int item = random.nextInt(17);

		switch (item) {
			case 0:
				return new ItemStack(BlocksAether.enchanted_gravitite, random.nextInt(4) + 1, random.nextInt(2));
			case 1:
				return new ItemStack(ItemsAether.valkyrie_ring);
			case 2: {
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.tipped_valkyrie_axe);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.tipped_valkyrie_shovel);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.tipped_valkyrie_pickaxe);
			}
			case 3:
				return new ItemStack(ItemsAether.dart_shooter, 0, 3);
			case 4:
				return new ItemStack(ItemsAether.bone_ring);
			case 5:
				return new ItemStack(ItemsAether.reinforced_regeneration_stone);
			case 6:
				return new ItemStack(ItemsAether.aer_cape);
			case 7:
				return new ItemStack(ItemsAether.false_wings);
			case 8:
				return new ItemStack(ItemsAether.haste_ring);
			case 9:
				return new ItemStack(ItemsAether.invisibility_cape);
			case 10:
				return new ItemStack(ItemsAether.divine_essence, random.nextInt(3) + 4);
			case 11:
				return new ItemStack(ItemsAether.divineral_pendant);
			case 12:
				return new ItemStack(ItemsAether.auralite_crystal, random.nextInt(3) + 4);
			case 13:
				return new ItemStack(ItemsAether.agility_boots);
			case 14:
				return new ItemStack(ItemsAether.dexterity_shard);
			case 15:
				return new ItemStack(ItemsAether.discharge_cape);
			default:
				return new ItemStack(ItemsAether.arkenium_chunk, random.nextInt(12) + 10);
		}
	}

}
