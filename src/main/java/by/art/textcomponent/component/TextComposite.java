package by.art.textcomponent.component;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
  private static final String TABULATION = "\t";
  private static final String LINE_BREAK = "\n";
  private static final String SPACE = " ";
  private final List<TextComponent> textComponents = new ArrayList<>();
  private final TextComponentType textComponentType;

  public TextComposite(TextComponentType textComponentType) {
    this.textComponentType = textComponentType;
  }

  @Override
  public void add(TextComponent textComponent) {
    textComponents.add(textComponent);
  }

  @Override
  public List<TextComponent> getChildrenComponents() {
    return textComponents;
  }

  @Override
  public TextComponentType getComponentType() {
    return textComponentType;
  }

  @Override
  public int countSymbols() {
    int counter = 0;
    for (TextComponent textComponent : textComponents) {
      counter += textComponent.countSymbols();
    }
    return counter;
  }

  @Override
  public String restoreText() {
    StringBuilder sb = new StringBuilder();
    for (TextComponent textComponent : textComponents) {
      sb.append(textComponent.restoreText());
    }
    return sb.toString();
  }
}
