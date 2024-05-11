package com.gildedgames.the_aether.network.packets;

import com.gildedgames.the_aether.entities.bosses.lurker.EntityLurker;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityAncientValkyrieQueen;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityDivineValkyrieQueen;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityValkyrieQueen;
import com.gildedgames.the_aether.player.PlayerAether;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketInitiateValkyrieFight extends AetherPacket<PacketInitiateValkyrieFight> {

	public int slotId, entityId;

	public PacketInitiateValkyrieFight() {

	}

	public PacketInitiateValkyrieFight(int slotId, int entityId) {
		this.slotId = slotId;
		this.entityId = entityId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.slotId = buf.readInt();
		this.entityId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.slotId);
		buf.writeInt(this.entityId);
	}

	@Override
	public void handleClient(PacketInitiateValkyrieFight message, EntityPlayer player) {

	}

	@Override
	public void handleServer(PacketInitiateValkyrieFight message, EntityPlayer player) {
		player.inventory.setInventorySlotContents(message.slotId, null);

		Entity entity = player.worldObj.getEntityByID(message.entityId);

		if (entity instanceof EntityValkyrieQueen) {
			((EntityValkyrieQueen) entity).setBossReady(true);
			PlayerAether.get(player).setFocusedBoss((EntityValkyrieQueen) entity);
		}
		
		if (entity instanceof EntityAncientValkyrieQueen) {
			((EntityAncientValkyrieQueen) entity).setBossReady(true);
			PlayerAether.get(player).setFocusedBoss((EntityAncientValkyrieQueen) entity);
		}
		
		if (entity instanceof EntityDivineValkyrieQueen) {
			((EntityDivineValkyrieQueen) entity).setBossReady(true);
			PlayerAether.get(player).setFocusedBoss((EntityDivineValkyrieQueen) entity);
		}
		
		if (entity instanceof EntityLurker) {
			((EntityLurker) entity).setBossReady(true);
			PlayerAether.get(player).setFocusedBoss((EntityLurker) entity);
		}
		
		
	}

}