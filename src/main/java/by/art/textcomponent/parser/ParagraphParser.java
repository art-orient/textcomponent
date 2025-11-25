package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractBaseParser {

  private static final String SENTENCE_REGEX = "([^.!?]+)([.!?])\\s";

  public ParagraphParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public TextComposite parseText(String paragraph) {
    TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
    Pattern pattern = Pattern.compile(SENTENCE_REGEX);
    Matcher matcher = pattern.matcher(paragraph);
    List<String> sentences = new ArrayList<>();
    while (matcher.find()) {
      String sentence = matcher.group(1);
      sentences.add(sentence);
      System.out.println(sentence);
      String punctuation = matcher.group(2);
      System.out.println(punctuation);
    }
//    String[] sentences = paragraph.split(SENTENCE_SEPARATOR);
    for (String sentence : sentences) {
      TextComponent sentenceComponent = nextParser.parseText(sentence);
      paragraphComposite.add(sentenceComponent);
    }
    return paragraphComposite;
  }
}
