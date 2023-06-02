package dev.epicsquid.ranchersdelight.machines.base

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState


interface TickableEntity {
	fun tick(level: Level?, pos: BlockPos?, state: BlockState?)

	companion object {
		fun <T : BlockEntity?> tick(pLevel: Level?, pPos: BlockPos?, pState: BlockState?, pBlockEntity: T) {
			if (pBlockEntity is TickableEntity) {
				pBlockEntity.tick(pLevel, pPos, pState)
			}
		}
	}
}

