package com.digis.quwa.test.domain.projects;

import com.google.gson.annotations.SerializedName;

public class UpdateProjectParams {

    private final transient long projectId;
    @SerializedName("name") private final String name;

    public UpdateProjectParams(long projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

    public long getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }
}
