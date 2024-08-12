package com.gildedgames.the_aether.network.packets;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.compatibility.inventory.InventoryBaubles;
import com.gildedgames.the_aether.inventory.InventoryAccessories;
import com.gildedgames.the_aether.player.PlayerAether;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketAccessory extends AetherPacket<PacketAccessory> {

	private IAccessoryInventory accessories;

	private ByteBuf readBuf;

	private int entityID;

	public PacketAccessory() {

	}

	public PacketAccessory(IPlayerAether player) {
		accessories = player.getAccessoryInventory();
		this.entityID = player.getEntity().getEntityId();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityID = buf.readInt();
		this.readBuf = buf;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityID);
		accessories.writeData(buf);
	}

	@Override
	public void handleClient(PacketAccessory message, EntityPlayer player) {
		if (player != null && player.worldObj != null) {
			EntityPlayer parent = (EntityPlayer) player.worldObj.getEntityByID(message.entityID);

			if (parent != null) {
				PlayerAether playerAether = PlayerAether.get(parent);
				IAccessoryInventory newInventory = AetherConfig.UseBaublesExpandedMenu() ? new InventoryBaubles(playerAether) : new InventoryAccessories(playerAether);

				newInventory.readData(message.readBuf);

				PlayerAether.get(parent).setAccessoryInventory(newInventory);
			}
		}
	}

	@Override
	public void handleServer(PacketAccessory message, EntityPlayer player) {

	}

}
