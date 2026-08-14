/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  top.theillusivec4.curios.api.type.capability.ICurioItem
 */
package cn.autoforged.projecte_soulstone_1785817675.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ElementalSoulStoneItem
extends Item
implements ICurioItem {
    public static final int ATTACKER_SLOWNESS_DURATION = 200;
    public static final int ATTACKER_SLOWNESS_AMPLIFIER = 7;

    public ElementalSoulStoneItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add((Component)Component.translatable((String)"tooltip.projecte_soulstone_1785817675.elemental_soul_stone").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}

