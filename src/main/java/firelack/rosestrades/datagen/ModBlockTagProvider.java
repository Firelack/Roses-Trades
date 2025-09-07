package firelack.rosestrades.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
//import firelack.rosestrades.block.ModBlocks;
//import net.minecraft.registry.tag.BlockTags;
//import net.minecraft.block.Blocks;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // no block tags yet
        // getOrCreateTagBuilder(ModTags.EXAMPLE_TAG).add(Blocks.EXAMPLE_BLOCK);
    }

}
