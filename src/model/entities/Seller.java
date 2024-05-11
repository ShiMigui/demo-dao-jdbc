package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double salary;

    private Department department;

    public Seller(Integer id, Department department) {
	this.id = id;
	this.department = department;
    }

    public Seller(Department department) {
	this.department = department;
    }

    public Seller(Integer id, String name, String email, Date birthDate, Double salary, Department department) {
	this.id = id;
	this.name = name;
	this.email = email;
	this.birthDate = birthDate;
	this.salary = salary;
	this.department = department;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setDepartment(Department department) {
	this.department = department;
    }

    public void setName(String name) {
	String[] fields = name.trim().replaceAll("\\s{2,}", " ").split(" ");
	name = "";

	for (String s : fields) {
	    name += s.substring(0, 1).toUpperCase();
	    name += s.substring(1).toLowerCase() + " ";
	}

	this.name = name.trim();
    }

    public void setEmail(String email) {
	this.email = email.toLowerCase();
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    public void setSalary(Double salary) {
	this.salary = salary;
    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getEmail() {
	return email;
    }

    public Date getBirthDate() {
	return birthDate;
    }

    public Double getSalary() {
	return salary;
    }

    public Department getDepartment() {
	return department;
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Seller other = (Seller) obj;
	return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
	return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate + ", salary="
		+ salary + ", department=" + department + "]";
    }

}
