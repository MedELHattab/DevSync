package com.service;

import com.model.Tag;
import com.repository.TagRepositoryImpl;
import com.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TagServiceTest {

    @Mock
    private TagRepositoryImpl tagRepository;

    @InjectMocks
    private TagService tagService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTags() {
        // Arrange
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Java");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Spring");

        when(tagRepository.findAll()).thenReturn(Arrays.asList(tag1, tag2));

        // Act
        List<Tag> result = tagService.getAllTags();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getName());
        assertEquals("Spring", result.get(1).getName());
        verify(tagRepository, times(1)).findAll();
    }

    @Test
    public void testGetTagById() {
        // Arrange
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("Java");

        when(tagRepository.findById(1L)).thenReturn(tag);

        // Act
        Tag result = tagService.getTagById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Java", result.getName());
        verify(tagRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateTag() {
        // Arrange
        Tag newTag = new Tag();
        newTag.setName("Spring Boot");

        Tag savedTag = new Tag();
        savedTag.setId(1L);
        savedTag.setName("Spring Boot");

        when(tagRepository.save(newTag)).thenReturn(savedTag);

        // Act
        Tag result = tagService.createTag(newTag);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Spring Boot", result.getName());
        verify(tagRepository, times(1)).save(newTag);
    }

    @Test
    public void testEditTag() {
        // Arrange
        Tag existingTag = new Tag();
        existingTag.setId(1L);
        existingTag.setName("Java");

        Tag updatedDetails = new Tag();
        updatedDetails.setName("Java 8");

        Tag updatedTag = new Tag();
        updatedTag.setId(1L);
        updatedTag.setName("Java 8");

        when(tagRepository.findById(1L)).thenReturn(existingTag);
        when(tagRepository.save(existingTag)).thenReturn(updatedTag);

        // Act
        Tag result = tagService.editTag(1L, updatedDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Java 8", result.getName());
        verify(tagRepository, times(1)).findById(1L);
        verify(tagRepository, times(1)).save(existingTag);
    }

    @Test
    public void testDeleteTag() {
        // Act
        tagService.deleteTag(1L);

        // Assert
        verify(tagRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetTagByName() {
        // Arrange
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("Spring Boot");

        when(tagRepository.findByName("Spring Boot")).thenReturn(tag);

        // Act
        Tag result = tagService.getTagByName("Spring Boot");

        // Assert
        assertNotNull(result);
        assertEquals("Spring Boot", result.getName());
        verify(tagRepository, times(1)).findByName("Spring Boot");
    }
}
