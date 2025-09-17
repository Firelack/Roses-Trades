package firelack.rosestrades.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record RoseSpendPayload(int amount) implements CustomPayload {
    public static final Id<RoseSpendPayload> ID =
            new Id<>(Identifier.of("rosestrades", "rose_spend"));

    public static final PacketCodec<PacketByteBuf, RoseSpendPayload> CODEC =
            PacketCodec.of((value, buf) -> buf.writeInt(value.amount),
                           buf -> new RoseSpendPayload(buf.readInt()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
