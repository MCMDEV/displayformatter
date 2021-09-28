package de.mcmdev.displayformatter.common.platform;

/**
 * A configuration section
 */
public interface Configuration {

    String getString(String key);

    boolean getBoolean(String key);

    int getInteger(String key);

}
