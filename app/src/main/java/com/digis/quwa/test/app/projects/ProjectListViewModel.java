package com.digis.quwa.test.app.projects;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.digis.quwa.test.app.base.BaseViewModel;
import com.digis.quwa.test.app.utils.SingleLiveEvent;
import com.digis.quwa.test.domain.entities.Project;
import com.digis.quwa.test.domain.entities.User;
import com.digis.quwa.test.domain.projects.GetProjectsUseCase;
import com.digis.quwa.test.domain.projects.UpdateProjectParams;
import com.digis.quwa.test.domain.projects.UpdateProjectUseCase;
import com.digis.quwa.test.domain.states.ErrorState;
import com.digis.quwa.test.domain.states.InitialLoadingState;
import com.digis.quwa.test.domain.states.LoadedState;
import com.digis.quwa.test.domain.states.State;

import java.util.List;

import javax.inject.Inject;

public class ProjectListViewModel extends BaseViewModel {

    private final GetProjectsUseCase getProjectsUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;

    @Inject
    public ProjectListViewModel(@NonNull Application application, GetProjectsUseCase getProjectsUseCase, UpdateProjectUseCase updateProjectUseCase) {
        super(application);
        this.getProjectsUseCase = getProjectsUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
        refreshProjects();
    }

    public MutableLiveData<List<Project>> projects = new MutableLiveData<>();
    public SingleLiveEvent<Project> updateProject = new SingleLiveEvent<>();
    public SingleLiveEvent<List<User>> openUsersScreen = new SingleLiveEvent<>();
    public SingleLiveEvent<Project> openChangeProjectNameDialog = new SingleLiveEvent<>();

    public void onProjectClick(Project project) {
        if (project == null) {
            return;
        }

        openUsersScreen.setValue(project.getUsers());
    }

    public void onProjectLongClick(Project project) {
        if (project == null) {
            return;
        }

        openChangeProjectNameDialog.setValue(project);
    }

    public void changeProjectName(long projectId, String newName) {
        runRxTask(
                updateProjectUseCase.execute(new UpdateProjectParams(projectId, newName))
                        .subscribe(this::handleProjectUpdatedState)
        );
    }

    private void refreshProjects() {
        runRxTask(
                getProjectsUseCase.execute(null)
                        .subscribe(this::handleProjectsState)
        );
    }

    private void handleProjectsState(State state) {
        if (state instanceof InitialLoadingState) {
            loading.setValue(true);
        } else if (state instanceof LoadedState) {
            loading.setValue(false);
            projects.setValue(((LoadedState<List<Project>>) state).getData());
        } else if (state instanceof ErrorState) {
            loading.setValue(false);
            errorMessage.setValue(((ErrorState) state).getThrowable().getMessage());
        }
    }

    private void handleProjectUpdatedState(State state) {
        if (state instanceof InitialLoadingState) {
            loading.setValue(true);
        } else if (state instanceof LoadedState) {
            loading.setValue(false);
            updateProject.setValue(((LoadedState<Project>) state).getData());
        } else if (state instanceof ErrorState) {
            loading.setValue(false);
            errorMessage.setValue(((ErrorState) state).getThrowable().getMessage());
        }
    }
}
