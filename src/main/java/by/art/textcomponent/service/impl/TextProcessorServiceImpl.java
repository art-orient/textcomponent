package by.art.textcomponent.service.impl;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.component.TextComposite;
import by.art.textcomponent.service.TextProcessorService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TextProcessorServiceImpl implements TextProcessorService {
  private static final String SPACE = " ";
  private static final String LINE_BREAK = "\n";

  @Override
  public int findMaxNumberOfSentencesWithTheSameWord(TextComponent text) {
    Map<String, Set<Integer>> wordToSentences = new HashMap<>();
    int sentenceIndex = 0;
    for (TextComponent paragraph : text.getChildrenComponents()) {
      if (paragraph.getComponentType() != TextComponentType.PARAGRAPH) {
        continue;
      }
      for (TextComponent sentence : paragraph.getChildrenComponents()) {
        if (sentence.getComponentType() != TextComponentType.SENTENCE) {
          continue;
        }
        Set<String> wordsInSentence = extractWords(sentence);
        for (String word : wordsInSentence) {
          wordToSentences
                  .computeIfAbsent(word, k -> new HashSet<>())
                  .add(sentenceIndex);
        }
        sentenceIndex++;
      }
    }
    int maxCount = 0;
    for (Set<Integer> sentenceSet : wordToSentences.values()) {
      maxCount = Math.max(maxCount, sentenceSet.size());
    }
    return maxCount;
  }

  @Override
  public List<String> sortSentencesByNumberLexeme(TextComponent textComponent) {
    List<TextComponent> sentences = extractSentences(textComponent);
    return sentences.stream()
            .filter(s -> s.getComponentType() == TextComponentType.SENTENCE)
            .sorted(Comparator.comparingInt(s -> (int) s.getChildrenComponents().stream()
                            .filter(c -> c.getComponentType() == TextComponentType.LEXEME)
                            .count()))
            .map(TextComponent :: restoreText)
            .map(s -> s.replace(LINE_BREAK, SPACE))
            .map(String::strip)
            .toList();
  }

  @Override
  public TextComponent swapFirstAndLastLexemes(TextComponent textComponent) {
    //TODO
    return new TextComposite(TextComponentType.SENTENCE);
  }

  private List<TextComponent> extractSentences(TextComponent textComponent) {
    List<TextComponent> sentenceComponents = new ArrayList<>();
    for (TextComponent paragraph : textComponent.getChildrenComponents()) {
      if (paragraph.getComponentType() == TextComponentType.PARAGRAPH) {
        sentenceComponents.addAll(paragraph.getChildrenComponents());
      }
    }
    return sentenceComponents;
  }

  private Set<String> extractWords(TextComponent sentenceComponent) {
    Set<String> wordsInSentence = new HashSet<>();
    for (TextComponent lexeme : sentenceComponent.getChildrenComponents()) {
      if (lexeme.getComponentType() == TextComponentType.LEXEME) {
        for (TextComponent child : lexeme.getChildrenComponents()) {
          if (child.getComponentType() == TextComponentType.WORD) {
            String word = child.restoreText().toLowerCase();
            if (!word.isBlank()) {
              wordsInSentence.add(word);
            }
          }
        }
      }
    }
    return wordsInSentence;
  }
}
