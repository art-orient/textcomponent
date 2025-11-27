package by.art.textcomponent.parser;

import by.art.textcomponent.component.LetterLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends AbstractBaseParser {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public TextComponent parseText(String word) {
    TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
    for (char letter: word.toCharArray()) {
      TextComponent letterComponent = new LetterLeaf(letter);
      wordComposite.add(letterComponent);
      logger.trace("Letter added: '{}'", letter);
    }
    return wordComposite;
  }
}
