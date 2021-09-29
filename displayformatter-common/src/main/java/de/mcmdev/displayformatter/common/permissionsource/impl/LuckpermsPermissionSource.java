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

    private final boolean tabPrefixSuffixUseMeta;
    private final boolean chatPrefixSuffixUseMeta;

    public LuckpermsPermissionSource(DisplayFormatter<P> displayFormatter) {
        super(displayFormatter);
        this.tabPrefixSuffixUseMeta =
                displayFormatter
                        .getMainConfiguration()
                        .getBoolean("luckperms.tab-prefix-suffix-use-meta");
        this.chatPrefixSuffixUseMeta =
                displayFormatter
                        .getMainConfiguration()
                        .getBoolean("luckperms.chat-prefix-suffix-use-meta");

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
                                    .getDisplayDataProvider()
                                    .invalidatePlayerCaches(player);

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
                                                displayFormatter
                                                        .getTeamManager()
                                                        .unregisterTeam(player);
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
    public Optional<String> getTabPrefix(UUID uuid) {
        return getUser(uuid)
                .map(
                        user ->
                                tabPrefixSuffixUseMeta
                                        ? user.getCachedData()
                                                .getMetaData()
                                                .getMetaValue("tab-prefix")
                                        : user.getCachedData().getMetaData().getPrefix());
    }

    @Override
    public Optional<String> getTabSuffix(UUID uuid) {
        return getUser(uuid)
                .map(
                        user ->
                                tabPrefixSuffixUseMeta
                                        ? user.getCachedData()
                                                .getMetaData()
                                                .getMetaValue("tab-suffix")
                                        : user.getCachedData().getMetaData().getSuffix());
    }

    @Override
    public Optional<String> getChatPrefix(UUID uuid) {
        return getUser(uuid)
                .map(
                        user ->
                                chatPrefixSuffixUseMeta
                                        ? user.getCachedData()
                                                .getMetaData()
                                                .getMetaValue("chat-prefix")
                                        : user.getCachedData().getMetaData().getPrefix());
    }

    @Override
    public Optional<String> getChatSuffix(UUID uuid) {
        return getUser(uuid)
                .map(
                        user ->
                                chatPrefixSuffixUseMeta
                                        ? user.getCachedData()
                                                .getMetaData()
                                                .getMetaValue("chat-suffix")
                                        : user.getCachedData().getMetaData().getSuffix());
    }

    @Override
    public Optional<String> getChatFormat(UUID uuid) {
        return getUser(uuid)
                .map(user -> user.getCachedData().getMetaData().getMetaValue("chat-format"));
    }

    @Override
    public Optional<String> getColor(UUID uuid) {
        return getUser(uuid)
                .map(user -> user.getCachedData().getMetaData().getMetaValue("tab-color"));
    }

    @Override
    public Optional<Integer> getSorting(UUID uuid) {
        return getUser(uuid)
                .map(user -> user.getCachedData().getMetaData().getMetaValue("tab-sorting"))
                .map(Integer::parseInt);
    }

    private Optional<User> getUser(UUID uuid) {
        return Optional.ofNullable(luckPerms.getUserManager().getUser(uuid));
    }
}
