package com.gildedgames.the_aether.api.events.enchantments;

import net.minecraft.tileentity.TileEntity;

import com.gildedgames.the_aether.api.enchantments.AetherAmplifier;

import cpw.mods.fml.common.eventhandler.Event;

public class AetherAmplifierEvent extends Event {

	public AetherAmplifierEvent() {

	}

	public static class SetTimeEvent extends AetherAmplifierEvent {
		private TileEntity tileEntity;

		private AetherAmplifier amplifier;

		private int original;

		private int newTime;

		public SetTimeEvent(TileEntity tileEntity, AetherAmplifier amplifier, int original) {
			this.tileEntity = tileEntity;
			this.amplifier = amplifier;
			this.original = original;

			this.setNewTime(original);
		}

		public TileEntity getTileEntity() {
			return this.tileEntity;
		}

		public AetherAmplifier getAmplifier() {
			return this.amplifier;
		}

		public int getOriginal() {
			return this.original;
		}

		public int getNewTime() {
			return this.newTime;
		}

		public void setNewTime(int newTime) {
			this.newTime = newTime;
		}
	}

	public static class AmplifyEvent extends AetherAmplifierEvent {
		private TileEntity tileEntity;

		private AetherAmplifier amplifier;

		public AmplifyEvent(TileEntity tileEntity, AetherAmplifier amplify) {
			this.tileEntity = tileEntity;
			this.amplifier = amplify;
		}

		public TileEntity getTileEntity() {
			return this.tileEntity;
		}

		public AetherAmplifier getAmplifier() {
			return this.amplifier;
		}
	}

}
