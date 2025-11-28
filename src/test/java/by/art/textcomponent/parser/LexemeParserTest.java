package by.art.textcomponent.parser;

import by.art.textcomponent.component.SymbolLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LexemeParserTest {
  AbstractBaseParser nextParser = Mockito.mock(AbstractBaseParser.class);
  LexemeParser parser = new LexemeParser(nextParser);

  @BeforeEach
  void setUp() {
    Mockito.when(nextParser.parseText(Mockito.anyString()))
            .thenReturn(new TextComposite(TextComponentType.WORD));
  }

  @Test
  @Tag("parser")
  void testWordAndPunctuation() {
    TextComponent result = parser.parseText("Hello!");
    assertAll(
            () -> assertEquals(TextComponentType.LEXEME, result.getComponentType()),
            () -> assertEquals(2, result.getChildrenComponents().size()),
            () -> assertEquals(TextComponentType.WORD, result.getChildrenComponents().get(0).getComponentType()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.PUNCTUATION,
                    result.getChildrenComponents().get(1).getComponentType())
    );
  }

  @Test
  @Tag("parser")
  void testNumberAndComma() {
    TextComponent result = parser.parseText("123,");
    assertAll(
            () -> assertEquals(2, result.getChildrenComponents().size()),
            () -> assertEquals(TextComponentType.WORD, result.getChildrenComponents().get(0).getComponentType()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.PUNCTUATION,
                    result.getChildrenComponents().get(1).getComponentType())
    );
  }

  @Test
  @Tag("parser")
  void testMultipleTokens() {
    TextComponent result = parser.parseText("Hi, world.");
    assertAll(
            () -> assertEquals(4, result.getChildrenComponents().size()),
            () -> assertEquals(TextComponentType.WORD, result.getChildrenComponents().get(0).getComponentType()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.PUNCTUATION,
                    result.getChildrenComponents().get(1).getComponentType()),
            () -> assertEquals(TextComponentType.WORD, result.getChildrenComponents().get(2).getComponentType()),
            () -> assertTrue(result.getChildrenComponents().get(3) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.PUNCTUATION,
                    result.getChildrenComponents().get(3).getComponentType())
    );
  }

  @Test
  @Tag("parser")
  void testEmptyLexeme() {
    TextComponent result = parser.parseText("");
    assertAll(
            () -> assertEquals(TextComponentType.LEXEME, result.getComponentType()),
            () -> assertTrue(result.getChildrenComponents().isEmpty())
    );
  }

  @Test
  @Tag("parser")
  void testDelegatesToNextParser() {
    parser.parseText("Hello Innowise");
    Mockito.verify(nextParser, Mockito.times(2)).parseText(Mockito.anyString());
  }
}