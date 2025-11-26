package by.art.textcomponent.service;

import by.art.textcomponent.component.TextComponent;

import java.util.List;

public interface TextProcessorService {

  int findMaxNumberOfSentencesWithTheSameWord(TextComponent textComponent);

  List<String> sortSentencesByNumberLexeme(TextComponent textComponent);

  TextComponent swapFirstAndLastLexemes(TextComponent textComponent);
}
