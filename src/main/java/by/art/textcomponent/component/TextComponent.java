package by.art.textcomponent.component;

import java.util.List;

public interface TextComponent {
  List<TextComponent> getChildrenComponents();
  void add(TextComponent textComponent);
  TextComponentType getComponentType();
  int countSymbols();
  String restoreText();
}
