package by.art.textcomponent.service.impl;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.component.TextComponentType;
import by.art.textcomponent.exception.TextProcessorException;
import by.art.textcomponent.service.TextProcessorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TextProcessorServiceImpl implements TextProcessorService {
  private static final Logger logger = LogManager.getLogger();
  private static final String SPACE = " ";
  private static final String LINE_BREAK = "\n";

  @Override
  public int findMaxNumberOfSentencesWithTheSameWord(TextComponent textComponent)
          throws TextProcessorException {
    checkComponent(textComponent);
    Map<String, Set<Integer>> wordInAllSentences = new HashMap<>();
    int sentenceIndex = 0;
    for (TextComponent paragraph : textComponent.getChildrenComponents()) {
      if (paragraph.getComponentType() != TextComponentType.PARAGRAPH) {
        continue;
      }
      for (TextComponent sentence : paragraph.getChildrenComponents()) {
        if (sentence.getComponentType() != TextComponentType.SENTENCE) {
          continue;
        }
        Set<String> wordsInSentence = extractWords(sentence);
        updateWordMap(wordInAllSentences, wordsInSentence, sentenceIndex);
        sentenceIndex++;
      }
    }
    int result = wordInAllSentences.values().stream()
            .mapToInt(Set::size)
            .max()
            .orElse(0);
    logger.info("Maximum number of sentences with the same word\n: {}", result);
    return result;
  }

  @Override
  public List<String> sortSentencesByNumberLexeme(TextComponent textComponent) throws TextProcessorException {
    checkComponent(textComponent);
    List<TextComponent> sentences = extractSentences(textComponent);
    List<String> sortedSentences = sentences.stream()
            .filter(s -> s.getComponentType() == TextComponentType.SENTENCE)
            .sorted(Comparator.comparingInt(s -> (int) s.getChildrenComponents().stream()
                            .filter(c -> c.getComponentType() == TextComponentType.LEXEME)
                            .count()))
            .map(TextComponent::restoreText)
            .map(s -> s.replace(LINE_BREAK, SPACE))
            .toList();
    logger.info("Sorted {} sentences by number of lexemes", sentences.size());
    return sortedSentences;
  }

  @Override
  public List<String> swapFirstAndLastLexemes(TextComponent textComponent) throws TextProcessorException {
    checkComponent(textComponent);
    List<TextComponent> sentences = extractSentences(textComponent);
    List<String> swappedSentences = new ArrayList<>();
    for (TextComponent sentenceComponent : sentences) {
      if (sentenceComponent.getComponentType() == TextComponentType.SENTENCE) {
        swapLexemesInSentence(sentenceComponent);
        String sentenceText = sentenceComponent.restoreText();
        swappedSentences.add(sentenceText);
      }
    }
    logger.info("Swapped {} sentences", sentences.size());
    return swappedSentences;
  }

  private void checkComponent(TextComponent textComponent) throws TextProcessorException {
    if (textComponent == null) {
      logger.warn("Text component is null");
      throw new TextProcessorException("Text component is null");
    }
  }

  private List<TextComponent> extractSentences(TextComponent textComponent) {
    List<TextComponent> sentenceComponents = new ArrayList<>();
    for (TextComponent paragraph : textComponent.getChildrenComponents()) {
      if (paragraph.getComponentType() == TextComponentType.PARAGRAPH) {
        for (TextComponent sentence : paragraph.getChildrenComponents()) {
          if (sentence.getComponentType() == TextComponentType.SENTENCE) {
            sentenceComponents.add(sentence);
          }
        }
      }
    }
    logger.debug("Extracted {} sentences", sentenceComponents.size());
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

  private void updateWordMap(Map<String, Set<Integer>> wordInAllSentences, Set<String> wordsInSentence,
                             int sentenceIndex) {
    for (String word : wordsInSentence) {
      wordInAllSentences.computeIfAbsent(word, key -> new HashSet<>())
              .add(sentenceIndex);
    }
  }

  private void swapLexemesInSentence(TextComponent sentence) {
    int firstLexemeIndex = -1;
    int lastLexemeIndex = -1;
    List<TextComponent> lexemesAndSpaces = sentence.getChildrenComponents();
    for (int i = 0; i < lexemesAndSpaces.size(); i++) {
      TextComponent child = sentence.getChildrenComponents().get(i);
      if (child.getComponentType() == TextComponentType.LEXEME) {
        if (firstLexemeIndex == -1) {
          firstLexemeIndex = i;
        }
        lastLexemeIndex = i;
      }
    }
    if (firstLexemeIndex != -1 && firstLexemeIndex != lastLexemeIndex) {
      TextComponent firstLexeme = lexemesAndSpaces.get(firstLexemeIndex);
      TextComponent lastLexeme = lexemesAndSpaces.get(lastLexemeIndex);
      lexemesAndSpaces.set(firstLexemeIndex, lastLexeme);
      lexemesAndSpaces.set(lastLexemeIndex, firstLexeme);
      logger.atDebug().log("Swap lexemes - {} {}", firstLexeme.restoreText(), lastLexeme.restoreText());
    }
  }
}
