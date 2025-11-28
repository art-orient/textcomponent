package by.art.textcomponent.component;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolLeafTest {
  char symbolA = 'a';
  SymbolLeaf leaf = new SymbolLeaf(TextComponentType.LETTER, symbolA);

  @Test
  @Tag("component")
  void testRestoreText() {
    assertEquals(String.valueOf(symbolA), leaf.restoreText());
  }

  @Test
  @Tag("component")
  void testCountSymbols() {
    assertEquals(1, leaf.countSymbols());
  }

  @Test
  @Tag("component")
  void testCountSpaceSymbols() {
    SymbolLeaf space = new SymbolLeaf(TextComponentType.SPACE, ' ');
    assertEquals(0, space.countSymbols());
  }

  @Test
  @Tag("component")
  void testGetComponentType() {
    assertEquals(TextComponentType.LETTER, leaf.getComponentType());
  }

  @Test
  @Tag("component")
  void testAddThrowsException() {
    assertThrows(UnsupportedOperationException.class, () -> leaf.add(leaf));
  }

  @Test
  @Tag("component")
  void testGetChildrenComponentsThrowsException() {
    assertThrows(UnsupportedOperationException.class, leaf::getChildrenComponents);
  }
}