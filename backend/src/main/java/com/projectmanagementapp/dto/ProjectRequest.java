package com.projectmanagementapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class ProjectRequest {
    @NotBlank
    String code;

    @NotBlank
    String name;

    @NotBlank
    String status;

    @NotBlank
    String owner;

    String description;

    @JsonCreator
    public ProjectRequest(
        @JsonProperty("code") String code,
        @JsonProperty("name") String name,
        @JsonProperty("status") String status,
        @JsonProperty("owner") String owner,
        @JsonProperty("description") String description
    ) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.owner = owner;
        this.description = description;
    }
}
