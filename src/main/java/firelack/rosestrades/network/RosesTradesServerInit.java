package firelack.rosestrades.network;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;
import firelack.rosestrades.block.ModBlocks;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import firelack.rosestrades.cosmetics.Cosmetic;
import firelack.rosestrades.cosmetics.CosmeticRegistry;

public class RosesTradesServerInit {

    public static void init() {

        // Save the rose spending and cosmetic buying packet types
        PayloadTypeRegistry.playC2S().register(RoseSpendPayload.ID, RoseSpendPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(BuyCosmeticPayload.ID, BuyCosmeticPayload.CODEC);

        // When a player joins, send them their rose count and owned cosmetics
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.player;
            ServerPlayerEntityMixinAccess access = (ServerPlayerEntityMixinAccess) player;

            // Send current rose count
            ServerPlayNetworking.send(player, new RoseCountPayload(access.getRoseCount()));

            // Send owned cosmetics
            ServerPlayNetworking.send(player, new CosmeticsPayload(access.getCosmetics()));
        });

        // Use the rose spending packet
        ServerPlayNetworking.registerGlobalReceiver(RoseSpendPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            ServerPlayerEntityMixinAccess access = (ServerPlayerEntityMixinAccess) player;

            int amount = payload.amount();
            int current = access.getRoseCount();

            if (current >= amount) {
                access.decrementRoseCount(amount);

                ItemStack roses = new ItemStack(ModBlocks.ROSE, amount);
                if (!player.getInventory().insertStack(roses)) {
                    player.dropItem(roses, false);
                }

                player.sendMessage(
                        Text.translatable("message.rosestrades.spend.success", amount)
                                .formatted(Formatting.GREEN),
                        false);
            } else {
                player.sendMessage(
                        Text.translatable("message.rosestrades.spend.fail", amount)
                                .formatted(Formatting.RED),
                        false);
            }

            // Update the client with the new rose count
            ServerPlayNetworking.send(player, new RoseCountPayload(access.getRoseCount()));
        });

        // Buy cosmetic packet
        ServerPlayNetworking.registerGlobalReceiver(BuyCosmeticPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            ServerPlayerEntityMixinAccess access = (ServerPlayerEntityMixinAccess) player;
            String id = payload.cosmeticId();

            Cosmetic cosmetic = CosmeticRegistry.get(id);
            if (cosmetic != null && access.getRoseCount() >= cosmetic.getPrice()) {
                // Apply the cosmetic purchase cost
                access.decrementRoseCount(cosmetic.getPrice());

                // Add the cosmetic to the player's owned cosmetics
                access.addCosmetic(id);

                // Send updated data to the client
                ServerPlayNetworking.send(player, new CosmeticsPayload(access.getCosmetics()));
                ServerPlayNetworking.send(player, new RoseCountPayload(access.getRoseCount()));

                player.sendMessage(
                        Text.translatable("message.rosestrades.buy.success", cosmetic.getName())
                                .formatted(Formatting.GREEN),
                        false);
            } else {
                player.sendMessage(
                        Text.translatable("message.rosestrades.buy.fail", cosmetic != null ? cosmetic.getName() : id)
                                .formatted(Formatting.RED),
                        false);
            }
        });
    }
}
