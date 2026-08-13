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

public class RedSoulStoneItem
extends Item
implements ICurioItem {
    public static final float MINING_SPEED_MULTIPLIER = 6.0f;
    public static final float ATTACK_DAMAGE_MULTIPLIER = 3.0f;
    public static final double DISINTEGRATE_CHANCE = 0.33;
    public static final float DISINTEGRATE_DAMAGE_PERCENT = 0.05f;
    public static final int DISINTEGRATE_COOLDOWN_TICKS = 20;

    public RedSoulStoneItem() {
        super(new Item.Properties().m_41487_(1).m_41497_(Rarity.EPIC));
    }

    public void m_7373_(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add((Component)Component.m_237115_((String)"tooltip.projecte_soulstone_1785817675.red_soul_stone").m_130940_(ChatFormatting.GRAY));
        super.m_7373_(stack, level, tooltipComponents, isAdvanced);
    }
}

