package hk.edu.uic.mad.hw;

import android.graphics.Point;

public class Card implements Comparable{
	public enum Suits {
		Diamonds, Club, Hearts, Spades
	};
	private String name;
	private int figure = 0;
	private Suits suit = null;
	private boolean isBack = false;
	private boolean isChosen = false;
	private Point location = null;
	private Point preLocation = null;

	Card(String name, Point p) {
		this.name = new String(name);

		String seprator = new String("_");
		String[] sub = name.split(seprator);
		if (sub.length != 2) {
			System.out.println("input strname has problem!");
			System.out.println(name);
			System.out.println(sub[0]);
			System.out.println(sub[1]);
			System.exit(-1);
		}

		int f = 0;

		try {
			f = Integer.parseInt(sub[1]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (f) {
		case 1:
			this.figure = 14; // for Ace
			break;
		case 2:
			this.figure = 16; // for 2
			break;
		default:
			this.figure = f;
			break;
		}
		// this.figure = Integer.parseInt(sub[1]);
		switch (name.charAt(0)) {
		case 'c':
			this.suit = Suits.Club;
			break;
		case 'd':
			this.suit = Suits.Diamonds;
			break;
		case 'h':
			this.suit = Suits.Hearts;
			break;
		case 's':
			this.suit = Suits.Spades;
			break;
		}
		
		this.location = new Point(p);
		this.preLocation = new Point(p);
	}

	public void refreshLocation(int x, int y){
		this.location.set(x, y);
		this.preLocation.set(x, y);
	}
	
	public void setLocation(int x, int y) {
		this.location.set(x, y);
	}
	
	public boolean getChosen() {
		return isChosen;
	}

	public void setChosen(boolean isChosen) {
		this.isChosen = isChosen;
	}

	public int getFigure() {
		return figure;
	}

	public Suits getSuit() {
		return suit;
	}

	public String toString() {

		// write your code here!
		String tmp = null;
		tmp = (suit == Suits.Club) ? new String("club")
				: (suit == Suits.Diamonds) ? new String("diamonds")
						: (suit == Suits.Hearts) ? new String("hearts")
								: (suit == Suits.Spades) ? new String("spades")
										: null;

		return String.valueOf(figure) + " " + tmp;
	}

	public boolean equals(Object obj) {

		// if rank and suit are all equal, return true
		// otherwise, return false
		if (obj instanceof Card) {
			Card c = (Card) obj;
			if (figure == c.getFigure()) {
				return suit.equals(c.getSuit());
			}
		}

		return false;
	}

	public int hashCode() {
		return name.hashCode();
	}

	public int compareTo(Object obj) {

		// we only compare the rank of two cards here. it's different form the
		// equals() method
		// write your code here!
		Card c = (Card) obj;
		if (figure == c.getFigure()) {
			return 0;
		} else if (figure > c.getFigure()) {
			return 1;
		} else
			return -1;
	}

	
}
