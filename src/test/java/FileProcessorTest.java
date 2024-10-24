import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import software.ulpgc.terrace.Summary;
import software.ulpgc.terrace.TextProcessor;

public class FileProcessorTest {

	@Test
	public void testProcessFile() {
		TextProcessor processor = new TextProcessor();
		String content = "This is a test.\nThis is another test.";
		String filename = "test-file.txt";

		Summary summary = processor.process(filename, content);

		// Assert
		assertEquals(37, summary.fileSize());
		assertEquals(28, summary.numLetters());
		assertEquals(8, summary.numWords());
		assertEquals(1, summary.numParagraphs());
	}
}