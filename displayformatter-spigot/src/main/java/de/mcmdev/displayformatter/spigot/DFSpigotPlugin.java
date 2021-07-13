package de.mcmdev.displayformatter.spigot;

import de.mcmdev.displayformatter.spigot.listeners.PlayerListener;
import de.mcmdev.displayformatter.spigot.utils.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class DFSpigotPlugin extends JavaPlugin {

    private DisplayFormatterSpigot displayFormatterSpigot;

    @Override
    public void onEnable() {
        this.displayFormatterSpigot = new DisplayFormatterSpigot();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        new Metrics(this, 12030);
    }

    public DisplayFormatterSpigot getDisplayFormatter() {
        return displayFormatterSpigot;
    }
}
