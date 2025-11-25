package by.art.textcomponent;

import by.art.textcomponent.component.TextComponent;
import by.art.textcomponent.exception.TextProcessorException;
import by.art.textcomponent.parser.LexemeParser;
import by.art.textcomponent.parser.ParagraphParser;
import by.art.textcomponent.parser.SentenceParser;
import by.art.textcomponent.parser.LetterParser;
import by.art.textcomponent.parser.TextParser;
import by.art.textcomponent.parser.WordParser;
import by.art.textcomponent.reader.TextReader;
import by.art.textcomponent.reader.impl.TextReaderImpl;

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
    System.out.println(textComponent.countSymbols());
  }
}