package hk.edu.uic.mad.hw;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Point;
import android.os.Environment;
import android.util.Log;

public class Dealer {

	private static final String path = Environment.getExternalStorageDirectory().toString() + "/GuanPoker/";
	
	public static CardGroup cg;
	public static CardGroup cg1;
	public static CardGroup cg2;
	public static CardGroup cg3;

	/**
	 * Read image directory to obtain image file name. the format of the file
	 * name should be "suits-number.gif"
	 * 
	 * @param url
	 *            image directory
	 * @return String array that recording file name
	 */
	public static String[] readImageFolder(String url) {
		String tmp = new String();
		String[] str = null;
		Pattern p = Pattern.compile("[a-z]{4,8}-\\d{1,2}\\.gif");
		HashSet<String> hs = new HashSet<String>();
		File f = new File(url);
		if (!f.exists() || !f.isDirectory()) {
			System.out.println("please input an valid directory name!");
			return null;
		}
		File[] fArray = f.listFiles();
		for (int i = 0; i < fArray.length; i++) {
			if (fArray[i].isFile()) {
				tmp = fArray[i].getName();
				Matcher m = p.matcher(tmp);
				if (m.matches()) {
					hs.add(tmp.substring(0, tmp.length() - 4));
				}
			}
		}
		if (str == null) {
			str = new String[hs.size()];
			Iterator<String> it = hs.iterator();
			int j = 0;
			while (it.hasNext()) {
				tmp = it.next();
				str[j] = new String(tmp);
				j++;
			}
		}
		return str;
	}

	public static void initCardGroup() {
		// PlayCardLogic.initCards(cg, cg1, cg2, cg3);
		String[] str = null;
		// Point p2 = new Point(10, 400);
		str = readImageFolder(path);
		Log.d("GUANPOKER", str[0]);
		System.out.println(str.length);
		cg = new CardGroup(str);
		cg.shuffleCards();

		deal();

	}

	public static void deal() {
		int num = cg.getCardsNumber() / 3;
		try {
			cg1 = new CardGroup(num, cg.getCards(), 0,
					num);
			cg2 = new CardGroup(num, cg.getCards(), num,
					2 * num);
			cg3 = new CardGroup(num, cg.getCards(), 2 * num, 
					3 * num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void newGame() {

		cg.shuffleCards();
		cg.unChosen();
		deal();
		// cg1.uncoverCards();
		cg1.refreshCardsPositoin(0);
		System.out.println("++" + cg1);

	}

}