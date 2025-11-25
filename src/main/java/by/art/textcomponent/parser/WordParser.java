package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

public class WordParser extends AbstractBaseParser {

  private static final String WORD_REGEX = "\\p{L}+";

  public WordParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public TextComponent parseText(String word) {
    if (!word.matches(WORD_REGEX)) {
      nextParser = new PunctuationParser();
      return nextParser.parseText(word);
    }
    TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
    for (char letter: word.toCharArray()) {
      TextComponent letterComponent = nextParser.parseText(String.valueOf(letter));
      wordComposite.add(letterComponent);
    }
    return wordComposite;
  }
}
