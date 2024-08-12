package com.gildedgames.the_aether.items.accessories;

import java.util.List;

import baubles.api.expanded.IBaubleExpanded;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.accessories.DegradationRate;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.client.ClientProxy;
import com.gildedgames.the_aether.compatibility.BaublesExpandedCompatibility;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import cpw.mods.fml.common.Optional;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.Interface(iface = "baubles.api.expanded.IBaubleExpanded", modid = "Baubles|Expanded")
public class ItemAccessory extends Item implements IBaubleExpanded {

	public final AccessoryType accessoryType, extraType;

	public ResourceLocation texture, texture_inactive;

	private int colorHex = 0xdddddd;
	private EnumRarity rarity;
	private DegradationRate degradationRate = DegradationRate.NEVER;

	private String[] baubleTypes;

	public static final IBehaviorDispenseItem DISPENSER_BEHAVIOR = new BehaviorDefaultDispenseItem() {
		protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
			ItemStack itemstack = ItemAccessory.dispenseAccessory(source, stack);
			return itemstack != null ? itemstack : super.dispenseStack(source, stack);
		}
	};

	public ItemAccessory(AccessoryType type) {
		accessoryType = type;
		extraType = type == AccessoryType.RING ? AccessoryType.EXTRA_RING : type == AccessoryType.MISC ? AccessoryType.EXTRA_MISC : null;
		texture = Aether.locate("textures/armor/accessory_base.png");

		if(AetherConfig.UseBaublesExpandedMenu()) {
			baubleTypes = new String[] { BaublesExpandedCompatibility.accessoryTypeToBaubleType(accessoryType) };
		} else {
			baubleTypes = new String[0];
		}

		setMaxStackSize(1);
		setCreativeTab(AetherCreativeTabs.accessories);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, DISPENSER_BEHAVIOR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registry) {
		super.registerIcons(registry);

		ObjectIntIdentityMap orderedList = AccessoryType.createCompleteList();

		for (int i = 0; i < ClientProxy.ACCESSORY_ICONS.length; ++i) {
			ClientProxy.ACCESSORY_ICONS[i] = registry.registerIcon(Aether.find("slots/" + ((AccessoryType) orderedList.func_148745_a(i)).getDisplayName()));
		}
	}

	public static ItemStack dispenseAccessory(IBlockSource blockSource, ItemStack stack) {
		final EnumFacing enumfacing = BlockDispenser.func_149937_b(blockSource.getBlockMetadata());
		final int x = blockSource.getXInt() + enumfacing.getFrontOffsetX();
		final int y = blockSource.getYInt() + enumfacing.getFrontOffsetY();
		final int z = blockSource.getZInt() + enumfacing.getFrontOffsetZ();
		final AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
		final List<EntityPlayer> list = blockSource.getWorld().getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
		if (list.isEmpty()) {
			return null;
		}

		ItemStack itemstack = stack.copy();
		itemstack.stackSize = 1;
		PlayerAether playerAether = PlayerAether.get(list.get(0));

		if (!playerAether.getAccessoryInventory().setAccessorySlot(itemstack)) {
			return null;
		}
		--stack.stackSize;
		return stack;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player) {
		if(AetherConfig.UseBaublesExpandedMenu()) {
			return baubles.api.expanded.BaubleItemHelper.onBaubleRightClick(stack, worldIn, player);
		} else {
			ItemStack heldItem = player.getHeldItem();
			if (heldItem != null && PlayerAether.get(player).getAccessoryInventory().setAccessorySlot(heldItem.copy())) {
				--heldItem.stackSize;
				return heldItem;
			}
		}
		return super.onItemRightClick(stack, worldIn, player);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return (repair.getItem() == ItemsAether.zanite_gemstone && toRepair.getItem() == ItemsAether.zanite_ring)
				|| (repair.getItem() == ItemsAether.zanite_gemstone && toRepair.getItem() == ItemsAether.zanite_pendant)
				|| (repair.getItem() == ItemsAether.valkyrie_ingot && toRepair.getItem() == ItemsAether.valkyrie_ring)
				|| (repair.getItem() == Items.diamond && toRepair.getItem() == ItemsAether.diamond_pendant)
				|| (repair.getItem() == Items.diamond && toRepair.getItem() == ItemsAether.diamond_ring);
	}

	public AccessoryType getExtraType() {
		return extraType;
	}

	public AccessoryType getType() {
		return accessoryType;
	}

	public ItemAccessory setColor(int color) {
		colorHex = color;
		return this;
	}

	public int getColor() {
		return colorHex;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return rarity != null ? rarity : super.getRarity(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int meta) {
		return colorHex;
	}

	public ItemAccessory setDungeonLoot() {
		rarity = ItemsAether.aether_loot;
		return this;
	}
	
	public ItemAccessory setReinforcedDungeonLoot() {
		rarity = ItemsAether.scaled_aether_loot;
		return this;
	}
	
	public ItemAccessory setAmplifiedDungeonLoot() {
		rarity = ItemsAether.divine_aether_loot;
		return this;
	}
	
	public ItemAccessory setPoweredDungeonLoot() {
		rarity = ItemsAether.powered;
		return this;
	}

	public ItemAccessory setTexture(String location) {
		texture = Aether.locate("textures/armor/accessory_" + location + ".png");
		return this;
	}

	public ItemAccessory setInactiveTexture(String location) {
		texture_inactive = new ResourceLocation("aether_legacy", "textures/armor/accessory_" + location + ".png");
		return this;
	}

	public boolean hasInactiveTexture() {
		return texture_inactive != null;
	}

	public ItemAccessory setDegradationRate(DegradationRate degradationRate) {
		this.degradationRate = degradationRate;
		return this;
	}

	public DegradationRate getDegradationRate() {
		return degradationRate;
	}


	//Baubles support

	@SuppressWarnings({"unchecked", "rawtypes"})
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean debug) {
		if(AetherConfig.UseBaublesExpandedMenu()) {
			baubles.api.expanded.BaubleItemHelper.addSlotInformation(tooltip, getBaubleTypes(stack));
		}
	}

	public ItemAccessory setBaubleTypes(String[] types) {
		baubleTypes = types;
		return this;
	}

	public String[] getBaubleTypes() {
		return baubleTypes;
	}

	@Override
	public String[] getBaubleTypes(ItemStack itemstack) {
		return baubleTypes;
	}

	@Override
	@Optional.Method(modid = "Baubles|Expanded")
	public baubles.api.BaubleType getBaubleType(ItemStack itemstack) {
		return BaublesExpandedCompatibility.accessoryTypeToLegacyBaubleType(accessoryType);
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {

	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

}
