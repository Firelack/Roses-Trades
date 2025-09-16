package firelack.rosestrades.network;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public class RosesTradesServerInit {

    public static void init() {
        // When a player joins the server, send them their current rose count
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.player;
            int count = ((ServerPlayerEntityMixinAccess) player).getRoseCount();

            // Send the packet
            ServerPlayNetworking.send(player, new RoseCountPayload(count));
        });
    }
}
