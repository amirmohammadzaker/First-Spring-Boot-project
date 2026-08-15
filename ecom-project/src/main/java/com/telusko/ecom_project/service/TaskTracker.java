package com.telusko.ecom_project.service;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskTracker {

    private final String taskId;

    public TaskTracker() {
        this.taskId = "TASK-" + System.currentTimeMillis();
        System.out.println("TaskTracker Created with ID: " + this.taskId);
    }

    public String getTaskId() {
        return taskId;
    }
}