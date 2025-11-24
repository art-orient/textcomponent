package by.art.component;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
  private final TextComponentType textComponentType;
  private final char leaf;

  public TextLeaf(TextComponentType textComponentType, char leaf) {
    this.textComponentType = textComponentType;
    this.leaf = leaf;
  }

  @Override
  public void add(TextComponent textComponent) {
    throw new UnsupportedOperationException("You can't add component to leaf.");
  }

  @Override
  public void remove(TextComponent textComponent) {
    throw new UnsupportedOperationException("You can't remove component from leaf.");
  }

  @Override
  public List<TextComponent> getChildrenComponents() {
    return Collections.emptyList();
  }

  @Override
  public TextComponentType getComponentType() {
    return textComponentType;
  }

  @Override
  public String restoreText() {
    return String.valueOf(leaf);
  }
}
