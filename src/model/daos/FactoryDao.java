package model.daos;

import db.DB;
import model.daos.impl.SellerDaoJdbc;

public class FactoryDao {
	public static SellerDao sellerDao() {
		return new SellerDaoJdbc(DB.connection());
	}
}
