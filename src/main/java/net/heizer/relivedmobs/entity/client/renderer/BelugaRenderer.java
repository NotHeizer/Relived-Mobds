package net.heizer.relivedmobs.entity.client.renderer;

import net.heizer.relivedmobs.RMMod;
import net.heizer.relivedmobs.entity.client.model.BelugaModel;
import net.heizer.relivedmobs.entity.custom.BelugaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BelugaRenderer extends MobRenderer<BelugaEntity, BelugaModel<BelugaEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(RMMod.MOD_ID,
            "textures/entity/beluga/beluga.png");

    protected static final ResourceLocation BABY = new ResourceLocation(RMMod.MOD_ID,
            "textures/entity/beluga/beluga_baby.png");

    public BelugaRenderer(EntityRendererProvider.Context p_174304_, BelugaModel<BelugaEntity> p_174305_, float p_174306_) {
        super(p_174304_, p_174305_, p_174306_);
    }

    @Override
    public ResourceLocation getTextureLocation(BelugaEntity entity)
    {
        if (entity.isBaby()) {
            return BABY;
        }
        return TEXTURE;
    }
}
