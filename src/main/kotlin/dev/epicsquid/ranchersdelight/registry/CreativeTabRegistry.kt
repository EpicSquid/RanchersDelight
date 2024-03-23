package dev.epicsquid.ranchersdelight.registry

import dev.epicsquid.ranchersdelight.RanchersDelight
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

class CreativeTabRegistry {

	val REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RanchersDelight.MODID)

	val tab by REGISTRY.registerObject("main") {
		CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.ranchersdelight.main"))
			.icon { ItemRegistry.rabbitWool.defaultInstance }
			.displayItems { _, output ->
				ItemRegistry.REGISTRY.entries.forEach {
					output.accept(it.get())
				}
			}
			.build()
	}
}