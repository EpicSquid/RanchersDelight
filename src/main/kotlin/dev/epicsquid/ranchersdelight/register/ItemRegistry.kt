package dev.epicsquid.ranchersdelight.register

import com.tterrag.registrate.util.DataIngredient
import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.utils.registryEntry
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraftforge.common.Tags

object ItemRegistry {

	private val registrate = RanchersDelight.registrate

	val truffle: Item by registryEntry {
		registrate.item<Item>("truffle", ::Item).register()
	}

	val rabbitWool: Item by registryEntry {
		registrate.item<Item>("rabbit_wool", ::Item)
			.tag(ItemTags.WOOL)
			.recipe { ctx, p ->
				ShapedRecipeBuilder.shaped(Items.RABBIT_HIDE).apply {
					pattern(" X ")
					pattern("XLX")
					pattern(" X ")
					define('X', ctx.entry)
					define('L', Tags.Items.LEATHER)
					unlockedBy("has_rabbit_wool", DataIngredient.items(ctx.get()).getCritereon(p))
				}.save(p, p.safeId(ctx.entry))
			}
			.register()
	}

	fun classload() {}
}