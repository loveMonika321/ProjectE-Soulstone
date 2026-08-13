/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.BrewingStandBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package cn.autoforged.projecte_soulstone_1785817675.mixin;

import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={BrewingStandBlockEntity.class})
public interface BrewingStandBlockEntityAccessor {
    @Accessor(value="fuel")
    public void setFuel(int var1);
}

