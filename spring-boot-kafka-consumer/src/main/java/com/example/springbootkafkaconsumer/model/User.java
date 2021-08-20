/**
 * 
 */
package com.example.springbootkafkaconsumer.model;

/**
 * @author virens
 *
 */
public class User {

	private String name;
	private Long salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", salary=" + salary + "]";
	}

}
