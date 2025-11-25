package by.art.textcomponent.service.impl;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import by.art.textcomponent.service.TextProcessorService;

import java.util.ArrayList;
import java.util.List;

public class TextProcessorServiceImpl implements TextProcessorService {

  @Override
  public int findMaxNumberOfSentencesWithTheSameWord(TextComponent textComponent) {
    //TODO
    return 0;
  }

  @Override
  public List<TextComponent> sortSentencesByNumberLexeme(TextComponent textComponent) {
    //TODO
    return new ArrayList<>();
  }

  @Override
  public TextComponent swapFirstAndLastLexemes(TextComponent textComponent) {
    //TODO
    return new TextComposite(TextComponentType.SENTENCE);
  }
}
