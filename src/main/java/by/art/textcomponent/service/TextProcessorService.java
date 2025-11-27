package by.art.textcomponent.service;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.exception.TextProcessorException;

import java.util.List;

public interface TextProcessorService {

  int findMaxNumberOfSentencesWithTheSameWord(TextComponent textComponent) throws TextProcessorException;

  List<String> sortSentencesByNumberLexeme(TextComponent textComponent) throws TextProcessorException;

  List<String> swapFirstAndLastLexemes(TextComponent textComponent)throws TextProcessorException;
}
