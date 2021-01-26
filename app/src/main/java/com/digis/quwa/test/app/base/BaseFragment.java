package com.digis.quwa.test.app.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.digis.quwa.test.BR;


public abstract class BaseFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends Fragment {

    protected abstract int getLayoutResId();
    protected abstract VM getViewModel();

    protected VM viewModel;
    protected DB binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        viewModel.setArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = getViewModel();
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        viewModel.onStart();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.setVariable(BR.viewModel, null);
        binding.setLifecycleOwner(null);
        binding.executePendingBindings();
    }

    protected void showToast(String text) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show();
    }
}
