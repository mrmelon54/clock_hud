package com.mrmelon54.ClockHud.old;

#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.event.Event;
import me.shedaniel.architectury.event.events.GuiEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ClientGuiEvent {
    Event<RenderHud> RENDER_HUD = EventWrapper.create(GuiEvent.RENDER_HUD, renderHud -> (matrices, tickDelta) -> renderHud.renderHud(new GuiGraphics(matrices), tickDelta));

    @Environment(EnvType.CLIENT)
    interface RenderHud {
        /**
         * Invoked after the in-game hud has been rendered.
         * Equivalent to Forge's {@code RenderGameOverlayEvent.Post@ElementType#ALL} and Fabric's {@code HudRenderCallback}.
         *
         * @param graphics  The graphics context.
         * @param tickDelta The tick delta.
         */
        void renderHud(GuiGraphics graphics, float tickDelta);
    }
}
#endif
