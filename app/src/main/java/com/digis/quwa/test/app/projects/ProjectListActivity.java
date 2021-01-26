package com.digis.quwa.test.app.projects;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digis.quwa.test.R;
import com.digis.quwa.test.app.base.BaseActivity;
import com.digis.quwa.test.app.di.ToothpickUtils;
import com.digis.quwa.test.app.projects.users.UsersActivity;
import com.digis.quwa.test.app.utils.ActionListener;
import com.digis.quwa.test.databinding.ActivityProjectListBinding;
import com.digis.quwa.test.domain.entities.Project;

import java.util.List;

public class ProjectListActivity extends BaseActivity<ProjectListViewModel, ActivityProjectListBinding> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_project_list;
    }

    @Override
    protected ProjectListViewModel getViewModel() {
        return ToothpickUtils.provideViewModel(this, ProjectListViewModel.class);
    }

    private final ProjectsAdapter adapter = new ProjectsAdapter(new ActionListener<Project>() {
        @Override
        public void onClick(Project item) {
            viewModel.onProjectClick(item);
        }

        @Override
        public void onLongClick(Project item) {
            viewModel.onProjectLongClick(item);
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupViews();
    }

    private void setupViewModel() {
        viewModel.errorMessage.observe(this, this::showToast);
        viewModel.projects.observe(this, this::showProjects);
        viewModel.updateProject.observe(this, this::onProjectUpdated);
        viewModel.openChangeProjectNameDialog.observe(this, project ->  {
            ChangeProjectNameDialogFragment.create(newName -> {
                viewModel.changeProjectName(project.getId(), newName);
            }).show(getSupportFragmentManager(), null);
        });
        viewModel.openUsersScreen.observe(this, users -> {
            UsersActivity.start(ProjectListActivity.this, users);
        });
    }

    private void setupViews() {
        RecyclerView rvProjects = findViewById(R.id.rvProjects);
        rvProjects.setAdapter(adapter);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showProjects(List<Project> projects) {
        showToast(getString(R.string.expl_long_tap_to_change_proj_name));
        adapter.setItems(projects);
    }

    private void onProjectUpdated(Project project) {
        adapter.onProjectUpdated(project);
    }
}
