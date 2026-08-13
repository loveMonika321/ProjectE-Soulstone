/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package cn.autoforged.projecte_soulstone_1785817675.mixin;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import cn.autoforged.projecte_soulstone_1785817675.util.SoulStoneUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LivingEntity.class})
public class PowderSnowImmunityMixin {
    @Inject(method={"canFreeze"}, at={@At(value="HEAD")}, cancellable=true)
    private void preventFreezing(CallbackInfoReturnable<Boolean> cir) {
        Player player;
        PowderSnowImmunityMixin powderSnowImmunityMixin = this;
        if (powderSnowImmunityMixin instanceof Player && SoulStoneUtil.hasEffect(player = (Player)powderSnowImmunityMixin, (Item)ModItems.ELEMENTAL_SOUL_STONE.get())) {
            cir.setReturnValue((Object)false);
        }
    }
}

