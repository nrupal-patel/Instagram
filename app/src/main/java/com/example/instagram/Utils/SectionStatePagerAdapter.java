package com.example.instagram.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final HashMap<Fragment, Integer> mFragments = new HashMap<>();
    private final HashMap<String, Integer> mFragmentNumber = new HashMap<>();
    private final HashMap<Integer, String> mFragmentNames = new HashMap<>();

    public SectionStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String fragmentName){
        mFragmentList.add(fragment);
        mFragments.put(fragment,mFragmentList.size());
        mFragmentNumber.put(fragmentName, mFragmentList.size()-1);
        mFragmentNames.put(mFragmentList.size()-1, fragmentName);
    }

//    return the fragment with the name @param
    public Integer getFragmentNumber(String fragmentName){
        if (mFragmentNumber.containsKey(fragmentName)){
            return mFragmentNumber.get(fragmentName);
        } else {
            return null;
        }
    }

//       return the fragment with the name @param
    public Integer getFragmentNumber(Fragment fragment){
        if (mFragmentNumber.containsKey(fragment)){
            return mFragmentNumber.get(fragment);
        } else {
            return null;
        }
    }

//       return the fragment with the name @param
    public String getFragmentName(Integer fragmentNumber){
        if (mFragmentNames.containsKey(fragmentNumber)){
            return mFragmentNames.get(fragmentNumber);
        } else {
            return null;
        }
    }
}
