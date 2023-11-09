package com.gachon.kingmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends BaseActivity {

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

        ListView listView = findViewById(R.id.menu_list);

        // 예시 데이터 생성
        List<ListItem> items = Arrays.asList(
                new ListItem(ListItem.ItemType.RADIO_BUTTON, "라디오 버튼"),
                new ListItem(ListItem.ItemType.SLIDER, ""),
                new ListItem(ListItem.ItemType.OPTION_BUTTON, "옵션 버튼"),
                new ListItem(ListItem.ItemType.TEXT_VIEW, "텍스트 뷰"),
                new ListItem(ListItem.ItemType.RADIO_BUTTON, "라디오 버튼2"),
                new ListItem(ListItem.ItemType.SLIDER, ""),
                new ListItem(ListItem.ItemType.OPTION_BUTTON, "옵션 버튼2"),
                new ListItem(ListItem.ItemType.TEXT_VIEW, "텍스트 뷰2"),
                new ListItem(ListItem.ItemType.RADIO_BUTTON, "라디오 버튼3"),
                new ListItem(ListItem.ItemType.SLIDER, ""),
                new ListItem(ListItem.ItemType.OPTION_BUTTON, "옵션 버튼3"),
                new ListItem(ListItem.ItemType.TEXT_VIEW, "텍스트 뷰3")
        );

        // 어댑터 생성 및 리스트뷰에 설정
        CustomAdapter adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);
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
