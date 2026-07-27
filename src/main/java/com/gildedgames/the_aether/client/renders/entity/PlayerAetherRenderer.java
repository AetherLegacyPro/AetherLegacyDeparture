package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.client.models.attachments.ModelAetherWings;
import com.gildedgames.the_aether.client.models.attachments.ModelAgilityBoots;
import com.gildedgames.the_aether.client.models.attachments.ModelHalo;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.items.armor.ItemAetherArmor;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.player.perks.AetherRankings;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

public class PlayerAetherRenderer {

    private static final ResourceLocation TEXTURE_VALKYRIE = Aether.locate("textures/other/wings.png");
    private static final ResourceLocation TEXTURE_VALKYRIE_RING = Aether.locate("textures/other/wings_ring.png");
    private static final ResourceLocation TEXTURE_HALO = Aether.locate("textures/other/halo.png");
    private static final ResourceLocation TEXTURE_AGILITY_WINGS = Aether.locate("textures/other/agility_wings.png");

    private final Minecraft mc;

    private final ModelHalo modelHalo = new ModelHalo();
    public final ModelBiped modelMisc = new ModelBiped(1.0F);
    public final ModelBiped modelGlow = new ModelBiped(0.7F);
    private final ModelAetherWings modelWings = new ModelAetherWings(1.0F);
    private final ModelAgilityBoots modelAgilityBoots = new ModelAgilityBoots();

    private boolean isCapeRendering;

    private static final PlayerAetherRenderer instance = new PlayerAetherRenderer();

    public PlayerAetherRenderer() {
        mc = Minecraft.getMinecraft();
    }

    // -----------------------------------------------------------------------
    // Armor rendering (unchanged)
    // -----------------------------------------------------------------------

    public int renderAetherArmor(PlayerAether playerAether, RenderPlayer renderPlayer, ItemStack stack, int slotType) {
        if (stack != null) {
            Item item = stack.getItem();

            if (item instanceof ItemAetherArmor itemArmor) {

                mc.getTextureManager()
                    .bindTexture(RenderBiped.getArmorResource(playerAether.getEntity(), stack, slotType, null));
                ModelBiped modelbiped = slotType == 2 ? renderPlayer.modelArmor : renderPlayer.modelArmorChestplate;
                modelbiped.bipedHead.showModel = slotType == 0;
                modelbiped.bipedHeadwear.showModel = slotType == 0;
                modelbiped.bipedBody.showModel = slotType == 1 || slotType == 2;
                modelbiped.bipedRightArm.showModel = slotType == 1;
                modelbiped.bipedLeftArm.showModel = slotType == 1;
                modelbiped.bipedRightLeg.showModel = slotType == 2 || slotType == 3;
                modelbiped.bipedLeftLeg.showModel = slotType == 2 || slotType == 3;
                modelbiped = net.minecraftforge.client.ForgeHooksClient
                    .getArmorModel(playerAether.getEntity(), stack, slotType, modelbiped);
                renderPlayer.setRenderPassModel(modelbiped);
                modelbiped.onGround = renderPlayer.modelBipedMain.onGround;
                modelbiped.isRiding = renderPlayer.modelBipedMain.isRiding;
                modelbiped.isChild = renderPlayer.modelBipedMain.isChild;

                int j = itemArmor.getColorFromItemStack(stack, 0);
                if (j != -1) {
                    GL11.glColor3f(
                        (float) (j >> 16 & 255) / 255.0F,
                        (float) (j >> 8 & 255) / 255.0F,
                        (float) (j & 255) / 255.0F);
                }

                return stack.isItemEnchanted() ? 15 : 1;
            }
        }
        return -1;
    }

    // -----------------------------------------------------------------------
    // Accessory rendering — called from RenderPlayerEvent.Specials.Pre
    //
    // At this point RendererLivingEntity has already pushed the full player
    // transform (renderLivingAt, rotateCorpse, glScalef(-1,-1,1), foot
    // translate). We must NOT redo any of those transforms — we simply
    // compute animation values, drive the models, and render.
    // This is the same approach used by Junction's BaubleRenderHandler and
    // LayerBetterElytra, and is the only approach that stays synced in
    // third-person view under all conditions (riding, sneaking, death spin,
    // Dinnerbone flip, etc.).
    // -----------------------------------------------------------------------

