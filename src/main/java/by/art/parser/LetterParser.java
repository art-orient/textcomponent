package by.art.parser;

import by.art.component.TextComponent;
import by.art.component.TextComponentType;
import by.art.component.TextLeaf;

public class LetterParser extends AbstractBaseParser {

  public LetterParser() {
    this.nextParser = null;
  }

  @Override
  public TextComponent parseText(String letter) {
    return new TextLeaf(TextComponentType.LETTER, letter.charAt(0));
  }
}
