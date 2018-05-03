package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue( "R" )
@PrimaryKeyJoinColumn( name="record_id" )
public class Record extends Item {

	@Column( name="singer", nullable=false )
	private String singer;
	
	@ManyToOne
	@JoinColumn( name="record_category_id", nullable=true )
	private RecordCategory category;

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public RecordCategory getCategory() {
		return category;
	}

	public void setCategory(RecordCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Record [singer=" + singer + ", category=" + category + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getRented()=" + getRented() + ", getRents()=" + getRents() + "]";
	}

	
}
