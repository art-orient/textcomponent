package by.art.parser;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;

public class ParagraphParser extends AbstractBaseParser {
  private static final String PARAGRAPH_SEPARATOR = "\\p{Blank}(4)";
  private static final String SPACE = " ";

  public ParagraphParser(SentenceParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public String parseText(TextComposite parentComposite, String text) {
    String[] paragraphs = text.split(PARAGRAPH_SEPARATOR);
    for (String paragraph : paragraphs) {
      TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
      parentComposite.addChildComponent(paragraphComposite);

    }

    return null;
  }
}
