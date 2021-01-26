package com.digis.quwa.test.data.projects.entities;

import com.digis.quwa.test.domain.entities.Project;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectResponse {
    @SerializedName("project") private final Project project;

    public ProjectResponse(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
