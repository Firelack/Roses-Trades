package firelack.rosestrades.registry;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpecialRoseBlock extends FlowerBlock {
    public SpecialRoseBlock(Settings settings) {
        super(StatusEffects.LUCK, 0, settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos,
                           BlockState state, net.minecraft.block.entity.BlockEntity blockEntity,
                           ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);

        if (!world.isClient && player != null) {
            // Incrémente une stat custom pour ce bloc spécifique
            player.incrementStat(Stats.CUSTOM.getOrCreateStat(Registries.BLOCK.getId(this)));
        }
    }
}
