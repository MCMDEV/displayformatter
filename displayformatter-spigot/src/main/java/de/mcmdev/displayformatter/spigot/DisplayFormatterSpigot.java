package de.mcmdev.displayformatter.spigot;

import de.mcmdev.displayformatter.common.DisplayInformationProvider;
import de.mcmdev.displayformatter.common.TabDisplayHandler;
import de.mcmdev.displayformatter.common.implementation.LuckpermsInformationProvider;
import de.mcmdev.displayformatter.spigot.implementation.SpigotTabDisplayHandler;
import de.mcmdev.displayformatter.spigot.utils.TeamUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class DisplayFormatterSpigot {

    private final TeamCache teamCache;
    private DisplayInformationProvider displayInformationProvider;
    private TabDisplayHandler tabDisplayHandler;

    public DisplayFormatterSpigot() {
        this.teamCache = new TeamCache();

        loadDisplayInformation();
        loadTabDisplayHandler();
    }

    private void loadDisplayInformation() {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            this.displayInformationProvider = new LuckpermsInformationProvider();
        }
    }

    private void loadTabDisplayHandler() {
        this.tabDisplayHandler = new SpigotTabDisplayHandler(teamCache);
    }

    public DisplayInformationProvider getDisplayInformationProvider() {
        return displayInformationProvider;
    }

    public TabDisplayHandler getTabDisplayHandler() {
        return tabDisplayHandler;
    }

    public TeamCache getTeamCache() {
        return teamCache;
    }

    public void update(Player player) {
        UUID uuid = player.getUniqueId();

        getTabDisplayHandler().update(
                uuid,
                ChatColor.translateAlternateColorCodes('&', getDisplayInformationProvider().getTabPrefix(uuid).orElse("")),
                getDisplayInformationProvider().getTabColor(uuid).orElse("WHITE"),
                ChatColor.translateAlternateColorCodes('&', getDisplayInformationProvider().getTabSuffix(uuid).orElse("")),
                getDisplayInformationProvider().getTabWeight(uuid).orElse(0)
        );
    }

    public void cleanup(Player player) {
        TeamWeightPair teamWeightPair = getTeamCache().getTeam(player);
        Team team = player.getScoreboard().getTeam(TeamUtils.generateTeamName(teamWeightPair.getTeamName(), teamWeightPair.getWeight()));
        team.removeEntry(player.getName());
        team.unregister();
        getTeamCache().removeCache(player);
    }
}
