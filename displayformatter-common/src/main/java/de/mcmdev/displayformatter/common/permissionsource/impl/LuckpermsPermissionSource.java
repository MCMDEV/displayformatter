package de.mcmdev.displayformatter.common.permissionsource.impl;

import de.mcmdev.displayformatter.common.DisplayFormatter;
import de.mcmdev.displayformatter.common.permissionsource.AbstractPermissionSource;
import de.mcmdev.displayformatter.common.platform.Team;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import net.luckperms.api.model.user.User;

import java.util.Optional;
import java.util.UUID;

public class LuckpermsPermissionSource<P> extends AbstractPermissionSource<P> {

    private final LuckPerms luckPerms = LuckPermsProvider.get();

    public LuckpermsPermissionSource(DisplayFormatter<P> displayFormatter) {
        super(displayFormatter);

        this.luckPerms
                .getEventBus()
                .subscribe(
                        UserDataRecalculateEvent.class,
                        userDataRecalculateEvent -> {
                            P player =
                                    displayFormatter
                                            .getPlatform()
                                            .getPlayer(
                                                    userDataRecalculateEvent
                                                            .getUser()
                                                            .getUniqueId());
                            if (player == null) return;

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
                                                                            .deleteTeam(
                                                                                    player,
                                                                                    team.getName());
                                                                });
                                            });

                            Team<P> newTeam =
                                    displayFormatter.getTeamManager().createAndRegisterTeam(player);

                            displayFormatter
                                    .getPlatform()
                                    .getAllPlayers()
                                    .forEach(
                                            all -> {
                                                displayFormatter
                                                        .getPlatform()
                                                        .createTeam(all, newTeam);
                                            });
                        });
    }

    @Override
    public Optional<String> getPrefix(UUID uuid) {
        return getUser(uuid).map(user -> user.getCachedData().getMetaData().getPrefix());
    }

    @Override
    public Optional<String> getSuffix(UUID uuid) {
        return getUser(uuid).map(user -> user.getCachedData().getMetaData().getSuffix());
    }

    @Override
    public Optional<String> getColor(UUID uuid) {
        return getUser(uuid).map(user -> user.getCachedData().getMetaData().getMetaValue("color"));
    }

    private Optional<User> getUser(UUID uuid) {
        return Optional.ofNullable(luckPerms.getUserManager().getUser(uuid));
    }

    @Override
    public Optional<Integer> getSorting(P player) {
        return Optional.of(1);
    }
}
