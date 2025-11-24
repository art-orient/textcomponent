package by.art.component;

import java.util.List;

public interface TextComponent {
  List<TextComponent> getChildrenComponents();
  void add(TextComponent textComponent);
  void remove(TextComponent textComponent);
  TextComponentType getComponentType();
  String restoreText();
}
