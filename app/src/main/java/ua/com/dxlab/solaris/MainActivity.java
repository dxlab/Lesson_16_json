package ua.com.dxlab.solaris;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    final static String TAG_COMMENTS = "FRAGMENT_COMMENTS";
    final static String TAG_SOLAR_SYSTEM = "FRAGMENT_SOLAR_SYSTEM";

    private FragmentManager mFragmentManager;
    private CommentsFragment mCommentsFragment;
    private SolarSystemFragment mSolarSystemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new BitmapCache(MainActivity.this).clearCache();
        initUI(savedInstanceState);
    }

    /**
     * initializes user interface
     */
    private void initUI(Bundle _bundle) {
        mFragmentManager = getFragmentManager();

        mCommentsFragment = new CommentsFragment();
        mSolarSystemFragment = new SolarSystemFragment();

        if (_bundle == null) {
            // first time launched
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // add to the container via add()
            fragmentTransaction.add(R.id.fragment_container, mCommentsFragment, TAG_COMMENTS);
            fragmentTransaction.commit();
        }
    }

    /**
     * buttons click handler
     * @param view
     */
    public void onFragmentButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button_comments_AM:
                showCommentsFragment();
                break;
            case R.id.button_solar_system_AM:
                showSolarSystemFragment();
                break;
        }

    }

    /**
     * shows comments fragment
     */
    private void showCommentsFragment() {
        CommentsFragment fragment = (CommentsFragment) mFragmentManager.findFragmentByTag(TAG_COMMENTS);
        if (fragment == null) {

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            fragmentTransaction.replace(R.id.fragment_container, mCommentsFragment, TAG_COMMENTS);
            fragmentTransaction.commit();

        }
    }

    /**
     * shows solar system objects fragment
     */
    private void showSolarSystemFragment() {
        SolarSystemFragment fragment = (SolarSystemFragment) mFragmentManager.findFragmentByTag(TAG_SOLAR_SYSTEM);
        if (fragment == null) {

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            fragmentTransaction.replace(R.id.fragment_container, mSolarSystemFragment, TAG_SOLAR_SYSTEM);
            fragmentTransaction.commit();

        }

    }


}
