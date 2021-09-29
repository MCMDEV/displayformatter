package de.mcmdev.displayformatter.common.permissionsource;

import de.mcmdev.displayformatter.common.DisplayFormatter;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractPermissionSource<P> implements PermissionSource<P> {

    private final DisplayFormatter<P> displayFormatter;

    public AbstractPermissionSource(DisplayFormatter<P> displayFormatter) {
        this.displayFormatter = displayFormatter;
    }

    @Override
    public Optional<String> getPrefix(P player) {
        return getPrefix(displayFormatter.getPlatform().getUUID(player));
    }

    public abstract Optional<String> getPrefix(UUID uuid);

    @Override
    public Optional<String> getSuffix(P player) {
        return getSuffix(displayFormatter.getPlatform().getUUID(player));
    }

    public abstract Optional<String> getSuffix(UUID uuid);

    @Override
    public Optional<String> getColor(P player) {
        return getColor(displayFormatter.getPlatform().getUUID(player));
    }

    public abstract Optional<String> getColor(UUID uuid);
}
