package com.epicsquid.ranchersdelight.machines.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface TickableEntity {

	void tick(Level level, BlockPos pos, BlockState state);

	static <T extends BlockEntity> void tick(Level pLevel, BlockPos pPos, BlockState pState, T pBlockEntity) {
		if (pBlockEntity instanceof TickableEntity be) {
			be.tick(pLevel, pPos, pState);
		}
	}
}
