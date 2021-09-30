package de.mcmdev.displayformatter.common.platform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

@Getter
@ToString
@RequiredArgsConstructor
public class Team<P> {

  private final String name;
  private final String owner;
  private final Component prefix;
  private final TextColor color;
  private final Component suffix;
}
