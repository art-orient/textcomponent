package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;

public abstract class AbstractBaseParser {
  private AbstractBaseParser nextParser;
  public abstract TextComponent parseText(String text);

  public AbstractBaseParser getNextParser() {
    return nextParser;
  }

  public void setNextParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }
}
