package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractBaseParser {

  private static final String SENTENCE_REGEX = "([^.!?]+)([.!?])(?=\\s|$)";

  public ParagraphParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComposite parseText(String paragraph) {
    TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
    Pattern pattern = Pattern.compile(SENTENCE_REGEX);
    Matcher matcher = pattern.matcher(paragraph);
    while (matcher.find()) {
      String sentence = matcher.group().strip();
      TextComponent sentenceComponent = getNextParser().parseText(sentence);
      paragraphComposite.add(sentenceComponent);
    }
    return paragraphComposite;
  }
}
