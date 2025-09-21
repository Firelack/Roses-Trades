package firelack.rosestrades.cosmetics;

import java.util.List;
import java.util.stream.Collectors;
import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;
import net.minecraft.server.network.ServerPlayerEntity;

public class CosmeticShop {

    // All available cosmetics in the shop
    private final List<String> allShopCosmetics = List.of("hat_red", "cape_blue", "skin_gold");

    // Filter cosmetics that the player does not own
    public List<Cosmetic> getAvailableForPlayer(ServerPlayerEntity player) {
        ServerPlayerEntityMixinAccess access = (ServerPlayerEntityMixinAccess) player;

        return allShopCosmetics.stream()
                .filter(id -> !access.getCosmetics().contains(id)) // Filter out owned cosmetics
                .map(CosmeticRegistry::get)
                .collect(Collectors.toList());
    }

    public List<Cosmetic> getAllCosmetics() {
        return CosmeticRegistry.all().stream().toList(); // Return all registered cosmetics
    }

}
