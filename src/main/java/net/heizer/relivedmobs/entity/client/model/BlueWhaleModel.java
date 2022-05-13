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

public class BlueWhaleModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RMMod.MOD_ID, "blue_whale"), "main");
    //Blue Whale
    private final ModelPart BlueWhale;
    private final ModelPart root;
    //Head
    private final ModelPart head;
    //Tail
    private final ModelPart tail;
    private final ModelPart tail2;
    private final ModelPart backtail;
    //Fins
    private final ModelPart fin1;
    private final ModelPart fin2;

    public BlueWhaleModel(ModelPart root) {
        //Root
        this.root = root;
        //Body
        this.BlueWhale = root.getChild("BlueWhale");
        //Head
        this.head = BlueWhale.getChild("head");
        //Tail
        this.tail = BlueWhale.getChild("tail");
        this.tail2 = tail.getChild("tail2");
        this.backtail = tail2.getChild("backtail");
        //Fins
        this.fin1 = BlueWhale.getChild("fin1");
        this.fin2 = BlueWhale.getChild("fin2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BlueWhale = partdefinition.addOrReplaceChild("BlueWhale", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

        BlueWhale.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -45.0F, -70.0F, 48.0F, 45.0F, 139.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

        PartDefinition head = BlueWhale.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -19.0F, -70.0F));

        head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(184, 185).addBox(-19.0F, -4.0F, -95.0F, 38.0F, 8.0F, 95.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(236, 0).addBox(-20.0F, -5.0F, -98.0F, 40.0F, 28.0F, 98.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

        PartDefinition tail = BlueWhale.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 185).addBox(-16.0F, -17.0F, 0.0F, 32.0F, 35.0F, 119.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -21.0F, 0.0F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 69.0F));

        PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 340).addBox(-11.0F, -10.0F, 0.0F, 22.0F, 21.0F, 83.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 119.0F));

        tail2.addOrReplaceChild("backtail", CubeListBuilder.create().texOffs(244, 300).addBox(-32.0F, -4.0F, -10.0F, 64.0F, 8.0F, 59.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 73.0F));

        BlueWhale.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(47, 17).addBox(0.0F, -6.0F, -1.0F, 4.0F, 16.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.0F, 11.0F, -28.0F, -1.5708F, 0.0F, -1.0472F));

        BlueWhale.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -1.0F, 4.0F, 16.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.0F, 11.0F, -28.0F, -1.5708F, 0.0F, 1.0472F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //Blue Whale
        this.BlueWhale.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * -0.4 * limbSwingAmount);
        this.BlueWhale.y = (float) (Mth.cos((float) (limbSwing * 0.5)) * 1 * limbSwingAmount);
        //Head
        this.head.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * -0.2 * limbSwingAmount);
        //Tail
        this.tail.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);
        this.tail2.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.5 * limbSwingAmount);
        this.backtail.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 1.5 * limbSwingAmount);
        //Fins
        this.fin1.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.8 * limbSwingAmount);
        this.fin1.yRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * -0.5 * limbSwingAmount);
        this.fin1.zRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * -0.3 * limbSwingAmount);
        this.fin2.xRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.8 * limbSwingAmount);
        this.fin2.yRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * -0.5 * limbSwingAmount);
        this.fin2.zRot = (float) (Mth.cos((float) (limbSwing * 0.5)) * 0.3 * limbSwingAmount);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        BlueWhale.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
