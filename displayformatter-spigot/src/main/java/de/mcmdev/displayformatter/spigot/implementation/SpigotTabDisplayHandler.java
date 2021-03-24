package de.mcmdev.displayformatter.spigot.implementation;

import de.mcmdev.displayformatter.common.TabDisplayHandler;
import de.mcmdev.displayformatter.spigot.TeamCache;
import de.mcmdev.displayformatter.spigot.TeamWeightPair;
import de.mcmdev.displayformatter.spigot.utils.TeamUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class SpigotTabDisplayHandler implements TabDisplayHandler {

    private final TeamCache teamCache;

    public SpigotTabDisplayHandler(TeamCache teamCache) {
        this.teamCache = teamCache;
    }

    @Override
    public void update(UUID uuid, String prefix, String color, String suffix, int weight) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null || !player.isOnline()) {
            return;
        }

        TeamWeightPair teamWeightPair = teamCache.getTeam(player);
        Scoreboard scoreboard = player.getScoreboard();
        Team team;
        if (teamWeightPair == null) {
            team = createNewTeam(weight, player, scoreboard);
        } else {
            team = scoreboard.getTeam(TeamUtils.generateTeamName(teamWeightPair.getTeamName(), teamWeightPair.getWeight()));

            if (team == null) {
                return;
            }

            if (weight != teamWeightPair.getWeight()) {
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

    private Team createNewTeam(int weight, Player player, Scoreboard scoreboard) {
        Team team;
        String teamName = TeamUtils.generateTeamName(player.getName(), weight);
        team = scoreboard.registerNewTeam(teamName);
        teamCache.setTeam(player, player.getName(), weight);
        return team;
    }
}
