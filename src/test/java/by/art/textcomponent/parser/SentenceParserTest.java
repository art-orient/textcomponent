package by.art.textcomponent.parser;

import by.art.textcomponent.component.SpaceSymbolLeaf;
import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SentenceParserTest {
  String hello = "Hello world";
  AbstractBaseParser nextParser = Mockito.mock(AbstractBaseParser.class);
  SentenceParser parser = new SentenceParser(nextParser);

  @BeforeEach
  void setUp() {
    Mockito.when(nextParser.parseText(Mockito.anyString()))
            .thenReturn(new TextComposite(TextComponentType.LEXEME));
  }

  @Test
  @Tag("parser")
  void testSplitIntoLexemesWithSpace() {
    TextComponent result = parser.parseText(hello);
    assertAll(
            () -> assertEquals(TextComponentType.SENTENCE, result.getComponentType()),
            () -> assertEquals(3, result.getChildrenComponents().size()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SpaceSymbolLeaf)
    );
  }

  @Test
  @Tag("parser")
  void testSplitIntoLexemesWithLineBreak() {
    TextComponent result = parser.parseText("Hello\nworld");
    assertAll(
            () -> assertEquals(3, result.getChildrenComponents().size()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SpaceSymbolLeaf),
            () -> assertEquals("\n", result.getChildrenComponents().get(1).restoreText())
    );
  }

  @Test
  @Tag("parser")
  void testDelegatesToNextParser() {
    parser.parseText(hello);
    Mockito.verify(nextParser, Mockito.times(2)).parseText(Mockito.anyString());
  }

  @Test
  @Tag("parser")
  void testEmptySentence() {
    TextComponent result = parser.parseText("");
    assertAll(
            () -> assertEquals(TextComponentType.SENTENCE, result.getComponentType()),
            () -> assertTrue(result.getChildrenComponents().isEmpty())
    );
  }
}