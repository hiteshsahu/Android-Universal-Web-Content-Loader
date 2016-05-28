package com.serveroverload.universal_webloader;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import universal_webloader.serveroverload.com.universalwebcontentloader.R;

/*
* This fragment work as in App browser and handle web page loading in your app.
* All you need to do is drop this fragment in your project along with universal_web_view.xml & video_progress.xml
*
 */

@SuppressLint("NewApi")
public class UniversalWebViewFragment extends Fragment {

    public static final String WEB_URL_TO_LOAD = "webURLToLoad";
    private static final String GOOGLE_SERACH_URL = "https://www.google.com/search?q=";

    private WebView webView;
    private FrameLayout customViewContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private View mCustomView;
    private MyWebChromeClient mWebChromeClient;
    private MyWebViewClient mWebViewClient;

    private int webViewPreviousState;
    private final int PAGE_STARTED = 0x1;
    private final int PAGE_REDIRECTED = 0x2;
    private View rootView;
    private String urlToLoad = GOOGLE_SERACH_URL;

    public static UniversalWebViewFragment newInstance(String webUrl,
                                                       boolean serachOnWeb) {
        Bundle bdl = new Bundle();
        if (serachOnWeb) {

            // Search on google for query
            bdl.putString(WEB_URL_TO_LOAD, GOOGLE_SERACH_URL + webUrl);
        } else {

            // simply load url
            bdl.putString(WEB_URL_TO_LOAD, webUrl);
        }
        UniversalWebViewFragment newInstance = new UniversalWebViewFragment();
        newInstance.setArguments(bdl);
        return newInstance;
    }

    /**
     * Called when the activity is first created.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.universal_web_view, container,
                false);


        if (null != getArguments()
                && null != getArguments().getString(WEB_URL_TO_LOAD)) {

            urlToLoad = getArguments().getString(WEB_URL_TO_LOAD);

        }

        customViewContainer = (FrameLayout) rootView
                .findViewById(R.id.customViewContainer);
        webView = (WebView) rootView.findViewById(R.id.webView);

        mWebViewClient = new MyWebViewClient();
        webView.setWebViewClient(mWebViewClient);

        mWebChromeClient = new MyWebChromeClient();
        webView.setWebChromeClient(mWebChromeClient);
        webView.getSettings().setJavaScriptEnabled(true);

        // Important for PayUMoney
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSaveFormData(true);

        webView.requestFocus();

        // if (isConnected(getActivity())) {
        loadWebUrl(urlToLoad);

        return rootView;
    }


    public void loadWebUrl(String urlLoad) {

        webView.loadUrl(urlToLoad);

        if (!isConnected(getActivity())) {

            Snackbar.make(rootView, "You are Offline", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadWebUrl(urlToLoad);
                        }
                    }).show();

        }

    }

    private boolean inCustomView() {
        return (mCustomView != null);
    }

    private void hideCustomView() {
        mWebChromeClient.onHideCustomView();
    }

    @Override
    public void onPause() {
        super.onPause(); // To change body of overridden methods use File |
        // Settings | File Templates.
        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume(); // To change body of overridden methods use File |
        // Settings | File Templates.
        webView.onResume();
    }

    @Override
    public void onStop() {
        super.onStop(); // To change body of overridden methods use File |
        // Settings | File Templates.
        if (inCustomView()) {
            hideCustomView();
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        private View mVideoProgressView;

        @Override
        public void onShowCustomView(View view, int requestedOrientation,
                                     CustomViewCallback callback) {
            onShowCustomView(view, callback); // To change body of overridden
            // methods use File | Settings |
            // File Templates.
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {

            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            webView.setVisibility(View.GONE);
            customViewContainer.setVisibility(View.VISIBLE);
            customViewContainer.addView(view);
            customViewCallback = callback;
        }

        @Override
        public View getVideoLoadingProgressView() {

            if (mVideoProgressView == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                mVideoProgressView = inflater.inflate(R.layout.video_progress,
                        null);
            }
            return mVideoProgressView;
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView(); // To change body of overridden methods
            // use File | Settings | File Templates.
            if (mCustomView == null)
                return;

            webView.setVisibility(View.VISIBLE);
            customViewContainer.setVisibility(View.GONE);

            // Hide the custom view.
            mCustomView.setVisibility(View.GONE);

            // Remove the custom view from its container.
            customViewContainer.removeView(mCustomView);
            customViewCallback.onCustomViewHidden();

            mCustomView = null;
        }
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        Dialog loadingDialog = new Dialog(getActivity());

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webViewPreviousState = PAGE_STARTED;

            if (loadingDialog == null || !loadingDialog.isShowing())
                loadingDialog = ProgressDialog.show(getActivity(), "",
                        "Loading Please Wait", true, true,
                        new OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {
                                // do something
                            }
                        });

            loadingDialog.setCancelable(false);

        }


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request,
                                    WebResourceError error) {


            Snackbar.make(rootView, error.getDescription(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadWebUrl(urlToLoad);
                }
            }).show();

            super.onReceivedError(view, request, error);

        }

        @Override
        public void onReceivedHttpError(WebView view,
                                        WebResourceRequest request, WebResourceResponse errorResponse) {


            Snackbar.make(rootView, errorResponse.getReasonPhrase(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadWebUrl(urlToLoad);
                }
            }).show();

            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            if (webViewPreviousState == PAGE_STARTED) {

                if (null != loadingDialog) {
                    loadingDialog.dismiss();
                    loadingDialog = null;
                }
            }
        }
    }

    /**
     * Check if there is any connectivity
     *
     * @param context
     * @return is Device Connected
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();

            return (info != null && info.isConnected());
        }

        return false;

    }
}
