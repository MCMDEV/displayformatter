package de.mcmdev.displayformatter.common.platform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Team<P> {

    private final String name;
    private final String owner;
    private final String prefix;
    private final String color;
    private final String suffix;
}
