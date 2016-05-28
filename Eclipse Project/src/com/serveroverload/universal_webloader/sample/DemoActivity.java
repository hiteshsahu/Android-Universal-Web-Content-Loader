package com.serveroverload.universal_webloader.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.serveroverload.youtube_webview.R;

@SuppressLint("NewApi")
public class DemoActivity extends FragmentActivity {

	private static final String DEMO_FRAGMENT_TAG = "DemoFragment";

	/**
	 * Called when the activity is first created.
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_activity);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.frag_root, new DemoFragment())
				.addToBackStack(DEMO_FRAGMENT_TAG).commit();

	}

	@Override
	public void onBackPressed() {

		int count = getFragmentManager().getBackStackEntryCount();

		if (count == 0) {
			
			super.onBackPressed();
			// finish();
		} else {
			getFragmentManager().popBackStack();
		}

	}

}
