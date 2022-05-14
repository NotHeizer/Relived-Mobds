package net.heizer.relivedmobs.world;

import net.heizer.relivedmobs.RMMod;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RMMod.MOD_ID)
public class RMModWorldGenerationEvent {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        RMModEntityGeneration.onEntitySpawn(event);
    }
}

