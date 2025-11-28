package by.art.textcomponent.parser;

import by.art.textcomponent.component.SymbolLeaf;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphParserTest {
  AbstractBaseParser nextParser = Mockito.mock(AbstractBaseParser.class);
  ParagraphParser parser = new ParagraphParser(nextParser);

  @BeforeEach
  void setUp() {
    Mockito.when(nextParser.parseText(Mockito.anyString()))
            .thenReturn(new TextComposite(TextComponentType.SENTENCE));
  }

  @Test
  @Tag("parser")
  void testParagraphStartsWithTab() {
    TextComposite result = parser.parseText("\tHello world.");
    assertAll(
            () -> assertEquals(TextComponentType.PARAGRAPH, result.getComponentType()),
            () -> assertTrue(result.getChildrenComponents().get(0) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.SPACE,
                    result.getChildrenComponents().get(0).getComponentType()),
            () -> assertEquals(2, result.getChildrenComponents().size())
    );
  }

  @Test
  @Tag("parser")
  void testParagraphStartsWithFourSpaces() {
    TextComposite result = parser.parseText("    Hello world.");
    assertAll(
            () -> assertEquals(TextComponentType.PARAGRAPH, result.getComponentType()),
            () -> assertTrue(result.getChildrenComponents().subList(0, 4).stream()
                    .allMatch(c -> c instanceof SymbolLeaf))
    );
  }

  @Test
  @Tag("parser")
  void testSplitIntoSentences() {
    TextComposite result = parser.parseText("Hello world. Bye!");
    assertAll(
            () -> assertEquals(3, result.getChildrenComponents().size()),
            () -> assertTrue(result.getChildrenComponents().get(1) instanceof SymbolLeaf),
            () -> assertEquals(TextComponentType.SPACE,
                    result.getChildrenComponents().get(1).getComponentType())
    );
  }

  @Test
  @Tag("parser")
  void testEmptyParagraph() {
    TextComposite result = parser.parseText("");
    assertAll(
            () -> assertEquals(TextComponentType.PARAGRAPH, result.getComponentType()),
            () -> assertTrue(result.getChildrenComponents().isEmpty())
    );
  }

  @Test
  @Tag("parser")
  void testDelegatesToNextParser() {
    parser.parseText("Hello. Bye!");
    Mockito.verify(nextParser, Mockito.times(2)).parseText(Mockito.anyString());
  }
}