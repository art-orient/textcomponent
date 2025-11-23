package by.art;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;
import by.art.exception.TextProcessorException;
import by.art.parser.LexemeParser;
import by.art.parser.ParagraphParser;
import by.art.parser.SentenceParser;
import by.art.parser.SymbolParser;
import by.art.parser.WordParser;
import by.art.reader.TextReader;
import by.art.reader.impl.TextReaderImpl;

public class Main {
  private static final String FILEPATH = "data/text.txt";
  public static void main(String[] args) throws TextProcessorException {
    TextReader reader = new TextReaderImpl();
    String text = reader.readText(FILEPATH);
    SymbolParser symbolParser = new SymbolParser();
    WordParser wordParser = new WordParser(symbolParser);
    LexemeParser lexemeParser = new LexemeParser(wordParser);
    SentenceParser sentenceParser = new SentenceParser(lexemeParser);
    ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
    TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);
    paragraphParser.parseText(textComposite, text);
    textComposite.restoreText();



  }
}