    public void renderAccessories(PlayerAether playerAether, RenderPlayer renderer, float partialTicks) {
        EntityPlayer player = playerAether.getEntity();

        // --- Animation values (identical to Junction's BaubleRenderHandler) ---

        float limbSwingAmount = player.prevLimbSwingAmount
            + (player.limbSwingAmount - player.prevLimbSwingAmount) * partialTicks;
        float limbSwing = player.limbSwing - player.limbSwingAmount * (1.0F - partialTicks);

        if (player.isChild()) limbSwing *= 3.0F;
        if (limbSwingAmount > 1.0F) limbSwingAmount = 1.0F;

        float ageInTicks = (float) player.ticksExisted + partialTicks;

        // netHeadYaw: head yaw relative to body yaw, with correct angle wrapping.
        float netHeadYaw = interpolateRotation(player.prevRotationYawHead, player.rotationYawHead, partialTicks)
            - interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, partialTicks);
        float headPitch = interpolateRotation(player.prevRotationPitch, player.rotationPitch, partialTicks);

        // --- Model state flags ---

        ItemStack heldItem = player.getCurrentEquippedItem();
        int heldItemRight = heldItem != null ? 1 : 0;
        boolean aimedBow = false;

        if (heldItem != null && player.getItemInUseCount() > 0) {
            EnumAction action = heldItem.getItemUseAction();
            if (action == EnumAction.block) heldItemRight = 3;
            else if (action == EnumAction.bow) aimedBow = true;
        }

        boolean isSneak = player.isSneaking();
        boolean isRiding = player.isRiding();
        boolean isChild = player.isChild();
        float onGround = player.getSwingProgress(partialTicks);

        modelMisc.heldItemRight = modelWings.heldItemRight = modelGlow.heldItemRight = heldItemRight;
        modelMisc.heldItemRight = modelAgilityBoots.heldItemRight = heldItemRight;
        modelMisc.aimedBow = modelWings.aimedBow = modelGlow.aimedBow = aimedBow;
        modelMisc.aimedBow = modelAgilityBoots.aimedBow = aimedBow;
        modelMisc.isSneak = modelWings.isSneak = modelGlow.isSneak = isSneak;
        modelMisc.isSneak = modelAgilityBoots.isSneak = isSneak;
        modelMisc.isRiding = modelWings.isRiding = modelGlow.isRiding = isRiding;
        modelMisc.isRiding = modelAgilityBoots.isRiding = isRiding;
        modelMisc.isChild = modelWings.isChild = modelGlow.isChild = isChild;
        modelMisc.isChild = modelAgilityBoots.isChild = isChild;
        modelMisc.onGround = modelWings.onGround = modelGlow.onGround = onGround;
        modelMisc.onGround = modelAgilityBoots.onGround = onGround;

