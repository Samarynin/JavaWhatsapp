package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button loginBtn, registerBtn;
    private EditText emailET, passwordET;
    private TextView changeTV;
    ProgressDialog loadingBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginBtn = (Button)findViewById(R.id.login_button);
        registerBtn = (Button)findViewById(R.id.register_button);
        emailET = (EditText) findViewById(R.id.email_input);
        passwordET = (EditText) findViewById(R.id.password_input);
        changeTV = (TextView)findViewById(R.id.changeTV);

        loadingBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        changeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTV.setVisibility(View.INVISIBLE);
                registerBtn.setVisibility(View.INVISIBLE);
                loginBtn.setVisibility(View.VISIBLE);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAccount();
            }
        });
    }

    private void signInAccount() {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Заполните поле email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Заполните поле пароль", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Вход в приложение");
            loadingBar.setMessage("Пожалуйста, подождите");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);

                                loadingBar.dismiss();
                                Toast.makeText(RegisterActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                String message = task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Ошибка" + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    private void createAccount() {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Заполните поле email", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Заполните поле пароль", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Создание аккаунта");
            loadingBar.setMessage("Пожалуйста, подождите");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {


                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);

                                loadingBar.dismiss();
                                Toast.makeText(RegisterActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                String message = task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Ошибка" + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}