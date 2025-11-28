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
            () -> assertEquals(TextComponentType.LEXEME,
                    result.getChildrenComponents().get(0).getComponentType()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.SPACE,
                    result.getChildrenComponents().get(1).getComponentType())
    );
  }

  @Test
  @Tag("parser")
  void testSplitIntoLexemesWithLineBreak() {
    TextComponent result = parser.parseText("Hello\nworld");
    assertAll(
            () -> assertEquals(3, result.getChildrenComponents().size()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.SPACE,
                    result.getChildrenComponents().get(1).getComponentType()),
            () -> assertEquals("\n", result.getChildrenComponents().get(1).restoreText())
    );
  }

  @Test
  @Tag("parser")
  void testDelegatesToNextParser() {
    parser.parseText(hello);
    Mockito.verify(nextParser, Mockito.times(2)).parseText(Mockito.anyString());
  }
}