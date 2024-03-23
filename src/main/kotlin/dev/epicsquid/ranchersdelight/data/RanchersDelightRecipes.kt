package dev.epicsquid.ranchersdelight.data

import dev.epicsquid.ranchersdelight.RanchersDelight
import dev.epicsquid.ranchersdelight.registry.ItemRegistry
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraftforge.common.Tags
import java.util.function.Consumer

class RanchersDelightRecipes(output: PackOutput) : RecipeProvider(output) {

	override fun buildRecipes(writer: Consumer<FinishedRecipe>) {
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.RABBIT_HIDE).apply {
			pattern(" X ")
			pattern("XLX")
			pattern(" X ")
			define('X', ItemRegistry.rabbitWool)
			define('L', Tags.Items.LEATHER)
			unlockedBy("has_rabbit_wool", has(ItemRegistry.rabbitWool))
			save(writer, ResourceLocation(RanchersDelight.MODID, "rabbit_wool_to_rabbit_hide"))
		}
	}
}