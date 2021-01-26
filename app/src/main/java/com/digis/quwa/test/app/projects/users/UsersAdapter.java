package com.digis.quwa.test.app.projects.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digis.quwa.test.R;
import com.digis.quwa.test.app.utils.ActionListener;
import com.digis.quwa.test.app.utils.ViewUtils;
import com.digis.quwa.test.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private final List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setItems(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAvatar;
        private final View vOnlineBubble;
        private final TextView tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            vOnlineBubble = itemView.findViewById(R.id.vOnlineBubble);
        }

        public void bind(User user) {
            ViewUtils.loadImage(ivAvatar, user.getAvatarUrl(), R.drawable.ic_user_avatar_placeholder);
            tvName.setText(user.getName());
            vOnlineBubble.setVisibility(user.isOnline() ? View.VISIBLE : View.GONE);
        }
    }
}
