package de.mcmdev.displayformatter.paper.platform;

import de.mcmdev.displayformatter.common.platform.Configuration;
import de.mcmdev.displayformatter.common.platform.Platform;
import de.mcmdev.displayformatter.common.platform.Team;
import de.mcmdev.displayformatter.paper.DFPaperPlugin;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.util.UUID;

public class PaperPlatform implements Platform<Player> {

  private final DFPaperPlugin plugin;

  public PaperPlatform(DFPaperPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public Configuration getConfiguration(String path) {
    return new PaperConfiguration(
        YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), path)));
  }

  @Override
  public void createTeam(Player player, Team<Player> team) {
    Scoreboard scoreboard = player.getScoreboard();
    org.bukkit.scoreboard.Team bukkitTeam = scoreboard.getTeam(team.getName());
    if (bukkitTeam == null) {
      bukkitTeam = scoreboard.registerNewTeam(team.getName());
    }
    bukkitTeam.prefix(team.getPrefix());
    bukkitTeam.suffix(team.getSuffix());
    bukkitTeam.color(NamedTextColor.nearestTo(team.getColor()));
    bukkitTeam.addEntry(team.getOwner());
  }

  @Override
  public void deleteTeam(Player player, String name) {
    Scoreboard scoreboard = player.getScoreboard();
    org.bukkit.scoreboard.Team bukkitTeam = scoreboard.getTeam(name);
    if (bukkitTeam == null) return;
    bukkitTeam.unregister();
  }

  @Override
  public Player getPlayer(UUID uuid) {
    return Bukkit.getPlayer(uuid);
  }

  @Override
  public Iterable<Player> getAllPlayers() {
    return (Iterable<Player>) Bukkit.getOnlinePlayers();
  }

  @Override
  public String getName(Player player) {
    return player.getName();
  }

  @Override
  public UUID getUUID(Player player) {
    return player.getUniqueId();
  }
}
