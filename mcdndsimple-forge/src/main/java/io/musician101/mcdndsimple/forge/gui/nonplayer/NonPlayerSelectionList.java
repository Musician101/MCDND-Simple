package io.musician101.mcdndsimple.forge.gui.nonplayer;

import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.list.ExtendedList;

//TODO left off here
public class NonPlayerSelectionList extends ExtendedList<NonPlayerSelectionList.Entry> {

    @Nonnull
    private final NonPlayersScreen nonPlayersScreen;

    public NonPlayerSelectionList(@Nonnull NonPlayersScreen nonPlayersScreen, @Nonnull Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.nonPlayersScreen = nonPlayersScreen;
    }

    public static class Entry extends ExtendedList.AbstractListEntry<NonPlayerSelectionList.Entry> {

        @Nonnull
        private final NonPlayersScreen nonPlayersScreen;

        protected Entry(@Nonnull NonPlayersScreen nonPlayersScreen) {
            this.nonPlayersScreen = nonPlayersScreen;
        }

        @Override
        public void render(int p_render_1_, int p_render_2_, int p_render_3_, int p_render_4_, int p_render_5_, int p_render_6_, int p_render_7_, boolean p_render_8_, float p_render_9_) {

        }
    }
}
