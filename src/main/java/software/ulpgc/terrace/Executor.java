package software.ulpgc.terrace;

public class Executor {
	private final Storage storage;
	private final Processor processor;

	public Executor(Storage storage, Processor processor) {
		this.storage = storage;
		this.processor = processor;
	}
	
	public void execute(String filename) {
		String content = storage.read(filename);
		Summary summary = processor.process(filename, content);
		storage.write(summary);

	}
}
