package com.springboot.assignment.library.controller;

import com.springboot.assignment.library.entity.Library;
import com.springboot.assignment.library.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private LibraryService libraryService;

    private Logger logger = LoggerFactory.getLogger(LibraryController.class);


    @Autowired
    public LibraryController(LibraryService libraryService){
        this.libraryService=libraryService;
    }


    @GetMapping("/name")
    public String findByStatus(@RequestParam("status") String status, Model model){

        List<Library> libraryList= libraryService.findAllByStatus(status);
        model.addAttribute("library",libraryList);
        return "list-books";
    }

    @GetMapping("/list")
    public String listBooks(Model model){
        List<Library> libraryList = libraryService.findAll();

        model.addAttribute("library",libraryList);
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
        model.addAttribute("library",library);
        return "library-form";

     }

     @GetMapping("/showFormForUpdate")
     public String showFormForUpdateBook(@RequestParam("bookId") int bookId,Model model){

         Library library = libraryService.findById(bookId);
         if (library == null ){
             model.addAttribute("bookId",bookId);
             return "error-page";
         }
         model.addAttribute("library",library);
         return "library-form";

     }

    @PostMapping("/processForm")
    public String processForm(@Valid  @ModelAttribute("library") Library library, BindingResult theBindingResult) {

        logger.info("Processing library form");

        if (theBindingResult.hasErrors()) {
            return "library-form";
        }
        else {
            libraryService.save(library);
            logger.info("***********"+library.toString()+"*********");
            logger.info("library tuple saved");
            return "list-books";
        }
    }

}
