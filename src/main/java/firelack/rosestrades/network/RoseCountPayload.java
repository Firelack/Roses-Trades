package firelack.rosestrades.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record RoseCountPayload(int count) implements CustomPayload {
    public static final CustomPayload.Id<RoseCountPayload> ID =
            new CustomPayload.Id<>(Identifier.of("rosestrades", "rose_count"));

    public static final PacketCodec<PacketByteBuf, RoseCountPayload> CODEC =
            PacketCodec.of((value, buf) -> buf.writeInt(value.count),
                           buf -> new RoseCountPayload(buf.readInt()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
