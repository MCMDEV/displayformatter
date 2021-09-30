package de.mcmdev.displayformatter.spigot.platform;

import de.mcmdev.displayformatter.common.platform.Configuration;
import de.mcmdev.displayformatter.common.platform.Platform;
import de.mcmdev.displayformatter.common.platform.Team;
import de.mcmdev.displayformatter.spigot.DFSpigotPlugin;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.util.Locale;
import java.util.UUID;

public class SpigotPlatform implements Platform<Player> {

  private final DFSpigotPlugin plugin;

  public SpigotPlatform(DFSpigotPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public Configuration getConfiguration(String path) {
    return new SpigotConfiguration(
        YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), path)));
  }

  @Override
  public void createTeam(Player player, Team<Player> team) {
    Scoreboard scoreboard = player.getScoreboard();
    org.bukkit.scoreboard.Team bukkitTeam = scoreboard.getTeam(team.getName());
    if (bukkitTeam == null) {
      bukkitTeam = scoreboard.registerNewTeam(team.getName());
    }
    bukkitTeam.setPrefix(LegacyComponentSerializer.legacySection().serialize(team.getPrefix()));
    bukkitTeam.setSuffix(LegacyComponentSerializer.legacySection().serialize(team.getSuffix()));
    bukkitTeam.setColor(
        ChatColor.valueOf(
            NamedTextColor.nearestTo(team.getColor()).toString().toUpperCase(Locale.ROOT)));
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
