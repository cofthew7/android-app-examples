package hk.edu.uic.mad.hw;

import hk.edu.hk.mad.hw.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotosActivity extends Activity implements OnClickListener{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos);
		
		ImageView imageView = (ImageView) findViewById(R.id.photo1);
		imageView.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.option_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.bookmark:
	            showToast(this.getResources().getText(R.string.menu_bookmark).toString());
	            return true;
	        case R.id.save:
	        	showToast(this.getResources().getText(R.string.menu_save).toString());
	            return true;
	        case R.id.search:
	        	showToast(this.getResources().getText(R.string.menu_search).toString());
	        	return true;
	        case R.id.share:
	        	showToast(this.getResources().getText(R.string.menu_share).toString());
	        	return true;
	        case R.id.delete:
	        	showToast(this.getResources().getText(R.string.menu_delete).toString());
	        	return true;
	        case R.id.preferences:
	        	showToast(this.getResources().getText(R.string.menu_preferences).toString());
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void showToast(String stelectedMenuItem) {
		Toast.makeText(getApplicationContext(),
				stelectedMenuItem + " is selected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		Dialog dialog = new AlertDialog.Builder(PhotosActivity.this)
		.setIcon(R.drawable.save)
		.setTitle(this.getResources().getText(R.string.dialog_title).toString())
		.setMessage(this.getResources().getText(R.string.dialog_message).toString())
		.setPositiveButton(R.string.dialog_positive, 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getApplicationContext(),
								"You clicked on " + getResources().getText(R.string.dialog_positive), Toast.LENGTH_SHORT).show();
					}
				})
		.setNegativeButton(R.string.dialog_negative,
				new DialogInterface.OnClickListener() {
			
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(),
							"You clicked on " + getResources().getText(R.string.dialog_negative), Toast.LENGTH_SHORT).show();
				}
			})
		.setNeutralButton(R.string.dialog_neutral,
				new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(),
							"You clicked on " + getResources().getText(R.string.dialog_neutral), Toast.LENGTH_SHORT).show();
				}
			}).create();
		dialog.show();
	}

}