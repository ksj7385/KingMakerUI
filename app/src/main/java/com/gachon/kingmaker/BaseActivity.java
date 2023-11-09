package com.gachon.kingmaker;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout; // 모든 액티비티에서 사용될 드로어 레이아웃

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 드로어 레이아웃 초기화는 각 액티비티에서 setContentView 이후에 설정
    }
    protected void setupNavigationDrawer() {
        // 드로어 아이콘 설정
        ImageView drawerIcon = findViewById(R.id.drawerIcon);
        if (drawerIcon != null) {
            drawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // EditText 외부 터치 시 키보드 숨김 처리
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                int[] outLocation = new int[2];
                v.getLocationOnScreen(outLocation);
                float x = ev.getRawX() + v.getLeft() - outLocation[0];
                float y = ev.getRawY() + v.getTop() - outLocation[1];

                if (x < v.getLeft() || x >= v.getRight() || y < v.getTop() || y > v.getBottom()) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        // 드로어 레이아웃이 열려 있으면 닫기
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public class CustomAdapter extends ArrayAdapter<ListItem> {
        public CustomAdapter(Context context, List<ListItem> items) {
            super(context, 0, items);
        }

        @Override
        public int getViewTypeCount() {
            // 사용되는 뷰 타입의 총 개수 반환
            return ListItem.ItemType.values().length;
        }

        @Override
        public int getItemViewType(int position) {
            // 각 포지션에 해당하는 아이템 타입 반환
            return getItem(position).getItemType().ordinal();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int viewType = getItemViewType(position);
            ViewHolder holder;

            // convertView 초기화 및 재사용 처리
            if (convertView == null) {
                holder = new ViewHolder();
                switch (viewType) {
                    case 0: // RADIO_BUTTON
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.radio_button_item, parent, false);
                        holder.radioButton = convertView.findViewById(R.id.radio_button);
                        break;
                    case 1: // SLIDER
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.slider_item, parent, false);
                        holder.slider = convertView.findViewById(R.id.slider);
                        break;
                    case 2: // OPTION_BUTTON
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.option_button_item, parent, false);
                        holder.optionButton = (Button) convertView.findViewById(R.id.option_button);
                        break;
                    case 3: // TEXT_VIEW
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_view_item, parent, false);
                        holder.textView = convertView.findViewById(R.id.text_view);
                        break;
                    // 기타 뷰 타입에 따른 케이스들을 추가할 수 있습니다.
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // 아이템 데이터에 따라 뷰 내용 설정
            ListItem item = getItem(position);
            switch (viewType) {
                case 0: // RADIO_BUTTON
                    holder.radioButton.setText(item.getData());
                    break;
                case 1: // SLIDER
                    // 슬라이더 설정 (해당되는 경우)
                    break;
                case 2: // OPTION_BUTTON
                    holder.optionButton.setText(item.getData());
                    break;
                case 3: // TEXT_VIEW
                    holder.textView.setText(item.getData());
                    break;
                // 기타 뷰 타입에 따른 데이터 설정
            }

            return convertView;
        }


    }
    // 각 리스트 아이템의 뷰를 재사용하기 위한 뷰 홀더 클래스
    static class ViewHolder {
        RadioButton radioButton;
        SeekBar slider;
        Button optionButton;
        TextView textView;
    }
}
