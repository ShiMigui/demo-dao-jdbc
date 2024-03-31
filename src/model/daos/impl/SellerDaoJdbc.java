package model.daos.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (st != null && !st.isClosed()) {
				st.close();
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		List<Seller> sellers = new ArrayList<>();

		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.Name as DName FROM seller s JOIN department d ON (s.DepartmentId = d.Id) WHERE d.Id = ? ORDER By Name;");
			st.setInt(1, department.getId());

			rs = st.executeQuery();

			while (rs.next()) {
				if (department.getName() == "" || department.getName() == null) {
					department = instanceDepartment(rs);
				}

				sellers.add(instanceSeller(rs, department));
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}

		return sellers;
	}

	@Override
	public void insert(Seller obj) {
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getSalary());
			st.setInt(5, obj.getDepartment().getId());

			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
			} else {
				throw new DBException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}
	}

	@Override
	public void update(Seller obj) {
		try {
			st = conn.prepareStatement(
					"UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? WHERE ID = ?;");

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}

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
		List<Seller> sellers = new ArrayList<>();

		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.Name as DName FROM seller s JOIN department d ON (s.DepartmentId = d.Id) ORDER By DName;");

			rs = st.executeQuery();

			Map<Integer, Department> deps = new HashMap<>();
			Department department;
			while (rs.next()) {
				int id = rs.getInt("DepartmentId");
				department = deps.get(id);
				if (department == null) {
					deps.put(id, instanceDepartment(rs));
					department = deps.get(id);
				}

				sellers.add(instanceSeller(rs, department));
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}

		return sellers;
	}

}
