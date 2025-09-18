package firelack.rosestrades.network;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import firelack.rosestrades.block.ModBlocks;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class RosesTradesServerInit {

    public static void init() {

        PayloadTypeRegistry.playC2S().register(RoseSpendPayload.ID, RoseSpendPayload.CODEC);

        // When a player joins, send them their current rose count
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.player;
            int count = ((ServerPlayerEntityMixinAccess) player).getRoseCount();

            // Send the packet
            ServerPlayNetworking.send(player, new RoseCountPayload(count));
        });

        // When a player wants to spend roses
        ServerPlayNetworking.registerGlobalReceiver(RoseSpendPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            ServerPlayerEntityMixinAccess access = (ServerPlayerEntityMixinAccess) player;

            int amount = payload.amount();

            context.server().execute(() -> {
                int current = access.getRoseCount();

                if (current >= amount) {
                    access.decrementRoseCount(amount);

                    ItemStack roses = new ItemStack(ModBlocks.ROSE, amount);
                    if (!player.getInventory().insertStack(roses)) {
                        player.dropItem(roses, false);
                    }

                    player.sendMessage(
                            Text.translatable("message.rosestrades.spend.success", amount).formatted(Formatting.GREEN),
                            false);
                } else {
                    player.sendMessage(
                            Text.translatable("message.rosestrades.spend.fail", amount).formatted(Formatting.RED),
                            false);
                }

                // Send updated count
                int newCount = access.getRoseCount();
                ServerPlayNetworking.send(player, new RoseCountPayload(newCount));
            });
        });
    }
}
