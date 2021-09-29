package de.mcmdev.displayformatter.spigot.listener;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.common.handler.ConnectionHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private final ConnectionHandler<Player> connectionHandler;

    public ConnectionListener(DisplayFormatter<Player> displayFormatter) {
        this.connectionHandler = new ConnectionHandler<>(displayFormatter);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer()
                .getScoreboard()
                .equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        this.connectionHandler.handleJoin(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.connectionHandler.handleQuit(event.getPlayer());
    }
}
