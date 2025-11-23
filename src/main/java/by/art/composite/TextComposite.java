package by.art.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

  private final List<TextComponent> textComponents = new ArrayList<>();
  private final TextComponentType textComponentType;

  public TextComposite(TextComponentType textComponentType) {
    this.textComponentType = textComponentType;
  }

  @Override
  public void addChildComponent(TextComponent textComponent) {
    textComponents.add(textComponent);
  }

  @Override
  public void removeChildComponent(TextComponent textComponent) {
    textComponents.remove(textComponent);
  }

//  @Override
//  public String receive() {
//    return null;
//  }

//  @Override
//  public TextComponentType getTextComponentType() {
//    return textComponentType;
//  }

//  @Override
//  public void setTextComponentType(TextComponentType textComponentType) {
//    this.textComponentType = textComponentType;
//  }

  @Override
  public String restoreText() {
    StringBuilder sb = new StringBuilder();
    for (TextComponent textComponent : textComponents) {
      sb.append(textComponent.restoreText());
    }
    return sb.toString();
  }
}
