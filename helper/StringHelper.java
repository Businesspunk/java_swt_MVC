package helper;

import java.util.Arrays;

public class StringHelper {

	private String[] strs;

	public StringHelper(String[] items) {
		strs = items;
	}

	public String[] push(String item) {

		boolean isFind = false;

		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(item)) {
				isFind = true;
			}
		}

		if (!isFind) {
			int oldLength = strs.length;
			int newLength = oldLength + 1;
			String[] copiedArray = Arrays.copyOf(strs, newLength);
			copiedArray[oldLength] = item;
			return copiedArray;
		}

		return strs;
	}
}
