package com.epicsquid.ranchersdelight;

import com.epicsquid.ranchersdelight.init.RanchersDelightItems;
import com.tterrag.registrate.Registrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RanchersDelight.MODID)
public class RanchersDelight {
	public static final String MODID = "ranchersdelight";

	private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(RanchersDelight.MODID) {
		@Override
		@Nonnull
		public ItemStack makeIcon() {
			return new ItemStack(RanchersDelightItems.TRUFFLE.get());
		}
	};

	public RanchersDelight() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		RanchersDelightItems.init();

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}

	public static Registrate registrate() {
		return REGISTRATE.get();
	}
}
