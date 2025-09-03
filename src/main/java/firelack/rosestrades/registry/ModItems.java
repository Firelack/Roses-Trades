package firelack.rosestrades.registry;

import firelack.rosestrades.RosesTrades;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

        public class ModItems {
        public static final Item SPECIAL_ROSE = Registry.register(
                Registries.ITEM,
                Identifier.of(RosesTrades.MOD_ID, "special_rose"),
                new SpecialRoseItem(new Item.Settings().maxCount(64))
        );

        public static final Item ROSE = Registry.register(
                Registries.ITEM,
                Identifier.of(RosesTrades.MOD_ID, "rose"),
                new RoseItem(new Item.Settings().maxCount(64))
        );

        public static final Item BOUQUET = Registry.register(
                Registries.ITEM,
                Identifier.of(RosesTrades.MOD_ID, "bouquet"),
                new Item(new Item.Settings().maxCount(64))
        );

        public static void registerModItems() {
                RosesTrades.LOGGER.info("Registering mod items for " + RosesTrades.MOD_ID);
        }
        }
