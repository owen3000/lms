package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name="rent",
		uniqueConstraints= {
				@UniqueConstraint(
						columnNames= {"item_id","user_id"}
						)
				}
)
public class Rent {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column( name="rent_id" )
	private Long id;
	
	@Column( name="rent_date", nullable=false )
	@Temporal( value=TemporalType.TIMESTAMP )
	private Date rentDate;
	@Column( name="return_date", nullable=false )
	@Temporal( value=TemporalType.TIMESTAMP )
	private Date returnDate;
	
	@ManyToOne
	@JoinColumn( name="item_id", nullable=false )
	private Item item;
	@ManyToOne
	@JoinColumn( name="user_id", nullable=false )
	private User user;
	
	@OneToOne( cascade=CascadeType.ALL )
	@JoinColumn( name="reserve_id", unique=true, nullable=true )
	private Reserve reserve;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getRentDate() {
		return rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Reserve getReserve() {
		return reserve;
	}
	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}
	@Override
	public String toString() {
		return "Rent [id=" + id + ", rentDate=" + rentDate + ", returnDate=" + returnDate + ", item=" + item + ", user="
				+ user + ", reserve=" + reserve + "]";
	}

	
	
	
}
