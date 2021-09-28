package de.mcmdev.displayformatter.common.permissionsource;

import de.mcmdev.displayformatter.common.platform.Platform;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractPermissionSource<P> implements PermissionSource<P> {

    private final Platform<P> platform;

    public AbstractPermissionSource(Platform<P> platform) {
        this.platform = platform;
    }

    @Override
    public Optional<String> getPrefix(P player) {
        return getPrefix(platform.getUUID(player));
    }

    public abstract Optional<String> getPrefix(UUID uuid);

    @Override
    public Optional<String> getSuffix(P player) {
        return getSuffix(platform.getUUID(player));
    }

    public abstract Optional<String> getSuffix(UUID uuid);

    @Override
    public Optional<String> getColor(P player) {
        return getColor(platform.getUUID(player));
    }

    public abstract Optional<String> getColor(UUID uuid);
}
