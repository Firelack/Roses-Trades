package firelack.rosestrades.world;

import firelack.rosestrades.RosesTrades;
import firelack.rosestrades.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPECIAL_ROSE =
            RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(RosesTrades.MOD_ID, "special_rose"));

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        ConfiguredFeature<?, ?> feature = new ConfiguredFeature<>(
            Feature.FLOWER,
            new RandomPatchFeatureConfig(
                4, // trys per chunk
                10,  // xz spread
                2,  // y spread
                PlacedFeatures.createEntry(
                    Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SPECIAL_ROSE))
                )
            )
        );

        context.register(SPECIAL_ROSE, feature);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(RosesTrades.MOD_ID, name));
    }

    // private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
    //         Registerable<ConfiguredFeature<?, ?>> context,
    //         RegistryKey<ConfiguredFeature<?, ?>> key, 
    //         F feature,
    //         FC configuration) {
    //     context.register(key, new ConfiguredFeature<>(feature, configuration));
    // }
}
