package firelack.rosestrades.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public record CosmeticsPayload(Set<String> cosmetics) implements CustomPayload {
    public static final CustomPayload.Id<CosmeticsPayload> ID =
            new CustomPayload.Id<>(Identifier.of("rosestrades", "cosmetics"));

    public static final PacketCodec<PacketByteBuf, CosmeticsPayload> CODEC =
            PacketCodec.of((value, buf) -> {
                buf.writeInt(value.cosmetics.size());
                for (String cosmetic : value.cosmetics) {
                    buf.writeString(cosmetic);
                }
            }, buf -> {
                int size = buf.readInt();
                Set<String> cosmetics = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    cosmetics.add(buf.readString());
                }
                return new CosmeticsPayload(cosmetics);
            });

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
