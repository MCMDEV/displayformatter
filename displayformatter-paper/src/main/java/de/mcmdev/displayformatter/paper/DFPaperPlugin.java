package de.mcmdev.displayformatter.paper;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.paper.listener.ChatListener;
import de.mcmdev.displayformatter.paper.listener.ConnectionListener;
import de.mcmdev.displayformatter.paper.platform.PaperPlatform;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DFPaperPlugin extends JavaPlugin {

  private DisplayFormatter<Player> displayFormatter;

  @Override
  public void onEnable() {
    saveDefaultConfig();

    this.displayFormatter = new DisplayFormatter<>(new PaperPlatform(this));
    this.displayFormatter.load();

    getServer()
        .getPluginManager()
        .registerEvents(new ConnectionListener(this.displayFormatter), this);
    getServer().getPluginManager().registerEvents(new ChatListener(this.displayFormatter), this);
  }

  @Override
  public void onDisable() {
    this.displayFormatter.unload();
  }
}
