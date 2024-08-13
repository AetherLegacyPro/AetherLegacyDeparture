package com.gildedgames.the_aether.blocks.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCrystallizedGenesisStone extends BlockLog {

	public BlockCrystallizedGenesisStone() {
		super();

		this.setHardness(6.5F);
		this.setResistance(25.0F);
		this.setLightLevel(0.3375F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		this.field_150167_a = new IIcon[1];
		this.field_150166_b = new IIcon[1];

		for (int i = 0; i < this.field_150167_a.length; ++i) {
			this.field_150167_a[i] = registry.registerIcon(this.getTextureName() + "_side");
			this.field_150166_b[i] = registry.registerIcon(this.getTextureName() + "_top");
		}
	}

}
