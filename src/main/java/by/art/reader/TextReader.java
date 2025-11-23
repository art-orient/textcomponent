package by.art.reader;

import by.art.exception.TextProcessorException;

public interface TextReader {
  String readText(String filepath) throws TextProcessorException;
}
