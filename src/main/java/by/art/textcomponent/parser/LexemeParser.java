package by.art.textcomponent.parser;

import by.art.textcomponent.component.PunctuationLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractBaseParser {
  private static final Logger logger = LogManager.getLogger();
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
        logger.atTrace().log("Word found - {}", wordComponent.restoreText());
      } else {
        TextComponent punctuationComponent = new PunctuationLeaf(token.charAt(0));
        lexemeComposite.add(punctuationComponent);
        logger.atTrace().log("Punctuation found - {}", punctuationComponent.restoreText());
      }
    }
    return lexemeComposite;
  }
}
