package com.serveroverload.universal_webloader;

import com.serveroverload.youtube_webview.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

@SuppressLint("NewApi")
public class HomeActivity extends FragmentActivity {
	private static final String FACEBOOK_DEMO_URL = "https://www.facebook.com/HitsDroid";
	private static final String YOU_TUBE_DEMO_URL = "https://www.youtube.com/user/androiddevelopers";
	private static final String PAYU_MONEY_URL = "https://www.payumoney.com/paybypayumoney/#/BCDCEAE6A98116CB48BDE55C440BC69D";
	private static final String PDF_URL = "http://partners.adobe.com/public/developer/en/acrobat/PDFOpenParameters.pdf";

	/**
	 * Called when the activity is first created.
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		findViewById(R.id.youtube).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Add the fragment to the 'fragment_container' FrameLayout

				toggleControlPanel(false);
				getSupportFragmentManager().beginTransaction()
						.add(R.id.frag_root, UniversalWebViewFragment.newInstance(YOU_TUBE_DEMO_URL, false)).commit();

			}
		});

		findViewById(R.id.facebook).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				toggleControlPanel(false);
				// Add the fragment to the 'fragment_container' FrameLayout
				getSupportFragmentManager().beginTransaction()
						.add(R.id.frag_root, UniversalWebViewFragment.newInstance(FACEBOOK_DEMO_URL, false)).commit();

			}
		});

		findViewById(R.id.pay_you_money).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				toggleControlPanel(false);
				// Add the fragment to the 'fragment_container'
				// FrameLayout
				getSupportFragmentManager().beginTransaction()
						.add(R.id.frag_root, UniversalWebViewFragment.newInstance(PAYU_MONEY_URL, false)).commit();

			}
		});

		findViewById(R.id.google_search).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				toggleControlPanel(false);

				// Add the fragment to the 'fragment_container'
				// FrameLayout
				getSupportFragmentManager().beginTransaction()
						.add(R.id.frag_root,
								UniversalWebViewFragment.newInstance(
										((EditText) findViewById(R.id.serach_edt)).getText().toString(), true))
						.commit();

			}
		});

		findViewById(R.id.pdf_demo).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				toggleControlPanel(false);

				// Add the fragment to the 'fragment_container'
				getSupportFragmentManager().beginTransaction().add(R.id.frag_root,
						UniversalWebViewFragment.newInstance("http://docs.google.com/viewer?url=" + PDF_URL, false))
						.commit();

			}
		});
	}

	public void toggleControlPanel(boolean shouldShowControl) {

		if (shouldShowControl) {
			findViewById(R.id.controlls_root).setVisibility(View.VISIBLE);
			findViewById(R.id.frag_root).setVisibility(View.GONE);
		} else {
			findViewById(R.id.controlls_root).setVisibility(View.GONE);
			findViewById(R.id.frag_root).setVisibility(View.VISIBLE);
		}

	}

}
