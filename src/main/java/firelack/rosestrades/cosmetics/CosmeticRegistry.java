package firelack.rosestrades.cosmetics;

import java.util.*;

public class CosmeticRegistry {
    private static final Map<String, Cosmetic> COSMETICS = new HashMap<>();

    public static void register(Cosmetic cosmetic) {
        COSMETICS.put(cosmetic.getId(), cosmetic);
    }

    public static Cosmetic get(String id) {
        return COSMETICS.get(id);
    }

    public static Collection<Cosmetic> all() {
        return COSMETICS.values();
    }

    // Exemple d'initialisation
    public static void init() {
        register(new Cosmetic("hat_red", "Chapeau Rouge", 5));
        register(new Cosmetic("cape_blue", "Cape Bleue", 10));
        register(new Cosmetic("skin_gold", "Peau Dorée", 20));
    }
}
