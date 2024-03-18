
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 1) {
			return "";
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
	word1 = word1.toLowerCase();
	word2 = word2.toLowerCase();

	if (word1.isEmpty()) {
		return word2.length();
		}
	if (word2.isEmpty()) {
		return word1.length();
		}
	if (word1.charAt(0) == word2.charAt(0)) {
		return levenshtein(tail(word1), tail(word2));
	}	

    int insert = levenshtein(tail(word1), word2);
    int delete = levenshtein(word1, tail(word2));
    int replace = levenshtein(tail(word1), tail(word2));

	return 1 + Math.min(Math.min(insert, delete), replace);


	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		String line;

		for (int i =  0; i < dictionary.length; i++){
			line = in.readLine();
			if (line != " ") {
				dictionary[i] = line.trim();

			}

		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String closestWord = word;
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < dictionary.length; i++) {
			int distance = levenshtein(word, dictionary[i]);

			if (distance < minDistance) {
				minDistance = distance;
				closestWord = dictionary[i];
			}
		}
		if (minDistance <= threshold) {
			return closestWord;

		}else {
			return word;
		}
	}

}
