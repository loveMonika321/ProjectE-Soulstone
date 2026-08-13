/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.HolderLookup$Provider
 *  net.minecraft.data.PackOutput
 *  net.minecraft.tags.BlockTags
 *  net.minecraftforge.common.data.BlockTagsProvider
 *  net.minecraftforge.common.data.ExistingFileHelper
 */
package cn.autoforged.projecte_soulstone_1785817675.datagen;

import cn.autoforged.projecte_soulstone_1785817675.util.ModTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagProvider
extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(output, lookupProvider, "projecte_soulstone_1785817675", helper);
    }

    protected void m_6577_(HolderLookup.Provider provider) {
        this.m_206424_(ModTags.PHASE_PASSABLE).m_206428_(BlockTags.f_13061_).m_206428_(BlockTags.f_13062_).m_206428_(BlockTags.f_144274_).m_206428_(BlockTags.f_13106_).m_206428_(BlockTags.f_13090_).m_206428_(BlockTags.f_144262_).m_206428_(BlockTags.f_144258_).m_206428_(BlockTags.f_13043_).m_206428_(BlockTags.f_144264_).m_206428_(BlockTags.f_144260_).m_206428_(BlockTags.f_144261_).m_206428_(BlockTags.f_144259_).m_206428_(BlockTags.f_144263_);
    }
}

