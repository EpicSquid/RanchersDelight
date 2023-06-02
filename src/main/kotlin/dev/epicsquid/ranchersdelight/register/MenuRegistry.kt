package dev.epicsquid.ranchersdelight.register

import com.tterrag.registrate.builders.MenuBuilder.ScreenFactory
import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.machines.mayo.MayoMachineMenu
import dev.epicsquid.ranchersdelight.machines.mayo.MayoMachineScreen

object MenuRegistry {

	private val registrate = RanchersDelight.registrate

	val mayonnaiseMachine =
		registrate.menu<MayoMachineMenu, MayoMachineScreen>(
			"mayonnaise_machine",
			::MayoMachineMenu
		) { ScreenFactory(::MayoMachineScreen) }.register()


	fun classload() {}
}