package by.art.textcomponent.parser;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {
  String text = "\tFirst paragraph.    Second paragraph.";
  AbstractBaseParser nextParser = Mockito.mock(AbstractBaseParser.class);
  TextParser parser = new TextParser(nextParser);

  @Test
  @Tag("parser")
  void testParseTextWithParagraphs() {
    Mockito.when(nextParser.parseText(Mockito.anyString()))
            .thenReturn(new TextComposite(TextComponentType.PARAGRAPH));
    TextComponent result = parser.parseText(text);
    assertAll (
            () ->  assertEquals(TextComponentType.TEXT, result.getComponentType()),
            () ->  assertEquals(2, result.getChildrenComponents().size())
    );
  }

  @Test
  @Tag("parser")
  void testParseEmptyText() {
    Mockito.when(nextParser.parseText(Mockito.anyString()))
            .thenReturn(new TextComposite(TextComponentType.PARAGRAPH));
    TextComponent result = parser.parseText("");
    assertAll(
            () -> assertEquals(TextComponentType.TEXT, result.getComponentType()),
            () -> assertEquals(0, result.getChildrenComponents().size())
    );
  }

  @Test
  @Tag("parser")
  void testDelegatesToNextParser() {
    parser.parseText(text);
    Mockito.verify(nextParser, Mockito.times(2)).parseText(Mockito.anyString());
  }
}