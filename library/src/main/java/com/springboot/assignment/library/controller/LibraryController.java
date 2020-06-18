package com.springboot.assignment.library.controller;

import com.springboot.assignment.library.entity.Library;
import com.springboot.assignment.library.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/*
* findByStatus - search for a book based on available/not available(status)
* listBooks - list all the books in repository
* saveLibraryEntry - save a new book entry
* deleteLibraryEntry - delete a book entry
*
* showFormForAddBook - open the page for adding a book entry
* showFormForUpdateBook - open the page for updating a book entry
* processForm - Processing the library form details - adding the new entry
* */




@Controller
@RequestMapping("/library")
@Slf4j
public class LibraryController {

    final LibraryService libraryService;

    //private Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    public LibraryController(LibraryService libraryService){
        this.libraryService=libraryService;
    }


    @GetMapping("/name")
    public String findByStatus(@RequestParam("status") String status, Model model){

        List<Library> libraryList= libraryService.findAllByStatus(status);
        model.addAttribute(MyValues.VALUE1,libraryList);
        return "list-books";
    }

    @GetMapping("/list")
    public String listBooks(Model model){
        List<Library> libraryList = libraryService.findAll();

        model.addAttribute(MyValues.VALUE1,libraryList);
        return "list-books";
    }

    @PostMapping("/save")
    public String saveLibraryEntry(@ModelAttribute("library") Library library){

        libraryService.save(library);

        //to redirect to the list page
        return "redirect:/library/list";
    }

    @GetMapping("/delete")
    public String deleteLibraryEntry(@RequestParam("bookId") int bookId){

        libraryService.deleteById(bookId);
        //to redirect to the list page
        return "redirect:/library/list";

    }

    @GetMapping("/showFormForAddBook")
    public String showFormForAddBook(Model model){

        Library library = new Library();
        model.addAttribute(MyValues.VALUE1,library);
        return "library-form";

     }

     @GetMapping("/showFormForUpdate")
     public String showFormForUpdateBook(@RequestParam("bookId") int bookId,Model model){

         Optional<Library> library = libraryService.findById(bookId);
         if (library == null ){
             model.addAttribute("bookId",bookId);
             return "error-page";
         }
         model.addAttribute(MyValues.VALUE1,library);
         return "library-form";

     }

    @PostMapping("/processForm")
    public String processForm(@Valid  @ModelAttribute("library") Library library, BindingResult theBindingResult) {

        log.info("Processing library form");

        if (theBindingResult.hasErrors()) {
            return "library-form";
        }
        else {
            libraryService.save(library);
            log.info("***********"+library.toString()+"*********");
            log.info("library tuple saved");
            return "list-books";
        }
    }

}

final class MyValues {
    public static final String VALUE1 = "library";
}
