package by.art;

import by.art.composite.TextComposite;
import by.art.parser.AbstractParser;
import by.art.parser.ParagraphParser;
import by.art.parser.SentenceParcer;
import by.art.parser.SymbolParser;
import by.art.parser.WordParser;

public class Main {
  public static void main(String[] args) {
    TextComposite textComposite = new TextComposite();
    ParagraphParser paragraphParser = new ParagraphParser();
    SentenceParcer sentenceParcer = new SentenceParcer();
    WordParser wordParser = new WordParser();
    SymbolParser symbolParser = new SymbolParser();

  }
}