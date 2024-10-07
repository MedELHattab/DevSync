package com.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<TaskTag> taskTags;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TaskTag> getTaskTags() {
        return taskTags;
    }

    public void setTaskTags(Set<TaskTag> taskTags) {
        this.taskTags = taskTags;
    }
}
