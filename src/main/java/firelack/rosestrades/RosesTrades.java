package firelack.rosestrades;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import firelack.rosestrades.block.ModBlocks;
import firelack.rosestrades.item.ModItems;
import firelack.rosestrades.world.gen.ModWorldGeneration;

public class RosesTrades implements ModInitializer {
    public static final String MOD_ID = "rosestrades";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Roses Trades mod initializing...");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModWorldGeneration.generateWorldGen();
    }
}
