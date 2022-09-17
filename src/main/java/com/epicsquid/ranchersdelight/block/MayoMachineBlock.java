package com.epicsquid.ranchersdelight.block;

import com.epicsquid.ranchersdelight.init.RanchersDelightBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
		return RanchersDelightBlockEntity.MAYONNAISE_MACHINE.create(pos, state);
	}
}
