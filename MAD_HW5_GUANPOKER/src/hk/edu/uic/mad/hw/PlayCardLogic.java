package hk.edu.uic.mad.hw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayCardLogic {

	/**
	 * Judge the input sorted string represents a hand of pure pairs, threes,
	 * four.
	 * 
	 * @param str
	 *            String represents cards figure
	 * @param Mode
	 *            2 for pairs, 3 for three and 4 for four
	 * @return true if the give string is pure pairs, threes or four. Will be
	 *         called by isPairs(), isThrees() and isFour().
	 */
	public static boolean isPure234(String str, int Mode) {
		if (str.length() % Mode != 0) {
			return false;
		}
		boolean sign = false;
		int countPair = str.length() / Mode;
		int start = 0;
		int end = 0;
		int count = 0;
		StringBuffer strPattern = new StringBuffer();
		for (int i = 0; i < str.length();) {
			char c = str.charAt(i);

			if (strPattern.length() != 0) {
				strPattern.delete(0, Mode);
			}

			for (int j = 0; j < Mode; j++) {
				strPattern.append(c);
			}

			if (i == 0) {
				start = Character.getNumericValue(c);
				end = start;
			} else
				end = Character.getNumericValue(c);
			Pattern p = Pattern.compile(strPattern.toString());
			Matcher m = p.matcher(str.subSequence(i, str.length()));
			if (m.find()) {
				// p(m.end());
				count++;
				i = i + Mode;
			} else {
				// p("exit from here");
				return false;
			}
		}
		// p(start);
		// p(end);
		if (count == countPair && (end - start == countPair - 1)) {
			if (Mode == 2 && end - start == 1) { // two pairs is not allowed
				return false;
			}
			sign = true;
		} else
			sign = false;
		return sign;
	}

	/**
	 * Judges the input sorted string represents pairs
	 * 
	 * @param str
	 *            String represents cards figure
	 * @return true if the given string are pairs. E.g. "33", and "334455"
	 */
	public static boolean isPairs(String str) {

		return isPure234(str, 2);
	}

	/**
	 * Judges the input sorted string represents three cards with the same
	 * figure or its multiple
	 * 
	 * @param str
	 *            String represents cards figure
	 * @return true if the given string are threes. E.g. "333", "333444", and
	 *         "333444555"
	 */
	public static boolean isThrees(String str) {
		return isPure234(str, 3);
	}

	/**
	 * Judges the input sorted string represents four cards with the same figure
	 * 
	 * @param str
	 *            String represents cards figure
	 * @return true if the given string are four. E.g. "4444" and "5555"
	 */
	public static boolean isFour(String str) {

		return isPure234(str, 4);
	}

	/**
	 * Judge the input sorted string represents a hand of Straight
	 * 
	 * @param str
	 *            String represents cards figure
	 * @return true if the given string are serials. E.g. "34567", "456789" etc.
	 */
	public static boolean isSerials(String str) {
		int countNum = str.length();
		boolean sign = true;
		if (countNum < 5) {
			return false;
		}
		char c = str.charAt(0);
		for (int i = 0; i < countNum - 1; i++) {
			char c2 = str.charAt(i + 1);
			if (c2 - c != 1) {
				sign = false;
				return sign;
			}
			c = c2;
		}
		return sign;
	}

	/**
	 * Judge the input sorted string represents three cards plus one or two
	 * cards.
	 * 
	 * @param str
	 *            String represents cards figure
	 * @param Mode
	 *            3 for three cards
	 * @param Plus
	 *            take 1 or 2 cards
	 * @return true if the given string are three takes one or two. Will be
	 *         called by isThreePlusOne() or isThreePlusTwo()
	 */
	public static boolean is34Plus12(String str, int Mode, int Plus) {
		if (str.length() % (Mode + Plus) != 0) {
			return false;
		}
		if (!(Mode == 3 || Mode == 4 || Plus == 1 || Plus == 2)) {
			System.out.print("input arguments are incorrect!");
			return false;
		}
		boolean sign = false;
		int countPair = str.length() / (Mode + Plus);
		int start = 0;
		int end = 0;
		int count = 0;
		StringBuffer strPattern = new StringBuffer();
		StringBuffer strLeft = new StringBuffer();
		for (int i = 0; i < str.length();) {
			char c = str.charAt(i);

			if (strPattern.length() != 0) {
				strPattern.delete(0, Mode);
			}

			for (int j = 0; j < Mode; j++) {
				strPattern.append(c);
			}

			/*
			 * if(i == 0){ start = Character.getNumericValue(c); end = start; }
			 * else end = Character.getNumericValue(c);
			 */
			Pattern p = Pattern.compile(strPattern.toString());
			Matcher m = p.matcher(str.subSequence(i, str.length()));
			if (m.find()) {
				// p(m.end());
				count++;
				i = i + Mode;
				if (count == 1) {
					start = Character.getNumericValue(c);
					end = start;
				} else {
					end = Character.getNumericValue(c);

				}
			} else {
				// p("exit from here");
				i++;
				strLeft.append(c);
			}
		}
		// p(start);
		// p(end);
		if (count == countPair && (end - start == countPair - 1)) {
			if (Plus == 1) { // strLeft can not have pair
				/*
				 * p(strLeft); p(strLeft.substring(0, 2));
				 */
				for (int i = 0; i <= strLeft.length() - 2; i++) {
					if (isPairs(strLeft.substring(i, i + 2))) {
						sign = false;
						return sign;
					}
				}
			} else { // strLeft must be all pair
				for (int i = 0; i <= strLeft.length() - 2; i = i + 2) {
					if (!isPairs(strLeft.substring(i, i + 2))) {
						sign = false;
						return sign;
					}
				}
			}
			sign = true;
		} else
			sign = false;
		return sign;

	}

	/**
	 * Judge the input sorted string represents three takes one
	 * 
	 * @param str
	 *            String represents cards figure
	 * @return true if the given string is three takes one. E.g. "3444", "5557",
	 *         "35556669" etc.
	 */
	public static boolean isThreePlusOne(String str) {
		if (isFour(str))
			return false;
		return is34Plus12(str, 3, 1);
	}

	/**
	 * Judge the input sorted string represents three takes two
	 * 
	 * @param str
	 *            String represents cards figure
	 * @return true if the given string is three takes two. E.g. "33355",
	 *         "3344455588", "335556667778899" etc.
	 */
	public static boolean isThreePlusTwo(String str) {
		return is34Plus12(str, 3, 2);
	}
}