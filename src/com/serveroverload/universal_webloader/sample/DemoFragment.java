package com.serveroverload.universal_webloader.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.serveroverload.universal_webloader.UniversalWebViewFragment;
import com.serveroverload.youtube_webview.R;

public class DemoFragment extends Fragment {

	private static final String UNIVERSAL_WEB_FRAG_TAG = "UniversalWebFragTag";
	// Demo URLS
	private static final String FACEBOOK_DEMO_URL = "https://www.facebook.com/HitsDroid";
	private static final String YOU_TUBE_DEMO_URL = "https://www.youtube.com/user/androiddevelopers";
	private static final String PAYU_MONEY_URL = "https://www.payumoney.com/paybypayumoney/#/BCDCEAE6A98116CB48BDE55C440BC69D";
	private static final String PDF_URL = "http://partners.adobe.com/public/developer/en/acrobat/PDFOpenParameters.pdf";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.demo_fragment,
				container, false);

		// Youtube Demo
		rootView.findViewById(R.id.youtube).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// Add the fragment to the 'fragment_container'
						// FrameLayout

						getActivity()
								.getSupportFragmentManager()
								.beginTransaction()
								.add(R.id.frag_root,
										UniversalWebViewFragment.newInstance(
												YOU_TUBE_DEMO_URL, false))
								.addToBackStack(UNIVERSAL_WEB_FRAG_TAG)
								.commit();

					}
				});

		// FaceBook Demo
		rootView.findViewById(R.id.facebook).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Add the fragment to the 'fragment_container'
						// FrameLayout
						getActivity()
								.getSupportFragmentManager()
								.beginTransaction()
								.add(R.id.frag_root,
										UniversalWebViewFragment.newInstance(
												FACEBOOK_DEMO_URL, false))
								.addToBackStack(UNIVERSAL_WEB_FRAG_TAG)
								.commit();

					}
				});

		// PayYou Money Demo
		rootView.findViewById(R.id.pay_you_money).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Add the fragment to the 'fragment_container'
						// FrameLayout
						getActivity()
								.getSupportFragmentManager()
								.beginTransaction()
								.add(R.id.frag_root,
										UniversalWebViewFragment.newInstance(
												PAYU_MONEY_URL, false))
								.addToBackStack(UNIVERSAL_WEB_FRAG_TAG)
								.commit();

					}
				});

		// Custom Google search Demo
		rootView.findViewById(R.id.google_search).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Add the fragment to the 'fragment_container'
						// FrameLayout
						getActivity()
								.getSupportFragmentManager()
								.beginTransaction()
								.add(R.id.frag_root,
										UniversalWebViewFragment.newInstance(
												((EditText) rootView
														.findViewById(R.id.serach_edt))
														.getText().toString(),
												true))
								.addToBackStack(UNIVERSAL_WEB_FRAG_TAG)
								.commit();

					}
				});

		// PDF Demo
		rootView.findViewById(R.id.pdf_demo).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						// Add the fragment to the 'fragment_container'
						getActivity()
								.getSupportFragmentManager()
								.beginTransaction()
								.add(R.id.frag_root,
										UniversalWebViewFragment.newInstance(
												"http://docs.google.com/viewer?url="
														+ PDF_URL, false))
								.addToBackStack(UNIVERSAL_WEB_FRAG_TAG)
								.commit();

					}
				});

		return rootView;

	}
}
