package net.heizer.relivedmobs.entity.client.renderer;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.RMModEntityTypes;
import net.heizer.relivedmobs.entity.custom.BelugaEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class RMModEntityAttributeHandler {

    @Mod.EventBusSubscriber(modid = RMMod.MOD_ID, bus =Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {

            event.put(RMModEntityTypes.BELUGA.get(), BelugaEntity.setAttributes());
        }
    }
}
