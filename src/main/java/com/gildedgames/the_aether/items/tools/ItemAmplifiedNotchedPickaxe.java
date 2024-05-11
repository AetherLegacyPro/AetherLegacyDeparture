package com.gildedgames.the_aether.items.tools;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.othermods.OtherModItems;
import com.gildedgames.the_aether.items.tools.ItemAetherTool;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import com.gildedgames.the_aether.registry.ExternalContentNovaCraft;
import com.gildedgames.the_aether.registry.OtherModBlocks;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemAmplifiedNotchedPickaxe extends ItemAetherTool {
	
	public ItemAmplifiedNotchedPickaxe(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.EMERALD, toolType);
		this.setMaxDamage(777);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase living)
    {		
	  boolean silkTouchModifier = EnchantmentHelper.getSilkTouchModifier(living);
	  if (silkTouchModifier == false) {
		//----------------------------------Aether Departure--------------------------------
        if (world.getBlock(x, y, z) == BlocksAether.ambrosium_ore || world.getBlock(x, y, z) == BlocksAether.deific_ambrosium_ore
        || world.getBlock(x, y, z) == BlocksAether.aetheral_ambrosium_ore || world.getBlock(x, y, z) == BlocksAether.agiosite_ambrosium_ore) {       
        	Durability(item, world, block, x, y,  z, living);
        	if (!world.isRemote) {
        	EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.ambrosium_shard, 2));
        	world.spawnEntityInWorld(entityItem);
        	}
        }
        if (world.getBlock(x, y, z) == BlocksAether.zanite_ore || world.getBlock(x, y, z) == BlocksAether.deific_zanite_ore
        || world.getBlock(x, y, z) == BlocksAether.aetheral_zanite_ore || world.getBlock(x, y, z) == BlocksAether.agiosite_zanite_ore) {
        	Durability(item, world, block, x, y,  z, living);   	
             if (!world.isRemote) {
             EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.zanite_gemstone, 2));
             world.spawnEntityInWorld(entityItem);
             }
        }
        if (world.getBlock(x, y, z) == BlocksAether.arkenium_ore || world.getBlock(x, y, z) == BlocksAether.deific_arkenium_ore
        || world.getBlock(x, y, z) == BlocksAether.aetheral_arkenium_ore || world.getBlock(x, y, z) == BlocksAether.agiosite_arkenium_ore) {
        	Durability(item, world, block, x, y,  z, living);          	
              if (!world.isRemote) {
              EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.arkenium_fragement, 2));
              world.spawnEntityInWorld(entityItem);
              }
        }
        if (world.getBlock(x, y, z) == BlocksAether.gravitite_ore || world.getBlock(x, y, z) == BlocksAether.deific_gravitite_ore
        || world.getBlock(x, y, z) == BlocksAether.aetheral_gravitite_ore || world.getBlock(x, y, z) == BlocksAether.agiosite_gravitite_ore) {
           world.setBlock(x, y, z, Blocks.air);                  	
           Durability(item, world, block, x, y,  z, living);                  	
              if (!world.isRemote) {
              EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.raw_gravitite, 3));
              world.spawnEntityInWorld(entityItem);
              }
              
              int rand3 = (int)(1 + Math.random() * 200);
      		if (!world.isRemote && rand3 == 1) {
      			int rand2 = (int)(1 + Math.random() * 3);
      			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
      	    	world.spawnEntityInWorld(entityItem);
      		}
        }
        if (world.getBlock(x, y, z) == BlocksAether.primeval_artifact) {        	
        	world.setBlock(x, y, z, Blocks.air);                  	
        	Durability(item, world, block, x, y,  z, living);                  	
              if (!world.isRemote) {
              EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divineral_nugget, 14));
              world.spawnEntityInWorld(entityItem);
              }
              
              int rand3 = (int)(1 + Math.random() * 180);
      		if (!world.isRemote && rand3 == 1) {
      			int rand2 = (int)(1 + Math.random() * 3);
      			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
      	    	world.spawnEntityInWorld(entityItem);
      		}
        }
        
        
        //----------------------------------------------Vanilla-----------------------------------------------
        if (world.getBlock(x, y, z) == Blocks.coal_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_coal_ore) {                               
        	  Durability(item, world, block, x, y,  z, living);                  	
              if (!world.isRemote) {
              EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.coal, 2));
              world.spawnEntityInWorld(entityItem);
              }
        }
        {
        if (Loader.isModLoaded("etfuturum")) {
            if (world.getBlock(x, y, z) == Blocks.iron_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_iron_ore) {
                  
            	  world.setBlock(x, y, z, Blocks.air);
            	  Durability(item, world, block, x, y,  z, living);                  	
                  if (!world.isRemote) {
                  EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.raw_ore, 2, 1));
                  world.spawnEntityInWorld(entityItem);
                  }
             }
            }
            else {
            if (world.getBlock(x, y, z) == Blocks.iron_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_iron_ore) { 
          	    world.setBlock(x, y, z, Blocks.air);
          	  Durability(item, world, block, x, y,  z, living);                  	
                if (!world.isRemote) {
                EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.iron_ingot, 2));
                world.spawnEntityInWorld(entityItem);
                }
            }
         }
        }
        {
        if (Loader.isModLoaded("etfuturum")) {
            if (world.getBlock(x, y, z) == Blocks.gold_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_gold_ore) {
                  
            	  world.setBlock(x, y, z, Blocks.air);
            	  Durability(item, world, block, x, y,  z, living);                  	
                  if (!world.isRemote) {
                  EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.raw_ore, 2, 2));
                  world.spawnEntityInWorld(entityItem);
                  }
             }
            }
            else {
            if (world.getBlock(x, y, z) == Blocks.gold_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_gold_ore) { 
          	    world.setBlock(x, y, z, Blocks.air);
          	    Durability(item, world, block, x, y,  z, living);                  	
                if (!world.isRemote) {
                EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.gold_ingot, 2));
                world.spawnEntityInWorld(entityItem);
                }
            }
         }
        }
        if (world.getBlock(x, y, z) == Blocks.redstone_ore || world.getBlock(x, y, z) == Blocks.lit_redstone_ore  
        || world.getBlock(x, y, z) == OtherModBlocks.deepslate_redstone_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_lit_redstone_ore) {
        	Durability(item, world, block, x, y,  z, living);                  	
            if (!world.isRemote) {
            EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.redstone, 4));
            world.spawnEntityInWorld(entityItem);
            }
        }
        if (world.getBlock(x, y, z) == Blocks.lapis_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_lapis_ore) {
        	Durability(item, world, block, x, y,  z, living);                  	
            if (!world.isRemote) {
            EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.dye, 4, 4));
            world.spawnEntityInWorld(entityItem);
            }
       }
       if (world.getBlock(x, y, z) == Blocks.diamond_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_diamond_ore) {
    	    Durability(item, world, block, x, y,  z, living);                  	
            if (!world.isRemote) {
            EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.diamond, 1));
            world.spawnEntityInWorld(entityItem);
            }
            
            int rand3 = (int)(1 + Math.random() * 200);
    		if (!world.isRemote && rand3 == 1) {
    			int rand2 = (int)(1 + Math.random() * 3);
    			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
    	    	world.spawnEntityInWorld(entityItem);
    		}
       }
       if (world.getBlock(x, y, z) == Blocks.emerald_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_emerald_ore) {
    	   Durability(item, world, block, x, y,  z, living);                  	
           if (!world.isRemote) {
           EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.emerald, 1));
           world.spawnEntityInWorld(entityItem);
           }
           
           int rand3 = (int)(1 + Math.random() * 290);
   		   if (!world.isRemote && rand3 == 1) {
   			int rand2 = (int)(1 + Math.random() * 3);
   			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
   	    	world.spawnEntityInWorld(entityItem);
   		  }
      }
       if (world.getBlock(x, y, z) == Blocks.quartz_ore) {
    	   Durability(item, world, block, x, y,  z, living);                  	
           if (!world.isRemote) {
           EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.quartz, 3));
           world.spawnEntityInWorld(entityItem);
           }
      }
      //----------------------------------------------Et Futurum Requiem-----------------------------------------------
       if (world.getBlock(x, y, z) == OtherModBlocks.ancient_debris) {
          
    	   world.setBlock(x, y, z, Blocks.air);
    	   Durability(item, world, block, x, y,  z, living);                  	
           if (!world.isRemote) {
           EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.netherite_scrap, 2));
           world.spawnEntityInWorld(entityItem);
           }
           
           int rand3 = (int)(1 + Math.random() * 290);
   		   if (!world.isRemote && rand3 == 1) {
   			int rand2 = (int)(1 + Math.random() * 3);
   			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
   	    	world.spawnEntityInWorld(entityItem);
   		  }
      }
      if (world.getBlock(x, y, z) == OtherModBlocks.copper_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_copper_ore) {
           
    	  world.setBlock(x, y, z, Blocks.air);
    	  Durability(item, world, block, x, y,  z, living);                  	
          if (!world.isRemote) {
          EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.raw_ore, 3, 0));
          world.spawnEntityInWorld(entityItem);
          }
       }
      //----------------------------------------------Netherlicious----------------------------------------------------
      if (world.getBlock(x, y, z) == OtherModBlocks.EfrineOre) {         
    	  Durability(item, world, block, x, y,  z, living);                   	
          if (!world.isRemote) {
          EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.Nugget, 4, 1));
          world.spawnEntityInWorld(entityItem);
          }
       } 
      //----------------------------------------------Divine RPG-------------------------------------------------------
      if (world.getBlock(x, y, z) == OtherModBlocks.realmiteOre) {
          
    	  world.setBlock(x, y, z, Blocks.air);
    	  Durability(item, world, block, x, y,  z, living);                   	
          if (!world.isRemote) {
          EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.realmiteIngot, 2));
          world.spawnEntityInWorld(entityItem);
          }
       }
      if (world.getBlock(x, y, z) == OtherModBlocks.rupeeOre) {
          
    	  world.setBlock(x, y, z, Blocks.air);
    	  Durability(item, world, block, x, y,  z, living);                   	
          if (!world.isRemote) {
          EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.rupeeIngot, 2));
          world.spawnEntityInWorld(entityItem);
          }
          
          int rand3 = (int)(1 + Math.random() * 290);
  		  if (!world.isRemote && rand3 == 1) {
  			int rand2 = (int)(1 + Math.random() * 3);
  			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
  	    	world.spawnEntityInWorld(entityItem);
  		}
       }
      if (world.getBlock(x, y, z) == OtherModBlocks.arlemiteOre) {
          
    	  world.setBlock(x, y, z, Blocks.air);
    	  Durability(item, world, block, x, y,  z, living);                   	
          if (!world.isRemote) {
          EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.arlemiteIngot, 2));
          world.spawnEntityInWorld(entityItem);
          }
          
          int rand3 = (int)(1 + Math.random() * 290);
  		  if (!world.isRemote && rand3 == 1) {
  			int rand2 = (int)(1 + Math.random() * 3);
  			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
  	    	world.spawnEntityInWorld(entityItem);
  		}
       }
      
      //----------------------------------------------NovaCraft--------------------------------------------------------
      if (world.getBlock(x, y, z) == OtherModBlocks.etherstone_coal) {     	
    	  Durability(item, world, block, x, y,  z, living);                  	
          if (!world.isRemote) {
          EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.coal, 2));
          world.spawnEntityInWorld(entityItem);
          }
    }
    {
    if (Loader.isModLoaded("etfuturum")) {
    	if (world.getBlock(x, y, z) == OtherModBlocks.etherstone_iron || world.getBlock(x, y, z) == OtherModBlocks.nullstone_iron 
    	        || world.getBlock(x, y, z) == OtherModBlocks.grimstone_iron) { 

        	  world.setBlock(x, y, z, Blocks.air);
        	  Durability(item, world, block, x, y,  z, living);                	
              if (!world.isRemote) {
              EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.raw_ore, 2, 1));
              world.spawnEntityInWorld(entityItem);
              }
         }
        }
        else {
        	if (world.getBlock(x, y, z) == OtherModBlocks.etherstone_iron || world.getBlock(x, y, z) == OtherModBlocks.nullstone_iron 
        	    || world.getBlock(x, y, z) == OtherModBlocks.grimstone_iron) {  
      	    world.setBlock(x, y, z, Blocks.air);
      	  Durability(item, world, block, x, y,  z, living);             	
            if (!world.isRemote) {
            EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.iron_ingot, 2));
            world.spawnEntityInWorld(entityItem);
            }
        }
     }
    }
    {
    if (Loader.isModLoaded("etfuturum")) {
    	if (world.getBlock(x, y, z) == OtherModBlocks.etherstone_gold || world.getBlock(x, y, z) == OtherModBlocks.nullstone_gold 
    	        || world.getBlock(x, y, z) == OtherModBlocks.grimstone_gold) { 
              
        	  world.setBlock(x, y, z, Blocks.air);
        	  Durability(item, world, block, x, y,  z, living);              	
              if (!world.isRemote) {
              EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(OtherModItems.raw_ore, 2, 2));
              world.spawnEntityInWorld(entityItem);
              }
         }
        }
        else {
        if (world.getBlock(x, y, z) == OtherModBlocks.etherstone_gold || world.getBlock(x, y, z) == OtherModBlocks.nullstone_gold 
        || world.getBlock(x, y, z) == OtherModBlocks.grimstone_gold) { 
      	    world.setBlock(x, y, z, Blocks.air);
      	  Durability(item, world, block, x, y,  z, living);            	
            if (!world.isRemote) {
            EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.gold_ingot, 2));
            world.spawnEntityInWorld(entityItem);
            }
        }
     }
    }
    if (world.getBlock(x, y, z) == OtherModBlocks.grimstone_redstone || world.getBlock(x, y, z) == OtherModBlocks.lit_grimstone_redstone
    || world.getBlock(x, y, z) == OtherModBlocks.lit_nullstone_redstone || world.getBlock(x, y, z) == OtherModBlocks.nullstone_redstone) {
    	Durability(item, world, block, x, y,  z, living);               	
        if (!world.isRemote) {
        EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.redstone, 4));
        world.spawnEntityInWorld(entityItem);
        }
    }
    if (world.getBlock(x, y, z) == OtherModBlocks.grimstone_lapis
    || world.getBlock(x, y, z) == OtherModBlocks.nullstone_lapis) {
    	Durability(item, world, block, x, y,  z, living);                	
        if (!world.isRemote) {
        EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.dye, 4, 4));
        world.spawnEntityInWorld(entityItem);
        }
   }
   if (world.getBlock(x, y, z) == OtherModBlocks.grimstone_diamond || world.getBlock(x, y, z) == OtherModBlocks.nullstone_diamond) {
	   Durability(item, world, block, x, y,  z, living);                 	
        if (!world.isRemote) {
        EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.diamond, 1));
        world.spawnEntityInWorld(entityItem);
        }
        
        int rand3 = (int)(1 + Math.random() * 200);
		if (!world.isRemote && rand3 == 1) {
			int rand2 = (int)(1 + Math.random() * 3);
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
	    	world.spawnEntityInWorld(entityItem);
		}
   }
   if (world.getBlock(x, y, z) == OtherModBlocks.etherstone_emerald || world.getBlock(x, y, z) == OtherModBlocks.grimstone_emerald
   || world.getBlock(x, y, z) == OtherModBlocks.nullstone_emerald) {
	   Durability(item, world, block, x, y,  z, living);             	
       if (!world.isRemote) {
       EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(Items.emerald, 1));
       world.spawnEntityInWorld(entityItem);
       	    }
       
       int rand3 = (int)(1 + Math.random() * 290);
		if (!world.isRemote && rand3 == 1) {
			int rand2 = (int)(1 + Math.random() * 3);
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
	    	world.spawnEntityInWorld(entityItem);
		}
   	   }
   
   if (world.getBlock(x, y, z) == OtherModBlocks.deepslate_vanite_ore || world.getBlock(x, y, z) == OtherModBlocks.grimstone_vanite_ore
   || world.getBlock(x, y, z) == OtherModBlocks.nullstone_vanite_ore || world.getBlock(x, y, z) == OtherModBlocks.stone_vanite_ore) {
	   Durability(item, world, block, x, y,  z, living);                 	
		       if (!world.isRemote) {
		       EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ExternalContentNovaCraft.vanite_chunk, 1));
		       world.spawnEntityInWorld(entityItem);
	   }
   }   
   if (world.getBlock(x, y, z) == OtherModBlocks.brimstone_ore || world.getBlock(x, y, z) == OtherModBlocks.etherstone_brimstone) {
		       
	   Durability(item, world, block, x, y,  z, living);
	   if (!world.isRemote) {
	   EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ExternalContentNovaCraft.brimstone_dust, 2));
	   world.spawnEntityInWorld(entityItem);
	   }
   }
   if (world.getBlock(x, y, z) == OtherModBlocks.deepslate_tophinite_ore || world.getBlock(x, y, z) == OtherModBlocks.nether_tophinite_ore
   || world.getBlock(x, y, z) == OtherModBlocks.nullstone_tophinite_ore || world.getBlock(x, y, z) == OtherModBlocks.stone_tophinite_ore) {
	   
	   Durability(item, world, block, x, y,  z, living);                   	
       if (!world.isRemote) {
       EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ExternalContentNovaCraft.tophinite_gemstone, 1));
       world.spawnEntityInWorld(entityItem);
       }
  }
  if (world.getBlock(x, y, z) == OtherModBlocks.small_pherithium_stalagmite || world.getBlock(x, y, z) == OtherModBlocks.large_pherithium_stalagmite) {
	   
	   Durability(item, world, block, x, y,  z, living);                  	
       if (!world.isRemote) {
       EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ExternalContentNovaCraft.pherithium_scraps, 3));
       world.spawnEntityInWorld(entityItem);
       }
  }
  if (world.getBlock(x, y, z) == OtherModBlocks.klangite_ore || world.getBlock(x, y, z) == OtherModBlocks.deepslate_klangite_ore
  || world.getBlock(x, y, z) == OtherModBlocks.end_klangite_ore || world.getBlock(x, y, z) == OtherModBlocks.frontierslate_klangite_ore
  || world.getBlock(x, y, z) == OtherModBlocks.stone_klangite_ore) {
		
	  Durability(item, world, block, x, y,  z, living);
	  if (!world.isRemote) {
	  EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ExternalContentNovaCraft.raw_klangite, 1));
	  world.spawnEntityInWorld(entityItem);
	  }
	  
	  int rand3 = (int)(1 + Math.random() * 150);
		if (!world.isRemote && rand3 == 1) {
			int rand2 = (int)(1 + Math.random() * 3);
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.divine_essence, rand2 + 1));
	    	world.spawnEntityInWorld(entityItem);
		}
  }
  if (world.getBlock(x, y, z) == OtherModBlocks.xancium_ore) {
	  
	  Durability(item, world, block, x, y,  z, living);
				                         	
	  if (!world.isRemote) {
	  EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ExternalContentNovaCraft.xancium_dust, 2));
	  world.spawnEntityInWorld(entityItem);
		}
	  
	}
   
   
  
	  }
	  
	  else {
		  Durability(item, world, block, x, y,  z, living);
	  }
	  
	  
      return true;
    }
	
	public boolean Durability(ItemStack tool, World world, Block block, int x, int y, int z, EntityLivingBase living) {
		int fortuneModifier = EnchantmentHelper.getFortuneModifier(living);
		int rand5 = (int)(1 + Math.random() * 2);
		if (rand5 == 1) {
			tool.damageItem(1, living);
			return true;
		}
		else {
			return false;
		 }
		
	 }
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
}