        // setLivingAnimations drives any tick-based bone state
        modelMisc.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);
        modelWings.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);
        modelHalo.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);
        modelGlow.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);
        modelAgilityBoots.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);

        // Render everything in the existing (already-correct) matrix space
        renderInExistingSpace(
            playerAether,
            player,
            limbSwing,
            limbSwingAmount,
            ageInTicks,
            netHeadYaw,
            headPitch,
            partialTicks);

        // Reset mutable flags
        modelMisc.aimedBow = modelWings.aimedBow = modelGlow.aimedBow = false;
        modelMisc.isSneak = modelWings.isSneak = modelGlow.isSneak = false;
        modelMisc.heldItemRight = modelWings.heldItemRight = modelGlow.heldItemRight = 0;
        modelMisc.aimedBow = modelAgilityBoots.aimedBow = false;
        modelMisc.isSneak = modelAgilityBoots.isSneak = false;
        modelMisc.heldItemRight = modelAgilityBoots.heldItemRight = 0;
    }

    /**
     * All rendering happens here, inside the player's already-established GL
     * matrix. No renderLivingAt / rotateCorpse / glScalef / glTranslatef
     * setup is performed — that coordinate system is inherited from
     * RendererLivingEntity.
     */
    private void renderInExistingSpace(PlayerAether playerAether, EntityPlayer player, float limbSwing,
                                       float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks) {

        final float scale = 0.0625F;
        IAccessoryInventory inv = playerAether.getAccessoryInventory();

        GL11.glPushMatrix();
        GL11.glColor3f(1.0F, 1.0F, 1.0F);

        // Drive rotation angles for every model
        modelMisc.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, player);
        modelWings.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, player);
        modelHalo.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, player);
        modelGlow.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, player);
        modelAgilityBoots
            .setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, player);

        renderPendant(inv.getFirstStackIfWearing(AccessoryType.PENDANT), player, scale);
        renderCape(
            inv.getFirstStackIfWearing(AccessoryType.CAPE),
            player,
            scale,
            playerAether.shouldRenderCape,
            partialTicks);

        GL11.glScalef(0.9375F, 0.9375F, 0.9375F);
        renderGloves(inv.getFirstStackIfWearing(AccessoryType.GLOVES), player, scale);
        renderShield(
            inv.getFirstStackIfWearing(AccessoryType.SHIELD),
            player,
            limbSwing,
            limbSwingAmount,
            ageInTicks,
            netHeadYaw,
            headPitch,
            scale);

        // --- Valkyrie wings ---
        if (inv.wearingAccessory(ItemsAether.amplified_valkyrie_ring)) {
            renderWings(player, scale, playerAether.wingSinage);
            player.triggerAchievement(AchievementsAether.balanced_flight);
        } else if (inv.isWearingAmplifiedValkyrieRingAndAmplifiedArmor()) {
            renderWings(player, scale, playerAether.wingSinage);
            player.triggerAchievement(AchievementsAether.not_balanced_flight);
        } else if (inv.isWearingValkyrieRing() || inv.wearingAccessory(ItemsAether.false_wings)
            || inv.isWearingValkyrieSet()
            || inv.isWearingAmplifiedValkyrieSet()
            || inv.isWearingValkyrieComboSet()
            || inv.isWearingAscensiteSet()) {
            renderWings(player, scale, playerAether.wingSinage);
        }

        // --- Agility boot wings ---
        // These must not rotate with the body yaw, so a small contained local
        // correction is applied — but no outer transform is reconstructed.
        if (inv.isWearingAgilityBoots() || inv.isWearingAmplifiedAgilityBoots()) {
            float localYaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks
                - (player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks);

            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glRotatef(localYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(headPitch, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.121875F, 0.0F);
            GL11.glRotatef(-headPitch, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-localYaw, 0.0F, 1.0F, 0.0F);
            mc.getTextureManager()
                .bindTexture(TEXTURE_AGILITY_WINGS);
            modelAgilityBoots.renderWings(scale);
            GL11.glPopMatrix();
        }

        // --- Halo ---
        if (AetherRankings.isRankedPlayer(player.getUniqueID()) && PlayerAether.get(player).shouldRenderHalo
            && !player.isInvisible()) {
            float localYaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks
                - (player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks);

            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glRotatef(localYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(headPitch, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.65F, 0.0F);
            GL11.glRotatef(-headPitch, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-localYaw, 0.0F, 1.0F, 0.0F);
            mc.getTextureManager()
                .bindTexture(TEXTURE_HALO);
            modelHalo.renderHalo(scale);
            GL11.glPopMatrix();
        }

        // --- Developer glow ---
        if (player.getUniqueID()
            .toString()
            .equals("cf51ef47-04a8-439a-aa41-47d871b0b837")
            || AetherRankings.isDeveloper(player.getUniqueID()) && playerAether.shouldRenderGlow
            && !player.isInvisible()) {
            mc.getTextureManager()
                .bindTexture(((AbstractClientPlayer) player).getLocationSkin());
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            modelGlow.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GL11.glDisable(GL11.GL_NORMALIZE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        }

        GL11.glPopMatrix();
    }

    // -----------------------------------------------------------------------
    // Individual accessory renderers (logic unchanged; partialTicks now local)
    // -----------------------------------------------------------------------

    private void renderPendant(ItemStack pendantStack, EntityPlayer player, float scale) {
        if (pendantStack == null) return;
        ItemAccessory pendant = (ItemAccessory) pendantStack.getItem();
        mc.getTextureManager()
            .bindTexture(pendant.texture);

        int colour = pendant.getColorFromItemStack(pendantStack, 0);
        if (player.hurtTime > 0) {
            GL11.glColor3f(1.0F, 0.5F, 0.5F);
        } else {
            GL11.glColor3f(((colour >> 16) & 0xff) / 255F, ((colour >> 8) & 0xff) / 255F, (colour & 0xff) / 255F);
        }
        modelMisc.bipedBody.render(scale);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }

    private void renderCape(ItemStack capeStack, EntityPlayer player, float scale, boolean shouldRenderCape,
                            float partialTicks) {
        if (capeStack == null) return;
        isCapeRendering = true;
        ItemAccessory cape = (ItemAccessory) capeStack.getItem();
        if (!shouldRenderCape || player.isInvisible() || cape == ItemsAether.invisibility_cape) return;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 0.125F);

        double d0 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * partialTicks
            - (player.prevPosX + (player.posX - player.prevPosX) * partialTicks);
        double d1 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * partialTicks
            - (player.prevPosY + (player.posY - player.prevPosY) * partialTicks);
        double d2 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * partialTicks
            - (player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks);

        float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
        double d3 = MathHelper.sin(f * (float) Math.PI / 180.0F);
        double d4 = -MathHelper.cos(f * (float) Math.PI / 180.0F);
        float f1 = MathHelper.clamp_float((float) d1 * 10.0F, -6.0F, 32.0F);
        float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
        float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;
        if (f2 < 0.0F) f2 = 0.0F;

        float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
        f1 += MathHelper.sin(
            (player.prevDistanceWalkedModified
                + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F)
            * 32.0F
            * f4;
        if (player.isSneaking()) f1 += 25.0F;

        GL11.glRotatef(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);

        int colour = cape.getColorFromItemStack(capeStack, 0);
        if (player.hurtTime > 0) {
            GL11.glColor3f(1.0F, 0.5F, 0.5F);
        } else {
            GL11.glColor3f(((colour >> 16) & 0xff) / 255F, ((colour >> 8) & 0xff) / 255F, (colour & 0xff) / 255F);
        }
        mc.getTextureManager()
            .bindTexture(cape.texture);
        GL11.glTranslatef(0.0F, 0.015625F, -0.0625F);
        GL11.glScalef(0.8F, 0.9375F, 0.234375F);
        modelMisc.renderCloak(scale);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    private void renderGloves(ItemStack glovesStack, EntityPlayer player, float scale) {
        if (glovesStack == null) return;
        ItemAccessory gloves = (ItemAccessory) glovesStack.getItem();
        mc.getTextureManager()
            .bindTexture(gloves.texture);

        int colour = gloves.getColorFromItemStack(glovesStack, 0);
        if (player.hurtTime > 0) {
            GL11.glColor3f(1.0F, 0.5F, 0.5F);
        } else if (gloves != ItemsAether.phoenix_gloves) {
            GL11.glColor3f(((colour >> 16) & 0xff) / 255F, ((colour >> 8) & 0xff) / 255F, (colour & 0xff) / 255F);
        }
        modelMisc.bipedLeftArm.render(scale);
        modelMisc.bipedRightArm.render(scale);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }

    private void renderShield(ItemStack shieldStack, EntityPlayer player, float limbSwing, float limbSwingAmount,
                              float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (shieldStack == null) return;
        ItemAccessory shield = (ItemAccessory) shieldStack.getItem();
        boolean isIdle = player.motionX == 0.0 && (player.motionY == -0.0784000015258789 || player.motionY == 0.0)
            && player.motionZ == 0.0;
        mc.getTextureManager()
            .bindTexture(isIdle && shield.hasInactiveTexture() ? shield.texture : shield.texture_inactive);

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glScalef(1.125F, 1.125F, 1.2F);
        GL11.glColor4f(
            1.0F,
            player.hurtResistantTime > 0 ? 0.5F : 1.0F,
            player.hurtResistantTime > 0 ? 0.5F : 1.0F,
            1.0F);
        modelGlow.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    private void renderWings(EntityPlayer player, float scale, float wingSinage) {
        mc.getTextureManager()
            .bindTexture(TEXTURE_VALKYRIE);
        modelWings.setWingSinage(wingSinage);
        modelWings.wingLeft.render(scale);
        modelWings.wingRight.render(scale);
        if (player.hurtResistantTime > 0) {
            GL11.glColor3f(1.0F, 0.5F, 0.5F);
        } else {
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
        }
    }

    // -----------------------------------------------------------------------
    // Utility
    // -----------------------------------------------------------------------

    /**
     * Interpolates between two angles with correct wrapping.
     * Matches Junction's implementation exactly.
     */
    private static float interpolateRotation(float prev, float current, float partialTicks) {
        float delta = current - prev;
        while (delta < -180.0F) delta += 360.0F;
        while (delta >= 180.0F) delta -= 360.0F;
        return prev + partialTicks * delta;
    }

    public boolean isCapeRendering() {
        return isCapeRendering;
    }

    public static PlayerAetherRenderer instance() {
        return instance;
    }
}
