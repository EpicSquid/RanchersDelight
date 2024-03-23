package dev.epicsquid.ranchersdelight.registry

import dev.epicsquid.ranchersdelight.RanchersDelight
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object ItemRegistry {

	val REGISTRY = DeferredRegister.create(Registries.ITEM, RanchersDelight.MODID)

	val truffle by REGISTRY.registerObject("truffle") {
		Item(Item.Properties())
	}

	val rabbitWool by REGISTRY.registerObject("rabbit_wool") {
		Item(Item.Properties())
	}
}