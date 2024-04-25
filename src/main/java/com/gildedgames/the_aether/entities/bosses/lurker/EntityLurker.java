package com.gildedgames.the_aether.entities.bosses.lurker;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.entities.util.EntityBossMob;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gildedgames.the_aether.api.player.util.IAetherBoss;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.dungeon.BlockDungeonBaseOsmium;
import com.gildedgames.the_aether.client.gui.dialogue.entity.GuiLurkerDialogue;
import com.gildedgames.the_aether.entities.ai.EntityAIAttackContinuously;
import com.gildedgames.the_aether.entities.ai.valkyrie_queen.ValkyrieQueenAIWander;
import com.gildedgames.the_aether.entities.hostile.EntityUligo;
import com.gildedgames.the_aether.entities.hostile.EntityUro;
import com.gildedgames.the_aether.entities.effects.EffectInebriation;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.entities.projectile.crystals.EntityCrystal;
import com.gildedgames.the_aether.entities.uro.uroswell.EntityAILurkerSwell;
import com.gildedgames.the_aether.entities.util.AetherNameGen;
import com.gildedgames.the_aether.entities.util.EntityAetherItem;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLurker extends EntityBossMob implements IAetherBoss {

    private EntityAIAttackContinuously enhancedCombat = new EntityAIAttackContinuously(this, 0.65D);

    public int angerLevel;

    public int timeLeft, timeUntilTeleport, chatTime, timeUntilTeleportToPlayer;

    public int dungeonX, dungeonY, dungeonZ;

    public int dungeonEntranceZ;

    public double safeX, safeY, safeZ;

    public float sinage;

    public double lastMotionY;
    
    /**
     * Time when this creeper was last in an active state (Messed up code here, probably causes creeper animation to go
     * weird)
     */
    private int lastActiveTime;
    /** The amount of time since the creeper was close enough to the player to ignite */
    private int timeSinceIgnited;
    private int fuseTime = 25;
    /** Explosion radius for this creeper. */
    private int explosionRadius = 3;
    private static final String __OBFID = "CL_00001684";

    public EntityLurker(World world) {
        super(world);

        this.timeUntilTeleport = this.rand.nextInt(250);
        
        this.registerEntityAI();
        this.dataWatcher.updateObject(19, AetherNameGen.valkGen());
        this.safeX = posX;
        this.safeY = posY;
        this.safeZ = posZ;
        addRandomArmor();
    }

    public EntityLurker(World world, double x, double y, double z) {
        this(world);
        this.safeX = posX = x;
        this.safeY = posY = y;
        this.safeZ = posZ = z;
    }

    @Override
    public void entityInit() {
        super.entityInit();

        this.dataWatcher.addObject(20, new Byte((byte) 0));
        this.dataWatcher.addObject(19, AetherNameGen.valkGen());
        this.dataWatcher.addObject(16, Byte.valueOf((byte) - 1));
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    public void registerEntityAI() {
        //this.targetTasks.addTask(0, this.enhancedCombat); 
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAILurkerSwell(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.45D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(22.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(800.0D);
    }
    
    @Override
	protected void addRandomArmor()
	{	
    this.setCurrentItemOrArmor(0, new ItemStack(ItemsAether.tipped_valkyrie_lance));	
	}
    
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * The number of iterations PathFinder.getSafePoint will execute before giving up.
     */
    public int getMaxSafePointTries()
    {
        return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
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
        this.dungeonZ = k - 38;
    }

    private void unlockDoor() {
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ, Blocks.air);
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ + 1, Blocks.air);
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ + 1, Blocks.air);
        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ, Blocks.air);
    }

    private void unlockTreasure() {
        for (int x = this.dungeonX - 54; x < this.dungeonX + 60; x++) {
            for (int y = this.dungeonY - 1; y < this.dungeonY + 44; y++) {
                for (int z = this.dungeonZ - 12; z < this.dungeonZ + 52; z++) {
                    Block block = this.worldObj.getBlock(x, y, z);

                    if (block == BlocksAether.locked_fuse_stone || block == BlocksAether.locked_creeping_stone) {
                        this.worldObj.setBlock(x, y, z, ((BlockDungeonBaseOsmium) block).getUnlockedBlock());
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
    public void displayLurkerDialogue() {
        if (this.worldObj.isRemote) {
            FMLClientHandler.instance().getClient().displayGuiScreen(new GuiLurkerDialogue(this));
        }
    }

    @Override
    public boolean interact(EntityPlayer entityplayer) {
        this.faceEntity(entityplayer, 180F, 180F);

        if (this.isBossReady()) {
            this.chatItUp(entityplayer, StatCollector.translateToLocal("gui.urker.dialog.ready"));
        } else if (this.worldObj.isRemote) {
            this.displayLurkerDialogue();
            
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

                        if (this.timeUntilTeleport++ >= 150) {
                            if (this.onGround && this.rand.nextInt(1) == 0) {
                                this.makeHomeShot(1, target);
                            } else {
                                this.teleport(target.posX, target.posY, target.posZ, 4);
                            }
                        } else if (this.timeUntilTeleport < 146 && (this.posY <= 0D || this.posY <= (this.safeY - 16D))) {
                            this.timeUntilTeleport = 146;
                        } else if ((this.timeUntilTeleport % 5) == 0 && !canEntityBeSeen(target)) {
                            this.timeUntilTeleport += 100;
                        }
                    }
                }
            }

            if (!this.worldObj.isRemote)
            {
                for (int k = 4; k < 46; k += 14)
                {
                    Block state = this.worldObj.getBlock(this.dungeonX - 1, this.dungeonY, this.dungeonZ + k);

                    if (state != BlocksAether.locked_fuse_stone || state != BlocksAether.locked_creeping_stone)
                    {
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonZ + k, BlocksAether.locked_fuse_stone);
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY, this.dungeonZ + k + 1, BlocksAether.locked_fuse_stone);
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonZ + k + 1, BlocksAether.locked_fuse_stone);
                        this.worldObj.setBlock(this.dungeonX - 1, this.dungeonY + 1, this.dungeonZ + k, BlocksAether.locked_fuse_stone);
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
        
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.func_146078_ca())
            {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("creeper.primed", 2.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.func_146077_cc();
            }
        }
        
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
                this.chatItUp((EntityPlayer) this.getEntityToAttack(), StatCollector.translateToLocal("gui.urker.dialog.defeated"));

                ((EntityPlayer) this.getEntityToAttack()).triggerAchievement(AchievementsAether.defeat_osmium);

                PlayerAether.get((EntityPlayer) this.getEntityToAttack()).setFocusedBoss(null);
                
                if (this.getPowered()) {
                ((EntityPlayer) this.getEntityToAttack()).triggerAchievement(AchievementsAether.electrified);

                 PlayerAether.get((EntityPlayer) this.getEntityToAttack()).setFocusedBoss(null);	
                }
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
        
        if (this.dataWatcher.getWatchableObjectByte(17) == 1)
        {
        	nbttagcompound.setBoolean("powered", true);
        }

        nbttagcompound.setShort("Fuse", (short)this.fuseTime);
        nbttagcompound.setByte("ExplosionRadius", (byte)this.explosionRadius);
        nbttagcompound.setBoolean("ignited", this.func_146078_ca());
        
        nbttagcompound.setShort("Anger", (short) angerLevel);
        nbttagcompound.setShort("TimeLeft", (short) timeLeft);
        nbttagcompound.setBoolean("Duel", this.isBossReady());
        nbttagcompound.setInteger("DungeonX", this.dungeonX);
        nbttagcompound.setInteger("DungeonY", this.dungeonY);
        nbttagcompound.setInteger("DungeonZ", this.dungeonZ);
        nbttagcompound.setInteger("DungeonEntranceZ", this.dungeonEntranceZ);
        nbttagcompound.setTag("SafePos", newDoubleNBTList(new double[]{this.safeX, this.safeY, this.safeZ}));
        nbttagcompound.setString("BossName", this.getName());
        
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)(nbttagcompound.getBoolean("powered") ? 1 : 0)));

        if (nbttagcompound.hasKey("Fuse", 99))
        {
            this.fuseTime = nbttagcompound.getShort("Fuse");
        }

        if (nbttagcompound.hasKey("ExplosionRadius", 99))
        {
            this.explosionRadius = nbttagcompound.getByte("ExplosionRadius");
        }

        if (nbttagcompound.getBoolean("ignited"))
        {
            this.func_146079_cb();
        }
        
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
        	
        	if (ds.isExplosion())
            {
                return false;
            }
        	
            EntityPlayer player = (EntityPlayer) ds.getEntity();

            if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
                this.spawnExplosionParticle();
                chatItUp(player, StatCollector.translateToLocal("gui.urker.dialog.peaceful"));
                return false;
            }

            if (!this.isBossReady()) {
                spawnExplosionParticle();
                int pokey = rand.nextInt(2);

                if (pokey == 2) {
                    chatItUp(player, StatCollector.translateToLocal("gui.urker.dialog.peaceful"));
                } else {
                    chatItUp(player, StatCollector.translateToLocal("gui.urker.dialog.nomedals"));
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
                    chatItUp(player, StatCollector.translateToLocal("gui.urker.dialog.fight"));
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
        
        {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        	
        {
            Entity entity = ds.getEntity();

            if (entity instanceof EntityPlayer)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                this.addPotionEffect(new EffectInebriation(Potion.invisibility.id, 30, 0));
                this.addPotionEffect(new EffectInebriation(Potion.moveSpeed.id, 60, 2));
                
             int random1 = (int)(1 + Math.random() * 10);
        	 if(random1 == 1 ) {
                EntityUro uro = new EntityUro(this.worldObj);
                uro.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                uro.setAttackTarget(this.getAttackTarget());

                if (!this.worldObj.isRemote) {
                    this.worldObj.spawnEntityInWorld(uro);
                	}
                
        		}
        	 
        	 int random2 = (int)(1 + Math.random() * 5);
        	 if(random2 == 1 ) {
                EntityUligo uligo = new EntityUligo(this.worldObj);
                uligo.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                uligo.setAttackTarget(this.getAttackTarget());

                if (!this.worldObj.isRemote) {
                    this.worldObj.spawnEntityInWorld(uligo);
                	}
                
        		}
        	 
        	 if (this.getPowered()) {
        		 this.addPotionEffect(new EffectInebriation(Potion.damageBoost.id, 1000, 0)); 
        	 }
        	 
            }
                           	
        	}
        
        }

        return super.attackEntityFrom(ds, i);
    }
    
    /**
     * Returns true if the creeper is powered by a lightning bolt.
     */
    public boolean getPowered()
    {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    /**
     * Params: (Float)Render tick. Returns the intensity of the creeper's flash when it is ignited.
     */
    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float p_70831_1_)
    {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (float)(this.fuseTime - 2);
    }
    
    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getCreeperState()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setCreeperState(int p_70829_1_)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)p_70829_1_));
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    public void onStruckByLightning(EntityLightningBolt p_70077_1_)
    {
        super.onStruckByLightning(p_70077_1_);
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
    }
    
    private void func_146077_cc()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

            if (this.getPowered())
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 3), flag);
            }
            else
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);
            }

            //normally entity is despawned here
        }
    }

    public boolean func_146078_ca()
    {
        return this.dataWatcher.getWatchableObjectByte(18) != 0;
    }

    public void func_146079_cb()
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
    }


    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = false;

        this.swingArm();
        {
        if (!this.getPowered()) {
        flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 8);
        flag = entity.attackEntityFrom(DamageSource.magic, 8);
        }
        else {
        flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 12);
        flag = entity.attackEntityFrom(DamageSource.magic, 14);	
         }
        }
        if (entity != null && this.getEntityToAttack() != null && entity == this.getEntityToAttack() && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            if (player.getHealth() <= 0 || player.isDead) {
                this.setTarget(null);
                this.angerLevel = this.chatTime = 0;
                this.chatItUp(player, StatCollector.translateToLocal("gui.urker.dialog.lost"));
                this.unlockDoor();
            }
        }
          
        ((EntityLivingBase) entity).addPotionEffect(new EffectInebriation(Potion.digSlowdown.id, 100, 2));
        ((EntityLivingBase) entity).addPotionEffect(new EffectInebriation(Potion.moveSlowdown.id, 100, 0));

        return flag;
        
    }

    @Override
    protected void dropFewItems(boolean var1, int var2) {
        this.entityDropItem(new ItemStack(ItemsAether.dungeon_key, 1, 11), 0.5F);
        this.dropItem(ItemsAether.valkyrie_lance, 1);
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
    
    @SideOnly(Side.CLIENT)
    public void onEntityUpdate() {
    	super.onEntityUpdate();
    	if (this.worldObj.isRemote) {
    		int k = MathHelper.floor_double(this.posY);
        	for (k = 0; k < 2; ++k)
            {
            	NewAetherParticleHandler.URKER_FLAME.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);
            }
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

        x += (double) a;
        y += (double) b;
        z += (double) c;

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
        return this.worldObj.checkBlockCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0;
    }

    public int getMedals(EntityPlayer entityplayer) {
        int medals = 0;

        for (ItemStack item : entityplayer.inventory.mainInventory) {
            if (item != null) {
                if (item.getItem() == ItemsAether.osmium_insignia) {
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
        return "mob.creeper.say";
    }

    @Override
    protected String getDeathSound() {
        return "mob.creeper.death";
    }
    
    protected float getSoundVolume() {
		return 1.5F;
	}

    public String getName() {
        return this.dataWatcher.getWatchableObjectString(19);
    }

    @Override
    public String getBossName() {
        return this.dataWatcher.getWatchableObjectString(19) + ", " + StatCollector.translateToLocal("tile.aether_legacy.aer_lurker.name");
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
        this.dataWatcher.updateObject(20, new Byte(isReady ? (byte) 1 : (byte) 0));
    }

    public boolean isBossReady() {
        return this.dataWatcher.getWatchableObjectByte(20) == (byte) 1;
    }

}