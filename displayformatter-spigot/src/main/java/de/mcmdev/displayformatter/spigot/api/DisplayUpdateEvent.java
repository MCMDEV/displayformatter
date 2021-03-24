package de.mcmdev.displayformatter.spigot.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DisplayUpdateEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private String prefix;
    private String suffix;
    private int weight;
    private String chatColor;

    public DisplayUpdateEvent(String prefix, String suffix, int weight, String chatColor) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.weight = weight;
        this.chatColor = chatColor;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getChatColor() {
        return chatColor;
    }

    public void setChatColor(String chatColor) {
        this.chatColor = chatColor;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
