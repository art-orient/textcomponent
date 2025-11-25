package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import by.art.textcomponent.component.PunctuationLeaf;

public class PunctuationParser extends AbstractBaseParser {

  public PunctuationParser() {
    this.nextParser = null;
  }

  @Override
  public TextComponent parseText(String punctuation) {
    TextComposite punctuationComposite = new TextComposite(TextComponentType.PUNCTUATION);
    for (char punctuationSymbol : punctuation.toCharArray()) {
      punctuationComposite.add(new PunctuationLeaf(punctuationSymbol));
    }
    return punctuationComposite;
  }
}
