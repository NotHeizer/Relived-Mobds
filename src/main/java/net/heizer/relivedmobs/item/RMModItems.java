package net.heizer.relivedmobs.item;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.RMModEntityTypes;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RMModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RMMod.MOD_ID);


    public static final RegistryObject<Item> BELUGA_SPAWN_EGG = ITEMS.register("beluga_spawn_egg",
            () -> new ForgeSpawnEggItem(RMModEntityTypes.BELUGA,247242225, 220203167,
                    new Item.Properties().tab(RMModTab.RELIVED_MOBS)));


    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
