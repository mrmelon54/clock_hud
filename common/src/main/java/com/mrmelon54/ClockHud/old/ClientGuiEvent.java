package com.mrmelon54.ClockHud.old;

#if MC_VER < MC_1_20_1
#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.event.Event;
#else
import dev.architectury.event.Event;
#endif

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.List;

@Environment(EnvType.CLIENT)
public interface ClientGuiEvent {
    #if MC_VER == MC_1_16_5
    interface GuiEvent extends me.shedaniel.architectury.event.events.GuiEvent {
    #else
    interface GuiEvent extends dev.architectury.event.events.client.ClientGuiEvent {
    #endif
    }

    Event<RenderHud> RENDER_HUD = EventWrapper.create(GuiEvent.RENDER_HUD, ClientGuiEvent::call);

    static GuiEvent.RenderHud call(List<RenderHud> renderHuds) {
        return (matrices, tickDelta) -> renderHuds.forEach(renderHud -> renderHud.renderHud(new GuiGraphics(matrices), tickDelta));
    }

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
