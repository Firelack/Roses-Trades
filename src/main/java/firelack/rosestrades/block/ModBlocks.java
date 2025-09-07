package firelack.rosestrades.block;

import firelack.rosestrades.RosesTrades;
import firelack.rosestrades.item.SpecialRoseItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Declatation of the "bouquet" block
    public static final Block BOUQUET = Registry.register(
        Registries.BLOCK,
        Identifier.of(RosesTrades.MOD_ID, "bouquet"),
        new FlowerBlock(StatusEffects.LUCK, 0, AbstractBlock.Settings.copy(Blocks.POPPY))
    );

    // Declatation of the "potted_bouquet" block
    public static final Block POTTED_BOUQUET = Registry.register(
        Registries.BLOCK,
        Identifier.of(RosesTrades.MOD_ID, "potted_bouquet"),
        new FlowerPotBlock(BOUQUET, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY))
    );

    // Declaration of the "bouquet" block item
    public static final Item BOUQUET_ITEM = Registry.register(
        Registries.ITEM,
        Identifier.of(RosesTrades.MOD_ID, "bouquet"),
        new BlockItem(BOUQUET, new Item.Settings())
    );

    // Declatation of the "rose" block
    public static final Block ROSE = Registry.register(
        Registries.BLOCK,
        Identifier.of(RosesTrades.MOD_ID, "rose"),
        new FlowerBlock(StatusEffects.LUCK, 0, AbstractBlock.Settings.copy(Blocks.POPPY))
    );

    // Declatation of the "potted_rose" block
    public static final Block POTTED_ROSE = Registry.register(
        Registries.BLOCK,
        Identifier.of(RosesTrades.MOD_ID, "potted_rose"),
        new FlowerPotBlock(ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY))
    );

    // Declaration of the "rose" block item
    public static final Item ROSE_ITEM = Registry.register(
        Registries.ITEM,
        Identifier.of(RosesTrades.MOD_ID, "rose"),
        new BlockItem(ROSE, new Item.Settings())
    );

    // Declatation of the "special_rose" block
    public static final SpecialRoseBlock SPECIAL_ROSE = Registry.register(
        Registries.BLOCK,
        Identifier.of(RosesTrades.MOD_ID, "special_rose"),
        new SpecialRoseBlock(AbstractBlock.Settings.copy(Blocks.POPPY))
    );

    // Declaration of the "special_rose" block item
    public static final Item SPECIAL_ROSE_ITEM = Registry.register(
        Registries.ITEM,
        Identifier.of(RosesTrades.MOD_ID, "special_rose"),
        new SpecialRoseItem(new Item.Settings())
    );

    // Method to register the blocks and add them to the item group
    public static void registerModBlocks() {
        RosesTrades.LOGGER.info("Registering mod blocks for " + RosesTrades.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(SPECIAL_ROSE);
            entries.add(ROSE);
            entries.add(BOUQUET);
        });
    }
}
