package by.art.service;

import by.art.component.TextComponent;

import java.util.List;

public interface TextProcessorService {

  int findMaxNumberOfSentencesWithTheSameWord(TextComponent textComponent);

  List<TextComponent> sortSentencesByNumberLexeme(TextComponent textComponent);

  void swapFirstAndLastLexemes(TextComponent textComponent);
}
