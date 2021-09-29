package de.mcmdev.displayformatter.spigot;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.spigot.listener.ChatListener;
import de.mcmdev.displayformatter.spigot.listener.ConnectionListener;
import de.mcmdev.displayformatter.spigot.platform.SpigotPlatform;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DFSpigotPlugin extends JavaPlugin {

    private DisplayFormatter<Player> displayFormatter;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.displayFormatter = new DisplayFormatter<>(new SpigotPlatform(this));
        this.displayFormatter.load();

        getServer()
                .getPluginManager()
                .registerEvents(new ConnectionListener(this.displayFormatter), this);
        getServer()
                .getPluginManager()
                .registerEvents(new ChatListener(this.displayFormatter), this);
    }

    @Override
    public void onDisable() {
        this.displayFormatter.unload();
    }
}
