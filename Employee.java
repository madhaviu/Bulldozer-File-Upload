package com.fup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "designation")
  private String designation;

  @Column(name = "age")
  private int age;

  public Employee() {

  }

  

  public Employee(long id, String name, String designation, int age) {
	super();
	this.id = id;
	this.name = name;
	this.designation = designation;
	this.age = age;
}



public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}

public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public int getAge() {
	return age;
}


public void setAge(int age) {
	this.age = age;
}


@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", age=" + age + "]";
}

}
