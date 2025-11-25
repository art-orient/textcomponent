package by.art.textcomponent.reader.impl;

import by.art.textcomponent.exception.TextProcessorException;
import by.art.textcomponent.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public String readText(String filepath) throws TextProcessorException {
    String fullText;
    Path path = Paths.get(filepath);
    try {
      byte[] receivedBytes = Files.readAllBytes(path);
      fullText = new String(receivedBytes);
      logger.info("File {} was read successfully", filepath);
    } catch (IOException e) {
      logger.error("Failed to read file {}", filepath);
      throw new TextProcessorException(String.format("Failed to read file %s", filepath), e);
    }
    return fullText;
  }
}
