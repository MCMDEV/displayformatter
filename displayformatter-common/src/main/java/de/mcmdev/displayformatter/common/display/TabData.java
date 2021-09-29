package de.mcmdev.displayformatter.common.display;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

@Getter
@RequiredArgsConstructor
public class TabData {

    private final Component prefix;
    private final Component suffix;
    private final TextColor color;
    private final int sort;
}
