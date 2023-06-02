package dev.epicsquid.ranchersdelight.machines.mayo

import dev.epicsquid.ranchersdelight.machines.base.TickableEntity
import dev.epicsquid.ranchersdelight.register.BlockEntityRegistry
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction.UP
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResult.SUCCESS
import net.minecraft.world.entity.player.Player
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
import net.minecraft.world.phys.BlockHitResult
import net.minecraftforge.network.NetworkHooks

class MayoMachineBlock(props: Properties) : Block(props), EntityBlock {
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
	): BlockEntityTicker<T> {
		return BlockEntityTicker { level: Level?, pos: BlockPos?, state: BlockState?, blockEntity: T ->
			TickableEntity.tick(
				level,
				pos,
				state,
				blockEntity
			)
		}
	}

	override fun use(
		state: BlockState,
		level: Level,
		pos: BlockPos,
		player: Player,
		hand: InteractionHand,
		hit: BlockHitResult
	): InteractionResult {
		if (level.isClientSide) {
			return SUCCESS
		}
		val be = level.getBlockEntity(pos)
		if (be is MayoMachineBlockEntity) {
			NetworkHooks.openScreen(
				player as ServerPlayer, be
			) { buffer: FriendlyByteBuf -> be.sendToMenu(buffer) }
		}
		return SUCCESS
	}
}
