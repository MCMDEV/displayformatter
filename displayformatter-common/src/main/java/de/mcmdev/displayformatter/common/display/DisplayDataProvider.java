package de.mcmdev.displayformatter.common.display;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import de.mcmdev.displayformatter.common.DisplayFormatter;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.concurrent.TimeUnit;

public class DisplayDataProvider<P> {

  private final DisplayFormatter<P> displayFormatter;
  private final LoadingCache<P, TabData> tabDataCache;
  private final LoadingCache<P, ChatData> chatDataCache;

  public DisplayDataProvider(DisplayFormatter<P> displayFormatter) {
    this.displayFormatter = displayFormatter;

    this.chatDataCache =
        Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(100)
            .build(this::loadChatDataForPlayer);
    this.tabDataCache =
        Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(100)
            .build(this::loadTabDataForPlayer);
  }

  public TabData getTabDataForPlayer(P player) {
    return tabDataCache.get(player);
  }

  public ChatData getChatDataForPlayer(P player) {
    return chatDataCache.get(player);
  }

  public TabData loadTabDataForPlayer(P player) {
    // TODO: Event stuff should be done here at some point.
    return new TabData(
        MiniMessage.get()
            .parse(this.displayFormatter.getPermissionSource().getTabPrefix(player).orElse("")),
        MiniMessage.get()
            .parse(this.displayFormatter.getPermissionSource().getTabSuffix(player).orElse("")),
        TextColor.fromHexString(
            this.displayFormatter.getPermissionSource().getTabColor(player).orElse("#ffffff")),
        this.displayFormatter.getPermissionSource().getSorting(player).orElse(1));
  }

  public ChatData loadChatDataForPlayer(P player) {
    // TODO: Event stuff should be done here at some point.
    return new ChatData(
        MiniMessage.get()
            .parse(this.displayFormatter.getPermissionSource().getChatPrefix(player).orElse("")),
        MiniMessage.get()
            .parse(this.displayFormatter.getPermissionSource().getChatSuffix(player).orElse("")),
        TextColor.fromHexString(
            this.displayFormatter.getPermissionSource().getTabColor(player).orElse("#ffffff")),
        this.displayFormatter.getPermissionSource().getChatFormat(player).orElse("<%s> %s"));
  }

  public void invalidatePlayerCaches(P player) {
    this.tabDataCache.invalidate(player);
    this.chatDataCache.invalidate(player);
  }
}
