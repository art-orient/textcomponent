package by.art.parser;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;

public class LexemeParser extends AbstractBaseParser {
  private static final String SENTENCE_SEPARATOR = "\\d+(?:[.,]\\d+)?|\\w+(?:[-.]\\w+)*|[^\\s\\w]";
  public LexemeParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public void parseText(TextComposite parentComposite, String text) {
    String[] lexemes = text.split(SENTENCE_SEPARATOR);
    for (String lexeme : lexemes) {
      TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
      parentComposite.addChildComponent(lexemeComposite);
      nextParser.parseText(lexemeComposite, lexeme);
    }
  }
}
