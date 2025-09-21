package firelack.rosestrades;

import java.util.HashSet;
import java.util.Set;

import firelack.rosestrades.block.ModBlocks;
import firelack.rosestrades.client.ModKeyBindings;
import firelack.rosestrades.client.gui.CustomMenuScreen;
import firelack.rosestrades.network.CosmeticsPayload;
import firelack.rosestrades.network.RoseCountPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class RosesTradesClient implements ClientModInitializer {

    public static int clientRoseCount = 0;
    public static Set<String> clientCosmetics = new HashSet<>();

    @Override
    public void onInitializeClient() {
        // Show the custom blocks with cutout rendering (to handle transparency)
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BOUQUET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BOUQUET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPECIAL_ROSE, RenderLayer.getCutout());

        ModKeyBindings.registerKeyBindings();

        // Register the custom packet
        PayloadTypeRegistry.playS2C().register(RoseCountPayload.ID, RoseCountPayload.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(RoseCountPayload.ID,
            (payload, context) -> {
                // Update the client-side rose count when the packet is received
                clientRoseCount = payload.count();
            });

        // Register cosmetics packet
        PayloadTypeRegistry.playS2C().register(CosmeticsPayload.ID, CosmeticsPayload.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(CosmeticsPayload.ID,
            (payload, context) -> {
                clientCosmetics = payload.cosmetics();
            });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ModKeyBindings.OPEN_MENU.wasPressed()) {
                if (client.player != null) {
                    // Show the custom menu screen with the current rose count
                    client.setScreen(new CustomMenuScreen(clientRoseCount));
                }
            }
        });
}
}
