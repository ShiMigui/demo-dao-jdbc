package application;

import db.DB;
import model.daos.DepartmentDao;
import model.daos.FactoryDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		DepartmentDao dep = FactoryDao.departmentDao();
		Department d = new Department().setName("Music");
		
		dep.insert(d);
		System.out.println("INSERTED: ID=" + d.getId() + " - Name=" + d.getName());
		
		d.setName("Radio");
		dep.update(d);
		System.out.println("UPDATED: ID=" + d.getId() + " - Name=" + d.getName());
		DB.close();
	}

}
