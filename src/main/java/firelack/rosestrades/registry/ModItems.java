package firelack.rosestrades.registry;

import firelack.rosestrades.RosesTrades;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SPECIAL_ROSE = Registry.register(
            Registries.ITEM,
            Identifier.of(RosesTrades.MOD_ID, "special_rose"),
            new SpecialRoseItem(new Item.Settings().maxCount(64))
    );

    public static void registerModItems() {
        RosesTrades.LOGGER.info("Registering mod items for " + RosesTrades.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(SPECIAL_ROSE);});
    }
}
