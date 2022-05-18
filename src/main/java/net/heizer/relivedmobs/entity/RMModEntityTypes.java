package net.heizer.relivedmobs.entity;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.custom.BelugaEntity;
import net.heizer.relivedmobs.entity.custom.PiranhaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RMModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, RMMod.MOD_ID);

    public static final RegistryObject<EntityType<BelugaEntity>> BELUGA = ENTITY_TYPES.register("beluga",
            () -> EntityType.Builder.of(BelugaEntity::new, MobCategory.WATER_CREATURE)
                    .sized(1.3f, 1f)
                    .build(new ResourceLocation(RMMod.MOD_ID, "beluga").toString()));

    public static final RegistryObject<EntityType<PiranhaEntity>> PIRANHA = ENTITY_TYPES.register("piranha",
            () -> EntityType.Builder.of(PiranhaEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.2f, 0.2f)
                    .build(new ResourceLocation(RMMod.MOD_ID, "piranha").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}