import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.ulpgc.terrace.Executor;
import software.ulpgc.terrace.Storage;
import software.ulpgc.terrace.Summary;
import software.ulpgc.terrace.TextProcessor;

public class ExecutorTest {
	@Mock
	private Storage storage;
	private Executor executor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		executor = new Executor(storage, new TextProcessor());
	}

	@Test
	public void testExecute() {
		String filename = "test-file.txt";
		String content = "This is a test content.";
		when(storage.read(filename)).thenReturn(content);
		executor.execute(filename);
		verify(storage, times(1)).read(filename);
		verify(storage, times(1)).write(any(Summary.class));
	}
}