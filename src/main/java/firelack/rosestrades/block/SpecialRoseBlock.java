package firelack.rosestrades.block;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
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

        if (!world.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            ((ServerPlayerEntityMixinAccess) serverPlayer).incrementRoseCount();
            int count = ((ServerPlayerEntityMixinAccess) serverPlayer).getRoseCount();

            // Log console
            System.out.println(serverPlayer.getName().getString() + " a cassé une Special Rose ! Total = " + count);

            // Message en jeu
            serverPlayer.sendMessage(Text.literal("Roses cassées : " + count), true);
        }
    }
}
