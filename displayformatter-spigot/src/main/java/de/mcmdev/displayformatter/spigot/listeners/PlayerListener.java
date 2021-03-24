package de.mcmdev.displayformatter.spigot.listeners;

import de.mcmdev.displayformatter.spigot.DFSpigotPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final DFSpigotPlugin spigotPlugin;

    public PlayerListener(DFSpigotPlugin spigotPlugin) {
        this.spigotPlugin = spigotPlugin;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        spigotPlugin.getDisplayFormatter().update(player);
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        spigotPlugin.getDisplayFormatter().cleanup(event.getPlayer());
    }
}
