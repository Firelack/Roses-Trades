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
            net.minecraft.text.Text.literal("Vous ne pouvez pas placer cette rose en survie !"), 
            true // true = message système, ne spam pas le chat
        );
        return ActionResult.FAIL; // Empêche le placement
    }
    return super.place(context);
}
}
