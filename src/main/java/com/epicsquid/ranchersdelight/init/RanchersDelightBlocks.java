package com.epicsquid.ranchersdelight.init;

import com.epicsquid.ranchersdelight.RanchersDelight;
import com.epicsquid.ranchersdelight.machines.mayo.MayoMachineBlock;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;

public class RanchersDelightBlocks {

	private static final Registrate REGISTRATE = RanchersDelight.registrate();

	public static final RegistryEntry<MayoMachineBlock> MAYONNAISE_MACHINE = REGISTRATE.block("mayonnaise_machine", MayoMachineBlock::new)
			.tag(BlockTags.MINEABLE_WITH_AXE)
			.properties(props -> props.sound(SoundType.WOOD))
			.blockstate((ctx, p) -> p.directionalBlock(ctx.get(), p.models().cubeBottomTop(
							"mayonnaise_machine",
							new ResourceLocation(RanchersDelight.MODID, "block/mayonnaise_machine_side"),
							new ResourceLocation(RanchersDelight.MODID, "block/mayonnaise_machine_bottom"),
							new ResourceLocation(RanchersDelight.MODID, "block/mayonnaise_machine_top")
					)
			))
			.item()
			.build()
			.register();

	public static void init() {
	}
}
