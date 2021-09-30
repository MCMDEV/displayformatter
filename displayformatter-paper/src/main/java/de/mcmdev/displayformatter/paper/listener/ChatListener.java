package de.mcmdev.displayformatter.paper.listener;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.common.display.ChatData;
import de.mcmdev.displayformatter.common.handler.ChatHandler;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

  private final ChatHandler<Player> connectionHandler;

  public ChatListener(DisplayFormatter<Player> displayFormatter) {
    this.connectionHandler = new ChatHandler<>(displayFormatter);
  }

  @EventHandler
  public void onChat(AsyncChatEvent event) {
    ChatData chatFormat = connectionHandler.handleChat(event.getPlayer());

    event
        .getPlayer()
        .displayName(
            Component.text()
                .append(chatFormat.getPrefix())
                .append(Component.text(event.getPlayer().getName(), chatFormat.getColor()))
                .append(chatFormat.getSuffix())
                .build());

    event.renderer(
        (source, sourceDisplayName, message, viewer) ->
            MiniMessage.get()
                .parse(
                    chatFormat.getFormat(),
                    Template.of("displayname", sourceDisplayName),
                    Template.of("message", message)));
  }
}
