package com.gildedgames.the_aether;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * Aether IFMLLoadingPlugin implementation, for early mod loading (coremod). We don't do any coremod things, but we also
 * implement IEarlyMixinLoader, which asks us for a list of early mixins to apply. We are basically piggybacking off of
 * FML's early coremod loading for our own purposes of applying early mixins.
 * <ul>
 * <li>Early mixins are those which modify Minecraft or Forge</li>
 * <li>Late mixins are those which modify other mods</li>
 * </ul>
 */
@IFMLLoadingPlugin.Name("Aether Legacy Departure")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class AetherLoadingPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader {

	@Override
	public String getMixinConfig() {
		return "mixins.aether_legacy.early.json";
	}

	@Override
	public List<String> getMixins(Set<String> loadedCoreMods) {
		List<String> mixins = new ArrayList<>();

		// mixins are resolved according to "package" property in mixins.the_aether.early.json
		mixins.add("minecraft.MixinRenderPlayer_AetherGloves");

		return mixins;
	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
