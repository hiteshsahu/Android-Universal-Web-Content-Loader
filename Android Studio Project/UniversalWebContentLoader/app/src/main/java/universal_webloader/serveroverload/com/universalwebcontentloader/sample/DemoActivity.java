package universal_webloader.serveroverload.com.universalwebcontentloader.sample;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import universal_webloader.serveroverload.com.universalwebcontentloader.R;

import com.serveroverload.universal_webloader.UniversalWebViewFragment;

public class DemoActivity extends AppCompatActivity {


    private static final String UNIVERSAL_WEB_FRAG_TAG = "UniversalWebFragTag";
    // Demo URLS
    private static final String FACEBOOK_DEMO_URL = "https://www.facebook.com/HitsDroid";
    private static final String YOU_TUBE_DEMO_URL = "https://www.youtube.com/user/androiddevelopers";
    private static final String PAYU_MONEY_URL = "https://www.payumoney.com/paybypayumoney/#/BCDCEAE6A98116CB48BDE55C440BC69D";
    private static final String PDF_URL = "http://partners.adobe.com/public/developer/en/acrobat/PDFOpenParameters.pdf";


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        toolbar.setTitle("Youtube Demo");

                        break;

                    case 1:
                        toolbar.setTitle("Facebook Demo");

                        break;

                    case 2:
                        toolbar.setTitle("PayUMoney Demo");

                        break;

                    case 3:

                        toolbar.setTitle("PDF Demo");

                        break;

                    default:
                        toolbar.setTitle("Google Search Demo");

                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {

                //Demo for Youtube
                case 0:
                    return UniversalWebViewFragment.newInstance(
                            YOU_TUBE_DEMO_URL, false);

                //Demo for Facebook
                case 1:
                    return UniversalWebViewFragment.newInstance(
                            FACEBOOK_DEMO_URL, false);

                //Demo for PDF
                case 2:
                    return UniversalWebViewFragment.newInstance(
                            PAYU_MONEY_URL, false);

                //Demo for PDF
                case 3:

                    return UniversalWebViewFragment.newInstance(
                            "http://docs.google.com/viewer?url="
                                    + PDF_URL, false);

                //Demo for Google search
                default:
                    return UniversalWebViewFragment.newInstance(
                            "Android", true);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

    }
}
