package by.art.textcomponent.reader.impl;

import by.art.textcomponent.exception.TextProcessorException;
import by.art.textcomponent.reader.TextReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderImplTest {
  TextReader reader;
  String filepath = "test.txt";
  String badFilePath = "no_file.txt";
  String expected = """
          1234567
              This is a paragraph. There are some sentences.
              And there is another paragraph. Some words
          are in it.
              Do you need one more?
              I don't think so.

              Bye.""";

  @BeforeEach
  void setUp() {
    reader = new TextReaderImpl();
  }

  @AfterEach
  void tearDown() {
    reader = null;
  }

  @Test
  @Tag("reader")
  void readFileWithMockShouldReturnLinesWhenFileReadSuccessfully() throws TextProcessorException {
    Path path = Path.of(filepath);
    try (var mocked = Mockito.mockStatic(Files.class)) {
      mocked.when(() -> Files.readAllBytes(path)).thenReturn(expected.getBytes());
      String actual = reader.readText(filepath);
      assertAll(
              () -> assertEquals(expected, actual),
              () -> mocked.verify(() -> Files.readAllBytes(path))
      );
    }
  }

  @Test()
  @Tag("reader")
  void readNoExistFileWithMockShouldThrowExceptionWhenExceptionOccurs() {
    Path path = Path.of(badFilePath);
    try (var mocked = Mockito.mockStatic(Files.class)) {
      mocked.when(() -> Files.readAllBytes(path))
              .thenThrow(new IOException(String.format("Failed to read file %s", badFilePath)));
      TextProcessorException exception = assertThrows(TextProcessorException.class,
              () -> reader.readText(badFilePath));
      boolean isMessageContainsFileNotRead = exception.getMessage().contains("Failed to read file");
      assertTrue(isMessageContainsFileNotRead);
    }
  }

  @Test
  @Tags({@Tag("reader"), @Tag("integration")})
  void readFileShouldReadRealFileFromResourcesSuccessfully() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resourceUrl = classLoader.getResource(filepath);
    assertNotNull(resourceUrl, "file not found");
    Path path = Path.of(resourceUrl.toURI());
    String fromFile = reader.readText(path.toString());
    String actual = fromFile.replace("\r\n", "\n");
    assertEquals(expected, actual);
  }

  @Test()
  @Tags({@Tag("reader"), @Tag("integration")})
  void readNoExistFileShouldThrowExceptionWhenExceptionOccurs() {
    TextProcessorException exception = assertThrows(TextProcessorException.class,
            () -> reader.readText(badFilePath));
    boolean isMessageContainsFileNotRead = exception.getMessage().contains("Failed to read file");
    assertTrue(isMessageContainsFileNotRead);
  }
}