package de.mcmdev.displayformatter.common.handler;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.common.display.ChatData;

public class ChatHandler<P> {

    private final DisplayFormatter<P> displayFormatter;

    public ChatHandler(DisplayFormatter<P> displayFormatter) {
        this.displayFormatter = displayFormatter;
    }

    public ChatData handleChat(P player) {
        return this.displayFormatter.getDisplayDataProvider().getChatDataForPlayer(player);
    }
}
