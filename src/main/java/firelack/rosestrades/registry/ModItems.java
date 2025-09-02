package firelack.rosestrades.registry;

import firelack.rosestrades.RosesTrades;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    // Déclare une clé d'enregistrement
    public static final RegistryKey<Item> SPECIAL_ROSE_KEY = RegistryKey.of(
            Registries.ITEM.getKey(),
            Identifier.of(RosesTrades.MOD_ID, "rose")
    );

    // Enregistre l'item et récupère sa référence
    public static final Item SPECIAL_ROSE = Registry.register(
            Registries.ITEM,
            SPECIAL_ROSE_KEY,
            new Item(new Item.Settings().maxCount(64)) // stackable jusqu'à 64
    );

    public static void registerModItems() {
        RosesTrades.LOGGER.info("Registering mod items for " + RosesTrades.MOD_ID);
    }
}
