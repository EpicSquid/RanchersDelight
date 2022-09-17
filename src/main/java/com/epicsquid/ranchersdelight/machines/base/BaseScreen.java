package com.epicsquid.ranchersdelight.machines.base;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

// Credit to Simi and the gang:
// https://github.com/Creators-of-Create/Create/blob/mc1.19/dev/src/main/java/com/simibubi/create/foundation/gui/container/AbstractSimiContainerScreen.java
public abstract class BaseScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

	protected int xOffset, yOffset;

	public BaseScreen(T pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	/**
	 * This method must be called before {@code super.init()}!
	 */
	protected void setWindowSize(int width, int height) {
		imageWidth = width;
		imageHeight = height;
	}

	/**
	 * This method must be called before {@code super.init()}!
	 */
	protected void setWindowOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	protected void init() {
		super.init();
		leftPos += xOffset;
		topPos += yOffset;
	}


	@SafeVarargs
	protected final <W extends GuiEventListener & Widget & NarratableEntry> void addRenderableWidgets(W @NotNull ... widgets) {
		for (W widget : widgets) {
			addRenderableWidget(widget);
		}
	}

	protected <W extends GuiEventListener & Widget & NarratableEntry> void addRenderableWidgets(@NotNull Collection<W> widgets) {
		for (W widget : widgets) {
			addRenderableWidget(widget);
		}
	}

	protected void removeWidgets(GuiEventListener @NotNull ... widgets) {
		for (GuiEventListener widget : widgets) {
			removeWidget(widget);
		}
	}

	protected void removeWidgets(@NotNull Collection<? extends GuiEventListener> widgets) {
		for (GuiEventListener widget : widgets) {
			removeWidget(widget);
		}
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTicks);
		renderForeground(poseStack, mouseX, mouseY, partialTicks);
	}

	protected void renderForeground(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		renderTooltip(ms, mouseX, mouseY);
	}

	public int getLeftOfCentered(int textureWidth) {
		return leftPos - xOffset + (imageWidth - textureWidth) / 2;
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
		InputConstants.Key mouseKey = InputConstants.getKey(pKeyCode, pScanCode);
		if (getFocused() != null) {
			assert this.minecraft != null;
			if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey)) {
				return false;
			}
		}
		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}

	@Override
	public GuiEventListener getFocused() {
		GuiEventListener focused = super.getFocused();
		if (focused instanceof AbstractWidget && !((AbstractWidget) focused).isFocused()) {
			focused = null;
		}
		setFocused(focused);
		return focused;
	}

	/**
	 * Used for moving JEI out of the way of extra things like block renders.
	 *
	 * @return the space that the GUI takes up outside the normal rectangle defined
	 * by {@link AbstractContainerScreen}.
	 */
	public List<Rect2i> getExtraAreas() {
		return Collections.emptyList();
	}
}
