package org.techtown.capstone_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.techtown.capstone_final.databinding.ActivityMainBinding;
import org.techtown.capstone_final.fragment.BookmarkActivity;
import org.techtown.capstone_final.fragment.GroupchatActivity;
import org.techtown.capstone_final.fragment.Home.HomeActivity;
import org.techtown.capstone_final.fragment.mypage.MypageActivity;

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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //현재 사용자의 auth 값이 널 값일때 실행하는 함수
//        if (user == null || !user.isEmailVerified()){
//            Intent intent =new Intent(this, SignInActivity.class);
//            Toast.makeText(this, "로그인이 필요하거나 이메일 인증이 필요합니다.", Toast.LENGTH_SHORT).show();
//            startActivity(intent);
//        }else{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                            } else {
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this,org.techtown.capstone_final.memberinit.memberinit1.class );
                                startActivity(intent);
                            }
                        }

                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
            //회원가입 or 로그인
//            if ( user.isEmailVerified()) {
//                for (UserInfo profile : user.getProviderData()) {
//                    String name = profile.getDisplayName();
//                    Uri photoUrl = profile.getPhotoUrl();
//                    if(name !=null){
//                        if(name.length()== 0){
//                            Intent intent = new Intent();
//                            intent.setClass(this,org.techtown.capstone_final.memberinit.memberinit1.class );
//                            startActivity(intent);
//                        }
//                    }
//
//                }
//            }

//        }
        //로그아웃기능

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

//@Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//      MenuInflater inflater =getMenuInflater();
//      inflater.inflate(R.menu.menu, menu);
//      return super.onCreateOptionsMenu(menu);
//  }
//
//  //상단 옵션 바 쓸때 쓸려고
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId())
//        {
//            case R.id.setttings:
//                Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.logout:
//                auth.signOut();
//                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//                startActivity(intent);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
private  void myStartActivity(Class c){
    Intent intent = new Intent(this,c);
    startActivity(intent);
}

}