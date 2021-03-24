package de.mcmdev.displayformatter.spigot;

import de.mcmdev.displayformatter.spigot.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class DFSpigotPlugin extends JavaPlugin {

    private DisplayFormatterSpigot displayFormatterSpigot;

    @Override
    public void onEnable() {
        this.displayFormatterSpigot = new DisplayFormatterSpigot();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public DisplayFormatterSpigot getDisplayFormatter() {
        return displayFormatterSpigot;
    }
}
