package model.daos;

import model.daos.impl.SellerDaoJdbc;

public class FactoryDao {
	public static SellerDao sellerDao() {
		return new SellerDaoJdbc();
	}
}
