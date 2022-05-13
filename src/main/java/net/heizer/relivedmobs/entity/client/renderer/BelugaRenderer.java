package net.heizer.relivedmobs.entity.client.renderer;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.client.model.BelugaModel;
import net.heizer.relivedmobs.entity.custom.BelugaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BelugaRenderer extends MobRenderer<BelugaEntity, BelugaModel<BelugaEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(RMMod.MOD_ID,
            "textures/entity/beluga/beluga.png");

    public BelugaRenderer(EntityRendererProvider.Context context) {
        super(context, new BelugaModel<>(context.bakeLayer(BelugaModel.LAYER_LOCATION)), 1F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BelugaEntity beluga) {
        return TEXTURE;
    }
}