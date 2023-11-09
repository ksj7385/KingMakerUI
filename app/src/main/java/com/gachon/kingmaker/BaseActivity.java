package com.gachon.kingmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                int[] outLocation = new int[2];
                v.getLocationOnScreen(outLocation);
                float x = ev.getRawX() + v.getLeft() - outLocation[0];
                float y = ev.getRawY() + v.getTop() - outLocation[1];

                // EditText가 포커스를 잃었을 때 키보드 숨김
                if (x < v.getLeft() || x >= v.getRight() || y < v.getTop() || y > v.getBottom()) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public class CustomAdapter extends ArrayAdapter<ListItem> {
        public CustomAdapter(Context context, List<ListItem> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItem item = getItem(position);
            if (item == null) return convertView;

            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                switch (item.getItemType()) {
                    case RADIO_BUTTON:
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.radio_button_item, parent, false);
                        holder.radioButton = convertView.findViewById(R.id.radio_button);
                        holder.radioButton.setText(item.getData());
                        break;
                    case SLIDER:
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.slider_item, parent, false);
                        holder.slider = convertView.findViewById(R.id.slider);
                        //holder.slider.setText(item.getData());
                        break;
                    case OPTION_BUTTON:
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.option_button_item, parent, false);
                        holder.optionButton = (Button) convertView.findViewById(R.id.option_button);
                        holder.optionButton.setText(item.getData());
                        break;
                    case TEXT_VIEW:
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_view_item, parent, false);
                        holder.textView = convertView.findViewById(R.id.text_view);
                        holder.textView.setText(item.getData());
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
}
    static class ViewHolder {
        RadioButton radioButton;
        SeekBar slider;
        Button optionButton;
        TextView textView;
    }
}

