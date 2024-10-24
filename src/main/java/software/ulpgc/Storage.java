package software.ulpgc;

public interface Storage {
	String read(String fileName);
	void write(Summary summary);
}
