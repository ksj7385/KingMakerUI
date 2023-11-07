package com.gachon.kingmaker;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

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
}
