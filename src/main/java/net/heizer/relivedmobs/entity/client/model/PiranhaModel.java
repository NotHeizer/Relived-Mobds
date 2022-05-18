package net.heizer.relivedmobs.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class PiranhaModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "piranha"), "main");

    private final ModelPart root;
    private final ModelPart piranha;
    private final ModelPart fin_r;
    private final ModelPart fin_l;
    private final ModelPart fin_back;

    public PiranhaModel(ModelPart root) {
        //Root
        this.root = root;
        //Piranha
        this.piranha = root.getChild("piranha");
        //Fins
        this.fin_r = piranha.getChild("fin_r");
        this.fin_l = piranha.getChild("fin_l");
        this.fin_back = piranha.getChild("fin_back");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition piranha = partdefinition.addOrReplaceChild("piranha", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(10, 0).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -0.5F));

        PartDefinition fin_r = piranha.addOrReplaceChild("fin_r", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.525F, -1.5F));

        fin_r.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.025F, 0.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition fin_l = piranha.addOrReplaceChild("fin_l", CubeListBuilder.create(), PartPose.offset(1.0F, 0.5F, -1.5F));

        fin_l.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

        piranha.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(10, 8).addBox(0.0F, -2.0F, -2.5F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.5F));

        piranha.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, 0.0F, -4.5F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));

        piranha.addOrReplaceChild("fin_back", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 3.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.piranha.xRot = headPitch * ((float)Math.PI / 180F);
        this.piranha.yRot = headPitch * ((float)Math.PI / 180F);
        if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
            this.piranha.xRot += -0.08F - 0.08F * Mth.cos(ageInTicks * 0.5F);
            this.fin_back.xRot = -0.1F * Mth.cos(ageInTicks * 0.5F);
        }
        this.fin_r.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);
        this.fin_l.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        piranha.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
