package com.gachon.kingmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends BaseActivity {

    Button button;
    TextView goBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //drawer설정
        setupNavigationDrawer();
        // BaseActivity의 drawerLayout을 현재 레이아웃과 연결
        drawerLayout = findViewById(R.id.drawer_layout);

        // UI 요소 초기화
        button = findViewById(R.id.button);
        goBackTextView = findViewById(R.id.goBack_textView);

        // 버튼 이벤트 리스너
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });
        // '뒤로 가기' 텍스트 이벤트 리스너
        goBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        // 리스트뷰 설정
        ListView listView = findViewById(R.id.menu_list);
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
                new ListItem(ListItem.ItemType.TEXT_VIEW, "텍스트 뷰3"),

                new ListItem(ListItem.ItemType.RADIO_BUTTON, "라디오 버튼4"),
                new ListItem(ListItem.ItemType.SLIDER, ""),
                new ListItem(ListItem.ItemType.OPTION_BUTTON, "옵션 버튼4"),
                new ListItem(ListItem.ItemType.TEXT_VIEW, "텍스트 뷰4")
        );

        CustomAdapter adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);
    }
}
