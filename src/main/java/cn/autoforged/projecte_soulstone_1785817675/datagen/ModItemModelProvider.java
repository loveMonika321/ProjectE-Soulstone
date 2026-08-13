/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.PackOutput
 *  net.minecraft.world.item.Item
 *  net.minecraftforge.client.model.generators.ItemModelProvider
 *  net.minecraftforge.common.data.ExistingFileHelper
 */
package cn.autoforged.projecte_soulstone_1785817675.datagen;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider
extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, "projecte_soulstone_1785817675", helper);
    }

    protected void registerModels() {
        this.basicItem((Item)ModItems.RED_SOUL_STONE.get());
        this.basicItem((Item)ModItems.DARK_SOUL_STONE.get());
        this.basicItem((Item)ModItems.SAGE_SOUL_STONE.get());
        this.basicItem((Item)ModItems.TRANSMUTATION_SOUL_STONE.get());
        this.basicItem((Item)ModItems.ELEMENTAL_SOUL_STONE.get());
        this.basicItem((Item)ModItems.ALCHEMY_BAG_SOUL_STONE.get());
        this.basicItem((Item)ModItems.EQUIVALENT_SOUL.get());
    }
}

