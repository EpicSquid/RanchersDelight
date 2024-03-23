package dev.epicsquid.ranchersdelight.data

import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.registry.BlockRegistry
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries

class RanchersDelightBlockStates(output: PackOutput, exFileHelper: ExistingFileHelper) :
	BlockStateProvider(output, RanchersDelight.MODID, exFileHelper) {

	override fun registerStatesAndModels() {
		directionalBlock(
			BlockRegistry.mayonnaiseMachine,
			models().cubeBottomTop(
				ForgeRegistries.BLOCKS.getKey(BlockRegistry.mayonnaiseMachine)!!.path,
				modLoc("block/mayonnaise_machine_side"),
				modLoc("block/mayonnaise_machine_bottom"),
				modLoc("block/mayonnaise_machine_top")
			)
		)
	}
}