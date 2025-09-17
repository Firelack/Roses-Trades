package firelack.rosestrades.network;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

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

            try {
                access.decrementRoseCount(amount);
            } catch (IllegalArgumentException e) {
                // If trying to spend more roses than available, ignore
            }

            // Send back the updated count
            int newCount = ((ServerPlayerEntityMixinAccess) player).getRoseCount();
            ServerPlayNetworking.send(player, new RoseCountPayload(newCount));
        });
    }
}
