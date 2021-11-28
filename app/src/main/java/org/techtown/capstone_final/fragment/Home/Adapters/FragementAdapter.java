package org.techtown.capstone_final.fragment.Home.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.techtown.capstone_final.fragment.Home.viewpager.MentorFragment;
import org.techtown.capstone_final.fragment.Home.viewpager.MentorFragment2;

public class FragementAdapter extends FragmentPagerAdapter {
    public FragementAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragementAdapter(@NonNull  FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : return  new MentorFragment();
            case 1 : return  new MentorFragment2();
//            case 2 : return  new CompetitionFragment();
//            case 3 : return  new FreeMeetingFragment();
            default: return  new MentorFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position==0){
            title = "1:1";
        }
        if (position==1){
            title = "1:N";
        }
//        if (position==2){
//            title = "소모임";
//        }
//        if (position==3){
//            title = "공모전";
//        }
        return title;
    }
}
