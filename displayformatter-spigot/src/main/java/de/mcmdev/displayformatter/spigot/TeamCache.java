package de.mcmdev.displayformatter.spigot;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TeamCache {

    private final Map<UUID, TeamWeightPair> teamCacheMap = new ConcurrentHashMap<>();

    public void setTeam(Player player, String teamName, int weight) {
        teamCacheMap.put(player.getUniqueId(), new TeamWeightPair(teamName, weight));
    }

    public TeamWeightPair getTeam(Player player) {
        return teamCacheMap.get(player.getUniqueId());
    }

    public void removeCache(Player player) {
        teamCacheMap.remove(player.getUniqueId());
    }

    @Override
    public String toString() {
        return "TeamCache{" +
                "teamCacheMap=" + teamCacheMap +
                '}';
    }
}
