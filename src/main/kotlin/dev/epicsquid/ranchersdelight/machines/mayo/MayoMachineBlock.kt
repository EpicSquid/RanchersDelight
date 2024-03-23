package dev.epicsquid.ranchersdelight.machines.mayo

import dev.epicsquid.ranchersdelight.machines.base.TickableEntity
import dev.epicsquid.ranchersdelight.registry.BlockEntityRegistry
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction.UP
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition.Builder
import net.minecraft.world.level.block.state.properties.BlockStateProperties

class MayoMachineBlock(
	props: Properties
) : Block(props), EntityBlock {
	override fun createBlockStateDefinition(builder: Builder<Block, BlockState>) {
		super.createBlockStateDefinition(builder)
		builder.add(BlockStateProperties.POWERED)
		builder.add(BlockStateProperties.FACING)
	}

	override fun getStateForPlacement(context: BlockPlaceContext): BlockState? =
		super.getStateForPlacement(context)
			?.setValue(BlockStateProperties.POWERED, false)
			?.setValue(BlockStateProperties.FACING, UP)

	override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
		return BlockEntityRegistry.mayonnaiseMachine.create(pos, state)
	}

	override fun <T : BlockEntity?> getTicker(
		tickerLevel: Level,
		tickerState: BlockState,
		tickerEntityType: BlockEntityType<T>
	): BlockEntityTicker<T> = BlockEntityTicker(TickableEntity::tick)
}
