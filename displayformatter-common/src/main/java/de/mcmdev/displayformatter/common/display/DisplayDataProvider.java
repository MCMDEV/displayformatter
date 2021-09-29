package de.mcmdev.displayformatter.common.display;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import de.mcmdev.displayformatter.common.DisplayFormatter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.concurrent.TimeUnit;

public class DisplayDataProvider<P> {

    private final DisplayFormatter<P> displayFormatter;
    private final ComponentSerializer<Component, ? extends Component, String> componentSerializer;

    private final LoadingCache<P, TabData> tabDataCache;
    private final LoadingCache<P, ChatData> chatDataCache;

    public DisplayDataProvider(DisplayFormatter<P> displayFormatter) {
        this.displayFormatter = displayFormatter;
        this.componentSerializer = loadSerializerFromConfig();

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
                this.componentSerializer.deserialize(
                        this.displayFormatter
                                .getPermissionSource()
                                .getTabPrefix(player)
                                .orElse("")),
                this.componentSerializer.deserialize(
                        this.displayFormatter
                                .getPermissionSource()
                                .getTabSuffix(player)
                                .orElse("")),
                TextColor.fromHexString(
                        this.displayFormatter
                                .getPermissionSource()
                                .getColor(player)
                                .orElse("#ffffff")),
                this.displayFormatter.getPermissionSource().getSorting(player).orElse(1));
    }

    public ChatData loadChatDataForPlayer(P player) {
        // TODO: Event stuff should be done here at some point.
        return new ChatData(
                this.componentSerializer.deserialize(
                        this.displayFormatter
                                .getPermissionSource()
                                .getTabPrefix(player)
                                .orElse("")),
                this.componentSerializer.deserialize(
                        this.displayFormatter
                                .getPermissionSource()
                                .getTabSuffix(player)
                                .orElse("")),
                this.displayFormatter
                        .getPermissionSource()
                        .getChatFormat(player)
                        .orElse("<%s> %s"));
    }

    private ComponentSerializer<Component, ? extends Component, String> loadSerializerFromConfig() {
        ComponentSerializer<Component, ? extends Component, String> configuredSerializer;
        switch (this.displayFormatter.getMainConfiguration().getString("serializer")) {
            case "gson":
                configuredSerializer = GsonComponentSerializer.gson();
                break;
            case "plain":
                configuredSerializer = PlainTextComponentSerializer.plainText();
                break;
            default:
                configuredSerializer = LegacyComponentSerializer.legacyAmpersand();
                break;
        }
        return configuredSerializer;
    }

    public void invalidatePlayerCaches(P player) {
        this.tabDataCache.invalidate(player);
        this.chatDataCache.invalidate(player);
    }
}
