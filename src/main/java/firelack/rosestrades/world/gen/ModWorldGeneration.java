package firelack.rosestrades.world.gen;

import firelack.rosestrades.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;

public class ModWorldGeneration {
    public static void generateWorldGen() {
        // Add the special rose to flower forest and meadows biomes
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(
                RegistryKey.of(RegistryKeys.BIOME, Identifier.of("minecraft", "flower_forest")),
                RegistryKey.of(RegistryKeys.BIOME, Identifier.of("minecraft", "meadow"))
            ),
            GenerationStep.Feature.VEGETAL_DECORATION,
            ModPlacedFeatures.SPECIAL_ROSE_PLACED
        );
    }
}
