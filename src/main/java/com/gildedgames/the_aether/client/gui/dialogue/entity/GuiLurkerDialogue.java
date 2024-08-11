package com.gildedgames.the_aether.client.gui.dialogue.entity;

import com.gildedgames.the_aether.client.gui.dialogue.DialogueOption;
import com.gildedgames.the_aether.client.gui.dialogue.GuiDialogue;
import com.gildedgames.the_aether.entities.bosses.lurker.EntityLurker;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.network.AetherNetwork;
import com.gildedgames.the_aether.network.packets.PacketInitiateValkyrieFight;

public class GuiLurkerDialogue extends GuiDialogue {

    private EntityLurker urker;   

    private String title;

    private int medalSlotId = -1;

    public GuiLurkerDialogue(EntityLurker urker) {
        super("[\247e" + urker.getName() + ", " + I18n.format("tile.aether_legacy.aer_lurker.name") + "\247r]", new DialogueOption(I18n.format("gui.urker.dialog.0")), new DialogueOption(I18n.format("gui.urker.dialog.1")), new DialogueOption(I18n.format("gui.urker.dialog.2")));

        this.title = this.getDialogue();
        this.urker = urker;
    }

    @Override
    public void dialogueClicked(DialogueOption dialogue) {
        if (this.getDialogueOptions().size() == 3) {
            if (dialogue.getDialogueId() == 0) {
                this.addDialogueMessage(this.title + ": " + I18n.format("gui.urker.dialog.answer.0"));
                this.dialogueTreeCompleted();
            } else if (dialogue.getDialogueId() == 1) {
                DialogueOption medalDialogue = new DialogueOption(this.getMedalDiaulogue());

                this.addDialogueWithOptions(this.title + ": " + I18n.format("gui.urker.dialog.answer.1"), medalDialogue, new DialogueOption(I18n.format("gui.valkyrie.dialog.player.denyfight")));
            } else if (dialogue.getDialogueId() == 2) {
                this.addDialogueMessage(this.title + ": " + I18n.format("gui.urker.dialog.answer.2"));
                this.dialogueTreeCompleted();
            }
        } else {
            if (dialogue.getDialogueId() == 0) {
                if (this.mc.theWorld.difficultySetting == EnumDifficulty.PEACEFUL) {
                    this.addDialogueMessage(this.title + ": " + I18n.format("gui.urker.dialog.peaceful"));
                    this.dialogueTreeCompleted();

                    return;
                }

                if (this.medalSlotId != -1) {
                    AetherNetwork.sendToServer(new PacketInitiateValkyrieFight(this.medalSlotId, this.urker.getEntityId()));

                    this.urker.setBossReady(true);
                    this.addDialogueMessage(this.title + ": " + I18n.format("gui.urker.dialog.ready"));
                    this.dialogueTreeCompleted();
                } else {
                    this.addDialogueMessage(this.title + ": " + I18n.format("gui.urker.dialog.nomedals"));
                    this.dialogueTreeCompleted();
                }
            } else if (dialogue.getDialogueId() == 1) {
                this.addDialogueMessage(this.title + ": " + I18n.format("gui.urker.dialog.answer.2"));
                this.dialogueTreeCompleted();
            }
        }
    }

    private String getMedalDiaulogue() {
        for (int slotId = 0; slotId < this.mc.thePlayer.inventory.mainInventory.length; ++slotId) {
            ItemStack stack = this.mc.thePlayer.inventory.mainInventory[slotId];

            if (stack != null && stack.getItem() == ItemsAether.osmium_insignia) {
                if (stack.stackSize >= 6) {
                    this.medalSlotId = slotId;
                    return I18n.format("gui.valkyrie.dialog.player.havemedals");
                } else {
                    return I18n.format("gui.valkyrie.dialog.player.lackmedals") + " (" + stack.stackSize + "/6)";
                }
            }
        }

        return I18n.format("gui.valkyrie.dialog.player.lackmedals") + " (0/6)";
    }

}
