package com.digis.quwa.test.app.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.digis.quwa.test.BR;

public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity {

    protected abstract int getLayoutResId();
    protected abstract VM getViewModel();

    protected VM viewModel;
    protected DB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        viewModel.setArgs(getIntent().getExtras());
        binding = DataBindingUtil.setContentView(this, getLayoutResId());
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.viewModel, viewModel);
        viewModel.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.setVariable(BR.viewModel, null);
        binding.executePendingBindings();
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
