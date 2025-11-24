package by.art.parser;

import by.art.component.TextComponent;
import by.art.component.TextComponentType;
import by.art.component.TextComposite;

public class ParagraphParser extends AbstractBaseParser {

  private static final String SENTENCE_SEPARATOR = "(?<=[.!?])\\s+";

  public ParagraphParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public TextComposite parseText(String paragraph) {
    TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
    String[] sentences = paragraph.split(SENTENCE_SEPARATOR);
    for (String sentence : sentences) {
      TextComponent sentenceComponent = nextParser.parseText(sentence);
      paragraphComposite.add(sentenceComponent);
    }
    return paragraphComposite;
  }
}
