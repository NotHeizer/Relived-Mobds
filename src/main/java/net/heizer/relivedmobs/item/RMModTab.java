package net.heizer.relivedmobs.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RMModTab {

    public static final CreativeModeTab TAB = new CreativeModeTab("relived_mobs_tab") {
        @Override
        public ItemStack makeIcon() {
            return new  ItemStack(RMModItems.BELUGA_SPAWN_EGG.get());
        }
    };
}
