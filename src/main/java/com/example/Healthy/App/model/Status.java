package com.example.Healthy.App.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    UPDATED(200, "Updated successfully"),
    DELETED(501, "Deleted successfully");

    private int status;

    private String message;
}
