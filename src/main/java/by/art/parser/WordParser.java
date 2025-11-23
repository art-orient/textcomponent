package by.art.parser;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractBaseParser {
  private static final String WORD_REGEX = "\\p{L}+";

  public WordParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public void parseText(TextComposite parentComposite, String text) {
    Pattern pattern = Pattern.compile(WORD_REGEX);
    Matcher matcher = pattern.matcher(text);
    List<String> words = new ArrayList<>();
    while (matcher.find()) {
      words.add(matcher.group());
    }
    for (String word : words) {
      TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
      parentComposite.addChildComponent(wordComposite);
      nextParser.parseText(wordComposite, word);
    }
  }
}
