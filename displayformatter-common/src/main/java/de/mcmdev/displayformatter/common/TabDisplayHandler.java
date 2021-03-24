package de.mcmdev.displayformatter.common;

import java.util.UUID;

public interface TabDisplayHandler {

    void update(UUID uuid, String prefix, String color, String suffix, int weight);

}
