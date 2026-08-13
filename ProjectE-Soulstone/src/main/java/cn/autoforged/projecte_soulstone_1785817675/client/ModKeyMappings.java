/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.InputConstants$Type
 *  net.minecraft.client.KeyMapping
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.RegisterKeyMappingsEvent
 *  net.minecraftforge.client.settings.IKeyConflictContext
 *  net.minecraftforge.client.settings.KeyConflictContext
 *  net.minecraftforge.common.util.Lazy
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package cn.autoforged.projecte_soulstone_1785817675.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="projecte_soulstone_1785817675", bus=Mod.EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class ModKeyMappings {
    public static final Lazy<KeyMapping> SAGE_ORB = Lazy.of(() -> new KeyMapping("key.projecte_soulstone_1785817675.sage_orb", (IKeyConflictContext)KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, 72, "key.categories.projecte_soulstone_1785817675"));
    public static final Lazy<KeyMapping> OPEN_TRANSMUTATION = Lazy.of(() -> new KeyMapping("key.projecte_soulstone_1785817675.open_transmutation", (IKeyConflictContext)KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, 74, "key.categories.projecte_soulstone_1785817675"));
    public static final Lazy<KeyMapping> OPEN_ALCHEMY_BAG = Lazy.of(() -> new KeyMapping("key.projecte_soulstone_1785817675.open_alchemy_bag", (IKeyConflictContext)KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, 75, "key.categories.projecte_soulstone_1785817675"));

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register((KeyMapping)SAGE_ORB.get());
        event.register((KeyMapping)OPEN_TRANSMUTATION.get());
        event.register((KeyMapping)OPEN_ALCHEMY_BAG.get());
    }
}

