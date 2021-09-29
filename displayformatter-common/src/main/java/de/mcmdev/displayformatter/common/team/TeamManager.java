package de.mcmdev.displayformatter.common.team;

import de.mcmdev.displayformatter.common.display.DisplayDataProvider;
import de.mcmdev.displayformatter.common.display.TabData;
import de.mcmdev.displayformatter.common.platform.Platform;
import de.mcmdev.displayformatter.common.platform.Team;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class TeamManager<P> {

    private final Platform<P> platform;
    private final DisplayDataProvider<P> displayDataProvider;

    private final Map<P, Team<P>> teamMap = new HashMap<>();

    public Team<P> createTeam(P owner) {
        TabData tabData = this.displayDataProvider.getTabDataForPlayer(owner);

        String playerName = this.platform.getName(owner);
        char sortingChar = (char) (tabData.getSort() + 33);
        String teamName =
                sortingChar + (playerName.length() < 8 ? playerName : playerName.substring(0, 8));

        return new Team<>(
                teamName, playerName, tabData.getPrefix(), tabData.getColor(), tabData.getSuffix());
    }

    public void registerTeam(P owner, Team<P> team) {
        this.teamMap.put(owner, team);
    }

    public Team<P> createAndRegisterTeam(P owner) {
        Team<P> team = createTeam(owner);
        registerTeam(owner, team);
        return team;
    }

    public Optional<Team<P>> getTeam(P owner) {
        return Optional.ofNullable(this.teamMap.get(owner));
    }

    public void unregisterTeam(P player) {
        this.teamMap.remove(player);
    }
}
