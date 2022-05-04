package net.heizer.relivedmobs;

import com.mojang.logging.LogUtils;
import net.heizer.relivedmobs.entity.RMModEntityTypes;
import net.heizer.relivedmobs.entity.client.renderer.BelugaRenderer;
import net.heizer.relivedmobs.entity.custom.BelugaEntity;
import net.heizer.relivedmobs.item.RMModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

        //register Classes here
        RMModItems.register(eventBus);
        RMModEntityTypes.register(eventBus);

        eventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        //register Custom Classes here
        EntityRenderers.register(RMModEntityTypes.BELUGA.get(), BelugaRenderer::new);
    }

    @SubscribeEvent
    public static void onAttributesRegistered(EntityAttributeCreationEvent event) {

        //register Attribute creation event here
    }
}
