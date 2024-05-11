package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public Department(Integer id, String name) {
	this.id = id;
	setName(name);
    }

    public Department() {
    }

    public Department setId(Integer id) {
	this.id = id;
	return this;
    }

    public Department setName(String name) {
	this.name = name.toLowerCase();
	return this;
    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
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
	Department other = (Department) obj;
	return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
	return "Department [id=" + id + ", name=" + name + "]";
    }

}
