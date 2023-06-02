package dev.epicsquid.ranchersdelight.machines.base

import com.mojang.blaze3d.platform.InputConstants
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.components.Widget
import net.minecraft.client.gui.components.events.GuiEventListener
import net.minecraft.client.gui.narration.NarratableEntry
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.Rect2i
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu

// Credit to Simi and the gang:
// https://github.com/Creators-of-Create/Create/blob/mc1.19/dev/src/main/java/com/simibubi/create/foundation/gui/container/AbstractSimiContainerScreen.java
abstract class BaseScreen<T : AbstractContainerMenu?>(pMenu: T, pPlayerInventory: Inventory?, pTitle: Component?) :
	AbstractContainerScreen<T>(pMenu, pPlayerInventory, pTitle) {
	protected var xOffset = 0
	protected var yOffset = 0

	/**
	 * This method must be called before `super.init()`!
	 */
	protected fun setWindowSize(width: Int, height: Int) {
		imageWidth = width
		imageHeight = height
	}

	/**
	 * This method must be called before `super.init()`!
	 */
	protected fun setWindowOffset(xOffset: Int, yOffset: Int) {
		this.xOffset = xOffset
		this.yOffset = yOffset
	}

	override fun init() {
		super.init()
		leftPos += xOffset
		topPos += yOffset
	}

	@SafeVarargs
	protected fun <W> addRenderableWidgets(vararg widgets: W) where W : GuiEventListener?, W : Widget?, W : NarratableEntry? {
		for (widget in widgets) {
			addRenderableWidget(widget)
		}
	}

	protected fun <W> addRenderableWidgets(widgets: Collection<W>) where W : GuiEventListener?, W : Widget?, W : NarratableEntry? {
		for (widget in widgets) {
			addRenderableWidget(widget)
		}
	}

	protected fun removeWidgets(vararg widgets: GuiEventListener) {
		for (widget in widgets) {
			removeWidget(widget)
		}
	}

	protected fun removeWidgets(widgets: Collection<GuiEventListener?>) {
		for (widget in widgets) {
			removeWidget(widget)
		}
	}

	override fun render(poseStack: PoseStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
		renderBackground(poseStack)
		super.render(poseStack, mouseX, mouseY, partialTicks)
		renderForeground(poseStack, mouseX, mouseY, partialTicks)
	}

	protected fun renderForeground(ms: PoseStack?, mouseX: Int, mouseY: Int, partialTicks: Float) {
		renderTooltip(ms, mouseX, mouseY)
	}

	fun getLeftOfCentered(textureWidth: Int): Int {
		return leftPos - xOffset + (imageWidth - textureWidth) / 2
	}

	override fun keyPressed(pKeyCode: Int, pScanCode: Int, pModifiers: Int): Boolean {
		val mouseKey = InputConstants.getKey(pKeyCode, pScanCode)
		if (focused != null) {
			assert(minecraft != null)
			if (minecraft!!.options.keyInventory.isActiveAndMatches(mouseKey)) {
				return false
			}
		}
		return super.keyPressed(pKeyCode, pScanCode, pModifiers)
	}

	override fun getFocused(): GuiEventListener? {
		var focused = super.getFocused()
		if (focused is AbstractWidget && !focused.isFocused) {
			focused = null
		}
		setFocused(focused)
		return focused
	}

	val extraAreas: List<Rect2i>
		/**
		 * Used for moving JEI out of the way of extra things like block renders.
		 *
		 * @return the space that the GUI takes up outside the normal rectangle defined
		 * by [AbstractContainerScreen].
		 */
		get() = emptyList()
}

