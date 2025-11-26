package by.art.textcomponent.parser;

import by.art.textcomponent.component.PunctuationLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

public class TextParser extends AbstractBaseParser {
  private static final String TAB = "\t";
  private static final String FOUR_SPACES = "    ";
  private static final String PARAGRAPH_SEPARATOR = "(?<=\\t| {4})|(?=\\t| {4})";

  public TextParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComponent parseText(String text) {
    TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
    String[] paragraphs = text.split(PARAGRAPH_SEPARATOR);
    for (String  tabOrParagraph: paragraphs) {
      if (tabOrParagraph.equals(TAB) || tabOrParagraph.equals(FOUR_SPACES)) {
        textComposite.add(new PunctuationLeaf(TAB.charAt(0)));
      } else {
        TextComponent component = getNextParser().parseText(tabOrParagraph);
        textComposite.add(component);
      }
    }
    return textComposite;
  }
}
