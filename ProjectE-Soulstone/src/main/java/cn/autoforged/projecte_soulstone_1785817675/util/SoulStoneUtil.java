/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.inolia_zaicek.mine_fargo.Util.MyGoUtil
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 */
package cn.autoforged.projecte_soulstone_1785817675.util;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import com.inolia_zaicek.mine_fargo.Util.MyGoUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public final class SoulStoneUtil {
    private SoulStoneUtil() {
    }

    public static boolean hasEffect(Player player, Item item) {
        return MyGoUtil.isCurioEquipped((LivingEntity)player, (Item)item) || MyGoUtil.isCurioEquipped((LivingEntity)player, (Item)((Item)ModItems.EQUIVALENT_SOUL.get()));
    }
}

