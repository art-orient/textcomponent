package by.art.textcomponent;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.exception.TextProcessorException;
import by.art.textcomponent.parser.LexemeParser;
import by.art.textcomponent.parser.ParagraphParser;
import by.art.textcomponent.parser.SentenceParser;
import by.art.textcomponent.parser.TextParser;
import by.art.textcomponent.parser.WordParser;
import by.art.textcomponent.reader.TextReader;
import by.art.textcomponent.reader.impl.TextReaderImpl;
import by.art.textcomponent.service.TextProcessorService;
import by.art.textcomponent.service.impl.TextProcessorServiceImpl;

import java.util.List;

public class Main {
  private static final String FILEPATH = "data/text.txt";
  public static void main(String[] args) throws TextProcessorException {
    TextReader reader = new TextReaderImpl();
    String text = reader.readText(FILEPATH);

    WordParser wordParser = new WordParser();
    LexemeParser lexemeParser = new LexemeParser(wordParser);
    SentenceParser sentenceParser = new SentenceParser(lexemeParser);
    ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
    TextParser textParser = new TextParser(paragraphParser);

    TextComponent textComponent = textParser.parseText(text);
    String restoredText = textComponent.restoreText();

    System.out.println(restoredText);
    System.out.println(textComponent.countSymbols());
    TextProcessorService service = new TextProcessorServiceImpl();
    List<String> sortedSentences = service.sortSentencesByNumberLexeme(textComponent);
    for (String sentence : sortedSentences) {
      System.out.println(sentence);
    }
    System.out.println(service.findMaxNumberOfSentencesWithTheSameWord(textComponent));
    List<String> swappedLexemes = service.swapFirstAndLastLexemes(textComponent);
    for (String sentence : swappedLexemes) {
      System.out.println(sentence);
    }
  }
}