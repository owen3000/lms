package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="reserve" )
public class Reserve {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column( name="reserve_id" )
	private Long id;
	
	private Long turn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTurn() {
		return turn;
	}

	public void setTurn(Long turn) {
		this.turn = turn;
	}


	@Override
	public String toString() {
		return "Reserve [id=" + id + ", turn=" + turn + "]";
	}
	
	
}
