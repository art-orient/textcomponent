package by.art.parser;

import by.art.component.TextComponent;

public abstract class AbstractBaseParser {
  protected AbstractBaseParser nextParser;
  public abstract TextComponent parseText(String text);
}
