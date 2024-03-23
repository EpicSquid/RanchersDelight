package dev.epicsquid.ranchersdelight.data

import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.registry.ItemRegistry
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider

class RanchersDelightLang(
	output: PackOutput,
	locale: String
) : LanguageProvider(output, RanchersDelight.MODID, locale) {

	override fun addTranslations() {
		add(ItemRegistry.truffle, "Truffle")
		add(ItemRegistry.rabbitWool, "Rabbit Wool")
		add(ItemRegistry.mayonnaiseMachine, "Mayonnaise Machine")
		add("itemGroup.ranchersdelight.main", "Rancher's Delight")
	}
}