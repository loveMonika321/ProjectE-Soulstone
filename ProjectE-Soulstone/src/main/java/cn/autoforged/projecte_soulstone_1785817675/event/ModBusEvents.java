/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.CreativeModeTabs
 *  net.minecraftforge.event.BuildCreativeModeTabContentsEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package cn.autoforged.projecte_soulstone_1785817675.event;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="projecte_soulstone_1785817675", bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.f_256869_) {
            event.accept(ModItems.RED_SOUL_STONE);
            event.accept(ModItems.DARK_SOUL_STONE);
            event.accept(ModItems.SAGE_SOUL_STONE);
            event.accept(ModItems.TRANSMUTATION_SOUL_STONE);
            event.accept(ModItems.ELEMENTAL_SOUL_STONE);
            event.accept(ModItems.ALCHEMY_BAG_SOUL_STONE);
            event.accept(ModItems.EQUIVALENT_SOUL);
        }
    }
}

