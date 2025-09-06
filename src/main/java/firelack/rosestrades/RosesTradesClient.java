package firelack.rosestrades;

import firelack.rosestrades.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class RosesTradesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Show the custom blocks with cutout rendering (to handle transparency)
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BOUQUET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BOUQUET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPECIAL_ROSE, RenderLayer.getCutout());

    }
}
