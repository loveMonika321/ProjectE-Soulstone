/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package cn.autoforged.projecte_soulstone_1785817675.networking;

import cn.autoforged.projecte_soulstone_1785817675.client.ClientPacketHandler;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class PhaseSyncPacket {
    private final boolean active;

    public PhaseSyncPacket(boolean active) {
        this.active = active;
    }

    public PhaseSyncPacket(FriendlyByteBuf buf) {
        this.active = buf.readBoolean();
    }

    public boolean isActive() {
        return this.active;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(this.active);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> ClientPacketHandler.handlePhaseSync(this)));
        ctx.setPacketHandled(true);
        return true;
    }
}

