package com.epicsquid.ranchersdelight.init;

import com.epicsquid.ranchersdelight.RanchersDelight;
import com.epicsquid.ranchersdelight.machines.mayo.MayoMachineMenu;
import com.epicsquid.ranchersdelight.machines.mayo.MayoMachineScreen;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.MenuEntry;

public class RanchersDelightMenus {

	private static final Registrate REGISTRATE = RanchersDelight.registrate();

	public static final MenuEntry<MayoMachineMenu> MAYONNAISE_MACHINE = REGISTRATE.menu("mayonnaise_machine", MayoMachineMenu::new, () -> MayoMachineScreen::new).register();

	public static void init() {
	}
}
