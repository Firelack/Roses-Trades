package firelack.rosestrades;

import firelack.rosestrades.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class RosesTradesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // On dit à Minecraft que le bouquet utilise du cutout (comme les fleurs vanilla)
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BOUQUET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BOUQUET, RenderLayer.getCutout());
    }
}
