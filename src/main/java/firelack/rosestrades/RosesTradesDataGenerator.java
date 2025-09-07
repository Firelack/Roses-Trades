package firelack.rosestrades;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import firelack.rosestrades.datagen.ModBlockTagProvider;
import firelack.rosestrades.datagen.ModItemTagProvider;
import firelack.rosestrades.datagen.ModLootTableProvider;
import firelack.rosestrades.datagen.ModModelProvider;
import firelack.rosestrades.datagen.ModRecipeProvider;
import firelack.rosestrades.datagen.ModRegistryDataGenerator;
import firelack.rosestrades.world.ModConfiguredFeatures;
import firelack.rosestrades.world.ModPlacedFeatures;
import net.minecraft.registry.RegistryKeys;

public class RosesTradesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
