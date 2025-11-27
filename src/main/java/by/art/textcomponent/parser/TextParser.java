package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends AbstractBaseParser {
  private static final Logger logger = LogManager.getLogger();
  private static final String PARAGRAPH_REGEX = "(?=(\\t| {4}))";

  public TextParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComponent parseText(String text) {
    TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
    String[] paragraphs = text.split(PARAGRAPH_REGEX);
    for (String paragraph: paragraphs) {
      if (!paragraph.isEmpty()) {
        TextComponent component = getNextParser().parseText(paragraph);
        textComposite.add(component);
      }
    }
    logger.info("{} paragraphs found", paragraphs.length);
    return textComposite;
  }
}
