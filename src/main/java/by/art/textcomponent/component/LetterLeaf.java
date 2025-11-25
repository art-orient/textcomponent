package by.art.textcomponent.component;

import java.util.List;

public class LetterLeaf implements TextComponent {
  private final TextComponentType textComponentType;
  private final char leaf;

  public LetterLeaf(char leaf) {
    this.textComponentType = TextComponentType.LETTER;
    this.leaf = leaf;
  }

  @Override
  public void add(TextComponent textComponent) {
    throw new UnsupportedOperationException("You can't add component to leaf.");
  }

  @Override
  public List<TextComponent> getChildrenComponents() {
    throw new UnsupportedOperationException("You can't get components from leaf.");
  }

  @Override
  public TextComponentType getComponentType() {
    return textComponentType;
  }

  @Override
  public int countSymbols() {
    return 1;
  }

  @Override
  public String restoreText() {
    return String.valueOf(leaf);
  }
}
