package com.epicsquid.ranchersdelight.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {

	public final ForgeConfigSpec.ConfigValue<Integer> FEATHER_SPAWN_DELAY;
	public final ForgeConfigSpec.ConfigValue<Integer> RABBIT_WOOL_SPAWN_DELAY;
	public final ForgeConfigSpec.ConfigValue<Integer> RABBIT_FOOT_SPAWN_DELAY;

	public ServerConfig(ForgeConfigSpec.Builder builder) {
		builder.push("Passive Mob Drops");
		builder.comment("Defines the parameters under which various passive mobs will drop associated items, like eggs from chickens.");
		FEATHER_SPAWN_DELAY = builder.comment("The max bound of the random amount of time before a feather will spawn from a chicken.")
				.define("feather_spawn_delay", 10000);
		RABBIT_WOOL_SPAWN_DELAY = builder.comment("The max bound of the random amount of time before a rabbit wool will spawn from a rabbit.")
				.define("rabbit_wool_spawn_delay", 6000);
		RABBIT_FOOT_SPAWN_DELAY = builder.comment("The max bound of the random amount of time before a rabbit foot will spawn from a rabbit.")
				.define("rabbit_foot_spawn_delay", 12000);
		builder.pop();
	}
}
