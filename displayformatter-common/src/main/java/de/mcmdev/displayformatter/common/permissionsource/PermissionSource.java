package de.mcmdev.displayformatter.common.permissionsource;

import java.util.Optional;

public interface PermissionSource<P> {

    Optional<String> getTabPrefix(P player);

    Optional<String> getTabSuffix(P player);

    Optional<String> getChatPrefix(P player);

    Optional<String> getChatSuffix(P player);

    Optional<String> getChatFormat(P player);

    Optional<String> getColor(P player);

    Optional<Integer> getSorting(P player);
}
