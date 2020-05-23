package com.springboot.assignment.library.dao;

import com.springboot.assignment.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library,Integer> {

    List<Library> findAllByStatus(String status);
}
