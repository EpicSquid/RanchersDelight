package com.epicsquid.ranchersdelight.init;

import com.epicsquid.ranchersdelight.RanchersDelight;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class RanchersDelightItems {

	private static final Registrate REGISTRATE = RanchersDelight.registrate();

	public static final RegistryEntry<Item> TRUFFLE = REGISTRATE.item("truffle", Item::new).register();
	public static final RegistryEntry<Item> RABBIT_WOOL = REGISTRATE.item("rabbit_wool", Item::new)
			.tag(ItemTags.WOOL)
			.recipe((ctx, p) -> ShapedRecipeBuilder.shaped(Items.RABBIT_HIDE)
					.pattern(" X ")
					.pattern("XLX")
					.pattern(" X ")
					.define('X', RanchersDelightItems.RABBIT_WOOL.get())
					.define('L', Items.LEATHER)
					.unlockedBy("has_rabbit_wool", DataIngredient.items(ctx.get()).getCritereon(p))
					.save(p, p.safeId(ctx.getEntry())))
			.register();

	public static void init() {
	}
}
