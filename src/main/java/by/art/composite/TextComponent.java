package by.art.composite;

public interface TextComponent {
  void add(TextComponent textComponent);
  void remove(TextComponent textComponent);
  TextLeaf getTextComponentType();
  String toString();
}
