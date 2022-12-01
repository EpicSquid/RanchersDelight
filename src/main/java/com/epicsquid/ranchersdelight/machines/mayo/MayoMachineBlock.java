package com.epicsquid.ranchersdelight.machines.mayo;

import com.epicsquid.ranchersdelight.init.RanchersDelightBlockEntities;
import com.epicsquid.ranchersdelight.machines.base.TickableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MayoMachineBlock extends Block implements EntityBlock {

	public MayoMachineBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(BlockStateProperties.POWERED);
		pBuilder.add(BlockStateProperties.FACING);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
		return super.getStateForPlacement(pContext)
				.setValue(BlockStateProperties.POWERED, false)
				.setValue(BlockStateProperties.FACING, Direction.UP);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return RanchersDelightBlockEntities.MAYONNAISE_MACHINE.create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return TickableEntity::tick;
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if (pLevel.isClientSide) {
			return InteractionResult.SUCCESS;
		}
		BlockEntity be = pLevel.getBlockEntity(pPos);
		if (be instanceof MayoMachineBlockEntity mayoBe) {
			NetworkHooks.openScreen((ServerPlayer) pPlayer, mayoBe, mayoBe::sendToMenu);
		}
		return InteractionResult.SUCCESS;
	}
}
