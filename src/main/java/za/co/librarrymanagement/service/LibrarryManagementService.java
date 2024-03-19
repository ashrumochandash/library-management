package za.co.librarrymanagement.service;

import java.util.List;

import za.co.librarrymanagement.dto.BookCreateDTO;
import za.co.librarrymanagement.dto.BookSearchDTO;
import za.co.librarrymanagement.dto.PatronDTO;
import za.co.librarrymanagement.entity.Book;

public interface LibrarryManagementService {
	
	public void addBook(BookCreateDTO bookDto);
	public List<Book> searchBook(BookSearchDTO bookSearchDTO);
	public Book getBookById(long id);
	public Book checkoutBook(Book book, PatronDTO patronDTO);
	public void returnBook(Book book);
	public List<Book> checkedOutBookList();
	public int checkPatronAllowedBorrowingLimit(long id);
	public void bookReservation(Book book, PatronDTO patronDTO);
	public void generateOverdueNotice();

}
