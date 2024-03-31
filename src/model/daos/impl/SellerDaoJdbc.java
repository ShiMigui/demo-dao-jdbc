package model.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DBException;
import model.daos.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJdbc implements SellerDao {
	private Connection conn;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public SellerDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	public void reset() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		Seller seller = null;
		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.Name as DName FROM seller s JOIN department d ON (s.DepartmentId = d.Id) WHERE s.Id = ?;");
			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instanceDepartment(rs);
				seller = instanceSeller(rs, dep);
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}
		return seller;
	}

	private Seller instanceSeller(ResultSet rs2, Department dep) throws SQLException {
		Seller seller = new Seller(rs.getInt("Id"), dep);
		seller.setEmail(rs.getString("Email"));
		seller.setName(rs.getString("Name"));
		seller.setSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		return seller;
	}

	private Department instanceDepartment(ResultSet rs2) throws SQLException {
		Department dep = new Department(rs.getInt("DepartmentId"), rs.getString("DName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
