package firelack.rosestrades.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;
import firelack.rosestrades.block.ModBlocks;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // Recipe for crafting a bouquet using roses and string
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.BOUQUET)
            .pattern("RRR")
            .pattern("RRR")
            .pattern(" S ")
            .input('R', ModBlocks.ROSE)
            .input('S', Items.STRING)
            .criterion("has_rose", conditionsFromItem(ModBlocks.ROSE))
            .offerTo(exporter);
    }
}
