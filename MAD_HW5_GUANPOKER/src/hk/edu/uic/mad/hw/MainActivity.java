package hk.edu.uic.mad.hw;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	/** Called when the activity is first created. */

	// my cards
	private List<HashMap<String, Object>> myCardAndImageList;
	private List<Card> myCards;
	private CardGroup cgMyCards;
	private ImageAdapter myCardsAdapter;
	private GridView myCardsGridview;
	
	// my play cards
	private List<HashMap<String, Object>> myPlayCardAndImageList;
	private List<Card> myPlayCards;
	private CardGroup cgMyPlayCards;
	private ImageAdapter myPlayCardsAdapter;
	private GridView myPlayCardsGridView;
	
	private Button sortByFigure;
	private Button sortBySuit;
	private Button play;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 发牌
		Dealer.initCardGroup();
		cgMyCards = Dealer.cg1;

		// buttons
		initButton();
		// my cards
		initMyCard();
		// my play cards
		initMyPlayCard();
	}

	private void initButton() {
		sortBySuit = (Button) findViewById(R.id.sort_by_suit);
		sortByFigure = (Button) findViewById(R.id.sort_by_figure);
		play = (Button) findViewById(R.id.play);
	
		sortByFigure.setOnClickListener(this);
		sortBySuit.setOnClickListener(this);
		play.setOnClickListener(this);
	}
	
	private void initMyCard() {
		myCardAndImageList = new LinkedList<HashMap<String, Object>>();
		myCardsGridview = (GridView) findViewById(R.id.mycards_gridview);
		myCardsAdapter = new ImageAdapter(this);
		
		myCardsAdapter.setCardImageMap(getCardImageMap(cgMyCards, myCardAndImageList));
		myCards = myCardsAdapter.getCards();
		myCardsGridview.setAdapter(myCardsAdapter);
		myCardsGridview.setNumColumns(myCardsAdapter.getCount());

		myCardsGridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {
				if (myCards.get(position).getChosen() == false) {
					v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom() + 20);
					myCards.get(position).setChosen(true);
					cgMyCards.setCards((LinkedList<Card>)myCards);
				} else {
					v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom() - 20);
					myCards.get(position).setChosen(false);
					cgMyCards.setCards((LinkedList<Card>)myCards);
				}	
			}
		});
	}
	
	private void initMyPlayCard() {
		myPlayCardsGridView = (GridView) findViewById(R.id.myplaycards_gridview);

		myPlayCardAndImageList = new LinkedList<HashMap<String, Object>>();
		myPlayCardsAdapter = new ImageAdapter(this);
		myPlayCards = new LinkedList<Card>();
		cgMyPlayCards = new CardGroup();
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.sort_by_figure:
			sortByFigure();
			break;
		case R.id.sort_by_suit:
			sortBySuit();
			break;
		case R.id.play:
			play();
			break;
		}
	}
	
	private List<HashMap<String, Object>> getCardImageMap(CardGroup cg, List<HashMap<String, Object>> cil) {
		
		if (cg == null) {
			cil.clear();
			return cil;
		}
		
		for (int i = 0; i < cg.getCardsNumber(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Card card = cg.getCards().get(i);
			Bitmap bm = BitmapFactory.decodeFile(card.getPath());

			map.put("card", card);
			map.put("image", bm);

			cil.add(map);
		}
		return cil;
	}


	public void sortByFigure(){
		cgMyCards.sortByFigure();
		myCardAndImageList.clear();
		myCardsAdapter.setCardImageMap(getCardImageMap(cgMyCards, myCardAndImageList));
		myCardsAdapter.notifyDataSetChanged();
		myCardsGridview.invalidate();
	}
	
	public void sortBySuit() {
		cgMyCards.sortBySuits();
		myCardAndImageList.clear();
		myCardsAdapter.setCardImageMap(getCardImageMap(cgMyCards, myCardAndImageList));
		myCardsAdapter.notifyDataSetChanged();
		myCardsGridview.invalidate();
	}
	
	public void play() {
		
		if (myPlayCards.size() != 0) {
			cgMyPlayCards = null;
			myPlayCards.clear();
			myPlayCardAndImageList.clear();
			myPlayCardsAdapter.setCardImageMap(getCardImageMap(cgMyPlayCards, myPlayCardAndImageList));
			myPlayCardsAdapter.notifyDataSetChanged();
			myPlayCardsGridView.invalidate();
		}
		
		cgMyPlayCards = cgMyCards.chosenCards();
		if (cgMyPlayCards.isValid()) {
			myPlayCardsAdapter.setCardImageMap(getCardImageMap(cgMyPlayCards, myPlayCardAndImageList));
			myPlayCards = myPlayCardsAdapter.getCards();
			myPlayCardsGridView.setAdapter(myPlayCardsAdapter);
			myPlayCardsGridView.setNumColumns(myPlayCardsAdapter.getCount());
			
			// 更新my cards
			cgMyCards.deleteCards(cgMyPlayCards);
			myCardAndImageList.clear();
			myCardsAdapter.setCardImageMap(getCardImageMap(cgMyCards, myCardAndImageList));
			myCardsAdapter.notifyDataSetChanged();
			myCardsGridview.setGravity(Gravity.CENTER);
			myCardsGridview.invalidate();
			
		} else {
			Toast.makeText(getApplicationContext(), "Invalid!!!", Toast.LENGTH_SHORT).show();
			cgMyPlayCards.deleteCards(cgMyPlayCards);
		}
	}
}