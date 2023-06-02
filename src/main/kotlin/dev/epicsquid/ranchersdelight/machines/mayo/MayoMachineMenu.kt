package dev.epicsquid.ranchersdelight.machines.mayo

import dev.epicsquid.ranchersdelight.machines.base.BaseMenu
import dev.epicsquid.ranchersdelight.register.MenuRegistry
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraftforge.items.SlotItemHandler
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMealSlot


class MayoMachineMenu : BaseMenu<MayoMachineBlockEntity> {
	private lateinit var inputSlot: Slot
	private lateinit var outputSlot: Slot

	constructor(menuType: MenuType<MayoMachineMenu>?, id: Int, playerInv: Inventory?, buf: FriendlyByteBuf?) : super(
		menuType!!,
		id,
		playerInv!!,
		buf!!
	)

	constructor(menuType: MenuType<MayoMachineMenu>, id: Int, playerInv: Inventory, menuContainer: MayoMachineBlockEntity) : super(
		menuType, id,
		playerInv, menuContainer
	)

	override fun initClient(extraData: FriendlyByteBuf): MayoMachineBlockEntity {
		val level = Minecraft.getInstance().level!!
		val be = level.getBlockEntity(extraData.readBlockPos())
		if (be is MayoMachineBlockEntity) {
			extraData.readNbt()?.let { be.load(it) }
			return be
		}
		throw IllegalStateException("Illegal block entity found at MayoMachineMenu#initClient")
	}

	override fun init(contentHolder: MayoMachineBlockEntity) {}

	override fun addSlots() {
		inputSlot = SlotItemHandler(menuContainer.inv, 0, 56, 35)
		outputSlot = CookingPotMealSlot(menuContainer.inv, 1, 116, 35)
		addSlot(inputSlot)
		addSlot(outputSlot)
		addPlayerSlots(8, 84)
	}

	override fun saveData(contentHolder: MayoMachineBlockEntity) {}

	override fun quickMoveStack(playerIn: Player, index: Int): ItemStack {
		val clickedSlot = getSlot(index)
		if (!clickedSlot.hasItem()) {
			return ItemStack.EMPTY
		}
		val stack = clickedSlot.item
		if (index < 2) {
			moveItemStackTo(stack, 2, slots.size, false)
		} else {
			moveItemStackTo(stack, 0, 1, false)
		}
		return ItemStack.EMPTY
	}

	val progressScaled: Int
		get() = menuContainer.progressScaled

	companion object {
		fun create(id: Int, inv: Inventory, be: MayoMachineBlockEntity): MayoMachineMenu {
			return MayoMachineMenu(MenuRegistry.mayonnaiseMachine.get(), id, inv, be)
		}
	}
}