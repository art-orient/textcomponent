package by.art.textcomponent.parser;

import by.art.textcomponent.component.PunctuationLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractBaseParser {

  private static final String WORD_AND_PUNCTUATION_REGEX = "(\\p{L}+|\\p{N}+|[^\\p{L}\\p{N}\\s])";
  private static final String WORD_REGEX = "\\p{L}+|\\p{N}+";

  public LexemeParser(AbstractBaseParser nextParser) {
    setNextParser(nextParser);
  }

  @Override
  public TextComponent parseText(String lexeme) {
    TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
    Pattern pattern = Pattern.compile(WORD_AND_PUNCTUATION_REGEX);
    Matcher matcher = pattern.matcher(lexeme);
    while (matcher.find()) {
      String token = matcher.group();
      if (token.matches(WORD_REGEX)) {
        TextComponent wordComponent = getNextParser().parseText(token);
        lexemeComposite.add(wordComponent);
      } else {
        TextComponent punctuationComponent = new PunctuationLeaf(token.charAt(0));
        lexemeComposite.add(punctuationComponent);
      }
    }
    return lexemeComposite;
  }
}
