package net.heizer.relivedmobs.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class BlueWhaleEntity extends WaterAnimal {
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(BlueWhaleEntity.class, EntityDataSerializers.INT);
    public static final int TOTAL_AIR_SUPPLY = 4800;
    private static final int TOTAL_MOISTNESS_LEVEL = 2400;

    public BlueWhaleEntity(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        xpReward = 5;
        setNoAi(false);
    }
    //--------------------------------------------------------------------------------

    //Blue Whale Attributes
    public static AttributeSupplier setAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120D)
                .add(Attributes.MOVEMENT_SPEED, 0.7f)
                .add(Attributes.FOLLOW_RANGE, 64D)
                .build();
    }
    //--------------------------------------------------------------------------------

    //Blue Whale Goals
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 0.7D, 10));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.7D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this ,0.7D));
    }
    //--------------------------------------------------------------------------------

    //Breath Underwater|Air Supply|Moistness
    public boolean canBreatheUnderwater() {
        return false;
    }
    protected void handleAirSupply(int p_28326_) {
    }
    public int getMaxAirSupply() {
        return 4800;
    }
    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }
    public void setMoisntessLevel(int moisntessLevel) {
        this.entityData.set(MOISTNESS_LEVEL, moisntessLevel);
    }
    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS_LEVEL, 2400);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Moistness", this.getMoistnessLevel());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setMoisntessLevel(pCompound.getInt("Moistness"));
    }



    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }


    //--------------------------------------------------------------------------------

    //Blue Whale Path Navigation
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }
    //--------------------------------------------------------------------------------

    //Blue Whale Sounds
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.DOLPHIN_HURT;
    }
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }
    @Nullable
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundEvents.DOLPHIN_AMBIENT_WATER : SoundEvents.DOLPHIN_AMBIENT;
    }
    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.DOLPHIN_SPLASH;
    }
    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }
    //--------------------------------------------------------------------------------

    //Blue Whale Dry out
    public void tick() {
        super.tick();
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(2400);
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(DamageSource.DRY_OUT, 1.0F);
                }

            }

        }
    }
    //--------------------------------------------------------------------------------

    //Blue Whale Travel
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }
}
