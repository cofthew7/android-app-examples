package hk.edu.uic.mad.hw;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import android.graphics.Point;
import android.util.Log;

public class CardGroup {
	public static final String LETTER = "abcdefghijklmnopqrstuvwxyz";
	public static final int CARDGAP = 25;
	private int numCards = 0; // number of cards
	private LinkedList<Card> cards = null; // cards list
	private Point cardsLocation = null; // the position of the first card in
										// this cards group

	CardGroup() {
		this.numCards = 0;
		this.cards = new LinkedList<Card>();
		//this.cardsLocation = new Point();
	}

	CardGroup(String[] str) {
		// 1, assign value for instance variable "numCards" and "cardsLocation"

		this.numCards = str.length;
		this.cards = new LinkedList<Card>();
		//this.cardsLocation = new Point(p);
		// 2, assign value for instance variable "cards"
		// assign value of each card in this CardGroup.
		// the gap between each card is "CARDGAP" along horizontal direction
		for (int i = 0; i < str.length; i++) {
			// Point cardP = new Point();
			// cardP.set(p.x + i * CARDGAP, p.y);
			Log.d("GUANPOKER", str[i]);
			Card c = new Card(str[i]);
			cards.add(c);
		}
	}

	CardGroup(int num, LinkedList<Card> c, int start, int end) {
		// 1, assign the value to instance variable "numCards", "cards",
		// "cardsLocation"

		this.numCards = num;
		this.cards = new LinkedList<Card>();
		//this.cardsLocation = new Point(p);
		// this.calPosition();
		// 2, extract the subset of the input "LinkedList<Card> c" from the
		// start index "start" to the end index "end"
		// you need to recalculate the position of each card in the new formed
		// card group
		int count = 0;
		int i = 0;
		Iterator<Card> it = c.iterator();
		while (it.hasNext()) {
			Card card = it.next();
			if (count >= start && count < end) {
				// card.setLocation(cardsLocation.x + i*CARDGAP,
				// cardsLocation.y);
				/*card.refreshLocation(cardsLocation.x + i * CARDGAP,
						cardsLocation.y);*/
				cards.add(card);
				i++;
			}
			count++;
		}
	}

	public void refreshCardsPositoin(int dis) {
		// this.calPosition();
		Iterator<Card> it = this.cards.iterator();
		int i = 0;
		while (it.hasNext()) {
			Card card = it.next();
			// card.setLocation((int)cardsLocation.getX() + i*CARDGAP,
			// (int)cardsLocation.getY()-dis);
			card.refreshLocation(cardsLocation.x + i * CARDGAP, cardsLocation.y
					- dis);
			i++;
		}
	}

	public CardGroup chosenCards() {
		// 0. initial some variables that will be used in this method
		CardGroup sc = new CardGroup();
		LinkedList<Card> l = new LinkedList<Card>();
		// 1. find which cards have been chosen, and form a new card group to
		// keep those cards
		Iterator<Card> it = cards.iterator();
		int count = 0;
		while (it.hasNext()) {
			Card c = it.next();

			if (c.getChosen() == true) {
				l.add(c);
				count++;
			}
		}

		// 2, return this new card group
		sc.numCards = count;
		sc.cards = l;
		//sc.cardsLocation = this.cardsLocation;
		return sc;
	}

	public String toString() {

		// print out all cards that contained in this card group
		// E.g.:
		// "8 spades"
		// "3 diamonds"
		// "14 diamonds"
		// "7 diamonds"
		// "5 club"
		// "7 hearts"
		StringBuffer str = new StringBuffer();
		Iterator<Card> it = cards.iterator();
		while (it.hasNext()) {
			Card c = it.next();
			str.append(c.toString());
			str.append("\n");
		}
		return str.toString();
	}

	public int getCardsNumber() {
		return this.numCards;
	}

	public LinkedList<Card> getCards() {
		return this.cards;
	}

	public Point getCardsLocation() {
		return this.cardsLocation;
	}

	public boolean isValid() {
		// 0. initial some variables that will be used in this method
		CardGroup cg = this;
		boolean sign = false;
		int figure = 0;
		StringBuffer strBuffer = new StringBuffer();

		// 1. sort this card group first
		try {
			Collections.sort(cg.cards);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // sort the card group

		// 2. for each card in the sorted card group, using the value of its
		// "figure" as the index to get one letter from the static String
		// "LETTER"
		// E.g.: the "figure" of one card is 4, then we can find the 4th letter
		// in "LETTER" is "d".
		// using those founded letters for each card to construct an new input
		// string
		Iterator<Card> it = cg.cards.iterator();
		while (it.hasNext()) {
			Card c = it.next();
			figure = c.getFigure();
			strBuffer.append(LETTER.charAt(figure));

		}

		// 3. call functions from PlayCardLogic to judge this input string is
		// valid or not
		// if it is valid, then return true. otherwise, return false.
		String str = strBuffer.toString();
		System.out.println(str);
		if (str.length() == 1 || PlayCardLogic.isPairs(str)
				|| PlayCardLogic.isThrees(str) || PlayCardLogic.isFour(str)
				|| PlayCardLogic.isSerials(str)
				|| PlayCardLogic.isThreePlusOne(str)
				|| PlayCardLogic.isThreePlusTwo(str)) {
			sign = true;
		}
		return sign;
	}

	public boolean deleteCards(CardGroup cg) {

		// 1. if there is no card left in "cg" or in the source card group, we
		// can not delete any more cards.
		// and return false for this case.
		try {
			if (cg.numCards == 0 || this.numCards == 0)
				return false;

			// 2. For each card appeared in "cg", delete the same one from the
			// source card group
			// if no problem happen, return true.
			Iterator<Card> it = cg.cards.iterator();
			while (it.hasNext()) {
				Card c = it.next();
				if (c.getChosen() == true) {
					this.numCards--;
					this.cards.remove(c);
				}
			}
			//this.refreshCardsPositoin(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void shuffleCards() {

		// write your code here!
		Collections.shuffle(this.cards);
	}

	public void sortByFigure() {

		// write your code here!
		Collections.sort(this.cards);
		// this.refreshCardsPositoin(0);
	}

	public void sortBySuits() {

		// write your code here!
		Card c1 = null;
		Card c2 = null;
		Card tmp = null;
		int pos = 0;
		for (int i = 0; i < this.numCards - 1; i++) {
			c1 = this.cards.get(i);
			tmp = c1;
			pos = -1;
			for (int j = i + 1; j < this.numCards; j++) {
				c2 = this.cards.get(j);
				if (c2.getSuit().compareTo(tmp.getSuit()) < 0) {
					tmp = c2;
					pos = j;
				}
			}
			if (pos != -1) {
				this.cards.set(pos, c1);
				this.cards.set(i, tmp);
			}

		}
		//this.refreshCardsPositoin(0);
	}
	
	public void setCards(LinkedList<Card> cards) {
		this.cards = cards;
	}

	public boolean isFinished(){
		if(this.numCards != this.cards.size()){
			System.out.println("need to check here!");
		}
		return this.cards.size() == 0;
	}
	
	public void unChosen(){
		Iterator<Card> it = this.getCards().iterator();
		while(it.hasNext()){
			Card c = it.next();
			c.setChosen(false);
			//c.setMoved(false);
		}
	}

	public int getNumCards() {
		return numCards;
	}
	
	
}
