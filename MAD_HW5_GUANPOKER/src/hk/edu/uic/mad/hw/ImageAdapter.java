package hk.edu.uic.mad.hw;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private List<Bitmap> images = new LinkedList<Bitmap>();
	private List<Card> cards = new LinkedList<Card>();
	private List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards() {
		cards.clear();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			cards.add((Card) map.get("card"));
		}
	}

	public List<Bitmap> getImages() {
		return images;
	}

	public void setImages() {
		images.clear();
		Log.d("GUANPOKER", "list size " + list.size());
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			images.add((Bitmap) map.get("image"));
		}
		Log.d("GUANPOKER", "image size " + images.size());
	}
	
	public void setCardImageMap(List<HashMap<String, Object>> list) {
		this.list = list;
		this.setCards();
		this.setImages();
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return images.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(71, 116));
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			//imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
			imageView.setPadding(0, 0, 0, 0);
			Log.d("GUANPOKER", "+++++++++++++++++++++++++++++++++++++++++++++");
		}
		Log.d("GUANPOKER", "position " + position);
		imageView.setImageBitmap(images.get(position));
		return imageView;
	}

}
