package by.art.textcomponent.parser;

import by.art.textcomponent.component.PunctuationLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractBaseParser {
  private static final String SENTENCE_REGEX = "([^.!?]+)([.!?])";

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
      char endPunctuation = matcher.group(2).charAt(0);
      if (!sentenceText.isEmpty()) {
        TextComponent sentenceComponent = getNextParser().parseText(sentenceText);
        paragraphComposite.add(sentenceComponent);
      }
      paragraphComposite.add(new PunctuationLeaf(endPunctuation));
      indexOfEndOfLastSentence = matcher.end();
    }
    if (indexOfEndOfLastSentence < paragraph.length()) {
      String tail = paragraph.substring(indexOfEndOfLastSentence);
      if (!tail.isEmpty()) {
        TextComponent sentenceComponent = getNextParser().parseText(tail);
        paragraphComposite.add(sentenceComponent);
      }
    }
    return paragraphComposite;
  }
}
