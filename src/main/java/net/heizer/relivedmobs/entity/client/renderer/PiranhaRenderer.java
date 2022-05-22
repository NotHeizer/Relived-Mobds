package net.heizer.relivedmobs.entity.client.renderer;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.client.model.PiranhaModel;
import net.heizer.relivedmobs.entity.custom.PiranhaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PiranhaRenderer extends MobRenderer<PiranhaEntity, PiranhaModel<PiranhaEntity>> {
    private static final ResourceLocation[] LOCATIONS = new ResourceLocation[] {
            new ResourceLocation(RMMod.MOD_ID, "textures/entity/piranha/piranha.png"),
            new ResourceLocation(RMMod.MOD_ID, "textures/entity/piranha/piranha_black.png"),
            new ResourceLocation(RMMod.MOD_ID, "textures/entity/piranha/piranha_green.png"),
            new ResourceLocation(RMMod.MOD_ID, "textures/entity/piranha/piranha_spottet.png")
    };

    public PiranhaRenderer(EntityRendererProvider.Context context) {
        super(context, new PiranhaModel<>(context.bakeLayer(PiranhaModel.LAYER_LOCATION)), 0.2F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PiranhaEntity piranha) {
        return LOCATIONS[piranha.getVariant()];
    }
}

