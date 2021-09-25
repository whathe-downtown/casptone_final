package org.techtown.capstone_final;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import org.techtown.capstone_final.databinding.ActivityMainBinding;
import org.techtown.capstone_final.fragment.BookmarkActivity;
import org.techtown.capstone_final.fragment.GroupchatActivity;
import org.techtown.capstone_final.fragment.HomeActivity;
import org.techtown.capstone_final.fragment.MypageActivity;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAG,"MainActivity - onCreate() called");

        auth = FirebaseAuth.getInstance();
        binding.bottomNavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                Log.d(TAG,"MainActivity - onNavigationItemSelected() called");
                switch (item.getItemId()){
                    case R.id.Home:
                        setFrag(0);
                        Log.d(TAG,"홈 버튼 클릭");
                        break;
                    case R.id.bookmark:
                        setFrag(1);
                        Log.d(TAG,"북마크 클릭");
                        break;
                    case R.id.group_chat:
                        setFrag(2);
                        Log.d(TAG,"그룹 챗 클릭");
                        break;
                    case R.id.mypage:
                        setFrag(3);
                        Log.d(TAG,"마이페이지 클릭");
                        break;

                }
                return true;
            }
        });

        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정 할 지
    }


    //프레그먼트 교체가 일어나는 실행문이다.
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame,new HomeActivity());
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,new BookmarkActivity());
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,new GroupchatActivity());
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame,new MypageActivity());
                ft.commit();
                break;

        }
    }

//    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater =getMenuInflater();
////        inflater.inflate(R.menu.menu, menu);
////        return super.onCreateOptionsMenu(menu);
////    }
////
////    //상단 옵션 바 쓸때 쓸려고
//////    @Override
//////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//////
//////        switch (item.getItemId())
//////        {
//////            case R.id.setttings:
//////                Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show();
//////                break;
//////
//////            case R.id.logout:
//////                auth.signOut();
//////                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//////                startActivity(intent);
//////                break;
//////        }
//////        return super.onOptionsItemSelected(item);
//////    }
}