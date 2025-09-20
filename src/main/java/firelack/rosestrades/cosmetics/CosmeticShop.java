package firelack.rosestrades.cosmetics;

import java.util.List;

public class CosmeticShop {
    private final List<String> available = List.of("hat_red", "cape_blue");

    public List<Cosmetic> getAvailableCosmetics() {
        return available.stream().map(CosmeticRegistry::get).toList();
    }
}
