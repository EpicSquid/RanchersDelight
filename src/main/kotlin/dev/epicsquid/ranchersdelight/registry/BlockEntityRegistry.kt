package dev.epicsquid.ranchersdelight.registry

import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.machines.mayo.MayoMachineBlockEntity
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object BlockEntityRegistry {

	val REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, RanchersDelight.MODID)

	val mayonnaiseMachine: BlockEntityType<MayoMachineBlockEntity> by REGISTRY.registerObject("mayonnaise_machine") {
		BlockEntityType.Builder.of({ pos, state ->
			MayoMachineBlockEntity(
				BlockEntityRegistry.mayonnaiseMachine,
				pos,
				state
			)
		}, BlockRegistry.mayonnaiseMachine)
			.build(null)
	}
}