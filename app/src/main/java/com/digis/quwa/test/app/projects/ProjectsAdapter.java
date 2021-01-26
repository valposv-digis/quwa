package com.digis.quwa.test.app.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.digis.quwa.test.R;
import com.digis.quwa.test.app.utils.ActionListener;
import com.digis.quwa.test.app.utils.ViewUtils;
import com.digis.quwa.test.domain.entities.Project;

import java.util.ArrayList;
import java.util.List;


public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder> {

    private final ActionListener<Project> actionListener;
    private final List<Project> projects;

    public ProjectsAdapter(ActionListener<Project> actionListener) {
        this.actionListener = actionListener;
        this.projects = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view, actionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.bind(project);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setItems(List<Project> projects) {
        this.projects.clear();
        this.projects.addAll(projects);
        notifyDataSetChanged();
    }

    public void onProjectUpdated(Project project) {
        int index = getProjectIndex(project.getId());
        if (index < 0) {
            throw new IllegalArgumentException("No such project");
        }

        projects.set(index, project);
        notifyItemChanged(index);
    }

    private int getProjectIndex(long projectId) {
        int index = -1;
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId() == projectId) {
                return i;
            }
        }

        return index;
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final ActionListener<Project> actionListener;
        private final ConstraintLayout clRoot;
        private final ImageView ivLogo;
        private final TextView tvName;

        public ProjectViewHolder(@NonNull View itemView, ActionListener<Project> actionListener) {
            super(itemView);
            this.actionListener = actionListener;
            clRoot = itemView.findViewById(R.id.clRoot);
            tvName = itemView.findViewById(R.id.tvName);
            ivLogo = itemView.findViewById(R.id.ivAvatar);
        }

        public void bind(Project project) {
            ViewUtils.loadImage(ivLogo, project.getLogoUrl(), R.drawable.ic_user_avatar_placeholder);
            tvName.setText(project.getName());
            clRoot.setOnClickListener(v -> {
                actionListener.onClick(project);
            });
            clRoot.setOnLongClickListener(v -> {
                actionListener.onLongClick(project);
                return true;
            });
        }
    }
}
