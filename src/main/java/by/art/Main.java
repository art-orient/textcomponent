package by.art;

import by.art.component.TextComponent;
import by.art.exception.TextProcessorException;
import by.art.parser.LexemeParser;
import by.art.parser.ParagraphParser;
import by.art.parser.SentenceParser;
import by.art.parser.LetterParser;
import by.art.parser.TextParser;
import by.art.parser.WordParser;
import by.art.reader.TextReader;
import by.art.reader.impl.TextReaderImpl;

public class Main {
  private static final String FILEPATH = "data/text.txt";
  public static void main(String[] args) throws TextProcessorException {
    TextReader reader = new TextReaderImpl();
    String text = reader.readText(FILEPATH);
    LetterParser letterParser = new LetterParser();
    WordParser wordParser = new WordParser(letterParser);
    LexemeParser lexemeParser = new LexemeParser(wordParser);
    SentenceParser sentenceParser = new SentenceParser(lexemeParser);
    ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
    TextParser textParser = new TextParser(paragraphParser);
    TextComponent textComponent = textParser.parseText(text);
    String restoredText = textComponent.restoreText();
    System.out.println(restoredText);
  }
}