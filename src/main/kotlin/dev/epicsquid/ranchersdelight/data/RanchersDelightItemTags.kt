package dev.epicsquid.ranchersdelight.data

import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.registry.ItemRegistry
import net.minecraft.core.HolderLookup.Provider
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class RanchersDelightItemTags(
	output: PackOutput,
	lookupProvider: CompletableFuture<Provider>,
	blockTags: BlockTagsProvider,
	existingFileHelper: ExistingFileHelper
) : ItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), RanchersDelight.MODID, existingFileHelper) {

	override fun addTags(pProvider: Provider) {
		tag(ItemTags.WOOL)
			.add(ItemRegistry.rabbitWool)
	}
}