package by.art.textcomponent.service.impl;

import by.art.textcomponent.component.LetterLeaf;
import by.art.textcomponent.component.SpaceSymbolLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import by.art.textcomponent.exception.TextProcessorException;
import by.art.textcomponent.service.TextProcessorService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextProcessorServiceImplTest {
  TextProcessorServiceImpl service = new TextProcessorServiceImpl();

  @ParameterizedTest
  @CsvSource({
          "hello world,hello again,2",
          "bye now,hello world,1"
  })
  @Tag("service")
  void testFindMaxNumberOfSentencesWithTheSameWordSimple(String sentence1, String sentence2, int expected)
          throws TextProcessorException {
    TextComposite text = new TextComposite(TextComponentType.TEXT);
    TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
    paragraph.add(buildSentence(sentence1.split(" ")));
    paragraph.add(buildSentence(sentence2.split(" ")));
    text.add(paragraph);
    int result = service.findMaxNumberOfSentencesWithTheSameWord(text);
    assertEquals(expected, result);
  }

  @Test
  @Tag("service")
  void testFindMaxNumberOfSentencesWithTheSameWordNull() {
    assertThrows(TextProcessorException.class,
            () -> service.findMaxNumberOfSentencesWithTheSameWord(null));
  }

  @ParameterizedTest
  @CsvSource({
          "'hello world;hi', 'hi,hello world'",
          "'a b c;a b', 'a b,a b c'"
  })
  @Tag("service")
  void testSortSentencesByNumberLexeme(String sentencesRaw, String expectedOrder) throws TextProcessorException {
    String[][] sentences =
            java.util.Arrays.stream(sentencesRaw.split(";"))
                    .map(s -> s.split(" "))
                    .toArray(String[][]::new);

    TextComponent text = buildText(sentences);

    List<String> sorted = service.sortSentencesByNumberLexeme(text);

    assertEquals(java.util.Arrays.asList(expectedOrder.split(",")), sorted);
  }

  @Test
  @Tag("service")
  void testSortSentencesByNumberLexemeNull() {
    assertThrows(TextProcessorException.class,
            () -> service.sortSentencesByNumberLexeme(null));
  }

  @ParameterizedTest
  @CsvSource({
          "'hello world', 'world hello'",
          "'a b c', 'c b a'"
  })
  @Tag("service")
  void testSwapFirstAndLastLexemes(String sentenceRaw, String expected) throws TextProcessorException {
    String[][] sentences = { sentenceRaw.split(" ") };
    TextComponent text = buildText(sentences);
    List<String> swapped = service.swapFirstAndLastLexemes(text);
    assertEquals(expected, swapped.get(0));
  }

  @Test
  @Tag("service")
  void testSwapFirstAndLastLexemesNull() {
    assertThrows(TextProcessorException.class,
            () -> service.swapFirstAndLastLexemes(null));
  }

  private TextComponent buildSentence(String... words) {
    TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
    for (int i = 0; i < words.length; i++) {
      TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);
      TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
      for (char symbol : words[i].toCharArray()) {
        wordComposite.add(new LetterLeaf(symbol));
      }
      lexeme.add(wordComposite);
      sentence.add(lexeme);
      if (i < words.length - 1) {
        sentence.add(new SpaceSymbolLeaf(' '));
      }
    }
    return sentence;
  }

  private TextComponent buildText(String[][] sentences) {
    TextComposite text = new TextComposite(TextComponentType.TEXT);
    TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
    for (String[] sentenceWords : sentences) {
      paragraph.add(buildSentence(sentenceWords));
    }
    text.add(paragraph);
    return text;
  }
}