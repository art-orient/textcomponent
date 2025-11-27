package by.art.textcomponent.component;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceSymbolLeafTest {
  char space = ' ';
  SpaceSymbolLeaf leaf = new SpaceSymbolLeaf(space);

  @Test
  @Tag("component")
  void testRestoreText() {
    assertEquals(String.valueOf(space), leaf.restoreText());
  }

  @Test
  @Tag("component")
  void testCountSymbols() {
    assertEquals(1, leaf.countSymbols());
  }

  @Test
  @Tag("component")
  void testGetComponentType() {
    assertEquals(TextComponentType.SPACE, leaf.getComponentType());
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
  }}