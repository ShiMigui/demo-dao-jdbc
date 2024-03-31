package application;

import db.DB;
import model.daos.FactoryDao;
import model.daos.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao SellerD = FactoryDao.sellerDao();
		Seller s = SellerD.findById(2);
		System.out.println("FindById(): " + s);
		
		
		System.out.println("FindByDepartment");
		SellerD.findByDepartment(s.getDepartment()).forEach(System.out::println);
		
		System.out.println("FindAll");
		SellerD.findAll().forEach(System.out::println);
		DB.close();
	}

}
