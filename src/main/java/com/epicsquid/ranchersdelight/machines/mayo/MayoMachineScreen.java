package com.epicsquid.ranchersdelight.machines.mayo;

import com.epicsquid.ranchersdelight.RanchersDelight;
import com.epicsquid.ranchersdelight.machines.base.BaseScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MayoMachineScreen extends BaseScreen<MayoMachineMenu> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(RanchersDelight.MODID, "textures/gui/container/mayonnaise_machine.png");

	public MayoMachineScreen(MayoMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}



	@Override
	protected void init() {
		setWindowOffset(0, 0);
		setWindowSize(176, 166);
		super.init();
//		addRenderableOnly(new ProgressWidget(this, () -> menu.getEntity().getProgress(), getGuiLeft() + 79, getGuiTop() + 31, 15, 15, 176, 0, ProgressWidget.Direction.BOTTOM_UP));
	}

	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		blit(pPoseStack, this.leftPos, this.topPos, 0, 0,this.imageWidth, this.imageHeight);
	}

	@Override
	protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
		//super.renderLabels(pPoseStack, pMouseX, pMouseY);
	}

}
