package firelack.rosestrades.cosmetics;

import java.util.HashSet;
import java.util.Set;

public class PlayerCosmeticInventory {
    private final Set<String> ownedCosmetics = new HashSet<>();

    public boolean owns(String id) {
        return ownedCosmetics.contains(id);
    }

    public void add(String id) {
        ownedCosmetics.add(id);
    }

    public Set<String> getOwnedCosmetics() {
        return ownedCosmetics;
    }
}
