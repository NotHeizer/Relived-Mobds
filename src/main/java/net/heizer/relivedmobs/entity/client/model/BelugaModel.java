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


public class BelugaModel<T extends Entity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RMMod.MOD_ID, "beluga"), "main");

    //Beluga Body
    private final ModelPart beluga;
    private final ModelPart root;
    //Tail
    private final ModelPart tail;
    private final ModelPart back_fin;
    //Fins
    private final ModelPart fin0;
    private final ModelPart fin1;


    public BelugaModel(ModelPart root) {
        //Root
        this.root = root;
        //Beluga
        this.beluga = root.getChild("beluga");
        //Tail
        this.tail = beluga.getChild("tail");
        this.back_fin = tail.getChild("back_fin");
        //Fin
        this.fin0 = beluga.getChild("fin0");
        this.fin1 = beluga.getChild("fin1");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition beluga = partdefinition.addOrReplaceChild("Beluga", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 1.0F));

        PartDefinition body = beluga.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -10.0F, -13.0F, 15.0F, 16.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 1.0F));

        body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(54, 0).addBox(-5.5F, -6.0F, -11.0F, 11.0F, 12.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -13.0F));

        PartDefinition tail = beluga.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 40).addBox(-5.5F, -5.0F, 0.0F, 11.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

        tail.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(38, 40).addBox(-8.5F, -1.5F, -3.0F, 17.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 15.0F));

        PartDefinition fin0 = beluga.addOrReplaceChild("fin0", CubeListBuilder.create().texOffs(47, 65).addBox(-1.0F, -1.0F, -3.5F, 11.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.9F, 4.0F, -6.5F, 0.1129F, -0.4842F, 0.7118F));

        PartDefinition fin1 = beluga.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(54, 56).mirror().addBox(-10.0F, -1.0F, -3.5F, 11.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.9F, 4.0F, -6.5F, 0.1129F, 0.4842F, -0.7118F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.beluga.xRot = headPitch * ((float)Math.PI / 180F);
        this.beluga.yRot = headPitch * ((float)Math.PI / 180F);
        if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
            this.beluga.xRot += -0.08F - 0.08F * Mth.cos(ageInTicks * 0.5F);
            this.tail.xRot = -0.1F * Mth.cos(ageInTicks * 0.5F);
            this.back_fin.xRot = -0.3F * Mth.cos(ageInTicks * 0.5F);
        }
        this.fin0.zRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * -0.3 * limbSwingAmount);
        this.fin1.zRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        beluga.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}