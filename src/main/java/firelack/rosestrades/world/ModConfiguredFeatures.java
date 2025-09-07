package firelack.rosestrades.world;

import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import firelack.rosestrades.RosesTrades;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModConfiguredFeatures {
    
    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(RosesTrades.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key, 
            F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
