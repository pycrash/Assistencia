package com.t9.assistencia;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MondayFragment();
        } else if (position == 1) {
            return new TuesdayFragment();
        } else if (position == 2) {
            return new WednesdayFragment();
        } else if (position == 3) {
            return new ThursdayFragment();
        } else if (position == 4) {
            return new FridayFragment();
        } else if (position == 5) {
            return new SaturdayFragment();
        } else {
            return new SundayFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_monday);
        } else if (position == 1) {
            return mContext.getString(R.string.category_tuesday);
        } else if (position == 2) {
            return mContext.getString(R.string.category_wednesday);
        } else if (position == 3) {
            return mContext.getString(R.string.category_thursday);
        }else if (position == 4) {
            return mContext.getString(R.string.category_friday);
        }else if (position == 5) {
            return mContext.getString(R.string.category_saturday);
        }else {
            return mContext.getString(R.string.category_sunday);
        }
    }
}
