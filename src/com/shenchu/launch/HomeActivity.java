package com.shenchu.launch;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.shenchu.R;
import com.shenchu.base.BaseActivity;
import com.shenchu.home.HomeFrag;
import com.shenchu.job.JobFrag;
import com.shenchu.purchase.PurchaseFrag;
import com.shenchu.transfer.TransferFrag;
import com.shenchu.utils.MainToast;

@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

	@SuppressWarnings("serial")
	@SuppressLint("UseSparseArrays")
	private Map<Integer, String> fragMap = new HashMap<Integer, String>() {
		{
			put(R.id.rbtn_home, "home");
			put(R.id.rbtn_job, "job");
			put(R.id.rbtn_purchase, "purchase");
			put(R.id.rbtn_transfer, "transfer");
		}
	};
	private long lastBackClickTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
	}

	@OnRadioGroupCheckedChange(R.id.rg_main)
	public void onHomeMenuTabChanged(RadioGroup group, int checkedId) {
		showFragByTag(fragMap.get(Integer.valueOf(checkedId)));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - this.lastBackClickTime > 2000L) {
				MainToast.show(this, "再按一次退出程序", Toast.LENGTH_SHORT);
				this.lastBackClickTime = System.currentTimeMillis();
			} else {
				exitApp(this);
				System.exit(0);
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	private void hideAllFrag() {
		this.ft = this.fm.beginTransaction();
		for (String tag : fragMap.values()) {
			Fragment frag = this.fm.findFragmentByTag(tag);
			if (frag != null) {
				this.ft.hide(frag);
			}
		}
		this.ft.commitAllowingStateLoss();
	}

	private void showFragByTag(String tag) {
		hideAllFrag();

		this.ft = this.fm.beginTransaction();
		Fragment frag = this.fm.findFragmentByTag(tag);
		if (frag == null) {
			if ("home".equals(tag)) {
				frag = new HomeFrag();
			} else if ("job".equals(tag)) {
				frag = new JobFrag();
			} else if ("purchase".equals(tag)) {
				frag = new PurchaseFrag();
			} else if ("transfer".equals(tag)) {
				frag = new TransferFrag();
			}
			this.ft.add(R.id.fl_content_main, frag, tag);
		}
		this.ft.show(frag);
		this.ft.commitAllowingStateLoss();
	}
}
