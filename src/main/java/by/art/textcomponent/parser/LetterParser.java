package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextLeaf;

public class LetterParser extends AbstractBaseParser {

  public LetterParser() {
    this.nextParser = null;
  }

  @Override
  public TextComponent parseText(String letter) {
    return new TextLeaf(TextComponentType.LETTER, letter.charAt(0));
  }
}
