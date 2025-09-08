package firelack.rosestrades.mixin;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
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

    // Sérialiser le compteur dans le NBT du joueur
    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeRoseCount(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SpecialRoseCount", specialRoseCount);
    }

    // Lire le compteur depuis le NBT du joueur
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
}
