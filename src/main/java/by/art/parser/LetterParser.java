package by.art.parser;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;

public class LetterParser extends AbstractBaseParser {

  public LetterParser() {
    this.nextParser = null;
  }

  @Override
  public void parseText(TextComposite parentComposite, String text) {
    char[] letters = new char[text.length()];
    for (int i = 0; i < text.length(); i++) {
      letters[i] = text.charAt(i);
    }
    for (char letter : letters) {
      TextComposite letterComposite = new TextComposite(TextComponentType.LETTER);
      parentComposite.add(letterComposite);
    }
  }
}
