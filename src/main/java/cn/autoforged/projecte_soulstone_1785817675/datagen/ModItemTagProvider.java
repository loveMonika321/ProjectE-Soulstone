/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.HolderLookup$Provider
 *  net.minecraft.data.PackOutput
 *  net.minecraft.data.tags.ItemTagsProvider
 *  net.minecraft.data.tags.TagsProvider$TagLookup
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.common.data.ExistingFileHelper
 */
package cn.autoforged.projecte_soulstone_1785817675.datagen;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import cn.autoforged.projecte_soulstone_1785817675.util.ModTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagProvider
extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, ExistingFileHelper helper) {
        super(output, lookupProvider, blockTags, "projecte_soulstone_1785817675", helper);
    }

    protected void m_6577_(HolderLookup.Provider provider) {
        this.m_206424_(ModTags.CURIOS_SOUL_STONE).m_255245_((Object)((Item)ModItems.RED_SOUL_STONE.get())).m_255245_((Object)((Item)ModItems.DARK_SOUL_STONE.get())).m_255245_((Object)((Item)ModItems.SAGE_SOUL_STONE.get())).m_255245_((Object)((Item)ModItems.TRANSMUTATION_SOUL_STONE.get())).m_255245_((Object)((Item)ModItems.ELEMENTAL_SOUL_STONE.get())).m_255245_((Object)((Item)ModItems.ALCHEMY_BAG_SOUL_STONE.get())).m_255245_((Object)((Item)ModItems.EQUIVALENT_SOUL.get()));
    }
}

