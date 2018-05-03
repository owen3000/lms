package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue( "D" )
@PrimaryKeyJoinColumn( name="book_id" )
public class DVD extends Item {

	private String company;
	
	@ManyToOne
	@JoinColumn( name="dvd_category_id" )
	private DVDCategory category;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public DVDCategory getCategory() {
		return category;
	}

	public void setCategory(DVDCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "DVD [company=" + company + ", category=" + category + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getRented()=" + getRented() + ", getRents()=" + getRents() + "]";
	}

	
	
}
