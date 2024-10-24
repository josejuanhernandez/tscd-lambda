package software.ulpgc;

public class TextProcessor implements Processor {

	@Override
	public Summary process(String filename, String content) {
		int size = content.length();
		long numLetters = content.chars().filter(Character::isLetter).count();
		long numWords = content.split("\\s+").length;
		long numParagraphs = content.split("\\n\\n").length;

		return new Summary(filename, size, numLetters, numWords, numParagraphs);
	}
}
