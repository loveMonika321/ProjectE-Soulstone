/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkRegistry
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.simple.SimpleChannel
 */
package cn.autoforged.projecte_soulstone_1785817675.networking;

import cn.autoforged.projecte_soulstone_1785817675.networking.OpenAlchemyBagPacket;
import cn.autoforged.projecte_soulstone_1785817675.networking.OpenTransmutationPacket;
import cn.autoforged.projecte_soulstone_1785817675.networking.PhaseSyncPacket;
import cn.autoforged.projecte_soulstone_1785817675.networking.SageOrbPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel INSTANCE;
    private static int id;

    public static void register() {
        SimpleChannel channel = NetworkRegistry.newSimpleChannel((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"projecte_soulstone_1785817675", (String)"main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
        channel.messageBuilder(PhaseSyncPacket.class, id++, NetworkDirection.PLAY_TO_CLIENT).encoder(PhaseSyncPacket::encode).decoder(PhaseSyncPacket::new).consumerMainThread(PhaseSyncPacket::handle).add();
        channel.messageBuilder(SageOrbPacket.class, id++, NetworkDirection.PLAY_TO_SERVER).encoder(SageOrbPacket::encode).decoder(SageOrbPacket::new).consumerMainThread(SageOrbPacket::handle).add();
        channel.messageBuilder(OpenTransmutationPacket.class, id++, NetworkDirection.PLAY_TO_SERVER).encoder(OpenTransmutationPacket::encode).decoder(OpenTransmutationPacket::new).consumerMainThread(OpenTransmutationPacket::handle).add();
        channel.messageBuilder(OpenAlchemyBagPacket.class, id++, NetworkDirection.PLAY_TO_SERVER).encoder(OpenAlchemyBagPacket::encode).decoder(OpenAlchemyBagPacket::new).consumerMainThread(OpenAlchemyBagPacket::handle).add();
        INSTANCE = channel;
    }

    public static void sendPhaseSync(ServerPlayer player, boolean active) {
        if (INSTANCE != null && player.f_8906_ != null) {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), (Object)new PhaseSyncPacket(active));
        }
    }

    public static void sendToServer(Object message) {
        if (INSTANCE != null) {
            INSTANCE.sendToServer(message);
        }
    }

    static {
        id = 0;
    }
}

