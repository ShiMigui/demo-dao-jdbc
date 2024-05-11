package application;

import db.DB;
import model.daos.DepartmentDao;
import model.daos.FactoryDao;

public class Program {

    public static void main(String[] args) {
	DepartmentDao dep = FactoryDao.departmentDao();

	/*
	 * Department d = new Department().setName("Music"); dep.deleteById(6);
	 * System.out.println("DELETED!");
	 * 
	 * dep.insert(d); System.out.println("INSERTED: ID=" + d.getId() + " - Name=" +
	 * d.getName());
	 * 
	 * d.setName("Radio"); dep.update(d); System.out.println("UPDATED: ID=" +
	 * d.getId() + " - Name=" + d.getName());
	 * 
	 * Department finded = dep.findById(2); System.out.println("FINDED BY ID: ID=" +
	 * finded.getId() + " - Name=" + finded.getName());
	 */

	System.out.println("FindALL()");
	dep.findAll().forEach(System.out::println);
	DB.close();
    }

}
