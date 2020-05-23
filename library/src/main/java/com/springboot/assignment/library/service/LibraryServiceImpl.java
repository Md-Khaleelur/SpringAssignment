package com.springboot.assignment.library.service;

import com.springboot.assignment.library.dao.LibraryRepository;
import com.springboot.assignment.library.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    private LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository){
        this.libraryRepository=libraryRepository;
    }

    @Override
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Library findById(int id) {
        Optional<Library> result = libraryRepository.findById(id);
        Library library = null;
        if (result.isPresent()) {
            library = result.get();
        }
        return library;
    }

    @Override
    public void save(Library library) {
        libraryRepository.save(library);
    }

    @Override
    public void deleteById(int id) {
        libraryRepository.deleteById(id);
    }

    @Override
    public List<Library> findAllByStatus(String status) {
        return libraryRepository.findAllByStatus(status);
    }

}
