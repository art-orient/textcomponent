package by.art.textcomponent.reader;

import by.art.textcomponent.exception.TextProcessorException;

public interface TextReader {
  String readText(String filepath) throws TextProcessorException;
}
