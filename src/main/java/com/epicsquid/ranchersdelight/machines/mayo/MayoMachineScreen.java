package com.epicsquid.ranchersdelight.machines.mayo;

import com.epicsquid.ranchersdelight.RanchersDelight;
import com.epicsquid.ranchersdelight.machines.base.BaseScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MayoMachineScreen extends BaseScreen<MayoMachineMenu> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(RanchersDelight.MODID, "textures/gui/container/mayonnaise_machine.png");
	private static final Rectangle PROGRESS_ARROW = new Rectangle(79, 34, 0, 17);

	public MayoMachineScreen(MayoMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}


	@Override
	protected void init() {
		setWindowOffset(0, 0);
		setWindowSize(176, 166);
		super.init();
	}

	@Override
	protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

		// Progress bar
		int progress = menu.getProgressScaled();
		this.blit(pPoseStack, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 0, progress + 1, PROGRESS_ARROW.height);
	}
}
