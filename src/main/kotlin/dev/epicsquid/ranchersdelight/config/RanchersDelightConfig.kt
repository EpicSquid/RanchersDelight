package dev.epicsquid.ranchersdelight.config

import dev.epicsquid.ranchersdelight.utils.stack
import net.minecraftforge.common.ForgeConfigSpec

class RanchersDelightConfig(
	builder: ForgeConfigSpec.Builder
) {
	val featherSpawnDelay: ForgeConfigSpec.ConfigValue<Int>
	val rabbitWoolSpawnDelay: ForgeConfigSpec.ConfigValue<Int>
	val rabbitFootSpawnDelay: ForgeConfigSpec.ConfigValue<Int>
	val truffleSpawnDelay: ForgeConfigSpec.ConfigValue<Int>

	init {
		builder.stack("Passive Mob Drops") {
			comment("Defines the parameters under which various passive mobs will drop associated items, like eggs from chickens.");
			featherSpawnDelay =
				comment("The max bound of the random amount of time before a feather will spawn from a chicken.").define(
					"feather_spawn_delay",
					8000
				)
			rabbitWoolSpawnDelay =
				comment("The max bound of the random amount of time before a rabbit wool will spawn from a rabbit.").define(
					"rabbit_wool_spawn_delay",
					8000
				)
			rabbitFootSpawnDelay =
				comment("The max bound of the random amount of time before a rabbit foot will spawn from a rabbit.").define(
					"rabbit_foot_spawn_delay",
					12000
				)
			truffleSpawnDelay =
				comment("The max bound of the random amount of time before a truffle will spawn from a pig.").define(
					"truffle_spawn_delay",
					10000
				)
		}

	}
}