package de.mcmdev.displayformatter.common;

import java.util.Optional;
import java.util.UUID;

/**
 * Provides methods for retrieving information on how to display information
 */
public interface DisplayInformationProvider {

    Optional<String> getTabPrefix(UUID uuid);

    Optional<String> getTabColor(UUID uuid);

    Optional<String> getTabSuffix(UUID uuid);

    Optional<Integer> getTabWeight(UUID uuid);

    Optional<String> getChatPrefix(UUID uuid);

    Optional<String> getChatSuffix(UUID uuid);

    Optional<String> getChatFormat(UUID uuid);

}
