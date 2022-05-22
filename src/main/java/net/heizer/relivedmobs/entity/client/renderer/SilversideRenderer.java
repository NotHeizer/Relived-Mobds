package net.heizer.relivedmobs.entity.client.renderer;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.client.model.SilversideModel;
import net.heizer.relivedmobs.entity.custom.SilversideEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SilversideRenderer extends MobRenderer<SilversideEntity, SilversideModel<SilversideEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(RMMod.MOD_ID,
            "textures/entity/silverside/silverside.png");

    public SilversideRenderer(EntityRendererProvider.Context context) {
        super(context, new SilversideModel<>(context.bakeLayer(SilversideModel.LAYER_LOCATION)), 0.1F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SilversideEntity silverside) {
        return TEXTURE;
    }
}