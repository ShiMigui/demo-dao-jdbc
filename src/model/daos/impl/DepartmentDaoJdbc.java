package model.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DBException;
import model.daos.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJdbc implements DepartmentDao {
	private Connection conn;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public DepartmentDaoJdbc(Connection conn) {
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
	public void insert(Department obj) {
		try {
			st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());

			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
			}
			else {
				throw new DBException("Unexpected error! No rows affected!");
			}
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}
	}

	@Override
	public void update(Department obj) {
		try {
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?;");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			if (st.executeUpdate() == 0) {
				throw new DBException("Unexpected error! No rows affected!");
			}
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}
	}

	@Override
	public void deleteById(Integer id) {
		try {
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?;");
			st.setInt(1, id);

			if (st.executeUpdate() == 0) {
				throw new DBException("Unexpected error! No rows affected!");
			}
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		} finally {
			reset();
		}
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
