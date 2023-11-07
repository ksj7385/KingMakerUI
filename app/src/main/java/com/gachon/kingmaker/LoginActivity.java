package com.gachon.kingmaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signUpTextView = findViewById(R.id.signup_textView);
        //로그인 부분. @@구현 필요
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username and Password cannot be empty", Toast.LENGTH_SHORT).show();
                    //임시!!!!
                    // 로그인 성공시 HomeActivity로 이동
                    //@@@@@@@@@@@@@@@@@@@@@
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (username.equals("admin") && password.equals("password")) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //회원가입 부분. @@구현 필요
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}

