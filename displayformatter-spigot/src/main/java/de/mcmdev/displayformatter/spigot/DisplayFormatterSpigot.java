package de.mcmdev.displayformatter.spigot;

import de.mcmdev.displayformatter.common.DisplayInformationProvider;
import de.mcmdev.displayformatter.common.TabDisplayHandler;
import de.mcmdev.displayformatter.common.implementation.LuckpermsInformationProvider;
import de.mcmdev.displayformatter.spigot.api.DisplayUpdateEvent;
import de.mcmdev.displayformatter.spigot.implementation.SpigotTabDisplayHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class DisplayFormatterSpigot {
    private DisplayInformationProvider displayInformationProvider;
    private TabDisplayHandler tabDisplayHandler;

    public DisplayFormatterSpigot() {
        loadDisplayInformation();
        loadTabDisplayHandler();
    }

    private void loadDisplayInformation() {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            this.displayInformationProvider = new LuckpermsInformationProvider();
        }
    }

    private void loadTabDisplayHandler() {
        this.tabDisplayHandler = new SpigotTabDisplayHandler();
    }

    public DisplayInformationProvider getDisplayInformationProvider() {
        return displayInformationProvider;
    }

    public TabDisplayHandler getTabDisplayHandler() {
        return tabDisplayHandler;
    }

    public String format(Player player) {
        UUID uuid = player.getUniqueId();

        String prefix = ChatColor.translateAlternateColorCodes('&', getDisplayInformationProvider().getChatPrefix(uuid).orElse(""));
        String suffix = ChatColor.translateAlternateColorCodes('&', getDisplayInformationProvider().getChatSuffix(uuid).orElse(""));

        String chatFormat = getDisplayInformationProvider().getChatFormat(uuid).orElse("<%s> %s");
        chatFormat = chatFormat.replaceFirst("%s", prefix + "%s" + suffix);
        return ChatColor.translateAlternateColorCodes('&', chatFormat);
    }

    public void update(Player player) {
        UUID uuid = player.getUniqueId();

        String prefix = ChatColor.translateAlternateColorCodes('&', getDisplayInformationProvider().getTabPrefix(uuid).orElse(""));
        String suffix = ChatColor.translateAlternateColorCodes('&', getDisplayInformationProvider().getTabSuffix(uuid).orElse(""));
        String color = getDisplayInformationProvider().getTabColor(uuid).orElse("WHITE");
        int weight = getDisplayInformationProvider().getTabWeight(uuid).orElse(0);

        DisplayUpdateEvent displayUpdateEvent = new DisplayUpdateEvent(prefix, suffix, weight, color);
        Bukkit.getPluginManager().callEvent(displayUpdateEvent);

        getTabDisplayHandler().update(
                uuid,
                displayUpdateEvent.getPrefix(),
                displayUpdateEvent.getChatColor(),
                displayUpdateEvent.getSuffix(),
                displayUpdateEvent.getWeight()
        );
    }

    public void cleanup(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Team team = onlinePlayer.getScoreboard().getEntryTeam(player.getName());
            if (team != null) {
                team.removeEntry(player.getName());
                team.unregister();
            }
        }
    }
}
