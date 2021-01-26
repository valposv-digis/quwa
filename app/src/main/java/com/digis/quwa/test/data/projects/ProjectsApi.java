package com.digis.quwa.test.data.projects;

import com.digis.quwa.test.data.projects.entities.ProjectResponse;
import com.digis.quwa.test.data.projects.entities.ProjectsListResponse;
import com.digis.quwa.test.domain.entities.Project;
import com.digis.quwa.test.domain.projects.UpdateProjectParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ProjectsApi {

    @GET("projects-manage")
    Observable<ProjectsListResponse> getProjects();

    @PUT("projects-manage/update")
    Observable<ProjectResponse> updateProject(
            @Query("id") long projectId,
            @Body UpdateProjectParams updateProjectParams
    );
}
