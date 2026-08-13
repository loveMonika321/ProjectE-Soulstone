/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package cn.autoforged.projecte_soulstone_1785817675;

import cn.autoforged.projecte_soulstone_1785817675.item.AlchemyBagSoulStoneItem;
import cn.autoforged.projecte_soulstone_1785817675.item.DarkSoulStoneItem;
import cn.autoforged.projecte_soulstone_1785817675.item.ElementalSoulStoneItem;
import cn.autoforged.projecte_soulstone_1785817675.item.EquivalentSoulItem;
import cn.autoforged.projecte_soulstone_1785817675.item.RedSoulStoneItem;
import cn.autoforged.projecte_soulstone_1785817675.item.SageSoulStoneItem;
import cn.autoforged.projecte_soulstone_1785817675.item.TransmutationSoulStoneItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.ITEMS, (String)"projecte_soulstone_1785817675");
    public static final RegistryObject<Item> RED_SOUL_STONE = ITEMS.register("red_soul_stone", RedSoulStoneItem::new);
    public static final RegistryObject<Item> DARK_SOUL_STONE = ITEMS.register("dark_soul_stone", DarkSoulStoneItem::new);
    public static final RegistryObject<Item> SAGE_SOUL_STONE = ITEMS.register("sage_soul_stone", SageSoulStoneItem::new);
    public static final RegistryObject<Item> TRANSMUTATION_SOUL_STONE = ITEMS.register("transmutation_soul_stone", TransmutationSoulStoneItem::new);
    public static final RegistryObject<Item> ELEMENTAL_SOUL_STONE = ITEMS.register("elemental_soul_stone", ElementalSoulStoneItem::new);
    public static final RegistryObject<Item> ALCHEMY_BAG_SOUL_STONE = ITEMS.register("alchemy_bag_soul_stone", AlchemyBagSoulStoneItem::new);
    public static final RegistryObject<Item> EQUIVALENT_SOUL = ITEMS.register("equivalent_soul", EquivalentSoulItem::new);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}

