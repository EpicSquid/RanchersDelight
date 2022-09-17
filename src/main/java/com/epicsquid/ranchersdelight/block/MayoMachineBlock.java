package com.epicsquid.ranchersdelight.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class MayoMachineBlock extends LodestoneEntityBlock {

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
}
