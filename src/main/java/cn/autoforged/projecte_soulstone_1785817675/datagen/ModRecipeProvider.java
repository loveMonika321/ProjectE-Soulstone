/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriterionTriggerInstance
 *  net.minecraft.data.PackOutput
 *  net.minecraft.data.recipes.FinishedRecipe
 *  net.minecraft.data.recipes.RecipeCategory
 *  net.minecraft.data.recipes.RecipeProvider
 *  net.minecraft.data.recipes.ShapedRecipeBuilder
 *  net.minecraft.data.recipes.ShapelessRecipeBuilder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.registries.ForgeRegistries
 */
package cn.autoforged.projecte_soulstone_1785817675.datagen;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import java.util.function.Consumer;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeProvider
extends RecipeProvider {
    private static final Item EMPTY_SOUL_STONE = ModRecipeProvider.item("mine_fargo", "empty_soul_stone");
    private static final Item PHILOSOPHERS_STONE = ModRecipeProvider.item("projecte", "philosophers_stone");
    private static final Item TRANSMUTATION_TABLET = ModRecipeProvider.item("projecte", "transmutation_tablet");
    private static final Item DARK_MATTER_BLOCK = ModRecipeProvider.item("projecte", "dark_matter_block");
    private static final Item ARCANA_RING = ModRecipeProvider.item("projecte", "arcana_ring");

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    protected void m_245200_(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.m_245327_((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.SAGE_SOUL_STONE.get())).m_126130_("PPP").m_126130_("PEP").m_126130_("PPP").m_126127_(Character.valueOf('P'), (ItemLike)PHILOSOPHERS_STONE).m_126127_(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).m_126132_("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.m_125977_((ItemLike)EMPTY_SOUL_STONE)).m_176498_(writer);
        ShapedRecipeBuilder.m_245327_((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.TRANSMUTATION_SOUL_STONE.get())).m_126130_("DTD").m_126130_("TET").m_126130_("DTD").m_126127_(Character.valueOf('D'), (ItemLike)DARK_MATTER_BLOCK).m_126127_(Character.valueOf('T'), (ItemLike)TRANSMUTATION_TABLET).m_126127_(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).m_126132_("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.m_125977_((ItemLike)EMPTY_SOUL_STONE)).m_176498_(writer);
        ShapedRecipeBuilder.m_245327_((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.ELEMENTAL_SOUL_STONE.get())).m_126130_(" A ").m_126130_("AEA").m_126130_(" A ").m_126127_(Character.valueOf('A'), (ItemLike)ARCANA_RING).m_126127_(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).m_126132_("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.m_125977_((ItemLike)EMPTY_SOUL_STONE)).m_176498_(writer);
        ShapedRecipeBuilder.m_245327_((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.ALCHEMY_BAG_SOUL_STONE.get())).m_126130_("BBB").m_126130_("BEB").m_126130_("BBB").m_206416_(Character.valueOf('B'), ItemTags.create((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"projecte", (String)"alchemical_bags"))).m_126127_(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).m_126132_("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.m_125977_((ItemLike)EMPTY_SOUL_STONE)).m_176498_(writer);
        ShapelessRecipeBuilder.m_245498_((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.EQUIVALENT_SOUL.get())).m_126209_((ItemLike)ModItems.RED_SOUL_STONE.get()).m_126209_((ItemLike)ModItems.DARK_SOUL_STONE.get()).m_126209_((ItemLike)ModItems.SAGE_SOUL_STONE.get()).m_126209_((ItemLike)ModItems.TRANSMUTATION_SOUL_STONE.get()).m_126209_((ItemLike)ModItems.ELEMENTAL_SOUL_STONE.get()).m_126209_((ItemLike)ModItems.ALCHEMY_BAG_SOUL_STONE.get()).m_126132_("has_red_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.m_125977_((ItemLike)((ItemLike)ModItems.RED_SOUL_STONE.get()))).m_176498_(writer);
    }

    private static Item item(String namespace, String path) {
        Item item = (Item)ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath((String)namespace, (String)path));
        if (item == null || item == Items.f_41852_) {
            throw new IllegalStateException("\u524d\u7f6e\u7269\u54c1\u4e0d\u5b58\u5728: " + namespace + ":" + path);
        }
        return item;
    }
}

