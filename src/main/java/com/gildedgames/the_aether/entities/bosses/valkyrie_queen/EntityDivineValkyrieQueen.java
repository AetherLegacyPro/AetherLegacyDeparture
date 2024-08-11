package com.gildedgames.the_aether.entities.bosses.valkyrie_queen;

import java.util.List;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.entities.util.EntityBossMob;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gildedgames.the_aether.api.player.util.IAetherBoss;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.dungeon.BlockDungeonBase;
import com.gildedgames.the_aether.client.gui.dialogue.entity.GuiDivineValkyrieDialogue;
import com.gildedgames.the_aether.entities.ai.EntityAIAttackContinuously;
import com.gildedgames.the_aether.entities.ai.valkyrie_queen.DivineValkyrieQueenAIWander;
import com.gildedgames.the_aether.entities.projectile.crystals.EntityCrystal;
import com.gildedgames.the_aether.entities.util.AetherNameGen;
import com.gildedgames.the_aether.entities.util.EntityAetherItem;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityDivineValkyrieQueen extends EntityBossMob implements IAetherBoss {

    private EntityAIAttackContinuously enhancedCombat = new EntityAIAttackContinuously(this, 0.65D);

    public int angerLevel;

    public int timeLeft, timeUntilTeleport, chatTime, timeUntilTeleportToPlayer;

    public int dungeonX, dungeonY, dungeonZ;

    public int dungeonEntranceZ;

    public double safeX, safeY, safeZ;

    public float sinage;

    public double lastMotionY;

    public EntityDivineValkyrieQueen(World world) {
        super(world);

        this.timeUntilTeleport = this.rand.nextInt(150);

        this.registerEntityAI();
        this.dataWatcher.updateObject(19, AetherNameGen.valkGen());
        this.safeX = posX;
        this.safeY = posY;
        this.safeZ = posZ;
    }

    public EntityDivineValkyrieQueen(World world, double x, double y, double z) {
        this(world);
        this.safeX = posX = x;
        this.safeY = posY = y;
        this.safeZ = posZ = z;
    }

    @Override
    public void entityInit() {
        super.entityInit();

        this.dataWatcher.addObject(18, (byte) 0);
        this.dataWatcher.addObject(19, AetherNameGen.valkGen());
    }

    public void registerEntityAI() {
        this.targetTasks.addTask(0, this.enhancedCombat);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new DivineValkyrieQueenAIWander(this, 0.5D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F, 200.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.47D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1300.0D);
    }

    @Override
    protected boolean isMovementBlocked() {
        return !this.isBossReady();
    }

    @Override
    public void addVelocity(double x, double y, double z) {
        if (this.isBossReady()) {
            super.addVelocity(x, y, z);
        }
    }

    public void swingArm() {
        if (!this.isSwingInProgress) {
            this.isSwingInProgress = true;
        }
    }

    private void becomeAngryAt(EntityLivingBase entity) {
        this.setTarget(entity);

        this.angerLevel = 200 + this.rand.nextInt(200);
    }

    public void setDungeon(int i, int j, int k) {
        this.dungeonX = i;
        this.dungeonY = j;
        this.dungeonZ = k - 19;
    }

    private void unlockDoor() {
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ, Blocks.air);
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ + 1, Blocks.air);
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ + 1, Blocks.air);
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ, Blocks.air);
    }

    private void unlockTreasure() {
        this.worldObj.setBlock(this.dungeonX + 16, dungeonY + 1, dungeonZ + 9, BlocksAether.golden_oak_trapdoor, 3, 2);
        this.worldObj.setBlock(this.dungeonX + 17, dungeonY + 1, dungeonZ + 9, BlocksAether.golden_oak_trapdoor, 2, 2);
        this.worldObj.setBlock(this.dungeonX + 16, dungeonY + 1, dungeonZ + 10, BlocksAether.golden_oak_trapdoor, 3, 2);
        this.worldObj.setBlock(this.dungeonX + 17, dungeonY + 1, dungeonZ + 10, BlocksAether.golden_oak_trapdoor, 2, 2);

        for (int x = this.dungeonX - 27; x < this.dungeonX + 30; x++) {
            for (int y = this.dungeonY - 1; y < this.dungeonY + 22; y++) {
                for (int z = this.dungeonZ - 6; z < this.dungeonZ + 26; z++) {
                    Block block = this.worldObj.getBlock(x, y, z);

                    if (block == BlocksAether.locked_divine_angelic_stone || block == BlocksAether.locked_divine_light_angelic_stone) {
                        this.worldObj.setBlock(x, y, z, ((BlockDungeonBase) block).getUnlockedBlock());
                    }
                }
            }
        }
    }

    private void chatItUp(EntityPlayer player, String s) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();

        if (this.chatTime <= 0) {
            if (side.isClient()) {
                Aether.proxy.sendMessage(player, s);
            }

            this.chatTime = 60;
        }
    }

    public void makeHomeShot(int shots, EntityPlayer player) {
        for (int i = 0; i < shots; i++) {
            EntityCrystal crystal = new EntityCrystal(this.worldObj, this.posX - (this.motionX / 2D), this.posY, this.posZ - (this.motionZ / 2D), player);

            if (!this.worldObj.isRemote) {
                this.worldObj.spawnEntityInWorld(crystal);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void displayDivineValkyrieDialogue() {
        if (this.worldObj.isRemote) {
            FMLClientHandler.instance().getClient().displayGuiScreen(new GuiDivineValkyrieDialogue(this));
        }
    }

    @Override
    public boolean interact(EntityPlayer entityplayer) {
        this.faceEntity(entityplayer, 180F, 180F);

        if (this.isBossReady()) {
            this.chatItUp(entityplayer, StatCollector.translateToLocal("gui.queen.ready"));
        } else if (this.worldObj.isRemote) {
            this.displayDivineValkyrieDialogue();
            
            return true;
        }

        return super.interact(entityplayer);
    }

    @Override
    public void updateEntityActionState() {
        super.updateEntityActionState();

        if (!this.isBossReady()) {
            this.motionY *= .5f;
            this.moveStrafing = this.moveForward = 0;
        } else {
            if (this.getEntityToAttack() != null) {
                if (this.getEntityToAttack() instanceof EntityPlayer) {
                    EntityPlayer target = (EntityPlayer) this.getEntityToAttack();

                    if (target != null) {
                        if (target.posY > this.posY) {
                            timeUntilTeleportToPlayer++;

                            if (timeUntilTeleportToPlayer >= 75 && !this.worldObj.isRemote) {
                                this.teleportToPlayer();
                            }
                        } else {
                            timeUntilTeleportToPlayer = 0;
                        }

                        if (this.timeUntilTeleport++ >= 450) {
                            if (this.onGround && this.rand.nextInt(5) == 0) {
                                this.makeHomeShot(1, target);
                            } else {
                                this.teleport(target.posX, target.posY, target.posZ, 4);
                            }
                        } else if (this.timeUntilTeleport < 446 && (this.posY <= 0D || this.posY <= (this.safeY - 16D))) {
                            this.timeUntilTeleport = 446;
                        } else if ((this.timeUntilTeleport % 5) == 0 && !canEntityBeSeen(target)) {
                            this.timeUntilTeleport += 100;
                        }
                    }
                }
            }

            if (!this.worldObj.isRemote)
            {
                for (int k = 2; k < 23; k += 7)
                {
                    Block state = this.worldObj.getBlock(this.dungeonX - 1, this.dungeonY, this.dungeonZ + k);

                    if (state != BlocksAether.locked_divine_angelic_stone || state != BlocksAether.locked_divine_light_angelic_stone)
                    {
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonZ + k, BlocksAether.locked_divine_angelic_stone);
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonZ + k + 1, BlocksAether.locked_divine_angelic_stone);
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonZ + k + 1, BlocksAether.locked_divine_angelic_stone);
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonZ + k, BlocksAether.locked_divine_angelic_stone);
                        this.dungeonEntranceZ = this.dungeonZ + k;
                    }
                }
            }
        }

        if (this.getEntityToAttack() != null && this.getEntityToAttack().isDead) {
            this.setTarget(null);
            unlockDoor();
            this.angerLevel = 0;
        }

        if (this.chatTime > 0) {
            this.chatTime--;
        }
    }

    @Override
    public void onUpdate() {
        this.lastMotionY = motionY;
        super.onUpdate();

        if (!this.onGround && this.getEntityToAttack() != null && this.lastMotionY >= 0.0D && motionY < 0.0D && getDistanceToEntity(this.getEntityToAttack()) <= 16F && canEntityBeSeen(this.getEntityToAttack())) {
            double a = this.getEntityToAttack().posX - posX;
            double b = this.getEntityToAttack().posZ - posZ;
            double angle = Math.atan2(a, b);
            this.motionX = Math.sin(angle) * 0.25D;
            this.motionZ = Math.cos(angle) * 0.25D;
        }

        if (!this.onGround && !isOnLadder() && Math.abs(this.motionY - this.lastMotionY) > 0.07D && Math.abs(this.motionY - this.lastMotionY) < 0.09D) {
            this.motionY += 0.055F;

            if (this.motionY < -0.275F) {
                this.motionY = -0.275F;
            }
        }

        if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && (this.getEntityToAttack() != null || this.angerLevel > 0)) {
            this.angerLevel = 0;
            this.setTarget(null);
        }

        if (!this.onGround) {
            this.sinage += 0.75F;
        } else {
            this.sinage += 0.15F;
        }

        if (this.sinage > 3.141593F * 2F) {
            this.sinage -= (3.141593F * 2F);
        }

        if (this.getHealth() <= 0 || this.isDead) {
            if (!this.worldObj.isRemote)
            {
                this.unlockDoor();
                this.unlockTreasure();
            }

            if (this.getEntityToAttack() instanceof EntityPlayer) {
                this.chatItUp((EntityPlayer) this.getEntityToAttack(), StatCollector.translateToLocal("gui.queen.defeated"));

                ((EntityPlayer) this.getEntityToAttack()).triggerAchievement(AchievementsAether.defeat_silver);
                ((EntityPlayer) this.getEntityToAttack()).triggerAchievement(AchievementsAether.ancient_defeat_silver);
                ((EntityPlayer) this.getEntityToAttack()).triggerAchievement(AchievementsAether.divine_defeat_silver);
                
                PlayerAether.get((EntityPlayer) this.getEntityToAttack()).setFocusedBoss(null);
            }

            this.spawnExplosionParticle();
            this.setDead();
        }

        if (!otherDimension()) {
            this.timeLeft--;
            if (this.timeLeft <= 0) {
                spawnExplosionParticle();
                this.setDead();
            }
        }
    }

    @Override
    protected Entity findPlayerToAttack() {
        return null;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short) angerLevel);
        nbttagcompound.setShort("TimeLeft", (short) timeLeft);
        nbttagcompound.setBoolean("Duel", this.isBossReady());
        nbttagcompound.setInteger("DungeonX", this.dungeonX);
        nbttagcompound.setInteger("DungeonY", this.dungeonY);
        nbttagcompound.setInteger("DungeonZ", this.dungeonZ);
        nbttagcompound.setInteger("DungeonEntranceZ", this.dungeonEntranceZ);
        nbttagcompound.setTag("SafePos", newDoubleNBTList(this.safeX, this.safeY, this.safeZ));
        nbttagcompound.setString("BossName", this.getName());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.angerLevel = nbttagcompound.getShort("Anger");
        this.timeLeft = nbttagcompound.getShort("TimeLeft");
        this.setBossReady(nbttagcompound.getBoolean("Duel"));
        this.dungeonX = nbttagcompound.getInteger("DungeonX");
        this.dungeonY = nbttagcompound.getInteger("DungeonY");
        this.dungeonZ = nbttagcompound.getInteger("DungeonZ");
        this.dungeonEntranceZ = nbttagcompound.getInteger("DungeonEntranceZ");
        NBTTagList nbttaglist = nbttagcompound.getTagList("SafePos", 10);
        this.setBossName(nbttagcompound.getString("BossName"));

        this.safeX = nbttaglist.func_150309_d(0);
        this.safeY = nbttaglist.func_150309_d(1);
        this.safeZ = nbttaglist.func_150309_d(2);
    }

    @Override
    public boolean attackEntityFrom(DamageSource ds, float i) {
        if (ds.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) ds.getEntity();
            
            int random1 = (int)(1 + Math.random() * 12);
         	if(random1 == 1) {
         		this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ));
         	}

            if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
                this.spawnExplosionParticle();
                chatItUp(player, StatCollector.translateToLocal("gui.queen.peaceful"));
                return false;
            }

            if (!this.isBossReady()) {
                spawnExplosionParticle();
                int pokey = rand.nextInt(2);

                if (pokey == 2) {
                    chatItUp(player, StatCollector.translateToLocal("gui.queen.peaceful"));
                } else {
                    chatItUp(player, StatCollector.translateToLocal("gui.queen.nomedals"));
                }
                return false;
            } else {
                PlayerAether playerAether = PlayerAether.get(player);
                boolean flag;

                if (playerAether != null) {
                    flag = true;

                    if (!player.isDead && flag) {
                        playerAether.setFocusedBoss(this);
                    }

                    if (this.isDead || this.getHealth() <= 0.0F) {
                        playerAether.setFocusedBoss(null);
                    }
                }

                if (this.getEntityToAttack() == null) {
                    this.chatTime = 0;
                    chatItUp(player, StatCollector.translateToLocal("gui.queen.fight"));
                    if (ds.getEntity() instanceof EntityLivingBase)
                        becomeAngryAt((EntityLivingBase) ds.getEntity());
                } else {
                    this.timeUntilTeleport += 60;
                }
            }
        } else {
            extinguish();
            return false;
        }

        return super.attackEntityFrom(ds, i);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = false;

        this.swingArm();
        flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 30);

        if (entity != null && this.getEntityToAttack() != null && entity == this.getEntityToAttack() && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            if (player.getHealth() <= 0 || player.isDead) {
                this.setTarget(null);
                this.angerLevel = this.chatTime = 0;
                this.chatItUp(player, StatCollector.translateToLocal("gui.queen.lost"));
                this.unlockDoor();
            }
        }

        return flag;
    }

    @Override
    protected void dropFewItems(boolean var1, int var2) {
        this.entityDropItem(new ItemStack(ItemsAether.dungeon_key, 1, 6), 0.5F);
        this.entityDropItem(new ItemStack(ItemsAether.dungeon_key, 1, 5), 0.5F);
        this.entityDropItem(new ItemStack(BlocksAether.primeval_artifact), 3 + rand.nextInt(2));
        this.dropItem(Items.golden_sword, 1);
        
        int rand2 = (int)(1 + Math.random() * 3);
		switch (rand2)
        {
        case 1: this.dropItem(ItemsAether.divine_essence, 2 + rand.nextInt(4));
        break;
        case 2: 
        break;
        case 3: 
        break;
        }
    }

    @Override
    public EntityItem entityDropItem(ItemStack stack, float offsetY) {
        if (stack.stackSize != 0 && stack.getItem() != null) {
            EntityAetherItem entityitem = new EntityAetherItem(this.worldObj, this.posX, this.posY + (double) offsetY, this.posZ, stack);

            if (captureDrops)
                this.capturedDrops.add(entityitem);
            else
                this.worldObj.spawnEntityInWorld(entityitem);
            return entityitem;
        } else {
            return null;
        }
    }

    @Override
    public void fall(float distance) {
    }

    public void teleport(double x, double y, double z, int rad) {
        int a = this.rand.nextInt(rad + 1);
        int b = this.rand.nextInt(rad / 2);
        int c = rad - a;

        a *= ((rand.nextInt(2) * 2) - 1); // Negate or Not
        b *= ((rand.nextInt(2) * 2) - 1); // Negate or Not
        c *= ((rand.nextInt(2) * 2) - 1); // Negate or Not

        x += a;
        y += b;
        z += c;

        int newX = (int) Math.floor(x - 0.5D);
        int newY = (int) Math.floor(y - 0.5D);
        int newZ = (int) Math.floor(z - 0.5D);

        boolean flag = false;

        for (int q = 0; q < 32 && !flag; q++) {
            int i = newX + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));
            int j = newY + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));
            int k = newZ + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));

            if (this.isAirySpace(i, j, k) && this.isAirySpace(i, j + 1, k) && !this.isAirySpace(i, j - 1, k) && (i > dungeonX && i < dungeonX + 20 && j > dungeonY && j < dungeonY + 12 && k > dungeonZ && k < dungeonZ + 20)) {
                newX = i;
                newY = j;
                newZ = k;
                flag = true;
            }
        }

        if (!flag) {
            this.timeUntilTeleport -= (this.rand.nextInt(40) + 40);

            if (this.posY <= 0D) {
                this.timeUntilTeleport = 446;
            }
        } else {
            this.spawnExplosionParticle();
            this.enhancedCombat.resetTask();
            this.setPosition((double) newX + 0.5D, (double) newY + 0.5D, (double) newZ + 0.5D);

            this.isJumping = false;
            this.renderYawOffset = this.rand.nextFloat() * 360F;
            this.timeUntilTeleport = this.rand.nextInt(40);

            this.motionX = this.motionY = this.motionZ = this.moveForward = this.moveStrafing = this.rotationPitch = this.rotationYaw = 0;
        }
    }

    public void teleportToPlayer() {
        if (this.getEntityToAttack() instanceof EntityPlayer) {
            this.spawnExplosionParticle();
            this.enhancedCombat.resetTask();
            this.setPosition(this.getEntityToAttack().posX + 0.5D, this.getEntityToAttack().posY + 0.5D, this.getEntityToAttack().posZ + 0.5D);

            this.isJumping = false;
            this.renderYawOffset = this.rand.nextFloat() * 360F;
            this.timeUntilTeleportToPlayer = 0;

            this.motionX = this.motionY = this.motionZ = this.moveForward = this.moveStrafing = this.rotationPitch = this.rotationYaw = 0;
        }
    }

    public boolean isAirySpace(int x, int y, int z) {
        Block block = this.worldObj.getBlock(x, y, z);

        return block == Blocks.air || block.getCollisionBoundingBoxFromPool(this.worldObj, x, y, z) == null;
    }

    public boolean otherDimension() {
        return true;
    }

    public boolean canDespawn() {
        return false;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        return this.worldObj.checkBlockCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty();
    }

    public int getMedals(EntityPlayer entityplayer) {
        int medals = 0;

        for (ItemStack item : entityplayer.inventory.mainInventory) {
            if (item != null) {
                if (item.getItem() == ItemsAether.victory_medal) {
                    medals += item.stackSize;
                }
            }
        }

        return medals;
    }

    public List<?> getPlayersInDungeon() {
        return this.worldObj.getEntitiesWithinAABBExcludingEntity(this.getEntityToAttack(), AxisAlignedBB.getBoundingBox(this.dungeonX, this.dungeonY, this.dungeonZ, this.dungeonX, this.dungeonY, this.dungeonZ).expand(20, 20, 20));
    }

    @Override
    protected String getHurtSound() {
        return "game.player.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "game.player.die";
    }

    public String getName() {
        return this.dataWatcher.getWatchableObjectString(19);
    }

    @Override
    public String getBossName() {
        return this.dataWatcher.getWatchableObjectString(19) + ", " + StatCollector.translateToLocal("title.aether_legacy.valkyrie_queen.name");
    }

    public void setBossName(String name) {
        this.dataWatcher.updateObject(19, name);
    }

    @Override
    public float getBossHealth() {
        return this.getHealth();
    }

    @Override
    public float getMaxBossHealth() {
        return this.getMaxHealth();
    }

    public void setBossReady(boolean isReady) {
        this.dataWatcher.updateObject(18, isReady ? (byte) 1 : (byte) 0);
    }

    public boolean isBossReady() {
        return this.dataWatcher.getWatchableObjectByte(18) == (byte) 1;
    }

}