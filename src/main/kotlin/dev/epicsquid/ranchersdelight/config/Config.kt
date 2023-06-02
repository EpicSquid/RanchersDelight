package dev.epicsquid.ranchersdelight.config

import net.minecraftforge.common.ForgeConfigSpec

object Config {

	private val configSpecPair = ForgeConfigSpec.Builder().configure(::RanchersDelightConfig)

	val RANCHERS_DELIGHT_CONFIG = configSpecPair.left
	val RANCHERS_DELIGHT_CONFIG_SPEC = configSpecPair.right
}