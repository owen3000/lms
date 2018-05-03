package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance( strategy=InheritanceType.JOINED )
@DiscriminatorColumn( name="DTYPE" )
public abstract class Item {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column( name="item_id" )
	private Long id;
	private String title;
	private String rented;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRented() {
		return rented;
	}
	public void setRented(String rented) {
		this.rented = rented;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", rented=" + rented + "]";
	}
	
	
}
