package de.mcmdev.displayformatter.common.implementation;

import de.mcmdev.displayformatter.common.DisplayInformationProvider;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;

import java.util.Optional;
import java.util.UUID;

public class LuckpermsInformationProvider implements DisplayInformationProvider {

    private final LuckPerms luckPerms = LuckPermsProvider.get();

    @Override
    public Optional<String> getTabPrefix(UUID uuid) {
        return Optional.ofNullable(getMetaData(uuid).getMetaValue("tab-prefix"));
    }

    @Override
    public Optional<String> getTabColor(UUID uuid) {
        return Optional.ofNullable(getMetaData(uuid).getMetaValue("tab-color"));
    }

    @Override
    public Optional<String> getTabSuffix(UUID uuid) {
        return Optional.ofNullable(getMetaData(uuid).getMetaValue("tab-suffix"));
    }

    @Override
    public Optional<Integer> getTabWeight(UUID uuid) {
        return Optional.of(getMetaData(uuid).getMetaValue("tab-weight", Integer::parseInt).orElse(0));
    }

    @Override
    public Optional<String> getChatPrefix(UUID uuid) {
        return Optional.ofNullable(getMetaData(uuid).getPrefix());
    }

    @Override
    public Optional<String> getChatSuffix(UUID uuid) {
        return Optional.ofNullable(getMetaData(uuid).getSuffix());
    }

    @Override
    public Optional<String> getChatFormat(UUID uuid) {
        return Optional.ofNullable(getMetaData(uuid).getMetaValue("chat-format"));
    }

    private CachedMetaData getMetaData(UUID uuid) {
        User user = luckPerms.getUserManager().getUser(uuid);
        return user.getCachedData().getMetaData(luckPerms.getContextManager().getQueryOptions(user).orElse(QueryOptions.nonContextual()));
    }
}
