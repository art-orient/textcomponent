package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

public class SentenceParser extends AbstractBaseParser {

  private static final String LEXEME_SEPARATOR = "\\s";

  public SentenceParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public TextComponent parseText(String sentence) {
    TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
    String[] lexemes = sentence.split(LEXEME_SEPARATOR);
    for (String lexeme : lexemes) {
      TextComponent lexemeComponent = nextParser.parseText(lexeme);
      sentenceComposite.add(lexemeComponent);
    }
    return sentenceComposite;
  }
}
