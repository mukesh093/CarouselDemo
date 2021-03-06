package com.bridgelabz.carouseldemo;

/**
 * Created by bridgeit on 11/8/16.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public final static float BIG_SCALE = 0.7f;
    public final static float SMALL_SCALE = 0.5f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private MainActivity mContext;
    private FragmentManager mFragmentManager;
    private float scale;

    public CarouselPagerAdapter(MainActivity context, FragmentManager fm) {
        super(fm);
        this.mFragmentManager = fm;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == MainActivity.FIRST_PAGE)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % MainActivity.count;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ItemFragment.newInstance(mContext, position, scale);
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = MainActivity.count * MainActivity.LOOPS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                CarouselLinearLayout cur = getRootView(position);
                CarouselLinearLayout next = getRootView(position + 1);

                cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
                next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @SuppressWarnings("ConstantConditions")
    private CarouselLinearLayout getRootView(int position) {
        return (CarouselLinearLayout) mFragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + mContext.pager.getId() + ":" + position;
    }
}