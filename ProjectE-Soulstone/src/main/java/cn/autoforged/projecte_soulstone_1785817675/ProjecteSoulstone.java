/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.DataGenerator
 *  net.minecraft.data.DataProvider
 *  net.minecraftforge.common.data.ExistingFileHelper
 *  net.minecraftforge.data.event.GatherDataEvent
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
 *  net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
 */
package cn.autoforged.projecte_soulstone_1785817675;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import cn.autoforged.projecte_soulstone_1785817675.datagen.ModBlockTagProvider;
import cn.autoforged.projecte_soulstone_1785817675.datagen.ModItemModelProvider;
import cn.autoforged.projecte_soulstone_1785817675.datagen.ModItemTagProvider;
import cn.autoforged.projecte_soulstone_1785817675.datagen.ModRecipeProvider;
import cn.autoforged.projecte_soulstone_1785817675.networking.ModMessages;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value="projecte_soulstone_1785817675")
public class ProjecteSoulstone {
    public static final String MOD_ID = "projecte_soulstone_1785817675";

    public ProjecteSoulstone() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModMessages::register);
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();
        generator.addProvider(client, (DataProvider)new ModItemModelProvider(generator.getPackOutput(), helper));
        generator.addProvider(server, (DataProvider)new ModRecipeProvider(generator.getPackOutput()));
        ModBlockTagProvider blockTags = (ModBlockTagProvider)generator.addProvider(server, (DataProvider)new ModBlockTagProvider(generator.getPackOutput(), event.getLookupProvider(), helper));
        generator.addProvider(server, (DataProvider)new ModItemTagProvider(generator.getPackOutput(), event.getLookupProvider(), blockTags.m_274426_(), helper));
    }
}

