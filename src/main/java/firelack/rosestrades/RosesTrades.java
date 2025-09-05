package firelack.rosestrades;

import firelack.rosestrades.registry.ModBlocks;
import firelack.rosestrades.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RosesTrades implements ModInitializer {
    public static final String MOD_ID = "rosestrades";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Roses Trades mod initializing...");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
    }
}
