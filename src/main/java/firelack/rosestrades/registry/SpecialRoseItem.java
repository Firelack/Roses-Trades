package firelack.rosestrades.registry;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpecialRoseItem extends Item {
    public SpecialRoseItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && miner instanceof PlayerEntity player) {
            // TODO : ajouter +1 au compteur plus tard
            // Ex : RosesCounter.increment(player);
        }
        // Empêche le drop de l'item (comportement spécial)
        return false;
    }
}
