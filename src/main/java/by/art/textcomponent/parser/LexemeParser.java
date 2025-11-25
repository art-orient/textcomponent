package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractBaseParser {

  private static final String WORD_REGEX = "\\p{L}+";
  public LexemeParser(AbstractBaseParser nextParser) {
    this.nextParser = nextParser;
  }

  @Override
  public TextComponent parseText(String lexeme) {
    TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
    Pattern pattern = Pattern.compile(WORD_REGEX); //TODO
    Matcher matcher = pattern.matcher(lexeme);
    List<String> words = new ArrayList<>();
    while (matcher.find()) {
      words.add(matcher.group());
    }
    for (String word : words) {
      TextComponent wordComponent = nextParser.parseText(word);
      lexemeComposite.add(wordComponent);
    }
    return lexemeComposite;
  }
}
