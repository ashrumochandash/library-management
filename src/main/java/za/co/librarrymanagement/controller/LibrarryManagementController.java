package za.co.librarrymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import za.co.librarrymanagement.dto.BookCreateDTO;
import za.co.librarrymanagement.dto.BookSearchDTO;
import za.co.librarrymanagement.dto.PatronDTO;
import za.co.librarrymanagement.entity.Book;
import za.co.librarrymanagement.service.LibrarryManagementServiceImpl;

@RestController
public class LibrarryManagementController {
	
	@Autowired
	private LibrarryManagementServiceImpl librarryManagementService;
	
	//Configurable values
	@Value("${book.limit}")
	private String bookIssuedLimit;
    
	
	/****
	 *  Librarian Adds a New Book: 
	 * */
	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@RequestBody BookCreateDTO bookDto){
		try {
			librarryManagementService.addBook(bookDto);
			return new ResponseEntity<String>("Book added successfully", HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while adding book", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<?> getBookById(@PathVariable long id){
		try {
			Book book = librarryManagementService.getBookById(id);
			
			if(book != null) {
				return new ResponseEntity<Book>(book, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Sorry! No result found", HttpStatus.OK);
			}			
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while finding book by id", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	/*
	 * Patron Searches for a Book:
	 */
	@GetMapping("/searchBook")
	public ResponseEntity<?> searchBook(@RequestBody BookSearchDTO bookSearchDTO){
		try {
			List<Book> bookList = librarryManagementService.searchBook(bookSearchDTO);
			return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while search book", HttpStatus.INTERNAL_SERVER_ERROR);
		}  
	}
	
	/***
	 * Patron Checks Out a Book: 
	 * */
	@PutMapping("/checkoutBook/{id}")
	public ResponseEntity<?> checkoutBook(@PathVariable long id, @RequestBody PatronDTO patronDTO){
		try {
			Book book = librarryManagementService.getBookById(id);
			
			if(book == null) {
				// throw a custom exception as book id is required for checkout 
			}
			
			book = librarryManagementService.checkoutBook(book, patronDTO);			
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while checkout book", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	/***
	 *  Patron Returns a Book: 
	 *  */
	@PutMapping("/returnBookById/{id}")
	public ResponseEntity<?> returnBookById(@PathVariable long id){
		try {
			Book book = librarryManagementService.getBookById(id);
			
			if(book != null) {
				librarryManagementService.returnBook(book);
				return new ResponseEntity<String>("Book successfully return by Patron id: " +book.getPatron().getId(), HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Sorry! No result found", HttpStatus.OK);
			}			
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while returning book", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
  
	
	/****
	 *  Librarian Views Checked Out Books: 
	 ***/
	@GetMapping("/checkedOutBookList")
	public ResponseEntity<?> checkedOutBookList(){
		try {
			List<Book> checkedOutBookList = librarryManagementService.checkedOutBookList();
			return new ResponseEntity<List<Book>>(checkedOutBookList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while fetching checkedout books", HttpStatus.INTERNAL_SERVER_ERROR);
		}  
	}
	
	/**** 
	 * Enforcing Borrowing Limits: 
	 ****/
	@GetMapping("/checkPatronAllowedBorrowingLimit/{id}")
	public ResponseEntity<?> checkPatronAllowedBorrowingLimit(@PathVariable long id){
		try {
			int borrowBookLimit = librarryManagementService.checkPatronAllowedBorrowingLimit(id);
			
			if(borrowBookLimit >= Integer.valueOf(bookIssuedLimit)) {
				return new ResponseEntity<String>("Patron has reached max number of allowed borrowing", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Patron still qualify for more books to checkout", HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while fetching checkedout books", HttpStatus.INTERNAL_SERVER_ERROR);
		}  
	}
	
	
	/**** 
	 * Handling Book Reservations: 
	 * */
	@PostMapping("/bookReservation/{id}")
	public ResponseEntity<?> bookReservation(@PathVariable long id, @RequestBody PatronDTO patronDTO){
		try {
			Book book = librarryManagementService.getBookById(id);
			
			if(book == null) {
				// throw a custom exception as book id is required for book reservation 
			}
			
			librarryManagementService.bookReservation(book, patronDTO);			
			return new ResponseEntity<Book>(book, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<String>("Error occured while creating book reservation", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
  
}
