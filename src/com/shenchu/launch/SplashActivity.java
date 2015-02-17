package com.shenchu.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.shenchu.R;
import com.shenchu.base.BaseActivity;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements Runnable {
	private final int SPLASH_DISPLAY_LENGHT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		new Handler().postDelayed(this, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public void run() {
		Intent mainIntent = new Intent(this, HomeActivity.class);
		this.startActivity(mainIntent);
		this.finish();
	}

}
