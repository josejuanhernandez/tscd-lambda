package software.ulpgc.terrace;

public interface Processor {
	Summary process(String filename, String content);
}