package firelack.rosestrades.network;

import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public record BuyCosmeticPayload(String cosmeticId) implements CustomPayload {
    public static final CustomPayload.Id<BuyCosmeticPayload> ID =
        new CustomPayload.Id<>(Identifier.of("rosestrades", "buy_cosmetic"));

    public static final PacketCodec<PacketByteBuf, BuyCosmeticPayload> CODEC =
        PacketCodec.of(
            (value, buf) -> buf.writeString(value.cosmeticId()),
            buf -> new BuyCosmeticPayload(buf.readString())
        );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
