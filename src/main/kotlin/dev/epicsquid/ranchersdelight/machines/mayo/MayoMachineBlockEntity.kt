package dev.epicsquid.ranchersdelight.machines.mayo

import dev.epicsquid.ranchersdelight.machines.base.TickableEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.api.distmarker.Dist.CLIENT
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler

class MayoMachineBlockEntity(
	type: BlockEntityType<*>,
	pos: BlockPos,
	state: BlockState
) : BlockEntity(type, pos, state), MenuProvider, TickableEntity {
	val inv = ItemStackHandler(2)
	private val invOp = LazyOptional.of<IItemHandler> { inv }
	private var progress = 0
	override fun <T> getCapability(cap: Capability<T>): LazyOptional<T> {
		return if (cap === ForgeCapabilities.ITEM_HANDLER) {
			invOp.cast()
		} else super.getCapability(cap)
	}

	override fun saveAdditional(tag: CompoundTag) {
		super.saveAdditional(tag)
		tag.put("inv", inv.serializeNBT())
		tag.putInt("progress", progress)
	}

	override fun load(tag: CompoundTag) {
		super.load(tag)
		inv.deserializeNBT(tag.getCompound("inv"))
		progress = tag.getInt("progress")
	}

	fun sendToMenu(buffer: FriendlyByteBuf) {
		buffer.writeBlockPos(blockPos)
		buffer.writeNbt(updateTag)
	}

	override fun getDisplayName(): Component {
		return Component.literal("Mayonnaise Machine")
	}

	override fun createMenu(pContainerId: Int, pPlayerInventory: Inventory, pPlayer: Player): AbstractContainerMenu? {
		return MayoMachineMenu.create(pContainerId, pPlayerInventory, this)
	}

	@get:OnlyIn(CLIENT)
	val progressScaled: Int
		get() = progress / 20

	override fun tick(level: Level?, pos: BlockPos?, state: BlockState?) {
		if (++progress >= MAX_PROGRESS) {
			progress = 0
			setChanged()
		}
	}

	companion object {
		private const val MAX_PROGRESS = 480
	}
}

