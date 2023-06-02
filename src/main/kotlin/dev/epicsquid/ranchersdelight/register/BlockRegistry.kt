package dev.epicsquid.ranchersdelight.register

import com.tterrag.registrate.util.entry.BlockEntry
import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.machines.mayo.MayoMachineBlock
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.SoundType

object BlockRegistry {

	private val registrate = RanchersDelight.registrate

	val mayonnaiseMachine: BlockEntry<MayoMachineBlock> =
		registrate.block<MayoMachineBlock>("mayonnaise_machine", ::MayoMachineBlock).apply {
			tag(BlockTags.MINEABLE_WITH_AXE)
			properties { props -> props.sound(SoundType.WOOD) }
			blockstate { ctx, p ->
				p.directionalBlock(
					ctx.get(), p.models().cubeBottomTop(
						"mayonnaise_machine",
						p.modLoc("block/mayonnaise_machine_side"),
						p.modLoc("block/mayonnaise_machine_bottom"),
						p.modLoc("block/mayonnaise_machine_top")
					)
				)
			}
			item()
			build()
		}.register()


	fun classload() {}
}