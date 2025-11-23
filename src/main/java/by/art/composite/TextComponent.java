package by.art.composite;

public interface TextComponent {
  void addChildComponent(TextComponent textComponent);
  void removeChildComponent(TextComponent textComponent);
//  TextComponentType getTextLeaf();
  String restoreText();
}
