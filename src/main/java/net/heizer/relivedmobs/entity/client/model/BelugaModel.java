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


public class BelugaModel<T extends Entity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("RMMod.MOD_ID", "beluga"), "main");

    //Beluga Body
    private final ModelPart root;
    private final ModelPart Beluga;
    //Beluga Fins
    private final ModelPart fin0;
    private final ModelPart fin1;
    //Beluga Tail
    private final ModelPart tail;
    private final ModelPart back_fin;


    public BelugaModel(ModelPart root) {
        //root
        this.root = root;
        //beluga
        this.Beluga = root.getChild("Beluga");
        //Beluga Fins
        this.fin0 = Beluga.getChild("fin0");
        this.fin1 = Beluga.getChild("fin1");
        //Beluga Tail
        this.tail = Beluga.getChild("tail");
        this.back_fin = Beluga.getChild("back_fin");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Beluga = partdefinition.addOrReplaceChild("Beluga", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 1.0F));

        PartDefinition body = Beluga.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -10.0F, -13.0F, 15.0F, 16.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 1.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(54, 0).addBox(-5.5F, -6.0F, -11.0F, 11.0F, 12.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -13.0F));

        PartDefinition tail = Beluga.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 40).addBox(-5.5F, -5.0F, 0.0F, 11.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

        PartDefinition back_fin = tail.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(38, 40).addBox(-8.5F, -1.5F, -3.0F, 17.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 15.0F));

        PartDefinition fin0 = Beluga.addOrReplaceChild("fin0", CubeListBuilder.create().texOffs(47, 65).addBox(-1.0F, -1.0F, -3.5F, 11.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.9F, 5.0F, -5.5F, 0.0F, 0.0F, 0.5236F));

        PartDefinition fin1 = Beluga.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(54, 56).mirror().addBox(-10.0F, -1.0F, -3.5F, 11.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.9F, 5.0F, -5.5F, 0.0F, 0.0F, -0.5236F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.Beluga.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.15 * limbSwingAmount);

        if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
            this.Beluga.xRot = headPitch * ((float)Math.PI / 180F);
            this.Beluga.zRot = headPitch * ((float)Math.PI / 180F);
        }

        this.fin0.zRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);
        this.fin1.zRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);
        this.tail.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);
        this.back_fin.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.5 * limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Beluga.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
