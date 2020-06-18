package com.springboot.assignment.library.service;

import com.springboot.assignment.library.entity.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    public List<Library> findAll();
    public Optional<Library> findById(int id);
    public void save(Library library);
    public void deleteById(int id);
    public List<Library> findAllByStatus(String status);

}
