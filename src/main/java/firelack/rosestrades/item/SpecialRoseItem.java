package firelack.rosestrades.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;

public class SpecialRoseItem extends BlockItem {
    public SpecialRoseItem(Settings settings) {
        super(firelack.rosestrades.block.ModBlocks.SPECIAL_ROSE, settings);
    }

@Override
public ActionResult place(ItemPlacementContext context) {
    if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
        context.getPlayer().sendMessage(
            net.minecraft.text.Text.translatable("message.rosestrades.cant_place"), 
            true // true = system message (doesn't appear in chat history)
        );
        return ActionResult.FAIL; // Prevent placement
    }
    return super.place(context);
}
}
