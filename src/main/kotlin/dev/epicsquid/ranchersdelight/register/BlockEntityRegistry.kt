package dev.epicsquid.ranchersdelight.register

import com.tterrag.registrate.util.entry.BlockEntityEntry
import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.machines.mayo.MayoMachineBlockEntity

object BlockEntityRegistry {

	private val registrate = RanchersDelight.registrate

	val mayonnaiseMachine: BlockEntityEntry<MayoMachineBlockEntity> =
		registrate.blockEntity<MayoMachineBlockEntity>("mayonnaise_machine", ::MayoMachineBlockEntity)
			.validBlocks(BlockRegistry.mayonnaiseMachine)
			.register()

	fun classload() {}
}