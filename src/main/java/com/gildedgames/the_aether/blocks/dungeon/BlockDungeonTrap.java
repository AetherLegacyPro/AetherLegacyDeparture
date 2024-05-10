package com.gildedgames.the_aether.blocks.dungeon;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.EntityEliteValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityValkyrie;
import com.gildedgames.the_aether.entities.hostile.EntityBattleSentry;
import com.gildedgames.the_aether.entities.hostile.EntityCockatrice;
import com.gildedgames.the_aether.entities.hostile.EntitySentry;
import com.gildedgames.the_aether.entities.hostile.EntityUro;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockDungeonTrap extends Block {

	private Block pickBlock;

	public BlockDungeonTrap(Block pickBlock) {
		super(Material.rock);

		this.pickBlock = pickBlock;
		this.setHardness(this.pickBlock != null ? -1F : -1F);
		
		if (pickBlock != null) {
			this.pickBlock = pickBlock;
			this.setResistance(6000000.0F);
		}
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entityIn) {
		if (entityIn instanceof EntityPlayer) {
			world.setBlock(x, y, z, this.pickBlock);

			if (this == BlocksAether.carved_trap) {
				EntitySentry sentry = new EntitySentry(world, x + 2D, y + 1D, z + 2D);

				if (!world.isRemote) {
					world.spawnEntityInWorld(sentry);
				}
			} else if (this == BlocksAether.divine_carved_trap) {
				EntityBattleSentry battle_sentry = new EntityBattleSentry(world);
				battle_sentry.setPosition(x + 0.5D, y + 1D, z + 0.5D);

					if (!world.isRemote) {
						world.spawnEntityInWorld(battle_sentry);		
				}			
			} else if (this == BlocksAether.angelic_trap) {
				EntityValkyrie valkyrie = new EntityValkyrie(world);
				valkyrie.setPosition(x + 0.5D, y + 1D, z + 0.5D);

				if (!world.isRemote) {
					world.spawnEntityInWorld(valkyrie);
				}
			} else if (this == BlocksAether.ancient_angelic_trap) {
				EntityEliteValkyrie valkyrie = new EntityEliteValkyrie(world);
				valkyrie.setPosition(x + 0.5D, y + 1D, z + 0.5D);

				if (!world.isRemote) {
					world.spawnEntityInWorld(valkyrie);
				}
			} else if (this == BlocksAether.divine_angelic_trap) {
					EntityEliteValkyrie valkyrie = new EntityEliteValkyrie(world);
					valkyrie.setPosition(x + 0.5D, y + 1D, z + 0.5D);

					if (!world.isRemote) {
						world.spawnEntityInWorld(valkyrie);
					}
				
			} else if (this == BlocksAether.hellfire_trap) {
				EntityFireMinion minion = new EntityFireMinion(world);
				minion.setPosition(x + 0.5D, y + 1D, z + 0.5D);

				if (!world.isRemote) {
					world.spawnEntityInWorld(minion);
				}
			} else if (this == BlocksAether.genesis_trap) {
				EntityCockatrice minion = new EntityCockatrice(world);
				minion.setPosition(x + 0.5D, y + 1D, z + 0.5D);

				if (!world.isRemote) {
					world.spawnEntityInWorld(minion);
				}			
			} else if (this == BlocksAether.fuse_trap) {
				EntityUro minion = new EntityUro(world);
				minion.setPosition(x + 0.5D, y + 1D, z + 0.5D);

				if (!world.isRemote) {
					world.spawnEntityInWorld(minion);
				}
			}

			world.playSoundEffect(x, y, z, "random.door_close", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
		}
	}

}