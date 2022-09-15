package com.epicsquid.ranchersdelight.init;

import com.epicsquid.ranchersdelight.RanchersDelight;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

public class RanchersDelightItems {

	private static final Registrate REGISTRATE = RanchersDelight.registrate();

	public static final RegistryEntry<Item> TRUFFLE = REGISTRATE.item("truffle", Item::new).tab(() -> RanchersDelight.ITEM_GROUP).register();
	public static final RegistryEntry<Item> RABBIT_WOOL = REGISTRATE.item("rabbit_wool", Item::new).tab(() -> RanchersDelight.ITEM_GROUP).register();

	public static void init() {
	}
}
