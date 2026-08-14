/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 */
package cn.autoforged.projecte_soulstone_1785817675.client;

import cn.autoforged.projecte_soulstone_1785817675.networking.PhaseSyncPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class ClientPacketHandler {
    public static void handlePhaseSync(PhaseSyncPacket msg) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            player.noPhysics = msg.isActive();
        }
    }
}

