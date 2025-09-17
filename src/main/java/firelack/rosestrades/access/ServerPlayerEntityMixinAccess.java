package firelack.rosestrades.access;

public interface ServerPlayerEntityMixinAccess {
    void incrementRoseCount();
    int getRoseCount();
    void decrementRoseCount(int amount);
}
