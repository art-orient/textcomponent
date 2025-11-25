package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;

public abstract class AbstractBaseParser {
  protected AbstractBaseParser nextParser;
  public abstract TextComponent parseText(String text);
}
