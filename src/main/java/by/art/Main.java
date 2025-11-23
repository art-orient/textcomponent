package by.art;

import by.art.composite.TextComponentType;
import by.art.composite.TextComposite;
import by.art.exception.TextProcessorException;
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
    System.out.println(text);
    TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);
//    ParagraphParser paragraphParser = new ParagraphParser(SentenceParser);
//    SentenceParser sentenceParser = new SentenceParser();
//    WordParser wordParser = new WordParser();
//    SymbolParser symbolParser = new SymbolParser();

  }
}