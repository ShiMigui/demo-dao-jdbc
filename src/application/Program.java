package application;

import java.util.Date;

import db.DB;
import model.daos.FactoryDao;
import model.daos.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao SellerD = FactoryDao.sellerDao();
		Seller s = SellerD.findById(2);
		System.out.println("FindById(): " + s);
		
		
		System.out.println("\nFindByDepartment");
		SellerD.findByDepartment(s.getDepartment()).forEach(System.out::println);
		
		System.out.println("\nFindAll");
		SellerD.findAll().forEach(System.out::println);
		
		Seller insertObj = new Seller(new Department(1));
		insertObj.setName("Miguel nascimento dos santos");
		insertObj.setEmail("miguel.nasto@gmail.com");
		insertObj.setBirthDate(new Date());
		insertObj.setSalary(2500.00);
		System.out.println("\nInsert");
		SellerD.insert(insertObj);
		System.out.println(insertObj);
		
		System.out.println("\nUpdate");
		insertObj.setName("Miguel Nascimento");
		SellerD.update(insertObj);
		System.out.println("UPDATED! " + insertObj);
		DB.close();
	}

}
