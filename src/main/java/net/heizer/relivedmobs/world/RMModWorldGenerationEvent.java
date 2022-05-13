package net.heizer.relivedmobs.world;

import net.heizer.relivedmobs.RMMod;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class RMModWorldGenerationEvent {

    @Mod.EventBusSubscriber(modid = RMMod.MOD_ID)
    public static class CreaturesFromTheSnowModWorldEvent {
        @SubscribeEvent
        public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
            RMModEntityGeneration.onEntitySpawn(event);
        }
    }
}
