package com.rock.reliantdispatch.Authority;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rock.reliantdispatch.Main.MainActivity;
import com.rock.reliantdispatch.Authority.Register.RegisterActivity;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    TextInputLayout userName;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forgotPassword)
    Button forgotPassword;
    @BindView(R.id.signUp)
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login, R.id.signUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.signUp:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
    @OnClick(R.id.forgotPassword)
    void OnClickForgotPassword()
    {
        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
    }
}
