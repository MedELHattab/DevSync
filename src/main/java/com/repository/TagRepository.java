package com.repository;

import com.model.Tag;
import java.util.List;

public interface TagRepository {
    List<Tag> findAll();
    Tag findById(Long id);
    Tag save(Tag tag);
    void deleteById(Long id);
    Tag findByName(String name);
}
