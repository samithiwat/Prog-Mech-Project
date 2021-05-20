package logic;

public class Util {

	public static String formatDescription(String description, int lineLength) {
		String[] word = description.split(" ");

		String[] temp = new String[0];
		String[] infoWord = new String[0];

		for (int i = 0; i < word.length; i++) {
			temp = addString(temp, word[i]);

			if (String.join(" ", temp).length() > lineLength) {
				popString(temp);
				temp = addString(temp, "\n");
				infoWord = addString(infoWord, String.join(" ", temp));
				temp = new String[0];
			}
		}

		if (temp.length > 0) {
			infoWord = addString(infoWord, String.join(" ", temp));
		}

		return String.join("", infoWord);
	}

	private static String[] addString(String[] oldArray, String data) {

		int len = oldArray.length;

		String[] newArray = new String[len + 1];

		for (int i = 0; i < len; i++) {
			newArray[i] = oldArray[i];
		}

		newArray[len] = data;

		return newArray;
	}

	private static String popString(String[] oldArray) {
		int len = oldArray.length;

		String lastString = oldArray[len - 1];

		String[] newArray = new String[len - 1];
		for (int i = 0; i < len - 1; i++) {
			newArray[i] = oldArray[i];
		}

		oldArray = newArray;

		return lastString;
	}

}
