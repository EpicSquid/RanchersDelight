package dev.epicsquid.ranchersdelight

import com.tterrag.registrate.Registrate
import dev.epicsquid.ranchersdelight.config.Config
import dev.epicsquid.ranchersdelight.register.BlockEntityRegistry
import dev.epicsquid.ranchersdelight.register.BlockRegistry
import dev.epicsquid.ranchersdelight.register.ItemRegistry
import dev.epicsquid.ranchersdelight.register.MenuRegistry
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig.Type.SERVER

@Mod(RanchersDelight.MODID)
object RanchersDelight {
	const val MODID = "ranchersdelight"

	val tab: CreativeModeTab = object : CreativeModeTab(MODID) {
		override fun makeIcon(): ItemStack {
			return ItemStack(ItemRegistry.rabbitWool)
		}
	}

	val registrate by lazy { Registrate.create(MODID).creativeModeTab { tab } }


	init {
		ModLoadingContext.get().registerConfig(SERVER, Config.RANCHERS_DELIGHT_CONFIG_SPEC, "ranchers-delight-server.toml")
		ItemRegistry.classload()
		BlockRegistry.classload()
		BlockEntityRegistry.classload()
		MenuRegistry.classload()
		MinecraftForge.EVENT_BUS.register(this)
	}
}