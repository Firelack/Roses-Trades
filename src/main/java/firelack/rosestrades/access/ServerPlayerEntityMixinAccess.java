package firelack.rosestrades.access;

public interface ServerPlayerEntityMixinAccess {
    void incrementRoseCount();
    int getRoseCount();
    void decrementRoseCount(int amount);
    void addCosmetic(String id);
    java.util.Set<String> getCosmetics();
}
