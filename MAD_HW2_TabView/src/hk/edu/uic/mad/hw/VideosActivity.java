package hk.edu.uic.mad.hw;

import hk.edu.hk.mad.hw.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class VideosActivity extends Activity implements OnGestureListener,
		OnTouchListener {
	/** Called when the activity is first created. */
	private ViewFlipper mFlipper;
	GestureDetector mGestureDetector;
	private static final int FLING_MIN_DISTANCE = 100;
	private static final int FLING_MIN_VELOCITY = 200;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videos);

		mFlipper = (ViewFlipper) findViewById(R.id.flipper);
		// 注册一个用于手势识别的类
		mGestureDetector = new GestureDetector(this);
		// 给mFlipper设置一个listener
		mFlipper.setOnTouchListener(this);
		// 允许长按住ViewFlipper,这样才能识别拖动等手势
		mFlipper.setLongClickable(true);

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return mGestureDetector.onTouchEvent(arg1);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// 当向左侧滑动的时候
			// 设置View进入屏幕时候使用的动画
			mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.right_in));
			// 设置View退出屏幕时候使用的动画
			mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.left_out));
			mFlipper.showNext();
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// 当向右侧滑动的时候
			// 设置View进入屏幕时候使用的动画
			mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.left_in));
			// 设置View退出屏幕时候使用的动画
			mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.right_out));
			mFlipper.showPrevious();
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}
