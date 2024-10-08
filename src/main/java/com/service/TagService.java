package com.service;

import com.model.Tag;
import com.repository.TagRepositoryImpl;

import java.util.List;

public class TagService {

    private TagRepositoryImpl tagRepository = new TagRepositoryImpl();

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag editTag(Long id, Tag tagDetails) {
        Tag tag = tagRepository.findById(id);
        if (tag != null) {
            tag.setName(tagDetails.getName());
            return tagRepository.save(tag);
        }
        return null; // Handle not found case appropriately
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
