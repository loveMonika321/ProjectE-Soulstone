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

public class SageOrbPacket {
    public SageOrbPacket() {
    }

    public SageOrbPacket(FriendlyByteBuf buf) {
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> ServerPacketHandlers.handleSageOrb(ctx.getSender()));
        ctx.setPacketHandled(true);
        return true;
    }
}

