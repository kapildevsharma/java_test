package javaTest;

public class LongestPalindrom {
	int resultStart;
	int resultlength;

	public String findLongestPalindrome(String s) {
		int strLength = s.length();
		if (strLength < 2) {
			return s;
		}
		for (int start = 0; start < strLength - 1; start++) {
			expandRange(s, start, start);
			expandRange(s, start, start + 1);
		}

		return s.substring(resultStart, resultStart + resultlength);
	}

	private void expandRange(String str, int begin, int end) {
		while (begin >= 0 && end < str.length() && str.charAt(begin) == str.charAt(end)) {
			begin--;
			end++;
		}
		if (resultlength < end - begin - 1) {
			resultStart = begin + 1;
			resultlength = end - begin - 1;
		}
	}

	static public String intermediatePalindrome(String s, int left, int right) {
		if (left > right) return null;
		while (left >= 0 && right < s.length()
				&& s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return s.substring(left + 1, right);
	}

	// O(n^2)
	public static String longestPalindromeString(String s) {
		if (s == null) return null;
		String longest = s.substring(0, 1);
		for (int i = 0; i < s.length() - 1; i++) {
			//odd cases like 121
			String palindrome = intermediatePalindrome(s, i, i);
			if (palindrome.length() > longest.length()) {
				longest = palindrome;
			}
			//even cases like 1221
			palindrome = intermediatePalindrome(s, i, i + 1);
			if (palindrome.length() > longest.length()) {
				longest = palindrome;
			}
		}
		return longest;
	}
	public static void main(String args[]) {
		LongestPalindrom lp = new LongestPalindrom();
		String result = lp.longestPalindromeString("fdsaippuakivikauppiasfyft");
		System.out.println("result :: " + result);
	}
}
