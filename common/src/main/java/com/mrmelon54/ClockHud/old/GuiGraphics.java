package com.mrmelon54.ClockHud.old;

#if MC_VER == MC_1_16_5
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.item.ItemStack;

public class GuiGraphics extends GuiComponent {
    private final PoseStack matrices;

    public GuiGraphics(PoseStack matrices) {
        this.matrices = matrices;
    }

    public int guiWidth() {
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    public int guiHeight() {
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    public void drawString(Font font, String clockText, int i, int j, int color) {
        GuiComponent.drawString(matrices, font, clockText, i, j, color);
    }

    public void renderItem(ItemStack clockItemStack, int x, int y, int z) {
        Minecraft.getInstance().getItemRenderer().renderGuiItem(clockItemStack, x, y);
    }
}
#endif
