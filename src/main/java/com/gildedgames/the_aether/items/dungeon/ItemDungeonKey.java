package com.gildedgames.the_aether.items.dungeon;

import java.util.List;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumDungeonKeyType;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDungeonKey extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon bronzeIcon;

	@SideOnly(Side.CLIENT)
	private IIcon silverIcon;

	@SideOnly(Side.CLIENT)
	private IIcon goldenIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon cobaltIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon osmiumIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon ancientbronzeIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon divinebronzeIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon mythicbronzeIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon ancientsilverIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon divinesilverIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon ancientgoldenIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon divinegoldenIcon;
		
	@SideOnly(Side.CLIENT)
	private IIcon mythicsilverIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon mythicgoldenIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon palladiumIcon;	
	

	public ItemDungeonKey() {
		super();

		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registry) {
		this.bronzeIcon = registry.registerIcon(Aether.find("misc/keys/new_bronze_key"));
		this.cobaltIcon = registry.registerIcon(Aether.find("misc/keys/cobalt_key"));
		this.silverIcon = registry.registerIcon(Aether.find("misc/keys/new_silver_key"));
		this.goldenIcon = registry.registerIcon(Aether.find("misc/keys/new_golden_key"));
		this.osmiumIcon = registry.registerIcon(Aether.find("misc/keys/osmium_key"));
		this.palladiumIcon = registry.registerIcon(Aether.find("misc/keys/palladium_key"));
		this.ancientbronzeIcon = registry.registerIcon(Aether.find("misc/keys/ancient_bronze_key"));
		this.divinebronzeIcon = registry.registerIcon(Aether.find("misc/keys/divine_bronze_key"));
		this.mythicbronzeIcon = registry.registerIcon(Aether.find("misc/keys/mythic_bronze_key"));
		this.ancientsilverIcon = registry.registerIcon(Aether.find("misc/keys/ancient_silver_key"));
		this.divinesilverIcon = registry.registerIcon(Aether.find("misc/keys/divine_silver_key"));
		this.mythicsilverIcon = registry.registerIcon(Aether.find("misc/keys/mythic_silver_key"));
		this.ancientgoldenIcon = registry.registerIcon(Aether.find("misc/keys/ancient_golden_key"));
		this.divinegoldenIcon = registry.registerIcon(Aether.find("misc/keys/divine_golden_key"));
		this.mythicgoldenIcon = registry.registerIcon(Aether.find("misc/keys/mythic_golden_key"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return meta == 1 ? this.silverIcon 
		: meta == 2 ? this.goldenIcon 
	    : meta == 3 ? this.ancientbronzeIcon 
		: meta == 4 ? this.divinebronzeIcon 
		: meta == 5 ? this.ancientsilverIcon
		: meta == 6 ? this.divinesilverIcon
		: meta == 7 ? this.ancientgoldenIcon
		: meta == 8 ? this.divinegoldenIcon
		: meta == 9 ? this.mythicbronzeIcon
		: meta == 10 ? this.cobaltIcon 
		: meta == 11 ? this.osmiumIcon
		: meta == 12 ? this.mythicsilverIcon
		: meta == 13 ? this.mythicgoldenIcon
		: meta == 14 ? this.palladiumIcon:this.bronzeIcon;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for (int meta = 0; meta < EnumDungeonKeyType.values().length; ++meta) {
			subItems.add(new ItemStack(this, 1, meta));
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int meta = itemstack.getItemDamage();

		return this.getUnlocalizedName() + "_" + EnumDungeonKeyType.getType(meta).toString();
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityFireProofItemAether(world, location, itemstack);
    }

}