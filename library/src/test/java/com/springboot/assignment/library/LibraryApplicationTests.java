package com.springboot.assignment.library;

import com.springboot.assignment.library.dao.LibraryRepository;
import com.springboot.assignment.library.entity.Library;
import com.springboot.assignment.library.service.LibraryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class LibraryServiceTest {
	// We are mocking the Repository : As hitting the Database for testing isn't efficient .
	// Mocking means we have to create a list or collection of data which is similar to the data in database.

	@MockBean
	private LibraryRepository libraryRepository;


	// Injecting the repository into service.
	@Autowired
	private LibraryServiceImpl libraryServiceImpl;


	// This list will work like repository
	List<Library> list= new ArrayList<Library>();

	// Testing each method of our Service.

	@Test
	public void findAllTest() {


		Library firstBook = new Library(1, "Mortal Engines", "Philip Reeve", "Mystery", "Pearson","Available");
		Library secondBook = new Library(2, "Harry Potter ", "J.K Rowling", "Fantasy", "Bloomsbury","Not Available");

		list.add(firstBook);
		list.add(secondBook);

		//Mocking
		Mockito.when(libraryRepository.findAll()).thenReturn(list);

		//Test
		List<Library> libraryList = libraryServiceImpl.findAll();

		assertEquals(2,libraryList.size());

	}


	@Test
	public void findByIdTest() {

		Library theBook = new Library(1, "Mortal Engines", "Philip Reeve", "Mystery", "Pearson","Available");

		Mockito.when(libraryRepository.findById(theBook.getId())).
				thenReturn(Optional.of(theBook));

		assertEquals(theBook,libraryServiceImpl.findById(theBook.getId()));
	}


	@Test
	public void saveTest() {

		Library firstBook = new Library(1, "Mortal Engines", "Philip Reeve", "Mystery", "Pearson","Available");
		libraryServiceImpl.save(firstBook);
		Mockito.verify(libraryRepository,Mockito.times(1)).save(firstBook);

	}

	@Test
	public void deleteTest() {

		Library firstBook = new Library(1, "Mortal Engines", "Philip Reeve", "Mystery", "Pearson","Available");
		libraryServiceImpl.deleteById(firstBook.getId());
		Mockito.verify(libraryRepository,Mockito.times(1)).deleteById(firstBook.getId());;

	}

}
