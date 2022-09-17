package com.epicsquid.ranchersdelight.machines.mayo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class MayoMachineBlockEntity extends BlockEntity {

	private final IItemHandler inv = new ItemStackHandler(3);
	private final LazyOptional<IItemHandler> invOp = LazyOptional.of(() -> inv);

	public MayoMachineBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
		super(pType, pPos, pBlockState);
	}
}
