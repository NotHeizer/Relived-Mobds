package net.heizer.relivedmobs.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.heizer.relivedmobs.RMMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SilversideModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RMMod.MOD_ID, "silverside"), "main");

    private final ModelPart silverside;
    private final ModelPart body;
    private final ModelPart tail;

    public SilversideModel(ModelPart root) {
        this.silverside = root.getChild("silverside");
        this.body = silverside.getChild("body");
        this.tail = silverside.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition silverside = partdefinition.addOrReplaceChild("silverside", CubeListBuilder.create(), PartPose.offset(0.0F, 19.5F, -0.5F));

        PartDefinition body = silverside.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.75F, -4.5F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.0F, -1.75F, 0.5F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 3).addBox(0.0F, 1.25F, -0.5F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, -1.0F));

        silverside.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.5F));

        silverside.addOrReplaceChild("fin0", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, -3.0F, -0.1354F, -0.2705F, 0.8533F));

        silverside.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5F, -3.0F, -0.1354F, 0.2705F, -0.8533F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.silverside.xRot = Mth.cos( (limbSwing * 1)) * 2 * limbSwingAmount;
        this.silverside.yRot = Mth.cos((float) (limbSwing * 0.5)) * 2 * limbSwingAmount;
        this.silverside.y = (float) (Mth.cos( (limbSwing * 1)) * 0.8 * limbSwingAmount);
        this.tail.yRot = (Mth.cos( (limbSwing * 1)) * 5 * limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        silverside.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

}
