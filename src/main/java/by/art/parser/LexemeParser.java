package by.art.parser;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractBaseParser {
  private static final String LEXEME_REGEX = "\\d+(?:[.,]\\d+)?|\\w+(?:[-.]\\w+)*|[^\\s\\w]";
  public LexemeParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public void parseText(TextComposite parentComposite, String text) {
    Pattern pattern = Pattern.compile(LEXEME_REGEX);
    Matcher matcher = pattern.matcher(text);
    List<String> lexemes = new ArrayList<>();
    while (matcher.find()) {
      lexemes.add(matcher.group());
    }
    for (String lexeme : lexemes) {
      TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
      parentComposite.add(lexemeComposite);
      nextParser.parseText(lexemeComposite, lexeme);
    }
  }
}
