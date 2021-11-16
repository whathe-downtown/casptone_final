package org.techtown.capstone_final.fragment.mypage.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.techtown.capstone_final.fragment.mypage.viewpager.FragRecord;
import org.techtown.capstone_final.fragment.mypage.viewpager.FragReview;

public class ViewpagerAdapter extends FragmentPagerAdapter {
    public ViewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewpagerAdapter(@NonNull  FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : return  new FragRecord();
            case 1 : return  new FragReview();
            default: return  new FragRecord();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    // 상단의 탭 레이아웃 인디케이터 쪽에 텍스트를 선언해주는 곳
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position==0){
            title = "기록";
        }
        if (position==1){
            title = "리뷰";
        }

        return title;
    }
}
