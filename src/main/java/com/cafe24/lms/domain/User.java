package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity( name="User" )
@Table( name="user" )
public class User {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column( name="user_id" )
	private Long id;
	@NotEmpty
	@Column( name="name", nullable=false )
	private String name;
	@Email
	@Column( name="email", nullable=false )
	private String email;
	@NotEmpty
	@Column( name="password", nullable=false )
	private String password;
	@Enumerated( value=EnumType.STRING )
	@Column( name="gender", columnDefinition="enum('male','female')", nullable=false )
	private Gender gender;
	@Column( name="role", nullable=false )
	private String role;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", role=" + role + "]";
	}
	
}
