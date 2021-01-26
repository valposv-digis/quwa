package com.digis.quwa.test.data.projects;

import com.digis.quwa.test.domain.projects.UpdateProjectParams;
import com.digis.quwa.test.domain.states.ErrorState;
import com.digis.quwa.test.domain.states.InitialLoadingState;
import com.digis.quwa.test.domain.states.LoadedState;
import com.digis.quwa.test.domain.states.State;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ProjectsRepository {

    private final ProjectsApi projectsApi;

    @Inject
    public ProjectsRepository(ProjectsApi projectsApi) {
        this.projectsApi = projectsApi;
    }

    public Observable<State> getProjects() {
        return Observable.concat(
                Observable.just(new InitialLoadingState()),
                projectsApi.getProjects()
                        .flatMap(response -> Observable.just(new LoadedState<>(response.getProjects())))
        ).onErrorReturn(ErrorState::new);
    }

    public Observable<State> updateProject(UpdateProjectParams params) {
        return Observable.concat(
                Observable.just(new InitialLoadingState()),
                projectsApi.updateProject(params.getProjectId(), params)
                        .flatMap(response -> Observable.just(new LoadedState<>(response.getProject())))
        ).onErrorReturn(ErrorState::new);
    }
}
