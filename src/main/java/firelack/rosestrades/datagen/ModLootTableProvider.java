package firelack.rosestrades.datagen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import firelack.rosestrades.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // Roses -> drop rose
        addDrop(ModBlocks.ROSE);
        addDrop(ModBlocks.BOUQUET);

        // Potted flowers -> drop flower pot + flower
        addDrop(ModBlocks.POTTED_ROSE, block -> pottedDrop(ModBlocks.ROSE));
        addDrop(ModBlocks.POTTED_BOUQUET, block -> pottedDrop(ModBlocks.BOUQUET));
    }

    /**
     * Creates a loot table for a potted flower that drops both the flower pot and the flower itself.
     */
    private LootTable.Builder pottedDrop(Block flower) {
        LootPool.Builder potPool = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1))
            .with(ItemEntry.builder(Items.FLOWER_POT));

        LootPool.Builder flowerPool = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1))
            .with(ItemEntry.builder(flower));

        return LootTable.builder()
            .pool(potPool)
            .pool(flowerPool);
    }
}
