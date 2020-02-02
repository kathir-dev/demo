package com.coding.app.demo.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.NonNull;

@Entity
@Table(name = "person")
@JsonPropertyOrder({"first_name", "last_name", "age", "favorite_color", "hobby"})
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long personId;

	@NonNull
	private String first_name;

	@NonNull
	private String last_name;

	@NonNull
	private int age;

	@Nullable
	private String favorite_color;

	protected Person() {

	}

	public Person(String first_name, String last_name, int age, String favorite_color, Set<String> hobby) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favorite_color = favorite_color;
		this.hobby = hobby;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "hobby", joinColumns = @JoinColumn(name = "personId"))
	@Column(name = "hobby")
	private Set<String> hobby;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long id) {
		this.personId = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavorite_color() {
		return favorite_color;
	}

	public void setFavorite_color(String favorite_color) {
		this.favorite_color = favorite_color;
	}

	public void setHobby(Set<String> hobby) {
		this.hobby = hobby;
	}

	public Set<String> getHobby() {
		return this.hobby;
	}
}