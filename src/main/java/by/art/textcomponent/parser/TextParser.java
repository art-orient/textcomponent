package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

public class TextParser extends AbstractBaseParser {
  private static final String PARAGRAPH_SEPARATOR = "(?=\\t| {4})";

  public TextParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public TextComponent parseText(String text) {
    TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
    String[] paragraphs = text.split(PARAGRAPH_SEPARATOR);
    for (String paragraph : paragraphs) {
      TextComponent paragraphComponent = nextParser.parseText(paragraph);
      textComposite.add(paragraphComponent);
    }
    return textComposite;
  }
}
