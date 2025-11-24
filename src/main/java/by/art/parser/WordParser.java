package by.art.parser;

import by.art.component.TextComponent;
import by.art.component.TextComponentType;
import by.art.component.TextComposite;

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
