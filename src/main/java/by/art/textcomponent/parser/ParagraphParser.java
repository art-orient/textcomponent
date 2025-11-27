package by.art.textcomponent.parser;

import by.art.textcomponent.component.PunctuationLeaf;
import by.art.textcomponent.component.SpaceSymbolLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractBaseParser {
  private static final String SENTENCE_REGEX = "([^.!?]+[.!?])(\\s*)";

  public ParagraphParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComposite parseText(String paragraph) {
    TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
    Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL);
    Matcher matcher = pattern.matcher(paragraph);
    int indexOfEndOfLastSentence = 0;
    while (matcher.find()) {
      String sentenceText = matcher.group(1);
      String separator = matcher.group(2);
      if (!sentenceText.isEmpty()) {
        TextComponent sentenceComponent = getNextParser().parseText(sentenceText);
        paragraphComposite.add(sentenceComponent);
      }
      if (!separator.isEmpty()) {
        paragraphComposite.add(new SpaceSymbolLeaf(separator.charAt(0)));
      }
      indexOfEndOfLastSentence = matcher.end();
    }
    if (indexOfEndOfLastSentence < paragraph.length()) {
      String lineBreak = paragraph.substring(indexOfEndOfLastSentence);
      if (!lineBreak.isEmpty()) {
        TextComponent sentenceComponent = new PunctuationLeaf(lineBreak.charAt(0));
        paragraphComposite.add(sentenceComponent);
      }
    }
    return paragraphComposite;
  }
}
