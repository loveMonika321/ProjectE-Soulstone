/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.registries.ForgeRegistries
 */
package cn.autoforged.projecte_soulstone_1785817675.client.gui;

import cn.autoforged.projecte_soulstone_1785817675.networking.ModMessages;
import cn.autoforged.projecte_soulstone_1785817675.networking.OpenAlchemyBagPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class AlchemyBagSelectorScreen
extends Screen {
    private static final DyeColor[] COLORS = DyeColor.values();
    private static final int CIRCLE_RADIUS = 18;
    private int centerX;
    private int centerY;
    private int ringRadius;

    public AlchemyBagSelectorScreen() {
        super((Component)Component.m_237115_((String)"screen.projecte_soulstone_1785817675.alchemy_bag_selector"));
    }

    protected void m_7856_() {
        super.m_7856_();
        this.centerX = this.f_96543_ / 2;
        this.centerY = this.f_96544_ / 2 - 20;
        this.ringRadius = Math.max(72, Math.min(120, Math.min(this.f_96543_, this.f_96544_) / 3));
    }

    public void m_88315_(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        ItemStack bag;
        this.m_280273_(graphics);
        int hovered = this.getHoveredIndex(mouseX, mouseY);
        for (int i = 0; i < COLORS.length; ++i) {
            ItemStack bag2;
            double angle = (double)i * (Math.PI * 2 / (double)COLORS.length) - 1.5707963267948966;
            int x = this.centerX + (int)(Math.cos(angle) * (double)this.ringRadius);
            int y = this.centerY + (int)(Math.sin(angle) * (double)this.ringRadius);
            int color = COLORS[i].m_41071_();
            AlchemyBagSelectorScreen.fillCircle(graphics, x, y, 18, color);
            if (i == hovered) {
                AlchemyBagSelectorScreen.fillCircle(graphics, x, y, 14, -1);
            }
            if ((bag2 = AlchemyBagSelectorScreen.getBagStack(COLORS[i])).m_41619_()) continue;
            graphics.m_280480_(bag2, x - 8, y - 8);
        }
        if (hovered >= 0 && !(bag = AlchemyBagSelectorScreen.getBagStack(COLORS[hovered])).m_41619_()) {
            graphics.m_280153_(this.f_96547_, bag, mouseX, mouseY);
        }
        super.m_88315_(graphics, mouseX, mouseY, partialTick);
    }

    public boolean m_6375_(double mouseX, double mouseY, int button) {
        int index;
        if (button == 0 && (index = this.getHoveredIndex(mouseX, mouseY)) >= 0) {
            ModMessages.sendToServer(new OpenAlchemyBagPacket(COLORS[index].m_41060_()));
            this.m_7379_();
            return true;
        }
        return super.m_6375_(mouseX, mouseY, button);
    }

    private int getHoveredIndex(double mouseX, double mouseY) {
        for (int i = 0; i < COLORS.length; ++i) {
            int y;
            double angle = (double)i * (Math.PI * 2 / (double)COLORS.length) - 1.5707963267948966;
            int x = this.centerX + (int)(Math.cos(angle) * (double)this.ringRadius);
            double dist = Math.sqrt((mouseX - (double)x) * (mouseX - (double)x) + (mouseY - (double)(y = this.centerY + (int)(Math.sin(angle) * (double)this.ringRadius))) * (mouseY - (double)y));
            if (!(dist <= 18.0)) continue;
            return i;
        }
        return -1;
    }

    private static void fillCircle(GuiGraphics graphics, int cx, int cy, int radius, int color) {
        int argb = 0xFF000000 | color;
        for (int dy = -radius; dy <= radius; ++dy) {
            int halfWidth = (int)Math.sqrt(radius * radius - dy * dy);
            graphics.m_280509_(cx - halfWidth, cy + dy, cx + halfWidth + 1, cy + dy + 1, argb);
        }
    }

    private static ItemStack getBagStack(DyeColor color) {
        Item item = (Item)ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath((String)"projecte", (String)(color.m_41065_() + "_alchemical_bag")));
        return item == null ? ItemStack.f_41583_ : new ItemStack((ItemLike)item);
    }
}

