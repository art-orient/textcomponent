package by.art.textcomponent.service.impl;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import by.art.textcomponent.service.TextProcessorService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextProcessorServiceImpl implements TextProcessorService {

  @Override
  public int findMaxNumberOfSentencesWithTheSameWord(TextComponent text) {
    //TODO
    return 0;
  }

  @Override
  public List<String> sortSentencesByNumberLexeme(TextComponent text) {
    List<TextComponent> sentences = new ArrayList<>();
    for (TextComponent paragraph : text.getChildrenComponents()) {
      if (paragraph.getComponentType() == TextComponentType.PARAGRAPH) {
        sentences.addAll(paragraph.getChildrenComponents());
      }
    }
    return sentences.stream()
            .filter(s -> s.getComponentType() == TextComponentType.SENTENCE)
            .sorted(Comparator.comparingInt(s -> s.getChildrenComponents().size()))
            .map(TextComponent :: restoreText)
            .map(String :: strip)
            .toList();
  }

  @Override
  public TextComponent swapFirstAndLastLexemes(TextComponent text) {
    //TODO
    return new TextComposite(TextComponentType.SENTENCE);
  }
}
