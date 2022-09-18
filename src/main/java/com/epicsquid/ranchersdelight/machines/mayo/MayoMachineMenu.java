package com.epicsquid.ranchersdelight.machines.mayo;

import com.epicsquid.ranchersdelight.init.RanchersDelightMenus;
import com.epicsquid.ranchersdelight.machines.base.BaseMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MayoMachineMenu extends BaseMenu<MayoMachineBlockEntity> {
	private Slot inputSlot;
	private Slot outputSlot;

	public MayoMachineMenu(@Nullable MenuType menuType, int id, Inventory playerInv, FriendlyByteBuf buf) {
		super(menuType, id, playerInv, buf);
	}

	public MayoMachineMenu(@Nullable MenuType menuType, int id, Inventory playerInv, MayoMachineBlockEntity menuContainer) {
		super(menuType, id, playerInv, menuContainer);
	}

	public static MayoMachineMenu create(int id, Inventory inv, MayoMachineBlockEntity be) {
		return new MayoMachineMenu(RanchersDelightMenus.MAYONNAISE_MACHINE.get(), id, inv, be);
	}


	@Override
	protected MayoMachineBlockEntity initClient(FriendlyByteBuf extraData) {
		ClientLevel level = Minecraft.getInstance().level;
		assert level != null;
		BlockEntity be = level.getBlockEntity(extraData.readBlockPos());
		if (be instanceof MayoMachineBlockEntity mayoMachine) {
			mayoMachine.load(extraData.readNbt());
			return mayoMachine;
		}
		return null;
	}

	@Override
	protected void init(MayoMachineBlockEntity contentHolder) {

	}

	@Override
	protected void addSlots() {
		inputSlot = new SlotItemHandler(menuContainer.inv, 0, 56, 35);
		outputSlot = new SlotItemHandler(menuContainer.inv, 1, 116, 35) {
			@Override
			public boolean mayPlace(@NotNull ItemStack stack) {
				return false;
			}
		};

		addSlot(inputSlot);
		addSlot(outputSlot);
		addPlayerSlots(8, 84);
	}

	@Override
	protected void saveData(MayoMachineBlockEntity contentHolder) {

	}

	@NotNull
	@Override
	public ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
		Slot clickedSlot = getSlot(index);
		if (!clickedSlot.hasItem()) {
			return ItemStack.EMPTY;
		}

		ItemStack stack = clickedSlot.getItem();
		if (index < 2) {
			moveItemStackTo(stack, 2, slots.size(), false);
		} else {
			moveItemStackTo(stack, 0, 1, false);
		}

		return ItemStack.EMPTY;
	}
}
