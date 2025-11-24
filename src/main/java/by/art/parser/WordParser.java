package by.art.parser;

import by.art.component.TextComponent;
import by.art.component.TextComponentType;
import by.art.component.TextComposite;
import by.art.component.TextLeaf;

public class WordParser extends AbstractBaseParser {

  public WordParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public TextComponent parseText(String word) {
    TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
    for (char letter: word.toCharArray()) {
      TextComponent letterComponent = nextParser.parseText(String.valueOf(letter));
      wordComposite.add(letterComponent);
    }
    return wordComposite;
  }
}
