package dev.epicsquid.ranchersdelight.machines.mayo

import dev.epicsquid.ranchersdelight.machines.base.TickableEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler

class MayoMachineBlockEntity(
	type: BlockEntityType<MayoMachineBlockEntity>,
	pos: BlockPos,
	state: BlockState
) : BlockEntity(type, pos, state), TickableEntity {
	companion object {
		private const val MAX_PROGRESS = 480
	}

	private val itemStackHandler = ItemStackHandler(2)

	private val itemStackHandlerOp = LazyOptional.of<IItemHandler> { itemStackHandler }

	private var progress = 0

	override fun <T> getCapability(cap: Capability<T>): LazyOptional<T> =
		if (cap == ForgeCapabilities.ITEM_HANDLER) itemStackHandlerOp.cast() else super.getCapability(cap)

	override fun saveAdditional(tag: CompoundTag) {
		super.saveAdditional(tag)
		tag.put("inv", itemStackHandler.serializeNBT())
		tag.putInt("progress", progress)
	}

	override fun load(tag: CompoundTag) {
		super.load(tag)
		itemStackHandler.deserializeNBT(tag.getCompound("inv"))
		progress = tag.getInt("progress")
	}

	override fun tick(level: Level, pos: BlockPos, state: BlockState) {
		if (++progress >= MAX_PROGRESS) {
			progress = 0
			setChanged()
		}
	}
}

