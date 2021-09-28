package de.mcmdev.displayformatter.spigot;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.spigot.platform.SpigotPlatform;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DFSpigotPlugin extends JavaPlugin {

    private DisplayFormatter<Player> displayFormatter;

    @Override
    public void onEnable() {
        this.displayFormatter = new DisplayFormatter<>(new SpigotPlatform(this));
        this.displayFormatter.load();
    }

    @Override
    public void onDisable() {
        this.displayFormatter.unload();
    }
}
