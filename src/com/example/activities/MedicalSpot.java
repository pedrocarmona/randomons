package com.example.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.util.ArrayList;

public class MedicalSpot extends SherlockFragmentActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_spot);

        final ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Medical Spot");

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);

        TabsAdapter mTabsAdapter = new TabsAdapter(this, bar, mPager);
        mTabsAdapter.addTab(bar.newTab().setText("Randomons"),
                MedicalSpotRandomons.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Potions"),
                MedicalSpotPotions.class, null);

        if (savedInstanceState != null)
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getSupportActionBar().getSelectedNavigationIndex());
    }

    public class TabsAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, ActionBar.TabListener
    {
        private final Context mContext;
        private final ActionBar mBar;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

        final class TabInfo
        {
            private final Class<?> clss;
            private final Bundle args;

            TabInfo(Class<?> _class, Bundle _args) {
                clss = _class;
                args = _args;
            }
        }

        public TabsAdapter(FragmentActivity activity, ActionBar bar, ViewPager pager)
        {
            super(activity.getSupportFragmentManager());
            mContext = activity;
            mBar = bar;
            mViewPager = pager;
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);
        }

        public void addTab(ActionBar.Tab tab, Class<? extends Fragment> clss, Bundle args)
        {
            TabInfo info = new TabInfo(clss, args);
            tab.setTag(info);
            tab.setTabListener(this);
            mTabs.add(info);
            mBar.addTab(tab);
            notifyDataSetChanged();
        }

        @Override
        public int getCount()
        {
            return mTabs.size();
        }

        @Override
        public Fragment getItem(int position)
        {
            TabInfo info = mTabs.get(position);
            return Fragment.instantiate(mContext, info.clss.getName(), info.args);
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
        {
            Object tag = tab.getTag();

            for (int i=0; i<mTabs.size(); i++)
            {
                if (mTabs.get(i) == tag)
                    mViewPager.setCurrentItem(i,false);
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onPageScrolled(int i, float v, int i2) {
        }

        @Override
        public void onPageSelected(int i)
        {
            mBar.setSelectedNavigationItem(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }
}
