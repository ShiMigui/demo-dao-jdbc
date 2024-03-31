package application;

import db.DB;
import model.daos.FactoryDao;
import model.daos.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao SellerD = FactoryDao.sellerDao();
		Seller s = SellerD.findById(3);
		System.out.println(s);
		DB.close();
	}

}
