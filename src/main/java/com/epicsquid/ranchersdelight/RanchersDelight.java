package com.epicsquid.ranchersdelight;

import com.epicsquid.ranchersdelight.config.RanchersDelightConfig;
import com.epicsquid.ranchersdelight.init.RanchersDelightBlocks;
import com.epicsquid.ranchersdelight.init.RanchersDelightItems;
import com.tterrag.registrate.Registrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import javax.annotation.Nonnull;

@Mod(RanchersDelight.MODID)
public class RanchersDelight {
	public static final String MODID = "ranchersdelight";

	private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(RanchersDelight.MODID) {
		@Override
		@Nonnull
		public ItemStack makeIcon() {
			return new ItemStack(RanchersDelightItems.RABBIT_WOOL.get());
		}
	};

	public RanchersDelight() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, RanchersDelightConfig.SERVER_CONFIG_SPEC);

		REGISTRATE.get().creativeModeTab(() -> ITEM_GROUP);

		RanchersDelightItems.init();
		RanchersDelightBlocks.init();

		MinecraftForge.EVENT_BUS.register(this);
	}

	public static Registrate registrate() {
		return REGISTRATE.get();
	}
}
