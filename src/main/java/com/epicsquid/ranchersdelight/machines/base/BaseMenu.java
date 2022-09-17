package com.epicsquid.ranchersdelight.machines.base;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;


// Credit to Simi and the gang here:
// https://github.com/Creators-of-Create/Create/blob/mc1.19/dev/src/main/java/com/simibubi/create/foundation/gui/container/ContainerBase.java
public abstract class BaseMenu<T> extends AbstractContainerMenu {

	public Player player;
	public Inventory playerInv;
	public T menuContainer;


	protected BaseMenu(@Nullable MenuType menuType, int id, Inventory playerInv, FriendlyByteBuf buf) {
		super(menuType, id);
		setup(playerInv, initClient(buf));
	}

	protected BaseMenu(@Nullable MenuType menuType, int id, Inventory playerInv, T menuContainer) {
		super(menuType, id);
		setup(playerInv, menuContainer);
	}

	private void setup(Inventory playerInv, T menuContainer) {
		this.playerInv = playerInv;
		this.player = playerInv.player;
		this.menuContainer = menuContainer;
		init(menuContainer);
		addSlots();
		broadcastChanges();
	}

	@OnlyIn(Dist.CLIENT)
	protected abstract T initClient(FriendlyByteBuf extraData);

	protected abstract void init(T contentHolder);

	protected abstract void addSlots();

	protected abstract void saveData(T contentHolder);

	protected void addPlayerSlots(int x, int y) {
		for (int hotbarSlot = 0; hotbarSlot < 9; ++hotbarSlot)
			this.addSlot(new Slot(playerInv, hotbarSlot, x + hotbarSlot * 18, y + 58));
		for (int row = 0; row < 3; ++row)
			for (int col = 0; col < 9; ++col)
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, x + col * 18, y + row * 18));
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		saveData(menuContainer);
	}

	@Override
	public boolean stillValid(Player player) {
		return menuContainer != null;
	}
}
