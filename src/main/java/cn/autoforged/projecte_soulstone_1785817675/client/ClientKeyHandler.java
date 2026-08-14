/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.KeyMapping
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package cn.autoforged.projecte_soulstone_1785817675.client;

import cn.autoforged.projecte_soulstone_1785817675.client.ModKeyMappings;
import cn.autoforged.projecte_soulstone_1785817675.client.gui.AlchemyBagSelectorScreen;
import cn.autoforged.projecte_soulstone_1785817675.networking.ModMessages;
import cn.autoforged.projecte_soulstone_1785817675.networking.OpenTransmutationPacket;
import cn.autoforged.projecte_soulstone_1785817675.networking.SageOrbPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="projecte_soulstone_1785817675", value={Dist.CLIENT})
public class ClientKeyHandler {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }
        while (((KeyMapping)ModKeyMappings.SAGE_ORB.get()).consumeClick()) {
            ModMessages.sendToServer(new SageOrbPacket());
        }
        while (((KeyMapping)ModKeyMappings.OPEN_TRANSMUTATION.get()).consumeClick()) {
            ModMessages.sendToServer(new OpenTransmutationPacket());
        }
        while (((KeyMapping)ModKeyMappings.OPEN_ALCHEMY_BAG.get()).consumeClick()) {
            mc.setScreen((Screen)new AlchemyBagSelectorScreen());
        }
    }
}

