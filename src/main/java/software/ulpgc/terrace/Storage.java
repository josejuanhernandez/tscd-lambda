package software.ulpgc.terrace;

public interface Storage {
	String read(String fileName);
	void write(Summary summary);
}
