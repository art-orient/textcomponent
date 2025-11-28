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
    textComponent.restoreText();
    textComponent.countSymbols();
    TextProcessorService service = new TextProcessorServiceImpl();
    service.findMaxNumberOfSentencesWithTheSameWord(textComponent);
    service.sortSentencesByNumberLexeme(textComponent);
    service.swapFirstAndLastLexemes(textComponent);
  }
}