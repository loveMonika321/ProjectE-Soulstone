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

    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.SAGE_SOUL_STONE.get())).pattern("PPP").pattern("PEP").pattern("PPP").define(Character.valueOf('P'), (ItemLike)PHILOSOPHERS_STONE).define(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).unlockedBy("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.has((ItemLike)EMPTY_SOUL_STONE)).save(writer);
        ShapedRecipeBuilder.shaped((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.TRANSMUTATION_SOUL_STONE.get())).pattern("DTD").pattern("TET").pattern("DTD").define(Character.valueOf('D'), (ItemLike)DARK_MATTER_BLOCK).define(Character.valueOf('T'), (ItemLike)TRANSMUTATION_TABLET).define(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).unlockedBy("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.has((ItemLike)EMPTY_SOUL_STONE)).save(writer);
        ShapedRecipeBuilder.shaped((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.ELEMENTAL_SOUL_STONE.get())).pattern(" A ").pattern("AEA").pattern(" A ").define(Character.valueOf('A'), (ItemLike)ARCANA_RING).define(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).unlockedBy("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.has((ItemLike)EMPTY_SOUL_STONE)).save(writer);
        ShapedRecipeBuilder.shaped((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.ALCHEMY_BAG_SOUL_STONE.get())).pattern("BBB").pattern("BEB").pattern("BBB").define(Character.valueOf('B'), ItemTags.create((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"projecte", (String)"alchemical_bags"))).define(Character.valueOf('E'), (ItemLike)EMPTY_SOUL_STONE).unlockedBy("has_empty_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.has((ItemLike)EMPTY_SOUL_STONE)).save(writer);
        ShapelessRecipeBuilder.shapeless((RecipeCategory)RecipeCategory.TOOLS, (ItemLike)((ItemLike)ModItems.EQUIVALENT_SOUL.get())).requires((ItemLike)ModItems.RED_SOUL_STONE.get()).requires((ItemLike)ModItems.DARK_SOUL_STONE.get()).requires((ItemLike)ModItems.SAGE_SOUL_STONE.get()).requires((ItemLike)ModItems.TRANSMUTATION_SOUL_STONE.get()).requires((ItemLike)ModItems.ELEMENTAL_SOUL_STONE.get()).requires((ItemLike)ModItems.ALCHEMY_BAG_SOUL_STONE.get()).unlockedBy("has_red_soul_stone", (CriterionTriggerInstance)ModRecipeProvider.has((ItemLike)((ItemLike)ModItems.RED_SOUL_STONE.get()))).save(writer);
    }

    private static Item item(String namespace, String path) {
        Item item = (Item)ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath((String)namespace, (String)path));
        if (item == null || item == Items.AIR) {
            throw new IllegalStateException("\u524d\u7f6e\u7269\u54c1\u4e0d\u5b58\u5728: " + namespace + ":" + path);
        }
        return item;
    }
}

