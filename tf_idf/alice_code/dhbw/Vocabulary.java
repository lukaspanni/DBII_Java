
package dhbw;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Vocabulary
{
	private final Set<String> ignoreWords = new HashSet<String>();

	public Vocabulary() {
	}

	public boolean isIgnored(String term) {
		return ignoreWords.contains(term);
	}

	public void loadIgnoreWords() {
		try {
			InputStream input = new FileInputStream(Alice.DIRNAME + "ignore_words.txt");
			Scanner sc = new Scanner(input);
			while (sc.hasNext())
				ignoreWords.add(sc.next());
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

