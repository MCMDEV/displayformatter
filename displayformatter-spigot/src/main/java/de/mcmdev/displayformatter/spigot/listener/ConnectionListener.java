package de.mcmdev.displayformatter.spigot.listener;

import de.mcmdev.displayformatter.spigot.platform.SpigotPlatform;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionListener implements Listener {

    private final SpigotPlatform platform;

    public ConnectionListener(SpigotPlatform platform) {
        this.platform = platform;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)   {
        if(event.getPlayer().getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
    }
}
