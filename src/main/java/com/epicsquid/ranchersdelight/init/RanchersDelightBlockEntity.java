package com.epicsquid.ranchersdelight.init;

import com.epicsquid.ranchersdelight.RanchersDelight;
import com.epicsquid.ranchersdelight.block.MayoMachineBlockEntity;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class RanchersDelightBlockEntity {

	private static final Registrate REGISTRATE = RanchersDelight.registrate();

	public static final BlockEntityEntry<MayoMachineBlockEntity> MAYONNAISE_MACHINE = REGISTRATE.blockEntity("mayonnaise_machine", MayoMachineBlockEntity::new)
			.validBlocks(RanchersDelightBlocks.MAYONNAISE_MACHINE).register();

	public static void init() {
	}
}
