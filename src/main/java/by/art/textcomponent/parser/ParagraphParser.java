package by.art.textcomponent.parser;

import by.art.textcomponent.component.SymbolLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractBaseParser {
  private static final Logger logger = LogManager.getLogger();
  private static final String TAB = "\t";
  private static final String SPACE = " ";
  private static final String FOUR_SPACES = "    ";
  private static final String SENTENCE_REGEX = "([^.!?]+[.!?])(\\s?)";

  public ParagraphParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComposite parseText(String paragraph) {
    TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
    if (paragraph.startsWith(TAB)) {
      paragraphComposite.add(new SymbolLeaf(TextComponentType.SPACE, TAB.charAt(0)));
      paragraph = paragraph.substring(1);
    } else if (paragraph.startsWith(FOUR_SPACES)) {
      for (int i = 0; i < 4; i++) {
        paragraphComposite.add(new SymbolLeaf(TextComponentType.SPACE, ' '));
      }
      paragraph = paragraph.substring(4);
    }

    Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL);
    Matcher matcher = pattern.matcher(paragraph);
    int indexOfEndOfLastSentence = 0;
    while (matcher.find()) {
      String sentenceText = matcher.group(1);
      String spaceBetweenSentences = matcher.group(2);
      if (!sentenceText.isBlank()) {
        TextComponent sentenceComponent = getNextParser().parseText(sentenceText);
        paragraphComposite.add(sentenceComponent);
        logger.debug("Sentence found: '{}'", sentenceText);
      }
      if (!spaceBetweenSentences.isEmpty()) {
        paragraphComposite.add(new SymbolLeaf(TextComponentType.SPACE, SPACE.charAt(0)));
        logger.debug("Added space between sentences");
      }
      indexOfEndOfLastSentence = matcher.end();
    }
    if (indexOfEndOfLastSentence < paragraph.length()) {
      String lineBreak = paragraph.substring(indexOfEndOfLastSentence);
      if (!lineBreak.isEmpty()) {
        TextComponent lineBreakComponent = new SymbolLeaf(TextComponentType.SPACE, lineBreak.charAt(0));
        paragraphComposite.add(lineBreakComponent);
        logger.debug("Added line break at the end of the paragraph");
      }
    }
    return paragraphComposite;
  }
}
