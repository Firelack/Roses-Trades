package firelack.rosestrades.mixin;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashSet;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import firelack.rosestrades.access.ServerPlayerEntityMixinAccess;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements ServerPlayerEntityMixinAccess {

    @Unique
    private int specialRoseCount = 0;

    // Add the rose count to the player's NBT data
    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeRoseCount(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SpecialRoseCount", specialRoseCount);
    }

    // Read the rose count from the player's NBT data
    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void readRoseCount(NbtCompound nbt, CallbackInfo ci) {
        specialRoseCount = nbt.getInt("SpecialRoseCount");
    }

    @Override
    public void incrementRoseCount() {
        specialRoseCount++;
    }

    @Override
    public int getRoseCount() {
        return specialRoseCount;
    }

    @Override
    public void decrementRoseCount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to decrement cannot be negative");
        } else if (specialRoseCount < amount) {
            throw new IllegalArgumentException("Cannot decrement more than the current rose count");
        } else {
        specialRoseCount = Math.max(0, specialRoseCount - amount);
        }
    }

    @Unique
    private final Set<String> cosmetics = new HashSet<>();

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SpecialRoseCount", specialRoseCount);

        NbtList list = new NbtList();
        for (String c : cosmetics) {
            list.add(NbtString.of(c));
        }
        nbt.put("Cosmetics", list);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void readData(NbtCompound nbt, CallbackInfo ci) {
        specialRoseCount = nbt.getInt("SpecialRoseCount");

        cosmetics.clear();
        NbtList list = nbt.getList("Cosmetics", NbtElement.STRING_TYPE);
        for (int i = 0; i < list.size(); i++) {
            cosmetics.add(list.getString(i));
        }
    }

    @Override
    public Set<String> getCosmetics() {
        return new HashSet<>(cosmetics);
    }

    @Override
    public void addCosmetic(String id) {
        cosmetics.add(id);
    }
}
