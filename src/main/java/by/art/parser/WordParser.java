package by.art.parser;

import by.art.composite.TextComposite;

public class WordParser extends AbstractBaseParser {

  public WordParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public void parseText(TextComposite parentComposite, String text) {
    //TODO
  }
}
