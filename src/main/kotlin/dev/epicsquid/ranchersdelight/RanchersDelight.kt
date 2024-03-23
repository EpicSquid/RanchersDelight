package dev.epicsquid.ranchersdelight

import dev.epicsquid.ranchersdelight.config.Config
import dev.epicsquid.ranchersdelight.data.*
import dev.epicsquid.ranchersdelight.registry.BlockEntityRegistry
import dev.epicsquid.ranchersdelight.registry.BlockRegistry
import dev.epicsquid.ranchersdelight.registry.ItemRegistry
import net.minecraft.core.HolderLookup.Provider
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig.Type.SERVER
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(RanchersDelight.MODID)
object RanchersDelight {
	const val MODID = "ranchersdelight"

	init {
		val modEventBus = MOD_BUS
		ModLoadingContext.get().registerConfig(SERVER, Config.RANCHERS_DELIGHT_CONFIG_SPEC, "ranchers-delight-server.toml")
		BlockRegistry.REGISTRY.register(modEventBus)
		ItemRegistry.REGISTRY.register(modEventBus)
		BlockEntityRegistry.REGISTRY.register(modEventBus)
	}

	private fun gatherData(event: GatherDataEvent) {
		val generator = event.generator
		val packOutput = event.generator.packOutput
		val lookupProvider = event.lookupProvider
		val existingFileHelper = event.existingFileHelper

		generator.addProvider(event.includeClient(), RanchersDelightBlockStates(packOutput, existingFileHelper))
		generator.addProvider(event.includeClient(), RanchersDelightItemModels(packOutput, existingFileHelper))
		generator.addProvider(event.includeClient(), RanchersDelightLang(packOutput, "en_us"))

		val blockTagsProvider = object : BlockTagsProvider(packOutput, lookupProvider, MODID, existingFileHelper) {
			override fun addTags(pProvider: Provider) {
			}
		}
		generator.addProvider(
			event.includeServer(),
			RanchersDelightItemTags(packOutput, lookupProvider, blockTagsProvider, existingFileHelper)
		)
		generator.addProvider(event.includeServer(), RanchersDelightRecipes(packOutput))

	}
}