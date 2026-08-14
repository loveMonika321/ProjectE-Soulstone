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

    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.PHASE_PASSABLE).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.BASE_STONE_NETHER).addTag(BlockTags.DIRT).addTag(BlockTags.LOGS).addTag(BlockTags.PLANKS).addTag(BlockTags.COAL_ORES).addTag(BlockTags.IRON_ORES).addTag(BlockTags.GOLD_ORES).addTag(BlockTags.COPPER_ORES).addTag(BlockTags.REDSTONE_ORES).addTag(BlockTags.LAPIS_ORES).addTag(BlockTags.DIAMOND_ORES).addTag(BlockTags.EMERALD_ORES);
    }
}

