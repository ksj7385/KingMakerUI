package com.gachon.kingmaker; // 프로젝트에 맞는 패키지 이름으로 변경하세요.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button button;
    TextView goBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);;
        button = findViewById(R.id.button);
        goBackTextView = findViewById(R.id.goBack_textView);

        // 네비게이션 드로어 아이콘 찾기 및 클릭 이벤트 리스너 설정
        ImageView drawerIcon = findViewById(R.id.drawerIcon);
        drawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 드로어 열기
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);

                //onBackPressed();
                finish();
            }
        });

        goBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);

                //onBackPressed();
                finish();
            }
        });








    }

    @Override
    public void onBackPressed() {
        // 뒤로가기 버튼 클릭 시 드로어가 열려있다면 드로어를 닫음
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
