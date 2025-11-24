package by.art.parser;

import by.art.component.TextComponent;
import by.art.component.TextComponentType;
import by.art.component.TextComposite;
import by.art.component.TextLeaf;

public class PunctuationParser extends AbstractBaseParser {

  public PunctuationParser() {
    this.nextParser = null;
  }

  @Override
  public TextComponent parseText(String punctuation) {
    TextComposite punctuationComposite = new TextComposite(TextComponentType.PUNCTUATION);
    for (char punctuationSymbol : punctuation.toCharArray()) {
      punctuationComposite.add(new TextLeaf(TextComponentType.PUNCTUATION, punctuationSymbol));
    }
    return punctuationComposite;
  }
}
