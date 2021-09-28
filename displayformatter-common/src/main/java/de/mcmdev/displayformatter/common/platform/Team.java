package de.mcmdev.displayformatter.common.platform;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Team<P> {

    private final String name;
    private final String owner;
    private final String prefix;
    private final String color;
    private final String suffix;

}
