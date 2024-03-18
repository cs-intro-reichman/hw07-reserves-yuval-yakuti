

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				return true;

			}
		}
			return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		
		hashtag = hashtag.toLowerCase();
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			String currentPrefix = hashtag.substring(0,i);
			if (existInDictionary(currentPrefix, dictionary)) {
				System.out.println(currentPrefix);
				breakHashTag(hashtag.substring(i), dictionary);

				break;

			}
		
        }
    }

}
