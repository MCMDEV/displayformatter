package de.mcmdev.displayformatter.common.permissionsource;

import java.util.Optional;

public interface PermissionSource<P> {

    Optional<String> getPrefix(P player);

    Optional<String> getSuffix(P player);

    Optional<String> getColor(P player);

}
