package by.art.textcomponent.component;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {

  @Test
  @Tag("component")
  void testAddAndGetChildren() {
    TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
    SymbolLeaf leaf = new SymbolLeaf(TextComponentType.LETTER, 'a');
    composite.add(leaf);
    assertAll (
            () -> assertEquals(1, composite.getChildrenComponents().size()),
            () -> assertTrue(composite.getChildrenComponents().contains(leaf))
    );
  }

  @Test
  @Tag("component")
  void testGetComponentType() {
    TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);
    assertEquals(TextComponentType.PARAGRAPH, composite.getComponentType());
  }

  @Test
  @Tag("component")
  void testCountSymbols() {
    TextComposite composite = new TextComposite(TextComponentType.WORD);
    composite.add(new SymbolLeaf(TextComponentType.LETTER, 'a'));
    composite.add(new SymbolLeaf(TextComponentType.LETTER, 'b'));
    composite.add(new SymbolLeaf(TextComponentType.LETTER, 'c'));
    assertEquals(3, composite.countSymbols());
  }

  @Test
  @Tag("component")
  void testRestoreText() {
    TextComposite composite = new TextComposite(TextComponentType.WORD);
    composite.add(new SymbolLeaf(TextComponentType.LETTER, 'h'));
    composite.add(new SymbolLeaf(TextComponentType.LETTER, 'i'));
    composite.add(new SymbolLeaf(TextComponentType.PUNCTUATION, '!'));
    assertEquals("hi!", composite.restoreText());
  }
}