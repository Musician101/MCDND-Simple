package io.musician101.mcdndsimple.forge.gui.nonplayer;

import io.musician101.mcdndsimple.common.reference.MenuText;
import java.awt.Color;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class NonPlayersScreen extends Screen {

    @Nonnull
    private final Map<String, String> npcs;

    public NonPlayersScreen(@Nonnull Map<String, String> npcs) {
        super(new StringTextComponent(MenuText.NPCS));
        this.npcs = npcs;
    }

    @Override
    protected void init() {
        addButton(new Button(100, 20, width / 2 - 110, 50, MenuText.NPC_SHEET, button -> {

        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        renderDirtBackground(0);
        drawCenteredString(font, title.getFormattedText(), width / 2, 20, Color.WHITE.getRGB());
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }
}
