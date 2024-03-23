package dev.epicsquid.ranchersdelight.data

import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.registry.BlockRegistry
import dev.epicsquid.ranchersdelight.registry.ItemRegistry
import dev.epicsquid.squidink.data.block
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper

class RanchersDelightItemModels(
	output: PackOutput,
	existingFileHelper: ExistingFileHelper
) : ItemModelProvider(output, RanchersDelight.MODID, existingFileHelper) {

	override fun registerModels() {
		block(BlockRegistry.mayonnaiseMachine)

		basicItem(ItemRegistry.truffle)
		basicItem(ItemRegistry.rabbitWool)
	}
}