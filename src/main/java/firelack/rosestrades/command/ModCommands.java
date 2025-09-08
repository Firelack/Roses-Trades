package firelack.rosestrades.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;

public class ModCommands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("roses")
            // /roses
            .executes(ctx -> {
                ServerPlayerEntity player = ctx.getSource().getPlayer();
                int count = ((ServerPlayerEntityMixinAccess) player).getRoseCount();
                player.sendMessage(Text.literal("Tu as cassé " + count + " roses."), false);
                return 1;
            })

            // /roses leaderboard
            .then(CommandManager.literal("leaderboard")
                .executes(ctx -> showLeaderboard(ctx.getSource(), 0))

                // /roses leaderboard <offset>
                .then(CommandManager.argument("offset", IntegerArgumentType.integer(0))
                    .executes(ctx -> {
                        int offset = IntegerArgumentType.getInteger(ctx, "offset");
                        return showLeaderboard(ctx.getSource(), offset);
                    })
                )
            )
        );
    }

    private static int showLeaderboard(ServerCommandSource source, int offset) {
        List<ServerPlayerEntity> players = source.getServer().getPlayerManager().getPlayerList();

        // Order players by rose count descending
        List<ServerPlayerEntity> sorted = players.stream()
            .sorted(Comparator.comparingInt(
                (ServerPlayerEntity p) -> ((ServerPlayerEntityMixinAccess) p).getRoseCount()
            ).reversed())
            .collect(Collectors.toList());

        if (sorted.isEmpty()) {
            source.sendFeedback(() -> Text.literal("Aucun joueur trouvé."), false);
            return 0;
        }

        ServerPlayerEntity self = source.getPlayer();
        if (self == null) return 0;

        // If offset is too large, reset to 0
        if (offset >= sorted.size()) {
            self.sendMessage(Text.literal("Offset trop grand, affichage du top 5 à la place."), false);
            offset = 0;
        }

        int max = Math.min(offset + 5, sorted.size());

        self.sendMessage(Text.literal("=== Classement des Roses (" + (offset + 1) + "-" + max + ") ==="), false);

        for (int i = offset; i < max; i++) {
            ServerPlayerEntity p = sorted.get(i);
            int count = ((ServerPlayerEntityMixinAccess) p).getRoseCount();
            self.sendMessage(Text.literal((i + 1) + ". " + p.getName().getString() + " - " + count), false);
        }

        // Always show the player's own rank
        int selfIndex = sorted.indexOf(self);
        if (selfIndex >= 0) {
            int selfCount = ((ServerPlayerEntityMixinAccess) self).getRoseCount();
            self.sendMessage(Text.literal("Ton rang : #" + (selfIndex + 1) + " avec " + selfCount + " roses."), false);
        }

        return 1;
    }
}
