package firelack.rosestrades.block;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;
import firelack.rosestrades.network.RoseCountPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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

            // Send the updated count to the client
            ServerPlayNetworking.send(serverPlayer, new RoseCountPayload(count));

            // Logs
            System.out.println(serverPlayer.getName().getString() + " broke a special rose ! Total = " + count);

            serverPlayer.sendMessage(Text.translatable("message.rosestrades.rose_count", count), true);
        }
    }
}
