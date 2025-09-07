package firelack.rosestrades.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import firelack.rosestrades.block.ModBlocks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    // Flower Pot Plants
    blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ROSE, ModBlocks.POTTED_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
    blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.BOUQUET, ModBlocks.POTTED_BOUQUET, BlockStateModelGenerator.TintType.NOT_TINTED);

    // Special Rose in cross
    blockStateModelGenerator.registerTintableCross(ModBlocks.SPECIAL_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
}

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // items block models
        // ex: itemModelGenerator.register(ModBlocks.ROSE_ITEM, Models.GENERATED);
    }
}
