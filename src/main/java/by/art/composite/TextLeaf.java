package by.art.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextLeaf implements TextComponent {
  private static final Logger logger = LogManager.getLogger();
  private TextComponentType textComponentType;
  private char leaf;

  public TextLeaf(TextComponentType textComponentType, char leaf) {
    this.textComponentType = textComponentType;
    this.leaf = leaf;
  }

  @Override
  public void addChildComponent(TextComponent textComponent) {
    //TODO
  }

  @Override
  public void removeChildComponent(TextComponent textComponent) {
    //TODO
  }

  @Override
  public String restoreText() {
    return ""; //TODO
  }

//  public getText() {
//    return leaf;
//  }

//  @Override
//  public void add(TextComponentType textComponentType) {
//
//  }
//
//  @Override
//  public void remove(TextComponentType textComponentType) {
//
//  }
}
