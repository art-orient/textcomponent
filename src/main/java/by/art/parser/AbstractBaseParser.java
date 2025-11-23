package by.art.parser;

import by.art.composite.TextComposite;

public abstract class AbstractBaseParser {
  protected AbstractBaseParser nextParser;
  public abstract String parseText(TextComposite parentComposite, String text);
}
