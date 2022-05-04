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

    private static final ResourceLocation BABY = new ResourceLocation(RMMod.MOD_ID,
            "textures/entity/beluga/beluga_baby.png");

    public BelugaRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new BelugaModel<>(), 3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BelugaEntity entity)
    {
        if (entity.isBaby()) {
            return BABY;
        }
        return TEXTURE;
    }
}