package by.art.textcomponent.service;

import by.art.textcomponent.component.TextComponent;

import java.util.List;

public interface TextProcessorService {

  int findMaxNumberOfSentencesWithTheSameWord(TextComponent textComponent);

  List<TextComponent> sortSentencesByNumberLexeme(TextComponent textComponent);

  TextComponent swapFirstAndLastLexemes(TextComponent textComponent);
}
