package de.mcmdev.displayformatter.common.team;

import de.mcmdev.displayformatter.common.platform.Platform;
import de.mcmdev.displayformatter.common.platform.Team;

import java.util.HashMap;
import java.util.Map;

public class TeamManager<P> {

    private final Platform<P> platform;

    private final Map<P, Team<P>> teamMap = new HashMap<>();

    public TeamManager(Platform<P> platform) {
        this.platform = platform;
    }
}
