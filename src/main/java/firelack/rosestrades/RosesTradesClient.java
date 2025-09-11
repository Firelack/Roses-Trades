package firelack.rosestrades;

import firelack.rosestrades.block.ModBlocks;
import firelack.rosestrades.client.ModKeyBindings;
import firelack.rosestrades.client.gui.CustomMenuScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RosesTradesClient implements ClientModInitializer {

    public static final Identifier ROSE_COUNT_SYNC = Identifier.of("rosestrades", "rose_count");

    @Override
    public void onInitializeClient() {
        // Show the custom blocks with cutout rendering (to handle transparency)
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BOUQUET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BOUQUET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPECIAL_ROSE, RenderLayer.getCutout());

        ModKeyBindings.registerKeyBindings();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ModKeyBindings.OPEN_MENU.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new CustomMenuScreen(0)); // pour l'instant compteur à 0
                }
            }
        });
    }
}
