package by.art.textcomponent.parser;

import by.art.textcomponent.component.LetterLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordParserTest {
  WordParser parser = new WordParser();

  @Test
  @Tag("parser")
  void testParseWordIntoLetters() {
    TextComponent result =  parser.parseText("Hi");
    assertAll(
            () -> assertEquals(TextComponentType.WORD, result.getComponentType()),
            () -> assertEquals(2, result.getChildrenComponents().size()),
            () -> assertTrue(result.getChildrenComponents().get(0) instanceof LetterLeaf),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof LetterLeaf),
            () -> assertEquals("Hi", result.restoreText()),
            () -> assertEquals(2, result.countSymbols())
    );
  }

  @Test
  @Tag("parser")
  void testEmptyWord() {
    TextComponent result = parser.parseText("");
    assertAll(
            () -> assertEquals(TextComponentType.WORD, result.getComponentType()),
            () -> assertTrue(result.getChildrenComponents().isEmpty()),
            () -> assertEquals("", result.restoreText()),
            () -> assertEquals(0, result.countSymbols())
    );
  }
}