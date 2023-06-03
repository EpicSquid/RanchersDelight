package dev.epicsquid.ranchersdelight.machines.base

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

interface TickableEntity {
	fun tick(level: Level, pos: BlockPos, state: BlockState)

	companion object {
		fun <T : BlockEntity?> tick(level: Level, pos: BlockPos, state: BlockState, blockEntity: T) {
			if (blockEntity is TickableEntity) {
				blockEntity.tick(level, pos, state)
			}
		}
	}
}

