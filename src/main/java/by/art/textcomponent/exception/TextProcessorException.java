package by.art.textcomponent.exception;

public class TextProcessorException extends Exception {
  public TextProcessorException() {
    super();
  }

  public TextProcessorException(String message) {
    super(message);
  }

  public TextProcessorException(String message, Throwable cause) {
    super(message, cause);
  }

  public TextProcessorException(Throwable cause) {
    super(cause);
  }
}