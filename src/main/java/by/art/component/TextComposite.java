package by.art.component;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
  private final List<TextComponent> textComponents = new ArrayList<>();
  private final TextComponentType textComponentType;
  private static final String TABULATION = "\t";
  private static final String SPACE = " ";
  private static final String LINE_BREAK = "\n";

  public TextComposite(TextComponentType textComponentType) {
    this.textComponentType = textComponentType;
  }

  @Override
  public void add(TextComponent textComponent) {
    textComponents.add(textComponent);
  }

  @Override
  public void remove(TextComponent textComponent) {
    textComponents.remove(textComponent);
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
  public String restoreText() {
    StringBuilder sb = new StringBuilder();
    for (TextComponent textComponent : textComponents) {
      if (getComponentType() == TextComponentType.PARAGRAPH) {
        sb.append(TABULATION);
      }
      sb.append(textComponent.restoreText());
      switch (getComponentType()) {
        case PARAGRAPH -> sb.append(LINE_BREAK);
        case SENTENCE, LEXEME -> sb.append(SPACE);
      }
    }
    return sb.toString().stripTrailing();
  }
}
