package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

public class TextParser extends AbstractBaseParser {
  private static final String PARAGRAPH_REGEX = "(?=(\\t| {4}))";

  public TextParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComponent parseText(String text) {
    TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
    String[] paragraphs = text.split(PARAGRAPH_REGEX);
    for (String paragraph: paragraphs) {
      TextComponent component = getNextParser().parseText(paragraph);
      textComposite.add(component);
    }
    return textComposite;
  }
}
