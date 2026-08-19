package com.gildedgames.the_aether.world.biome;

import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import org.lwjgl.opengl.GL11;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.AetherWorld;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;

@SideOnly(Side.CLIENT)
public class StormySkiesWeatherClientHandler {
    private final Random random = new Random();

    private float previousStormStrength = 0.0F;
    private float currentStormStrength = 0.0F;
    private float previousCloudBankStrength = 0.0F;
    private float currentCloudBankStrength = 0.0F;
    private long lastRainImpactSoundTick = -100L;

    //If I do not disable the GL fog above y=128 this causes the full-screen color/overlay to completely cover the screen in the fog color,
    //making it so you cannot see anything...
    //This was hell to fix, if someone wants to find a way to make it work no matter the height, please do so
    private static final double HIGH_ALTITUDE_CAMERA_CUTOFF_Y = 128.0D;

    //When looking down you can see a fog at y=128 blocking the view of the biome
    private static final double HIGH_ALTITUDE_HAZE_Y = 126.0D;
    private static final int HIGH_ALTITUDE_HAZE_RADIUS = 768;
    private static final int HIGH_ALTITUDE_HAZE_TILE_SIZE = 32;
    private static final int HIGH_ALTITUDE_HAZE_BIOME_PADDING = 128;
    private static final int HIGH_ALTITUDE_HAZE_BIOME_SAMPLE_STEP = 32;
    private static final int HIGH_ALTITUDE_HAZE_LAYERS = 4;
    private static final double HIGH_ALTITUDE_HAZE_LAYER_SPACING = 5.0D;
    private static final float HIGH_ALTITUDE_HAZE_MAX_ALPHA = 0.78F;

    //Controls the high-altitude haze doubles
    private static final double HIGH_ALTITUDE_HAZE_ALPHA_DISTANCE_START = 24.0D;
    private static final double HIGH_ALTITUDE_HAZE_ALPHA_DISTANCE_FULL = 180.0D;
    private static final float HIGH_ALTITUDE_HAZE_DISTANCE_ALPHA_MULTIPLIER = 2.75F;
    private static final float HIGH_ALTITUDE_HAZE_ABSOLUTE_MAX_ALPHA = 0.72F;

    //Cloud wall radius around the Stormy Skies biome.(24 chunks)
    private static final int STORMY_SKIES_CLOUD_BANK_RADIUS = 384;

    //Every 2 chunks closer or further to the biome causes the fog to become thicker and darker
    private static final int STORMY_SKIES_CLOUD_BANK_SAMPLE_STEP = 24;

    //Wind DOUBLES
    private static final double RAIN_WIND_X = 1.35D;
    private static final double RAIN_WIND_Y = -0.28D;
    private static final double RAIN_WIND_Z = 0.40D;

    private static final double RAIN_LINE_MIN_LENGTH = 0.55D;
    private static final double RAIN_LINE_EXTRA_LENGTH = 0.75D;

    private static final int WEST_SHELTER_CHECK_DISTANCE = 18;

