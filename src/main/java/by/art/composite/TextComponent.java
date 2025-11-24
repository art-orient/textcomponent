package by.art.composite;

import java.util.List;

public interface TextComponent {
  List<TextComponent> getChildren();
  void add(TextComponent textComponent);
  void remove(TextComponent textComponent);
  TextComponentType getComponentType();
  String restoreText();
}
