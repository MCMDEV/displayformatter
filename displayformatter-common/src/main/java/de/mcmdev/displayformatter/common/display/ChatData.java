package de.mcmdev.displayformatter.common.display;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;

@Getter
@RequiredArgsConstructor
public class ChatData {

    private final Component prefix;
    private final Component suffix;
    private final String format;
}
