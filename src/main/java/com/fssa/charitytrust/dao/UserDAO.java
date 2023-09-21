package com.fssa.charitytrust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.connection.ConnectionUtil;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.model.User;
import com.fssa.charitytrust.model.UserRole;

public class UserDAO {

	public static boolean addUser(User user) throws DaoException, ConnectionException {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "insert into users(username,address,contact,password,email,aadhaar,role) values (?,?,?,?,?,?,?)";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, user.getUsername());
				pst.setString(2, user.getAddress());
				pst.setString(3, user.getContactNumber());
				pst.setString(4, user.getPassword());
				pst.setString(5, user.getEmail());
				pst.setString(6, user.getAadhaarNumber());
				pst.setString(7, user.getRole().toString());

				int rows = pst.executeUpdate();

				return rows > 0;
			}
		} catch (SQLException e) {

			throw new DaoException(e.getMessage());
		}
	}

	public static boolean updateUser(User user) throws DaoException, ConnectionException {

		try (Connection con = ConnectionUtil.getConnection()) {

			int id = getIdByEmail(user.getEmail().trim());

			String query = "update users SET username = ?,address = ?,contact = ? ,aadhaar=? where id = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, user.getUsername());
				pst.setString(2, user.getAddress());
				pst.setString(3, user.getContactNumber());
				pst.setString(4, user.getAadhaarNumber());
				pst.setInt(5, id);

				int rowsAffected = pst.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {

			throw new DaoException(e.getMessage());

		}

	}

	public static boolean deleteUser(User user) throws DaoException, ConnectionException {

		try (Connection con = ConnectionUtil.getConnection()) {

			int id = getIdByEmail(user.getEmail());

			String query = "update users Set is_active = ? Where id = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setBoolean(1, false);
				pst.setInt(2, id);

				int rowsAffected = pst.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {

			throw new DaoException(e.getMessage());

		}

	}

	public static int getIdByEmail(String email) throws DaoException, ConnectionException {

		int userId = 0;
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id FROM users where email = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					userId = rs.getInt("id");
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}

		return userId;

	}

	public static List<User> getUserByEmail(String email) throws DaoException, ConnectionException {
		ArrayList<User> userArray = new ArrayList<>();
		User user = null;

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id,username,address,contact,password,email,aadhaar,role FROM users where email = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					user = new User();
					user.setId(rs.getInt("id"));
					user.setContactNumber(rs.getString("Contact"));
					user.setAadhaarNumber(rs.getNString("aadhaar"));
					user.setUsername(rs.getString("username"));
					user.setAddress(rs.getString("Address"));
					user.setEmail(rs.getString("email"));
					user.setRole(UserRole.valueOf(rs.getString("role")));
					userArray.add(user);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}

		return userArray;

	}
	public static boolean CheckMobileExists(String mobile) throws DaoException, ConnectionException {
		

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT contact FROM users where contact = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, mobile);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					return true;
					
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}

		return false;

	}

	public static boolean makeIsActive(String email) throws ConnectionException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "Update users set is_Active = ? where email = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setBoolean(1, true);
				pst.setString(2, email);
				pst.executeUpdate();

			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return true;
	}

	public static boolean checkIsActiveAccessblity(String email) throws ConnectionException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT Accessblity FROM users WHERE email = ? ";

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					int result = rs.getInt("Accessblity");
					if (result == 0) {
						return false;
					} else {
						return true;
					}

				}

			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return false;
	}

	public static boolean makeActiveAccessblity(String email,boolean val) throws ConnectionException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "Update users set Accessblity = ? where email = ?";
			try (PreparedStatement pst = con.prepareStatement(query)) {
			
				
					pst.setBoolean(1, val);
					pst.setString(2, email);
					pst.executeUpdate();
				

			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return false;
	}

	public static boolean checkMailAvailable(String email) throws ConnectionException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT email FROM users WHERE email = ? ";

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					return true;
				}

			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return false;
	}

	public static boolean checkMailAndPassword(String email, String password) throws ConnectionException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT email FROM users WHERE email = ? and password = ? ";

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, email);
				pst.setString(2, password);
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					return true;
				}

			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return false;
	}

	public static boolean checkIsActive(String email) throws ConnectionException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT is_Active FROM users WHERE email = ? ";

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					return true;

				}

			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return false;
	}

}
