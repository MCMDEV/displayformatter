package de.mcmdev.displayformatter.spigot.implementation;

import de.mcmdev.displayformatter.common.TabDisplayHandler;
import de.mcmdev.displayformatter.spigot.utils.TeamUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class SpigotTabDisplayHandler implements TabDisplayHandler {

    @Override
    public void update(UUID uuid, String prefix, String color, String suffix, int weight) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null || !player.isOnline()) {
            return;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = onlinePlayer.getScoreboard();
            Team team = scoreboard.getEntryTeam(player.getName());
            if (team == null) {
                team = createNewTeam(weight, player, scoreboard);
            } else {
                int teamWeight = Integer.parseInt(team.getName().substring(0, 3));

                if (weight != teamWeight) {
                    team.removeEntry(player.getName());
                    team.unregister();

                    team = createNewTeam(weight, player, scoreboard);
                }
            }

            if (!team.getPrefix().equals(prefix)) {
                team.setPrefix(prefix);
            }

            if (!team.getSuffix().equals(suffix)) {
                team.setSuffix(suffix);
            }

            ChatColor chatColor = ChatColor.valueOf(color);

            if (!team.getColor().equals(chatColor)) {
                team.setColor(chatColor);
            }

            if (!team.hasEntry(player.getName())) {
                team.addEntry(player.getName());
            }
        }
    }

    private Team createNewTeam(int weight, Player player, Scoreboard scoreboard) {
        String teamName = TeamUtils.generateTeamName(player.getName(), weight);
        return scoreboard.registerNewTeam(teamName);
    }
}
