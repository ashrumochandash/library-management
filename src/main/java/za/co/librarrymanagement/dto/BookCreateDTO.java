package za.co.librarrymanagement.dto;

import java.util.Date;

public class BookCreateDTO {
	private String title;
	private AuthorDto author;
	private BookCategoryEnum category;
	private Date createDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public AuthorDto getAuthor() {
		return author;
	}
	public void setAuthor(AuthorDto author) {
		this.author = author;
	}
	public BookCategoryEnum getCategory() {
		return category;
	}
	public void setCategory(BookCategoryEnum category) {
		this.category = category;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
