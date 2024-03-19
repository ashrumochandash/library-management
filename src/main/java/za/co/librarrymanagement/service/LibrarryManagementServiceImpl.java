package za.co.librarrymanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import za.co.librarrymanagement.dto.AuthorDto;
import za.co.librarrymanagement.dto.BookCreateDTO;
import za.co.librarrymanagement.dto.BookSearchDTO;
import za.co.librarrymanagement.dto.PatronDTO;
import za.co.librarrymanagement.entity.Author;
import za.co.librarrymanagement.entity.Book;
import za.co.librarrymanagement.repository.AuthorRepository;
import za.co.librarrymanagement.repository.BookRepository;
import za.co.librarrymanagement.repository.PatronRepository;

@Service
public class LibrarryManagementServiceImpl implements LibrarryManagementService{
	
	//Repository dependency section
	@Autowired
	private BookRepository bookRepo;	
	@Autowired
	private AuthorRepository authorRepo;	
	@Autowired
	private PatronRepository patronRepo;
	
	//Configuration from application.properties
	@Value("${book.borrow.max.period}")
	private String bookMaxBorrowPeriod;

	/***
	 * This method will create Book object
	 * @param bookDto
	 * @return void
	 */
	@Override
	public void addBook(BookCreateDTO bookDto){
		try {
			Author author = populateAuthor(bookDto.getAuthor());
			Book book = new Book();
			book.setTitle(bookDto.getTitle());			
			book.setAuthor(author);
			book.setCategory(bookDto.getCategory().name());
			book.setCreateDate(new Date());
			
			bookRepo.save(book);
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private Author populateAuthor(AuthorDto authorDto) {
		
		//Search author name in Author table
		//IF Author exist return the same author ELSE create a new author as below:
		
		Author author = new Author();
		author.setAuthorName(authorDto.getAuthorName());
		return author;
	}

	/**** 
	 * This method will return book list by search criteria
	 * @param bookSearchDTO
	 * @return bookList
	 ****/
	@Override
	public List<Book> searchBook(BookSearchDTO bookSearchDTO) {	
		List<Book> bookList = null;
		try {
			String searchKey = "";			
			
			if(!StringUtils.isBlank(bookSearchDTO.getTitle())) {
				searchKey = bookSearchDTO.getTitle();
			}else if(!StringUtils.isBlank(bookSearchDTO.getCategory())) {
				searchKey = bookSearchDTO.getCategory();
			}else if(!StringUtils.isBlank(bookSearchDTO.getAuthor())){
				searchKey = bookSearchDTO.getAuthor();
			}
			
			if(!StringUtils.isEmpty(searchKey)) {
				//Further this searchKey will be used to return list of books to Controller class
			}else {
				//Return all book list by default to allow user to proceed further
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;		
	}

	/***
	 * This method will checkout book, update book status and capture Patron's information
	 * @param book, patronDTO
	 * @return book
	 */
	@Override
	public Book checkoutBook(Book book, PatronDTO patronDTO) {
		try {
			//Validate PatronDTO properties against null or empty			
			//Set issueDate into Book object as system date
			//Set returnDate into Book object as NULL
			//Search Patron with name & surname from patronDTO
			//IF Patron exist return the same ELSE convert PatronDTO into Patron entity
			//And then set this Patron entity into Book object
			//Set isBookIssued indicator as TRUE in Book entity
			
			bookRepo.save(book);
			
			//Now book status changed and patron information recorded
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return book;		
	}
	
	/***
	 * This method will find book by id
	 * @param id
	 * @return book
	 */
	@Override
	public Book getBookById(long id) {
		Book book = null;
		try {
			book = bookRepo.findById(id).orElse(null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return book;
		
	}

	/***
	 * This method will handle return book scenario by Patron.
	 * @param book
	 * @return void
	 */
	@Override
	public void returnBook(Book book) {
		try {
			book.setBookIssued(false);
			book.setReturnDate(new Date());
			
			bookRepo.save(book);			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/***
	 * This method will return checkedout book list
	 * @return checkedOutBookList
	 */
	@Override
	public List<Book> checkedOutBookList() {
		List<Book> checkedOutBookList = new ArrayList<Book>();
		try {
			List<Book> bookList = bookRepo.findAll();
			
			for(Book book : bookList) {
				if(book.isBookIssued() == true) {
					checkedOutBookList.add(book);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return checkedOutBookList;
	}

	/***
	 * This method will return a count of total number of books borrowed by Patron
	 * @param id
	 * @return count
	 */
	@Override
	public int checkPatronAllowedBorrowingLimit(long id) {
		int count = 0;
		try {
			//First find all books
			//increment count++ if Book.patron_id = id && Book.isBookIssued = true
			//return count 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

	/***
	 * This method will reserve book for Patron
	 * @param book, bookDTO
	 * @return void
	 */
	@Override
	public void bookReservation(Book book, PatronDTO patronDTO) {
		try {
			//Validate PatronDTO properties against null or empty			
			//Search Patron with name & surname from patronDTO
			//IF Patron exist return the same ELSE convert PatronDTO into Patron entity
			//And then set this Patron entity into BOOK_RESERVATION_INFO object
			//Set Book entity reference into BOOK_RESERVATION_INFO object
			//Set Reservation date as System Date
			
			bookRepo.save(book);
			
			//Now book reserved for the Patron
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/***
	 * This method will send overdue notification to Patron
	 * It will be execute via Scheduler
	 */
	@Override
	public void generateOverdueNotice() {
		try {
			List<Book> bookList =  bookRepo.findAll();
			
			//Iterate bookList
			//Calculate days difference between book issue date & current date IF isBookIssued is TRUE
			//Compare days differnce with config value - bookMaxBorrowPeriod
			//IF days diff > bookMaxBorrowPeriod, send email notification to Patron
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
