package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue( "B" )
@PrimaryKeyJoinColumn( name="book_id" )
public class Book extends Item {

	private String isbn;
	
	@ManyToOne
	@JoinColumn( name="book_category_id" )
	private BookCategory category;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", category=" + category + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getRented()=" + getRented() + "]";
	}

	
	
}
