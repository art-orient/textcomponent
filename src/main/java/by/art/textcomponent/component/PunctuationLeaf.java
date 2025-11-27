package by.art.textcomponent.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PunctuationLeaf implements TextComponent {
  private static final Logger logger = LogManager.getLogger();
  private final TextComponentType textComponentType;
  private final char leaf;

  public PunctuationLeaf(char leaf) {
    this.textComponentType = TextComponentType.PUNCTUATION;
    this.leaf = leaf;
  }

  @Override
  public void add(TextComponent textComponent) {
    logger.error("Attempt to add component to punctuation leaf");
    throw new UnsupportedOperationException("You can't add component to leaf.");
  }

  @Override
  public List<TextComponent> getChildrenComponents() {
    logger.error("Attempt to get children components from punctuation leaf");
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
