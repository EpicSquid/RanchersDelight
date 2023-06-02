package dev.epicsquid.ranchersdelight.machines.base

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.inventory.Slot
import net.minecraftforge.api.distmarker.Dist.CLIENT
import net.minecraftforge.api.distmarker.OnlyIn

// Credit to Simi and the gang here:
// https://github.com/Creators-of-Create/Create/blob/mc1.19/dev/src/main/java/com/simibubi/create/foundation/gui/container/ContainerBase.java
abstract class BaseMenu<T : Any> : AbstractContainerMenu {
	lateinit var player: Player
	lateinit var playerInv: Inventory
	lateinit var menuContainer: T

	protected constructor(menuType: MenuType<*>, id: Int, playerInv: Inventory, buf: FriendlyByteBuf) : super(
		menuType,
		id
	) {
		setup(playerInv, initClient(buf))
	}

	protected constructor(menuType: MenuType<*>, id: Int, playerInv: Inventory, menuContainer: T) : super(menuType, id) {
		setup(playerInv, menuContainer)
	}

	private fun setup(playerInv: Inventory, menuContainer: T) {
		this.playerInv = playerInv
		player = playerInv.player
		this.menuContainer = menuContainer
		init(menuContainer)
		addSlots()
		broadcastChanges()
	}

	@OnlyIn(CLIENT)
	protected abstract fun initClient(extraData: FriendlyByteBuf): T
	protected abstract fun init(contentHolder: T)
	protected abstract fun addSlots()
	protected abstract fun saveData(contentHolder: T)
	protected fun addPlayerSlots(x: Int, y: Int) {
		for (hotbarSlot in 0..8) addSlot(Slot(playerInv, hotbarSlot, x + hotbarSlot * 18, y + 58))
		for (row in 0..2) for (col in 0..8) addSlot(Slot(playerInv, col + row * 9 + 9, x + col * 18, y + row * 18))
	}

	override fun removed(playerIn: Player) {
		super.removed(playerIn)
		saveData(menuContainer)
	}

	override fun stillValid(player: Player): Boolean {
		return menuContainer != null
	}
}

