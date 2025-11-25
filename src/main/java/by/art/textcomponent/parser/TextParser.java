package by.art.textcomponent.parser;

import by.art.textcomponent.component.PunctuationLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

public class TextParser extends AbstractBaseParser {
  private static final String TAB = "\t";
  private static final String FOUR_SPACES = "    ";
  private static final String SPACE = " ";
  private static final String PARAGRAPH_SEPARATOR = "(?<=\\t| {4})|(?=\\t| {4})";

  public TextParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComponent parseText(String text) {
    TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
    String[] paragraphs = text.split(PARAGRAPH_SEPARATOR);
    for (String part : paragraphs) {
      if (part.equals(TAB)) {
        textComposite.add(new PunctuationLeaf(TAB.charAt(0)));
      } else if (part.equals(FOUR_SPACES)) {
        for (int i = 0; i < 4; i++) {
          textComposite.add(new PunctuationLeaf(SPACE.charAt(0)));
        }
      } else {
        TextComponent component = getNextParser().parseText(part);
        textComposite.add(component);
      }
    }
    return textComposite;
  }
}
