package dev.epicsquid.ranchersdelight.registry

import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.machines.mayo.MayoMachineBlock
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object BlockRegistry {

	val REGISTRY = DeferredRegister.create(Registries.BLOCK, RanchersDelight.MODID)

	val mayonnaiseMachine by REGISTRY.registerObject("mayonnaise_machine") {
		MayoMachineBlock(Properties.of().sound(SoundType.WOOD))
	}
}