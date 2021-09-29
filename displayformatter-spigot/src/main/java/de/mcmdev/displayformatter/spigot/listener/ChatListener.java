package de.mcmdev.displayformatter.spigot.listener;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.common.display.ChatData;
import de.mcmdev.displayformatter.common.handler.ChatHandler;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final ChatHandler<Player> connectionHandler;

    public ChatListener(DisplayFormatter<Player> displayFormatter) {
        this.connectionHandler = new ChatHandler<>(displayFormatter);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        ChatData chatFormat = connectionHandler.handleChat(event.getPlayer());

        event.getPlayer()
                .setDisplayName(
                        LegacyComponentSerializer.legacySection().serialize(chatFormat.getPrefix())
                                + event.getPlayer().getName()
                                + LegacyComponentSerializer.legacySection()
                                        .serialize(chatFormat.getSuffix()));
        event.setFormat(chatFormat.getFormat());
    }
}
