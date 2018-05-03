package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="record_category" )
public class RecordCategory {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column( name="record_category_id" )
	private Long id;
	
	@Column( name="name", unique=true, nullable=false )
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RecordCategory [id=" + id + ", name=" + name + "]";
	}
	
	
}
