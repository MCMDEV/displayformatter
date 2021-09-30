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
  public Optional<String> getTabPrefix(P player) {
    return getTabPrefix(displayFormatter.getPlatform().getUUID(player));
  }

  public abstract Optional<String> getTabPrefix(UUID uuid);

  @Override
  public Optional<String> getTabSuffix(P player) {
    return getTabSuffix(displayFormatter.getPlatform().getUUID(player));
  }

  public abstract Optional<String> getTabSuffix(UUID uuid);

  @Override
  public Optional<String> getTabColor(P player) {
    return getTabColor(displayFormatter.getPlatform().getUUID(player));
  }

  public abstract Optional<String> getTabColor(UUID uuid);

  @Override
  public Optional<String> getChatPrefix(P player) {
    return getChatPrefix(displayFormatter.getPlatform().getUUID(player));
  }

  public abstract Optional<String> getChatPrefix(UUID uuid);

  @Override
  public Optional<String> getChatSuffix(P player) {
    return getChatSuffix(displayFormatter.getPlatform().getUUID(player));
  }

  public abstract Optional<String> getChatSuffix(UUID uuid);

  @Override
  public Optional<String> getChatColor(P player) {
    return getChatSuffix(displayFormatter.getPlatform().getUUID(player));
  }

  public abstract Optional<String> getChatColor(UUID uuid);

  @Override
  public Optional<String> getChatFormat(P player) {
    return getChatFormat(displayFormatter.getPlatform().getUUID(player));
  }

  public abstract Optional<String> getChatFormat(UUID uuid);

  @Override
  public Optional<Integer> getSorting(P player) {
    return Optional.empty();
  }

  public abstract Optional<Integer> getSorting(UUID uuid);
}
