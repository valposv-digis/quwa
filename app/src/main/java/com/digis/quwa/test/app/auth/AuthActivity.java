package com.digis.quwa.test.app.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.digis.quwa.test.R;
import com.digis.quwa.test.app.base.BaseActivity;
import com.digis.quwa.test.app.di.ToothpickUtils;
import com.digis.quwa.test.app.main.MainActivity;
import com.digis.quwa.test.databinding.ActivityAuthBinding;

public class AuthActivity extends BaseActivity<AuthViewModel, ActivityAuthBinding> {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_auth;
    }

    @Override
    protected AuthViewModel getViewModel() {
        return ToothpickUtils.provideViewModel(this, AuthViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.errorMessage.observe(this, this::showToast);
        viewModel.loggedInEvent.observe(this, aVoid -> {
            startActivity(new Intent(AuthActivity.this, MainActivity.class));
            finish();
        });

        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            String email = ((EditText) findViewById(R.id.etEmail)).getText().toString();
            String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
            viewModel.login(email, password);
        });
    }
}
