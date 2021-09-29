package de.mcmdev.displayformatter.common.platform;

import java.util.UUID;

public interface Platform<P> {

    Configuration getConfiguration(String path);

    void createTeam(P player, Team<P> team);

    void deleteTeam(P player, String name);

    Iterable<P> getAllPlayers();

    P getPlayer(UUID uuid);

    String getName(P player);

    UUID getUUID(P player);
}
