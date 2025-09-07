package firelack.rosestrades.world;

import firelack.rosestrades.RosesTrades;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
// import net.minecraft.world.gen.placementmodifier.PlacementModifier;
// import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> SPECIAL_ROSE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RosesTrades.MOD_ID, "special_rose_placed"));

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry<ConfiguredFeature<?, ?>> entry = configuredFeatures.getOrThrow(ModConfiguredFeatures.SPECIAL_ROSE);

        context.register(
            SPECIAL_ROSE_PLACED,
            new PlacedFeature(
                entry,
                VegetationPlacedFeatures.modifiers(1)
            )
        );
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RosesTrades.MOD_ID, name));
    }

    // private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
    //                              List<PlacementModifier> modifiers) {
    //     context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    // }

    // private static <FC extends net.minecraft.world.gen.feature.FeatureConfig, F extends net.minecraft.world.gen.feature.Feature<FC>> void register(
    //         Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
    //         RegistryEntry<ConfiguredFeature<?, ?>> configuration,
    //         PlacementModifier... modifiers) {
    //     register(context, key, configuration, List.of(modifiers));
    // }
}
