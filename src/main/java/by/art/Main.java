package by.art;

import by.art.exception.TextProcessorException;
import by.art.reader.TextReader;
import by.art.reader.impl.TextReaderImpl;

public class Main {
  private static final String FILEPATH = "data/text.txt";
  public static void main(String[] args) throws TextProcessorException {
    TextReader reader = new TextReaderImpl();
    String text = reader.readText(FILEPATH);
    System.out.println(text);

//    TextComposite textComposite = new TextComposite();
//    ParagraphParser paragraphParser = new ParagraphParser();
//    SentenceParser sentenceParser = new SentenceParser();
//    WordParser wordParser = new WordParser();
//    SymbolParser symbolParser = new SymbolParser();

  }
}