    private double getCameraY() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.thePlayer == null) {
            return 0.0D;
        }

        return mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
    }

    private boolean isInAetherDimension() {
        Minecraft mc = Minecraft.getMinecraft();
        return mc != null && mc.theWorld != null && mc.theWorld.provider != null && mc.theWorld.provider.dimensionId == AetherConfig.getAetherDimensionID();
    }

    private void resetStormState() {
        this.previousStormStrength = 0.0F;
        this.currentStormStrength = 0.0F;
        this.previousCloudBankStrength = 0.0F;
        this.currentCloudBankStrength = 0.0F;
        this.lastRainImpactSoundTick = -100L;
    }

    private boolean isCameraHighAltitude() {
        return this.getCameraY() >= HIGH_ALTITUDE_CAMERA_CUTOFF_Y;
    }

    //Makes the Haze appear faster when approaching a stormy skies biome
    private float getHighAltitudeStormCoverageStrength() {
        if (!this.isCameraHighAltitude()) {
            return 0.0F;
        }

        float strength = Math.max(this.currentCloudBankStrength, this.getTargetCloudBankStrength());
        if (this.isPlayerDirectlyInStormySkies() && strength < 0.18F) {
            strength = 0.18F;
        }

        if (strength < 0.0F) {
            strength = 0.0F;
        }

        if (strength > 1.0F) {
            strength = 1.0F;
        }

        return strength;
    }

    //Removes the GL fog above y=128
    //Used above Y=128 so standard global fog cannot blind the whole screen.
    private void applyNoStormFogGL() {
        GL11.glEnable(GL11.GL_FOG);
        GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
        GL11.glFogf(GL11.GL_FOG_START, 100000.0F);
        GL11.glFogf(GL11.GL_FOG_END, 100001.0F);
        GL11.glFogf(GL11.GL_FOG_DENSITY, 0.0F);
    }

    private boolean isStormySkiesBiome(BiomeGenBase biome) {
        return biome != null && AetherWorld.stormy_skies != null && biome.biomeID == AetherWorld.stormy_skies.biomeID;
    }

    private BiomeGenBase getPlayerBiome() {
        if (!this.isInAetherDimension()) {
            return null;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.theWorld == null || mc.thePlayer == null) {
            return null;
        }

        int x = MathHelper.floor_double(mc.thePlayer.posX);
        int z = MathHelper.floor_double(mc.thePlayer.posZ);

        return mc.theWorld.getBiomeGenForCoords(x, z);
    }

    private boolean isPlayerDirectlyInStormySkies() {
        return this.isStormySkiesBiome(this.getPlayerBiome());
    }

    //Controls the rain lines, strength and hiding the sun and moon
    private float getTargetStormStrength() {
        if (!this.isInAetherDimension()) {
            return 0.0F;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.theWorld == null || mc.thePlayer == null) {
            return 0.0F;
        }

        World world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;
        int playerX = MathHelper.floor_double(player.posX);
        int playerZ = MathHelper.floor_double(player.posZ);
        int radius = 96;
        int step = 8;

        float totalWeight = 0.0F;
        float stormWeight = 0.0F;

        for (int dx = -radius; dx <= radius; dx += step) {
            for (int dz = -radius; dz <= radius; dz += step) {
                double dist = Math.sqrt((double)(dx * dx + dz * dz));

                if (dist > radius) {
                    continue;
                }

                float weight = 1.0F - (float)(dist / radius);
                weight = weight * weight;

                int sampleX = playerX + dx;
                int sampleZ = playerZ + dz;
                BiomeGenBase biome = world.getBiomeGenForCoords(sampleX, sampleZ);

                totalWeight += weight;

                if (this.isStormySkiesBiome(biome)) {
                    stormWeight += weight;
                }
            }
        }

        if (totalWeight <= 0.0F) {
            return 0.0F;
        }

        float strength = stormWeight / totalWeight;

        BiomeGenBase centerBiome = world.getBiomeGenForCoords(playerX, playerZ);

        if (this.isStormySkiesBiome(centerBiome) && strength < 0.65F) {
            strength = 0.65F;
        }

        if (strength < 0.0F) {
            strength = 0.0F;
        }

        if (strength > 1.0F) {
            strength = 1.0F;
        }

        strength = this.smootherstep(strength);
        strength = (float)Math.pow(strength, 1.25F);

        return strength;
    }

    //Controls the fog surrounding the biome while standing in another biome
    private float getTargetCloudBankStrength() {
        if (!this.isInAetherDimension()) {
            return 0.0F;
        }

        Minecraft mc = Minecraft.getMinecraft();

        if (mc == null || mc.theWorld == null || mc.thePlayer == null) {
            return 0.0F;
        }

        World world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;
        int playerX = MathHelper.floor_double(player.posX);
        int playerZ = MathHelper.floor_double(player.posZ);
        BiomeGenBase centerBiome = world.getBiomeGenForCoords(playerX, playerZ);

        if (this.isStormySkiesBiome(centerBiome)) {
            return 1.0F;
        }

        int radius = STORMY_SKIES_CLOUD_BANK_RADIUS;
        int step = STORMY_SKIES_CLOUD_BANK_SAMPLE_STEP;

        int bestDistanceSq = radius * radius + 1;

        for (int dx = -radius; dx <= radius; dx += step) {
            for (int dz = -radius; dz <= radius; dz += step) {
                int distanceSq = dx * dx + dz * dz;

                if (distanceSq > radius * radius) {
                    continue;
                }

                if (distanceSq >= bestDistanceSq) {
                    continue;
                }

                int sampleX = playerX + dx;
                int sampleZ = playerZ + dz;

                BiomeGenBase biome = world.getBiomeGenForCoords(sampleX, sampleZ);

                if (this.isStormySkiesBiome(biome)) {
                    bestDistanceSq = distanceSq;
                }
            }
        }

        if (bestDistanceSq > radius * radius) {
            return 0.0F;
        }

        double distance = Math.sqrt((double)bestDistanceSq);

        float strength = 1.0F - (float)(distance / (double)radius);

        if (strength < 0.0F) {
            strength = 0.0F;
        }

        if (strength > 1.0F) {
            strength = 1.0F;
        }

        strength = this.smootherstep(strength);
        strength = (float)Math.pow(strength, 1.35F);

        return strength;
    }

    private void updateStormStrength() {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc == null || mc.theWorld == null || mc.thePlayer == null) {
            this.previousStormStrength = 0.0F;
            this.currentStormStrength = 0.0F;

            this.previousCloudBankStrength = 0.0F;
            this.currentCloudBankStrength = 0.0F;
            return;
        }

        float targetStorm = this.getTargetStormStrength();
        float targetCloudBank = this.getTargetCloudBankStrength();

        this.previousStormStrength = this.currentStormStrength;
        this.previousCloudBankStrength = this.currentCloudBankStrength;

        float stormTransitionSpeed = targetStorm > this.currentStormStrength ? 0.08F : 0.035F;
        this.currentStormStrength += (targetStorm - this.currentStormStrength) * stormTransitionSpeed;

        float cloudTransitionSpeed = targetCloudBank > this.currentCloudBankStrength ? 0.06F : 0.025F;
        this.currentCloudBankStrength += (targetCloudBank - this.currentCloudBankStrength) * cloudTransitionSpeed;

        if (this.currentStormStrength < 0.001F) {
            this.currentStormStrength = 0.0F;
        }

        if (this.currentStormStrength > 0.999F) {
            this.currentStormStrength = 1.0F;
        }

        if (this.currentCloudBankStrength < 0.001F) {
            this.currentCloudBankStrength = 0.0F;
        }

        if (this.currentCloudBankStrength > 0.999F) {
            this.currentCloudBankStrength = 1.0F;
        }
    }

    private float getStormStrength() {
        return this.currentStormStrength;
    }

    private float getStormStrength(float partialTicks) {
        return this.previousStormStrength + (this.currentStormStrength - this.previousStormStrength) * partialTicks;
    }

    //Makes fog strength within the biome the max value
    private float getDirectFogStrength() {
        if (this.isPlayerDirectlyInStormySkies()) {
            return 1.0F;
        }

        //If Stormy Sky biome is nearby start applying the fog surrounding it
        float target = this.getTargetCloudBankStrength();

        return Math.max(this.currentCloudBankStrength, target);
    }

    //Uses the EXP2 Stormy Skies logic below y=128
    private void applyStormFogGL(float strength) {
        if (strength <= 0.01F) {
            return;
        }

        GL11.glEnable(GL11.GL_FOG);
        if (this.isPlayerDirectlyInStormySkies()) {
            float density = this.lerp(0.025F, 0.065F, strength);
            GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP2);
            GL11.glFogf(GL11.GL_FOG_DENSITY, density);
            GL11.glFogf(GL11.GL_FOG_START, 2.0F);
            GL11.glFogf(GL11.GL_FOG_END, this.lerp(72.0F, 22.0F, strength));
        } else {
            float density = this.lerp(0.006F, 0.026F, strength);
            GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP2);
            GL11.glFogf(GL11.GL_FOG_DENSITY, density);
            GL11.glFogf(GL11.GL_FOG_START, this.lerp(256.0F, 48.0F, strength));
            GL11.glFogf(GL11.GL_FOG_END, this.lerp(384.0F, 160.0F, strength));
        }
    }

    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        if (!this.isInAetherDimension()) {
            return;
        }

        if (this.isCameraHighAltitude()) {
            return;
        }

        float strength = this.getDirectFogStrength();

        if (strength <= 0.01F) {
            return;
        }

        if (this.isPlayerDirectlyInStormySkies()) {
            event.red = this.lerp(event.red, 0.08F, strength);
            event.green = this.lerp(event.green, 0.10F, strength);
            event.blue = this.lerp(event.blue, 0.15F, strength);
        } else {
            event.red = this.lerp(event.red, 0.22F, strength);
            event.green = this.lerp(event.green, 0.27F, strength);
            event.blue = this.lerp(event.blue, 0.34F, strength);
        }
    }

    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (!this.isInAetherDimension()) {
            return;
        }

        if (this.isCameraHighAltitude()) {
            event.density = 0.0F;
            event.setCanceled(true);
            this.applyNoStormFogGL();
            return;
        }

        float strength = this.getDirectFogStrength();
        if (strength <= 0.01F) {
            return;
        }

        if (this.isPlayerDirectlyInStormySkies()) {
            event.density = this.lerp(0.025F, 0.065F, strength);
        } else {
            event.density = this.lerp(0.006F, 0.026F, strength);
        }

        event.setCanceled(true);
        this.applyStormFogGL(strength);
    }

    @SubscribeEvent
    public void onRenderFog(EntityViewRenderEvent.RenderFogEvent event) {
        if (!this.isInAetherDimension()) {
            return;
        }

        if (this.isCameraHighAltitude()) {
            this.applyNoStormFogGL();
            return;
        }

        float strength = this.getDirectFogStrength();
        if (strength <= 0.01F) {
            return;
        }

        this.applyStormFogGL(strength);
    }

    private float getHighAltitudeHazeDistanceAlphaMultiplier() {
        double distance = Math.abs(this.getCameraY() - HIGH_ALTITUDE_HAZE_Y);

        if (distance <= HIGH_ALTITUDE_HAZE_ALPHA_DISTANCE_START) {
            return 1.0F;
        }

        if (distance >= HIGH_ALTITUDE_HAZE_ALPHA_DISTANCE_FULL) {
            return HIGH_ALTITUDE_HAZE_DISTANCE_ALPHA_MULTIPLIER;
        }

        float t = (float)((distance - HIGH_ALTITUDE_HAZE_ALPHA_DISTANCE_START) / (HIGH_ALTITUDE_HAZE_ALPHA_DISTANCE_FULL - HIGH_ALTITUDE_HAZE_ALPHA_DISTANCE_START));

        if (t < 0.0F) {
            t = 0.0F;
        }

        if (t > 1.0F) {
            t = 1.0F;
        }

        t = this.smootherstep(t);

        return this.lerp(1.0F, HIGH_ALTITUDE_HAZE_DISTANCE_ALPHA_MULTIPLIER, t);
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc == null || mc.theWorld == null || mc.thePlayer == null || !this.isInAetherDimension()) {
            return;
        }

        float strength = this.getStormStrength(event.partialTicks);
        if (this.isPlayerDirectlyInStormySkies()) {
            strength = 1.0F;
        }

        if (strength > 0.02F) {
            mc.theWorld.setRainStrength(strength);
            mc.theWorld.setThunderStrength(strength * 0.75F);
            this.renderStormyRain(mc, event.partialTicks, strength);
        }

        this.renderHighAltitudeStormHaze(mc, event.partialTicks);
    }

    private boolean shouldRenderHighAltitudeHazeTile(World world, int tileMinX, int tileMinZ, int tileSize, int padding) {
        int minX = tileMinX - padding;
        int maxX = tileMinX + tileSize + padding;
        int minZ = tileMinZ - padding;
        int maxZ = tileMinZ + tileSize + padding;
        int step = HIGH_ALTITUDE_HAZE_BIOME_SAMPLE_STEP;

        for (int x = minX; x <= maxX; x += step) {
            for (int z = minZ; z <= maxZ; z += step) {
                BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
                if (this.isStormySkiesBiome(biome)) {
                    return true;
                }
            }
        }

        return this.isStormySkiesBiome(world.getBiomeGenForCoords(minX, minZ)) || this.isStormySkiesBiome(world.getBiomeGenForCoords(maxX, minZ)) || this.isStormySkiesBiome(world.getBiomeGenForCoords(minX, maxZ)) || this.isStormySkiesBiome(world.getBiomeGenForCoords(maxX, maxZ));
    }

    private void renderHighAltitudeStormHaze(Minecraft mc, float partialTicks) {
        if (!this.isCameraHighAltitude()) {
            return;
        }

        float strength = this.getHighAltitudeStormCoverageStrength();
        if (strength <= 0.02F) {
            return;
        }

        World world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;
        if (world == null || player == null) {
            return;
        }

        double interpX = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double interpY = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double interpZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glTranslated(-interpX, -interpY, -interpZ);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        int radius = HIGH_ALTITUDE_HAZE_RADIUS;
        int tileSize = HIGH_ALTITUDE_HAZE_TILE_SIZE;

        int startX = MathHelper.floor_double((player.posX - radius) / tileSize) * tileSize;
        int endX = MathHelper.floor_double((player.posX + radius) / tileSize) * tileSize;

        int startZ = MathHelper.floor_double((player.posZ - radius) / tileSize) * tileSize;
        int endZ = MathHelper.floor_double((player.posZ + radius) / tileSize) * tileSize;

        float r = this.lerp(0.22F, 0.08F, strength);
        float g = this.lerp(0.26F, 0.11F, strength);
        float b = this.lerp(0.34F, 0.18F, strength);

        for (int layer = 0; layer < HIGH_ALTITUDE_HAZE_LAYERS; layer++) {
            double y = HIGH_ALTITUDE_HAZE_Y + layer * HIGH_ALTITUDE_HAZE_LAYER_SPACING;
            float layerFactor = 1.0F - ((float)layer / (float)HIGH_ALTITUDE_HAZE_LAYERS);
            float distanceAlphaMultiplier = this.getHighAltitudeHazeDistanceAlphaMultiplier();

            float alpha = HIGH_ALTITUDE_HAZE_MAX_ALPHA * strength * layerFactor * distanceAlphaMultiplier;
            if (alpha > HIGH_ALTITUDE_HAZE_ABSOLUTE_MAX_ALPHA) {
                alpha = HIGH_ALTITUDE_HAZE_ABSOLUTE_MAX_ALPHA;
            }

            if (alpha <= 0.01F) {
                continue;
            }

            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(r, g, b, alpha);

            for (int tileX = startX; tileX <= endX; tileX += tileSize) {
                for (int tileZ = startZ; tileZ <= endZ; tileZ += tileSize) {
                    if (!this.shouldRenderHighAltitudeHazeTile(world, tileX, tileZ, tileSize, HIGH_ALTITUDE_HAZE_BIOME_PADDING)) {
                        continue;
                    }

                    double minX = tileX;
                    double maxX = tileX + tileSize;
                    double minZ = tileZ;
                    double maxZ = tileZ + tileSize;

                    tessellator.addVertex(minX, y, minZ);
                    tessellator.addVertex(maxX, y, minZ);
                    tessellator.addVertex(maxX, y, maxZ);
                    tessellator.addVertex(minX, y, maxZ);

                    tessellator.addVertex(minX, y, maxZ);
                    tessellator.addVertex(maxX, y, maxZ);
                    tessellator.addVertex(maxX, y, minZ);
                    tessellator.addVertex(minX, y, minZ);
                }
            }

            tessellator.draw();
        }

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    private void renderStormyRain(Minecraft mc, float partialTicks, float strength) {
        World world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;

        double interpX = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double interpY = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double interpZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glTranslated(-interpX, -interpY, -interpZ);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(1.0F);

        tessellator.startDrawing(GL11.GL_LINES);
        float alpha = 0.10F + 0.45F * strength;
        tessellator.setColorRGBA_F(0.55F, 0.75F, 1.0F, alpha);

        long time = world.getTotalWorldTime();
        this.random.setSeed(time / 2L);
        double drift = (time + partialTicks) * 0.14D;
        int rainLines = (int)(60 + 320 * strength);

        for (int i = 0; i < rainLines; i++) {
            double x = player.posX + this.random.nextDouble() * 64.0D - 32.0D;
            double y = player.posY + this.random.nextDouble() * 40.0D - 10.0D;
            double z = player.posZ + this.random.nextDouble() * 64.0D - 32.0D;

            int biomeX = MathHelper.floor_double(x);
            int biomeZ = MathHelper.floor_double(z);
            BiomeGenBase biome = world.getBiomeGenForCoords(biomeX, biomeZ);

            if (!this.isStormySkiesBiome(biome)) {
                if (this.random.nextFloat() > strength * 0.45F) {
                    continue;
                }
            }

            x += (drift * RAIN_WIND_X) % 12.0D;
            z += (drift * RAIN_WIND_Z) % 12.0D;

            double length = RAIN_LINE_MIN_LENGTH + this.random.nextDouble() * RAIN_LINE_EXTRA_LENGTH;
            double endX = x + RAIN_WIND_X * length;
            double endY = y + RAIN_WIND_Y * length;
            double endZ = z + RAIN_WIND_Z * length;

            if (!this.isRainPositionExposedFromWest(world, x, y, z, WEST_SHELTER_CHECK_DISTANCE)) {
                continue;
            }

            if (!this.isRainPositionExposedToWind(world, x, y, z, RAIN_WIND_X, RAIN_WIND_Y, RAIN_WIND_Z)) {
                continue;
            }

            if (!this.isRainStreakClear(world, x, y, z, endX, endY, endZ)) {
                continue;
            }

            tessellator.addVertex(x, y, z);
            tessellator.addVertex(endX, endY, endZ);
        }

        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.theWorld == null || mc.thePlayer == null) {
            this.resetStormState();
            return;
        }

        if (!this.isInAetherDimension()) {
            this.resetStormState();
            return;
        }

        this.updateStormStrength();

        float strength = this.getStormStrength();
        if (this.isPlayerDirectlyInStormySkies()) {
            strength = 1.0F;
            this.currentStormStrength = Math.max(this.currentStormStrength, 0.85F);
        }

        mc.theWorld.setRainStrength(strength);
        mc.theWorld.setThunderStrength(strength * 0.75F);

        this.playStormyRainSound(mc, strength);
        if (strength <= 0.05F) {
            return;
        }

        this.spawnWestSideRainParticles(mc, strength);
        this.spawnTopRainParticles(mc, strength);
    }

    private void playStormyRainSound(Minecraft mc, float strength) {
        if (strength <= 0.08F) {
            return;
        }

        if (mc == null || mc.theWorld == null || mc.thePlayer == null) {
            return;
        }

        World world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;

        long time = world.getTotalWorldTime();
        int interval = this.isPlayerDirectlyInStormySkies() ? 10 : 24;

        if (time - this.lastRainImpactSoundTick < interval) {
            return;
        }

        if (this.random.nextFloat() > strength) {
            return;
        }

        int[] pos = this.findRainSoundImpactPosition(world, player);

        if (pos == null) {
            world.playSound(player.posX, player.posY, player.posZ, "ambient.weather.rain", this.lerp(0.05F, 0.25F, strength), 0.85F + this.random.nextFloat() * 0.25F, false);
            this.lastRainImpactSoundTick = time;
            return;
        }

        int x = pos[0];
        int y = pos[1];
        int z = pos[2];
        float volume = this.lerp(0.08F, 0.35F, strength);
        float pitch = 0.75F + this.random.nextFloat() * 0.30F;
        world.playSound(x + 0.5D, y + 1.0D, z + 0.5D, "ambient.weather.rain", volume, pitch, false);
        this.lastRainImpactSoundTick = time;
    }

    private int[] findRainSoundImpactPosition(World world, EntityClientPlayerMP player) {
        for (int attempt = 0; attempt < 12; attempt++) {
            int x = MathHelper.floor_double(player.posX + this.random.nextDouble() * 32.0D - 16.0D);
            int z = MathHelper.floor_double(player.posZ + this.random.nextDouble() * 32.0D - 16.0D);

            BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
            if (!this.isStormySkiesBiome(biome)) {
                continue;
            }

            int startY = MathHelper.floor_double(player.posY + 24.0D);
            int minY = MathHelper.floor_double(player.posY - 24.0D);

            if (startY > 255) {
                startY = 255;
            }

            if (minY < 1) {
                minY = 1;
            }

            for (int y = startY; y >= minY; y--) {
                Block block = world.getBlock(x, y, z);

                if (!this.canRainHitTop(block)) {
                    continue;
                }

                Block above = world.getBlock(x, y + 1, z);
                if (!this.isOpenForRainParticle(above)) {
                    continue;
                }

                if (!this.canSeeSkyForRain(world, x, y + 1, z)) {
                    continue;
                }

                return new int[] {x, y, z};
            }
        }

        return null;
    }

    private void spawnWestSideRainParticles(Minecraft mc, float strength) {
        World world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;

        int attempts = (int)(40 + 160 * strength);
        for (int i = 0; i < attempts; i++) {
            int blockX = MathHelper.floor_double(player.posX + this.random.nextDouble() * 48.0D - 24.0D);
            int blockZ = MathHelper.floor_double(player.posZ + this.random.nextDouble() * 48.0D - 24.0D);
            BiomeGenBase biome = world.getBiomeGenForCoords(blockX, blockZ);

            if (!this.isStormySkiesBiome(biome)) {
                if (this.random.nextFloat() > strength * 0.45F) {
                    continue;
                }
            }

            int startY = MathHelper.floor_double(player.posY + 18.0D);
            int minY = MathHelper.floor_double(player.posY - 18.0D);

            if (startY > 255) {
                startY = 255;
            }

            if (minY < 1) {
                minY = 1;
            }

            for (int blockY = startY; blockY >= minY; blockY--) {
                Block block = world.getBlock(blockX, blockY, blockZ);
                if (!this.canRainHitSide(block)) {
                    continue;
                }

                Block westBlock = world.getBlock(blockX - 1, blockY, blockZ);
                if (!this.isOpenForRainParticle(westBlock)) {
                    continue;
                }

                double particleX = blockX - 0.02D;
                double particleY = blockY + this.random.nextDouble();
                double particleZ = blockZ + this.random.nextDouble();

                if (!this.isRainPositionExposedFromWest(world, particleX, particleY, particleZ, WEST_SHELTER_CHECK_DISTANCE)) {
                    continue;
                }

                if (!this.isRainPositionExposedToWind(world, particleX, particleY, particleZ, RAIN_WIND_X, RAIN_WIND_Y, RAIN_WIND_Z)) {
                    continue;
                }

                double motionX = -0.04D - this.random.nextDouble() * 0.03D;
                double motionY = -0.008D - this.random.nextDouble() * 0.015D;
                double motionZ = (this.random.nextDouble() - 0.5D) * 0.018D;

                world.spawnParticle("splash", particleX, particleY, particleZ, motionX, motionY, motionZ);

                break;
            }
        }
    }

    private void spawnTopRainParticles(Minecraft mc, float strength) {
        World world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;

        int attempts = (int)(30 + 130 * strength);
        for (int i = 0; i < attempts; i++) {
            int x = MathHelper.floor_double(player.posX + this.random.nextDouble() * 48.0D - 24.0D);
            int z = MathHelper.floor_double(player.posZ + this.random.nextDouble() * 48.0D - 24.0D);

            BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
            if (!this.isStormySkiesBiome(biome)) {
                if (this.random.nextFloat() > strength * 0.45F) {
                    continue;
                }
            }

            int startY = MathHelper.floor_double(player.posY + 24.0D);
            int minY = MathHelper.floor_double(player.posY - 24.0D);

            if (startY > 255) {
                startY = 255;
            }

            if (minY < 1) {
                minY = 1;
            }

            for (int y = startY; y >= minY; y--) {
                Block block = world.getBlock(x, y, z);
                if (!this.canRainHitTop(block)) {
                    continue;
                }

                Block above = world.getBlock(x, y + 1, z);
                if (!this.isOpenForRainParticle(above)) {
                    continue;
                }

                if (!this.canSeeSkyForRain(world, x, y + 1, z)) {
                    continue;
                }

                double particleX = x + this.random.nextDouble();
                double particleY = y + 1.01D;
                double particleZ = z + this.random.nextDouble();

                double motionX = (this.random.nextDouble() - 0.5D) * 0.035D;
                double motionY = 0.015D + this.random.nextDouble() * 0.025D;
                double motionZ = (this.random.nextDouble() - 0.5D) * 0.035D;
                world.spawnParticle("splash", particleX, particleY, particleZ, motionX, motionY, motionZ);

                break;
            }
        }
    }

    private boolean isStormRainBlockingBlock(Block block) {
        if (block == null || block == Blocks.air) {
            return false;
        }

        return block == BlocksAether.aercloud || block == BlocksAether.stratos_aercloud || block == BlocksAether.storm_aercloud
            || block == BlocksAether.holystone || block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt
            || block == BlocksAether.arctic_grass || block == BlocksAether.divine_grass || block == BlocksAether.enchanted_aether_grass
            || block.isOpaqueCube() || block.renderAsNormalBlock() || block.getMaterial().isSolid();
    }

    private boolean isRainPositionExposedFromWest(World world, double x, double y, double z, int maxDistance) {
        int startX = MathHelper.floor_double(x);
        int blockY = MathHelper.floor_double(y);
        int blockZ = MathHelper.floor_double(z);
        if (blockY <= 0 || blockY >= 256) {
            return false;
        }

        for (int distance = 1; distance <= maxDistance; distance++) {
            int checkX = startX - distance;
            for (int dy = -1; dy <= 1; dy++) {

                int checkY = blockY + dy;
                if (checkY <= 0 || checkY >= 256) {
                    continue;
                }

                Block block = world.getBlock(checkX, checkY, blockZ);
                if (this.isStormRainBlockingBlock(block)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isRainPositionExposedToWind(World world, double x, double y, double z, double windX, double windY, double windZ) {
        double length = Math.sqrt(windX * windX + windY * windY + windZ * windZ);

        if (length <= 0.0001D) {
            return true;
        }

        double nx = windX / length;
        double ny = windY / length;
        double nz = windZ / length;
        double maxCheckDistance = 16.0D;
        double step = 0.50D;

        for (double d = 0.75D; d <= maxCheckDistance; d += step) {
            int blockX = MathHelper.floor_double(x - nx * d);
            int blockY = MathHelper.floor_double(y - ny * d);
            int blockZ = MathHelper.floor_double(z - nz * d);

            if (blockY <= 0 || blockY >= 256) {
                continue;
            }

            Block block = world.getBlock(blockX, blockY, blockZ);
            if (this.isStormRainBlockingBlock(block)) {
                return false;
            }
        }

        return true;
    }

    private boolean isRainStreakClear(World world, double x1, double y1, double z1, double x2, double y2, double z2) {
        int checks = 4;

        for (int i = 1; i <= checks; i++) {
            double t = (double)i / (double)checks;
            double x = x1 + (x2 - x1) * t;
            double y = y1 + (y2 - y1) * t;
            double z = z1 + (z2 - z1) * t;

            int blockX = MathHelper.floor_double(x);
            int blockY = MathHelper.floor_double(y);
            int blockZ = MathHelper.floor_double(z);
            if (blockY <= 0 || blockY >= 256) {
                continue;
            }

            Block block = world.getBlock(blockX, blockY, blockZ);
            if (this.isStormRainBlockingBlock(block)) {
                return false;
            }
        }

        return true;
    }

    private boolean canRainHitSide(Block block) {
        if (block == null || block == Blocks.air) {
            return false;
        }

        return block == BlocksAether.aercloud || block == BlocksAether.stratos_aercloud || block == BlocksAether.storm_aercloud || block == BlocksAether.holystone
            || block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == BlocksAether.arctic_grass
            || block == BlocksAether.divine_grass || block == BlocksAether.enchanted_aether_grass || block.isOpaqueCube() || block.renderAsNormalBlock();
    }

    private boolean canRainHitTop(Block block) {
        if (block == null || block == Blocks.air) {
            return false;
        }

        return block == BlocksAether.aercloud || block == BlocksAether.stratos_aercloud || block == BlocksAether.storm_aercloud || block == BlocksAether.holystone
            || block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == BlocksAether.arctic_grass || block == BlocksAether.divine_grass
            || block == BlocksAether.enchanted_aether_grass || block.isOpaqueCube() || block.renderAsNormalBlock() || block.getMaterial().isSolid();
    }

    private boolean canSeeSkyForRain(World world, int x, int y, int z) {
        if (y <= 0 || y >= 256) {
            return false;
        }

        if (world.canBlockSeeTheSky(x, y, z)) {
            return true;
        }

        for (int scanY = y + 1; scanY < 256; scanY++) {
            Block block = world.getBlock(x, scanY, z);
            if (this.isStormRainBlockingBlock(block)) {
                return false;
            }
        }

        return true;
    }

    private boolean isOpenForRainParticle(Block block) {
        return block == null || block == Blocks.air;
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
        if (!this.isInAetherDimension()) {
            return;
        }

        if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        if (this.isCameraHighAltitude()) {
            return;
        }

        float strength = this.isPlayerDirectlyInStormySkies() ? 1.0F : this.getDirectFogStrength();
        if (strength <= 0.02F) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.theWorld == null || mc.thePlayer == null) {
            return;
        }

        this.renderStormSkyVeil(mc, strength);
    }

    private void renderStormSkyVeil(Minecraft mc, float strength) {
        if (!this.isInAetherDimension()) {
            return;
        }

        if (this.isCameraHighAltitude()) {
            return;
        }

        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        Tessellator tessellator = Tessellator.instance;

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float baseAlpha = 0.25F + 0.45F * strength;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(0.04F, 0.06F, 0.09F, baseAlpha);
        tessellator.addVertex(0, height, -90);
        tessellator.addVertex(width, height, -90);
        tessellator.addVertex(width, 0, -90);
        tessellator.addVertex(0, 0, -90);
        tessellator.draw();

        float topAlpha = 0.30F + 0.45F * strength;
        float midAlpha = 0.08F + 0.18F * strength;
        int fadeY = (int)(height * 0.65F);

        tessellator.startDrawingQuads();

        tessellator.setColorRGBA_F(0.02F, 0.035F, 0.06F, midAlpha);
        tessellator.addVertex(0, fadeY, -91);
        tessellator.addVertex(width, fadeY, -91);

        tessellator.setColorRGBA_F(0.02F, 0.035F, 0.06F, topAlpha);
        tessellator.addVertex(width, 0, -91);
        tessellator.addVertex(0, 0, -91);

        tessellator.draw();

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }

    private float lerp(float a, float b, float t) {
        if (t < 0.0F) {
            t = 0.0F;
        }

        if (t > 1.0F) {
            t = 1.0F;
        }

        return a + (b - a) * t;
    }

    private float smootherstep(float t) {
        if (t < 0.0F) {
            t = 0.0F;
        }

        if (t > 1.0F) {
            t = 1.0F;
        }

        return t * t * t * (t * (t * 6.0F - 15.0F) + 10.0F);
    }
}
