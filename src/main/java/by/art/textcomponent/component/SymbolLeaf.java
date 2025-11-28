package by.art.textcomponent.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolLeaf implements TextComponent {
  private static final Logger logger = LogManager.getLogger();
  private final TextComponentType textComponentType;
  private final char leaf;

  public SymbolLeaf(TextComponentType textComponentType, char leaf) {
    this.textComponentType = textComponentType;
    this.leaf = leaf;
  }

  @Override
  public void add(TextComponent textComponent) {
    logger.error("Attempt to add component to leaf");
    throw new UnsupportedOperationException("You can't add component to leaf.");
  }

  @Override
  public List<TextComponent> getChildrenComponents() {
    logger.error("Attempt to get children components from leaf");
    throw new UnsupportedOperationException("You can't get components from leaf.");
  }

  @Override
  public TextComponentType getComponentType() {
    return textComponentType;
  }

  @Override
  public int countSymbols() {
    return getComponentType() == TextComponentType.SPACE ? 0 : 1;
  }

  @Override
  public String restoreText() {
    return String.valueOf(leaf);
  }
}
