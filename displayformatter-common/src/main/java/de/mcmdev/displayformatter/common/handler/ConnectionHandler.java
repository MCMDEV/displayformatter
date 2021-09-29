package de.mcmdev.displayformatter.common.handler;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.common.platform.Team;

public class ConnectionHandler<P> {

    private final DisplayFormatter<P> displayFormatter;

    public ConnectionHandler(DisplayFormatter<P> displayFormatter) {
        this.displayFormatter = displayFormatter;
    }

    public void handleJoin(P player) {
        Team<P> team = displayFormatter.getTeamManager().createAndRegisterTeam(player);

        displayFormatter
                .getPlatform()
                .getAllPlayers()
                .forEach(
                        all -> {
                            displayFormatter.getPlatform().createTeam(all, team);

                            if (all.equals(player)) return;
                            displayFormatter
                                    .getTeamManager()
                                    .getTeam(all)
                                    .ifPresent(
                                            allsTeam -> {
                                                displayFormatter
                                                        .getPlatform()
                                                        .createTeam(player, allsTeam);
                                            });
                        });
    }

    public void handleQuit(P player) {
        displayFormatter
                .getTeamManager()
                .getTeam(player)
                .ifPresent(
                        team -> {
                            displayFormatter
                                    .getPlatform()
                                    .getAllPlayers()
                                    .forEach(
                                            all -> {
                                                displayFormatter
                                                        .getPlatform()
                                                        .deleteTeam(all, team.getName());
                                            });
                        });
    }
}
