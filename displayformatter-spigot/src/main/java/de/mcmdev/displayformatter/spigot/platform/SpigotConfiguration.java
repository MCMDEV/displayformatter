package de.mcmdev.displayformatter.spigot.platform;

import de.mcmdev.displayformatter.common.platform.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SpigotConfiguration implements Configuration {

    private final YamlConfiguration handle;

    public SpigotConfiguration(YamlConfiguration handle) {
        this.handle = handle;
    }

    @Override
    public String getString(String key) {
        return handle.getString(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return handle.getBoolean(key);
    }

    @Override
    public int getInteger(String key) {
        return handle.getInt(key);
    }
}
