package net.heizer.relivedmobs.entity.custom;

import net.heizer.relivedmobs.item.RMModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;


public class BelugaEntity extends WaterAnimal {
    public static final int TOTAL_AIR_SUPPLY = 4800;
    private static final int TOTAL_MOISTNESS_LEVEL = 2400;
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(BelugaEntity.class, EntityDataSerializers.INT);

    static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();

    public BelugaEntity(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        xpReward = 2;
        setNoAi(false);
    }
    //--------------------------------------------------------------------------------

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30D)
                .add(Attributes.MOVEMENT_SPEED, 1.5f)
                .add(Attributes.FOLLOW_RANGE, 100D)
                .build();
    }
    //--------------------------------------------------------------------------------

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new BelugaSwimWithPlayerGoal(this, 4.0D));
        this.goalSelector.addGoal(2, new BelugaPlayWithItemsGoal());
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.7D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this ,0.7D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.3D, Ingredient.of(Items.SALMON), false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.3D, Ingredient.of(Items.COD), false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 2D, Ingredient.of(Items.PUFFERFISH), true));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new FollowBoatGoal(this));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.5D, 2.0D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Pufferfish.class, 8.0F, 1.5D, 2.0D));
    }
    //--------------------------------------------------------------------------------

    public boolean canBreatheUnderwater() {
        return false;
    }
    protected void handleAirSupply(int p_28326_) {
    }
    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }
    public void setMoisntessLevel(int p_28344_) {
        this.entityData.set(MOISTNESS_LEVEL, p_28344_);
    }
    public int getMaxAirSupply() {
        return 4800;
    }
    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }
    //--------------------------------------------------------------------------------

    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    //--------------------------------------------------------------------------------

    //Beluga Path Navigation
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    //--------------------------------------------------------------------------------

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

                if (this.onGround) {
                    this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.onGround = false;
                    this.hasImpulse = true;
                }
            }

            if (this.level.isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;

                for(int i = 0; i < 2; ++i) {
                    this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 + (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 + (double)f1, 0.0D, 0.0D, 0.0D);
                    this.level.addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 - (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 - (double)f1, 0.0D, 0.0D, 0.0D);
                }
            }

        }
    }
    //--------------------------------------------------------------------------------

    public void travel(Vec3 p_28383_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), p_28383_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_28383_);
        }

    }
    //--------------------------------------------------------------------------------

    static class BelugaSwimWithPlayerGoal extends Goal {
        private final BelugaEntity beluga;
        private final double speedModifier;
        @Nullable
        private Player player;

        BelugaSwimWithPlayerGoal(BelugaEntity p_28413_, double p_28414_) {
            this.beluga = p_28413_;
            this.speedModifier = p_28414_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            this.player = this.beluga.level.getNearestPlayer(BelugaEntity.SWIM_WITH_PLAYER_TARGETING, this.beluga);
            if (this.player == null) {
                return false;
            } else {
                return this.player.isSwimming() && this.beluga.getTarget() != this.player;
            }
        }

        public boolean canContinueToUse() {
            return this.player != null && this.player.isSwimming() && this.beluga.distanceToSqr(this.player) < 256.0D;
        }

        public void start() {
            this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100), this.beluga);
        }

        public void stop() {
            this.player = null;
            this.beluga.getNavigation().stop();
        }

        public void tick() {
            this.beluga.getLookControl().setLookAt(this.player, (float)(this.beluga.getMaxHeadYRot() + 20), (float)this.beluga.getMaxHeadXRot());
            if (this.beluga.distanceToSqr(this.player) < 6.25D) {
                this.beluga.getNavigation().stop();
            } else {
                this.beluga.getNavigation().moveTo(this.player, this.speedModifier);
            }

            if (this.player.isSwimming() && this.player.level.random.nextInt(6) == 0) {
                this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100), this.beluga);
            }

        }
    }

    //--------------------------------------------------------------------------------

    class BelugaPlayWithItemsGoal extends Goal {
        private int cooldown;

        public boolean canUse() {
            if (this.cooldown > BelugaEntity.this.tickCount) {
                return false;
            } else {
                List<ItemEntity> list = BelugaEntity.this.level.getEntitiesOfClass(ItemEntity.class, BelugaEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Dolphin.ALLOWED_ITEMS);
                return !list.isEmpty() || !BelugaEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        }

        public void start() {
            List<ItemEntity> list = BelugaEntity.this.level.getEntitiesOfClass(ItemEntity.class, BelugaEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Dolphin.ALLOWED_ITEMS);
            if (!list.isEmpty()) {
                BelugaEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
                BelugaEntity.this.playSound(SoundEvents.DOLPHIN_PLAY, 1.0F, 1.0F);
            }

            this.cooldown = 0;
        }

        public void stop() {
            ItemStack itemstack = BelugaEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.drop(itemstack);
                BelugaEntity.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                this.cooldown = BelugaEntity.this.tickCount + BelugaEntity.this.random.nextInt(100);
            }

        }

        public void tick() {
            List<ItemEntity> list = BelugaEntity.this.level.getEntitiesOfClass(ItemEntity.class, BelugaEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Dolphin.ALLOWED_ITEMS);
            ItemStack itemstack = BelugaEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.drop(itemstack);
                BelugaEntity.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (!list.isEmpty()) {
                BelugaEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }

        private void drop(ItemStack p_28429_) {
            if (!p_28429_.isEmpty()) {
                double d0 = BelugaEntity.this.getEyeY() - (double)0.3F;
                ItemEntity itementity = new ItemEntity(BelugaEntity.this.level, BelugaEntity.this.getX(), d0, BelugaEntity.this.getZ(), p_28429_);
                itementity.setPickUpDelay(40);
                itementity.setThrower(BelugaEntity.this.getUUID());
                float f = 0.3F;
                float f1 = BelugaEntity.this.random.nextFloat() * ((float)Math.PI * 2F);
                float f2 = 0.02F * BelugaEntity.this.random.nextFloat();
                itementity.setDeltaMovement((double)(0.3F * -Mth.sin(BelugaEntity.this.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(BelugaEntity.this.getXRot() * ((float)Math.PI / 180F)) + Mth.cos(f1) * f2), (double)(0.3F * Mth.sin(BelugaEntity.this.getXRot() * ((float)Math.PI / 180F)) * 1.5F), (double)(0.3F * Mth.cos(BelugaEntity.this.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(BelugaEntity.this.getXRot() * ((float)Math.PI / 180F)) + Mth.sin(f1) * f2));
                BelugaEntity.this.level.addFreshEntity(itementity);
            }
        }
    }
}
