package dev.epicsquid.ranchersdelight.machines.mayo

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.machines.base.BaseScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import java.awt.Rectangle


class MayoMachineScreen(pMenu: MayoMachineMenu?, pPlayerInventory: Inventory?, pTitle: Component?) :
	BaseScreen<MayoMachineMenu?>(pMenu, pPlayerInventory, pTitle) {
	override fun init() {
		setWindowOffset(0, 0)
		setWindowSize(176, 166)
		super.init()
	}

	override fun renderBg(pPoseStack: PoseStack, pPartialTick: Float, pMouseX: Int, pMouseY: Int) {
		RenderSystem.setShader { GameRenderer.getPositionTexShader() }
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
		RenderSystem.setShaderTexture(0, TEXTURE)
		blit(pPoseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight)

		// Progress bar
		val menuInternal = menu ?: return
		val progress: Int = menuInternal.progressScaled
		this.blit(
			pPoseStack,
			leftPos + PROGRESS_ARROW.x,
			topPos + PROGRESS_ARROW.y,
			176,
			0,
			progress + 1,
			PROGRESS_ARROW.height
		)
	}

	companion object {
		private val TEXTURE = ResourceLocation(RanchersDelight.MODID, "textures/gui/container/mayonnaise_machine.png")
		private val PROGRESS_ARROW = Rectangle(79, 34, 0, 17)
	}
}

