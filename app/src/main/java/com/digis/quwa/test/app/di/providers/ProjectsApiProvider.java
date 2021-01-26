package com.digis.quwa.test.app.di.providers;


import com.digis.quwa.test.data.projects.ProjectsApi;

import javax.inject.Inject;
import javax.inject.Provider;

import retrofit2.Retrofit;

public class ProjectsApiProvider implements Provider<ProjectsApi> {

    private final Retrofit retrofit;

    @Inject
    public ProjectsApiProvider(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public ProjectsApi get() {
        return retrofit.create(ProjectsApi.class);
    }
}
