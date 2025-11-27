package by.art.textcomponent.parser;

import by.art.textcomponent.component.SpaceSymbolLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends AbstractBaseParser {
  private static final Logger logger = LogManager.getLogger();
  private static final String LEXEME_SEPARATOR = "(?<=\\s)|(?=\\s)";
  private static final String SPACE = " ";
  private static final String LINE_BREAK = "\n";

  public SentenceParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComponent parseText(String sentence) {
    TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
    if (sentence == null || sentence.isEmpty()) {
      return sentenceComposite;
    }
    String[] lexemes = sentence.split(LEXEME_SEPARATOR);
    logger.debug("The sentence is broken down into {} lexemes", lexemes.length);
    for (String lexeme : lexemes) {
      switch (lexeme) {
        case SPACE -> sentenceComposite.add(new SpaceSymbolLeaf(SPACE.charAt(0)));
        case LINE_BREAK -> sentenceComposite.add(new SpaceSymbolLeaf(LINE_BREAK.charAt(0)));
        default -> {
          TextComponent lexemeComponent = getNextParser().parseText(lexeme);
          sentenceComposite.add(lexemeComponent);
        }
      }
    }
    return sentenceComposite;
  }
}
