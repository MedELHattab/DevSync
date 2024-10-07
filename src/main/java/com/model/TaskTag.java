package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_tags")
public class TaskTag {

    @EmbeddedId
    private TaskTagId id;

    @ManyToOne
    @MapsId("taskId")
    private Task task;

    @ManyToOne
    @MapsId("tagId")
    private Tag tag;

    // Getters and Setters
    public TaskTagId getId() {
        return id;
    }

    public void setId(TaskTagId id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
