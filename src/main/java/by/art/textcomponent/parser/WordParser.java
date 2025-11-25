package by.art.textcomponent.parser;

import by.art.textcomponent.component.LetterLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

public class WordParser extends AbstractBaseParser {

  @Override
  public TextComponent parseText(String word) {
    TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
    for (char letter: word.toCharArray()) {
      TextComponent letterComponent = new LetterLeaf(letter);
      wordComposite.add(letterComponent);
    }
    return wordComposite;
  }
}
