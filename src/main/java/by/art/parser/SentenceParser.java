package by.art.parser;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;

public class SentenceParser extends AbstractBaseParser {
  private static final String SENTENCE_SEPARATOR = "(?<=[.!?])\\s+";

  public SentenceParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public void parseText(TextComposite parentComposite, String text) {
    String[] sentences = text.split(SENTENCE_SEPARATOR);
    for (String sentence : sentences) {
      TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
      parentComposite.addChildComponent(sentenceComposite);
      nextParser.parseText(sentenceComposite, sentence);
    }
  }
}
