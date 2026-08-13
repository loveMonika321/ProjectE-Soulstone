/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.Block
 */
package cn.autoforged.projecte_soulstone_1785817675.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> PHASE_PASSABLE = BlockTags.create((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"projecte_soulstone_1785817675", (String)"phase_passable"));
    public static final TagKey<Item> CURIOS_SOUL_STONE = ItemTags.create((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"curios", (String)"soul_stone"));
}

