package net.heizer.relivedmobs.entity.client.renderer;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.client.model.BlueWhaleModel;
import net.heizer.relivedmobs.entity.custom.BlueWhaleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BlueWhaleRenderer extends MobRenderer<BlueWhaleEntity, BlueWhaleModel<BlueWhaleEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(RMMod.MOD_ID,
            "textures/entity/blue_whale/blue_whale.png");

    public BlueWhaleRenderer(EntityRendererProvider.Context context) {
        super(context, new BlueWhaleModel<>(context.bakeLayer(BlueWhaleModel.LAYER_LOCATION)), 1F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BlueWhaleEntity blue_whale) {
        return TEXTURE;
    }
}