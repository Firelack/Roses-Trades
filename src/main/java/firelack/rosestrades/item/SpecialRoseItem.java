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
            return ActionResult.FAIL; // interdit si pas créatif
        }
        return super.place(context);
    }
}
