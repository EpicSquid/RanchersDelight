package com.epicsquid.ranchersdelight.machines.mayo;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

public class MayoMachineMenu extends AbstractContainerMenu {

	private final IItemHandler playerInv;
	private MayoMachineBlockEntity entity;
	public static Component component = new TextComponent("Mayonnaise Machine");

	public MayoMachineMenu(MenuType<?> menuType, int pContainerId, Inventory playerInv, MayoMachineBlockEntity entity) {
		super(menuType, pContainerId);
		this.playerInv = new InvWrapper(playerInv);
		this.entity = entity;
		entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(c -> {
			addSlot(new SlotItemHandler(c, 0, 79, 7));
			addSlot(new SlotItemHandler(c, 1, 79, 58));
		});
		layoutPlayerInventorySlots(8, 84);
	}

	public MayoMachineMenu(MenuType<?> type, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(type, id);
		this.playerInv = new InvWrapper(inv);
		layoutPlayerInventorySlots(8, 84);
	}

	private void layoutPlayerInventorySlots(int leftCol, int topRow) {
		// Player inventory
		addSlotBox(this.playerInv, 9, leftCol, topRow, 9, 18, 3, 18);

		// Hotbar
		topRow += 58;
		addSlotRange(this.playerInv, 0, leftCol, topRow, 9, 18);
	}


	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return false;
	}
}
