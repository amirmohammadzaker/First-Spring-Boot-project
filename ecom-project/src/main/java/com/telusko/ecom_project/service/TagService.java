package com.telusko.ecom_project.service;

import com.telusko.ecom_project.model.Tag;
import com.telusko.ecom_project.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
    }

    @Transactional
    public Tag createTag(Tag tag) {
        String tagName = tag.getName().trim().toUpperCase();

        return tagRepository.findByName(tagName)
                .orElseGet(() -> {
                    tag.setName(tagName);
                    return tagRepository.save(tag);
                });
    }

    @Transactional
    public void deleteTag(Long id) {
        Tag tag = getTagById(id);
        tagRepository.delete(tag);
    }
}