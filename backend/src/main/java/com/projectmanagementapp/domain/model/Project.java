package com.projectmanagementapp.domain.model;

import lombok.Value;

@Value
public class Project {
    Long id;
    String code;
    String name;
    String status;
    String owner;
    String description;
}
