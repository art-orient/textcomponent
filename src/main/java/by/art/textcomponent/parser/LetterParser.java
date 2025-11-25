package by.art.textcomponent.parser;

import by.art.textcomponent.component.LetterLeaf;
import by.art.textcomponent.component.TextComponent;

public class LetterParser extends AbstractBaseParser {

  public LetterParser() {
    this.nextParser = null;
  }

  @Override
  public TextComponent parseText(String letter) {
    return new LetterLeaf(letter.charAt(0));
  }
}
