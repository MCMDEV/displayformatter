package de.mcmdev.displayformatter.common.permissionsource.impl;

import de.mcmdev.displayformatter.common.permissionsource.AbstractPermissionSource;
import de.mcmdev.displayformatter.common.platform.Platform;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

import java.util.Optional;
import java.util.UUID;

public class LuckpermsPermissionSource<P> extends AbstractPermissionSource<P> {

    private final LuckPerms luckPerms = LuckPermsProvider.get();

    public LuckpermsPermissionSource(Platform<P> platform) {
        super(platform);
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

    private Optional<User> getUser(UUID uuid)   {
        return Optional.ofNullable(luckPerms.getUserManager().getUser(uuid));
    }
}
