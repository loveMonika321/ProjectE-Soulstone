/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package cn.autoforged.projecte_soulstone_1785817675.networking;

import cn.autoforged.projecte_soulstone_1785817675.networking.ServerPacketHandlers;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class OpenAlchemyBagPacket {
    private final int colorId;

    public OpenAlchemyBagPacket(int colorId) {
        this.colorId = colorId;
    }

    public OpenAlchemyBagPacket(FriendlyByteBuf buf) {
        this.colorId = buf.readByte();
    }

    public int getColorId() {
        return this.colorId;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeByte(this.colorId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> ServerPacketHandlers.handleOpenAlchemyBag(ctx.getSender(), this.colorId));
        ctx.setPacketHandled(true);
        return true;
    }
}

