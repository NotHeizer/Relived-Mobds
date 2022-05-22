package net.heizer.relivedmobs;

import com.mojang.logging.LogUtils;
import net.heizer.relivedmobs.entity.RMModEntityTypes;
import net.heizer.relivedmobs.entity.client.model.BelugaModel;
import net.heizer.relivedmobs.entity.client.model.PiranhaModel;
import net.heizer.relivedmobs.entity.client.model.SilversideModel;
import net.heizer.relivedmobs.entity.client.renderer.BelugaRenderer;
import net.heizer.relivedmobs.entity.client.renderer.PiranhaRenderer;
import net.heizer.relivedmobs.entity.client.renderer.SilversideRenderer;
import net.heizer.relivedmobs.entity.custom.BelugaEntity;
import net.heizer.relivedmobs.entity.custom.PiranhaEntity;
import net.heizer.relivedmobs.entity.custom.SilversideEntity;
import net.heizer.relivedmobs.item.RMModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(RMMod.MOD_ID)
public class RMMod {

    public static final String MOD_ID = "relivedmobs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RMMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RMModItems.register(eventBus);
        RMModEntityTypes.register(eventBus);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::registerLayers);
        eventBus.addListener(this::entityAttributeEvent);
        eventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        EntityRenderers.register(RMModEntityTypes.BELUGA.get(), BelugaRenderer::new);
        EntityRenderers.register(RMModEntityTypes.PIRANHA.get(), PiranhaRenderer::new);
        EntityRenderers.register(RMModEntityTypes.SILVERSIDE.get(), SilversideRenderer::new);
    }

    private void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(BelugaModel.LAYER_LOCATION, BelugaModel::createBodyLayer);
        event.registerLayerDefinition(PiranhaModel.LAYER_LOCATION, PiranhaModel::createBodyLayer);
        event.registerLayerDefinition(SilversideModel.LAYER_LOCATION, SilversideModel::createBodyLayer);
    }


    private void setup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {
            SpawnPlacements.register(RMModEntityTypes.BELUGA.get(),
                    SpawnPlacements.Type.IN_WATER,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(RMModEntityTypes.PIRANHA.get(),
                    SpawnPlacements.Type.IN_WATER,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(RMModEntityTypes.SILVERSIDE.get(),
                    SpawnPlacements.Type.IN_WATER,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
    }

    private void entityAttributeEvent(EntityAttributeCreationEvent event) {

        event.put(RMModEntityTypes.BELUGA.get(), BelugaEntity.setAttributes());
        event.put(RMModEntityTypes.PIRANHA.get(), PiranhaEntity.setAttributes());
    }
}
