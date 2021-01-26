package com.digis.quwa.test.app.projects.users;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digis.quwa.test.R;
import com.digis.quwa.test.app.base.BaseActivity;
import com.digis.quwa.test.app.di.ToothpickUtils;
import com.digis.quwa.test.databinding.ActivityUsersBinding;
import com.digis.quwa.test.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends BaseActivity<UsersViewModel, ActivityUsersBinding> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_users;
    }

    @Override
    protected UsersViewModel getViewModel() {
        return ToothpickUtils.provideViewModel(this, UsersViewModel.class);
    }

    public static final String KEY_USERS = "UsersActivity.KEY_USERS";
    private final UsersAdapter adapter = new UsersAdapter();

    public static void start(AppCompatActivity activity, List<User> users) {
        Intent intent = new Intent(activity, UsersActivity.class);
        intent.putParcelableArrayListExtra(KEY_USERS, (ArrayList<User>) users);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.users.observe(this, this::showUsers);

        RecyclerView rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setAdapter(adapter);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showUsers(List<User> users) {
        adapter.setItems(users);
    }
}
